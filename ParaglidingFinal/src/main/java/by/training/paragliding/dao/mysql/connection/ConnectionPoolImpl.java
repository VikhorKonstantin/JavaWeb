package by.training.paragliding.dao.mysql.connection;

import by.training.paragliding.dao.exception.DaoException;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.locks.ReentrantLock;

public class ConnectionPoolImpl implements ConnectionPool {

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

    private AtomicBoolean shutdownCalled;

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
    public void releaseConnection(final Connection connection) {
        if (connectionValidator.isValid(connection)) {
            executor.submit(new ConnectionReturner(connections, connection));
        }
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
                //todo:logger
            }
        }
    }


    private static class ConnectionReturner
            implements Callable<Void> {
        private BlockingQueue<Connection> queue;
        private Connection e;

        ConnectionReturner(BlockingQueue<Connection> queue, Connection e) {
            this.queue = queue;
            this.e = e;
        }

        public Void call() {
            while (true) {
                try {
                    queue.put(e);
                    break;
                } catch (InterruptedException ie) {
                    Thread.currentThread().interrupt();
                }
            }
            return null;
        }
    }
}
