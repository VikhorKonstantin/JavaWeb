package by.training.paragliding.dao.mysql.connection;

import by.training.paragliding.dao.exception.DaoException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Blocking implementation of connection pool.
 */
public final class ConnectionPoolImpl extends AbstractConnectionPool {
    /**
     * Logger.
     */
    private final Logger logger = LogManager.getLogger("ConnectionPoolImpl");

    /**
     * Singleton-class instance.
     */
    private static ConnectionPoolImpl instance;

    /**
     * Lock for synchronization.
     */
    private static ReentrantLock lock = new ReentrantLock();

    /**
     * Pool size.
     */
    private int size;

    /**
     * Pooled connections.
     */
    private BlockingQueue<Connection> connections;

    /**
     * Connection factory. Used for creating new connections.
     */
    private ConnectionFactory connectionFactory;

    /**
     * Executor. Used for executing asynchronous tasks.
     */
    private ExecutorService executor =
            Executors.newCachedThreadPool();

    /**
     * ConnectionValidator. Used for connection validating.
     */
    private ConnectionValidator connectionValidator;

    /**
     * Represents if shutdown method was called.
     */
    private AtomicBoolean shutdownCalled = new AtomicBoolean();

    /**
     * Initialize pool instance.
     *
     * @param newSize                pool size.
     * @param newConnectionFactory   connection factory.
     * @param newConnectionValidator connection validator.
     * @throws DaoException if exception was thrown while pool filling
     */
    public void initialize(final int newSize,
                           final ConnectionFactory newConnectionFactory,
                           final ConnectionValidator newConnectionValidator)
            throws DaoException {
        size = newSize;
        connections = new LinkedBlockingQueue<>(size);
        connectionFactory = newConnectionFactory;
        connectionValidator = newConnectionValidator;
        fillConnections();
        shutdownCalled.set(false);
    }

    private void fillConnections() throws DaoException {
        for (int i = 0; i < size; i++) {
            connections.add(connectionFactory.createNewConnection());
        }
    }

    /**
     * Returns instance of controller.
     *
     * @return instance
     */
    public static ConnectionPoolImpl getInstance() {
        lock.lock();
        try {
            if (instance == null) {
                instance = new ConnectionPoolImpl();
            }
        } finally {
            lock.unlock();
        }
        return instance;
    }

    /**
     * Returns connection instance from the pool
     * or null if the specified waiting time elapses before
     * an element is available. (Used in blocking impls)
     *
     * @param timeOut max amount of time to wait before giving up,
     *                in units of <tt>unit</tt>
     * @param unit    a <tt>TimeUnit</tt> determining
     *                how to interpret the
     *                <tt>timeout</tt> parameter
     * @return connection instance from the pool
     * @throws DaoException if something goes
     *                      wrong while trying to get a connection
     *                      (ex. interrupted while waiting)
     */
    @Override
    public Connection get(final long timeOut,
                          final TimeUnit unit) throws DaoException {
        if (!shutdownCalled.get()) {
            Connection connection = null;
            try {
                connection = connections.poll(timeOut, unit);
                logger.debug("Get connection {}", connection);
                return connection;
            } catch (InterruptedException ie) {
                Thread.currentThread().interrupt();
            }
            return connection;
        }
        throw new DaoException(
                "Object pool is already shutdown");
    }

    /**
     * Returns connection instance from the pool.
     * Waiting if necessary until an element becomes available.
     *
     * @return connection instance from the pool
     * @throws DaoException if something goes
     *                      wrong while trying to get a connection
     *                      (ex. interrupted while waiting)
     */
    @Override
    public Connection get() throws DaoException {
        if (!shutdownCalled.get()) {
            Connection connection = null;
            try {
                connection = connections.take();
                return connection;
            } catch (InterruptedException ie) {
                Thread.currentThread().interrupt();
            }
            return connection;
        }
        throw new DaoException(
                "Object pool is already shutdown");
    }

    /**
     * Releases all connection from pool.
     */
    @Override
    public void shutdown() {
        shutdownCalled.set(true);
        executor.shutdownNow();
        clearResources();
    }

    private void clearResources() {
        for (var connection : connections) {
            try {
                connection.clearWarnings();
                connection.setAutoCommit(true);
                connection.close();
            } catch (SQLException newE) {
                logger.error(newE);
            }
        }
        logger.debug("Resources cleared!!");
    }

    /**
     * Invoked when connection release was failed.
     * Tries to create new connection and returns it to pool.
     *
     * @param connection connection which user tried to return.
     */
    @Override
    protected void handleInvalidReturn(final Connection connection) {
        logger.error("It is impossible"
                + " to return database connection {} into pool."
                + " Trying to create new..", connection);
        try {
            returnToPool(connectionFactory.createNewConnection());
        } catch (DaoException newE) {
            logger.error("Can't create new connection and return it to pool!");
        }
    }

    /**
     * Creates an asynchronous task of inserting the object into the pool
     * and submit it to an Executor instance so that
     * the client thread can return immediately.
     *
     * @param connection to return.
     */
    @Override
    protected void returnToPool(final Connection connection) {
        logger.debug("Connection {} will be returned to pool", connection);
        executor.submit(new ConnectionReturner(connections, connection));
    }

    /**
     * Checks if connection valid.
     *
     * @param newConnection connection to check
     * @return if connection valid.
     */
    @Override
    protected boolean isValid(final Connection newConnection) {
        return connectionValidator.isValid(newConnection);
    }

    /**
     * Task of inserting the object into the pool.
     */
    private static class ConnectionReturner
            implements Callable<Void> {
        private BlockingQueue<Connection> queue;
        private Connection connection;

        ConnectionReturner(final BlockingQueue<Connection> newQueue,
                           final Connection newConnection) {
            this.queue = newQueue;
            this.connection = newConnection;
        }

        public Void call() throws SQLException {
            while (true) {
                try {
                    queue.put(connection);
                    break;
                } catch (InterruptedException ie) {
                    Thread.currentThread().interrupt();
                }
            }
            return null;
        }
    }
}
