package by.training.paragliding.dao.mysql.connection;

import by.training.paragliding.dao.exception.DaoException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.locks.ReentrantLock;

public final class ConnectionPoolImpl extends AbstractConnectionPool {
    /**
     * Logger.
     */
    private final Logger logger = LogManager.getLogger("main");

    /**
     * Singleton-class instance.
     */
    private static ConnectionPoolImpl instance;

    /**
     * Lock for synchronization.
     */
    private static ReentrantLock lock = new ReentrantLock();

    private int size;

    private BlockingQueue<Connection> connections;

    private ConnectionFactory connectionFactory;

    private ExecutorService executor =
            Executors.newCachedThreadPool();

    private ConnectionValidator connectionValidator;

    private AtomicBoolean shutdownCalled = new AtomicBoolean();

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

    @Override
    public Connection get(final long timeOut,
                          final TimeUnit unit) throws DaoException {
        if (!shutdownCalled.get()) {
            Connection connection = null;
            try {
                connection = connections.poll(timeOut, unit);
                return connection;
            } catch (InterruptedException ie) {
                Thread.currentThread().interrupt();
            }
            return connection;
        }
        throw new DaoException(
                "Object pool is already shutdown");
    }

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

    @Override
    public void shutdown() {
        shutdownCalled.set(true);
        executor.shutdownNow();
        clearResources();
    }

    private void clearResources() {
        for (var connection : connections) {
            try {
                connection.close();
            } catch (SQLException newE) {
                logger.error(newE);
            }
        }
    }

    @Override
    protected void handleInvalidReturn(final Connection connection) {
        logger.warn("It is impossible to return database connection into pool. Trying to create new..");
        try {
            returnToPool(connectionFactory.createNewConnection());
        } catch (DaoException newE) {
            logger.error("Can't create new connection and return it to pool!");
        }
    }

    @Override
    protected void returnToPool(final Connection connection) {
        executor.submit(new ConnectionReturner(connections, connection));
    }

    @Override
    protected boolean isValid(final Connection newConnection) {
        return connectionValidator.isValid(newConnection);
    }

    private static class ConnectionReturner
            implements Callable<Void> {
        private BlockingQueue<Connection> queue;
        private Connection connection;

        ConnectionReturner(BlockingQueue<Connection> queue, Connection connection) {
            this.queue = queue;
            this.connection = connection;
        }

        public Void call() throws SQLException {
            while (true) {
                try {
                    connection.clearWarnings();
                    connection.setAutoCommit(true);
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
