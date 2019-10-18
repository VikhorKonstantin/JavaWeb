package by.training.paragliding.dao.mysql.connection;

import by.training.paragliding.dao.exception.DaoException;

import java.sql.Connection;
import java.util.concurrent.TimeUnit;

public interface ConnectionPool {

    /**
     * Returns connection instance from the pool. (Used in blocking impls)
     *
     * @param timeOut max amount of time to wait before giving up,
     *                in units of <tt>unit</tt>
     * @param unit    a <tt>TimeUnit</tt> determining
     *                how to interpret the
     *                <tt>timeout</tt> parameter
     * @return connection instance from the pool
     * @throws DaoException if something goes
     *                      wrong while trying to get
     *                      a connection (ex. interrupted while waiting)
     */
    Connection get(long timeOut, TimeUnit unit) throws DaoException;

    /**
     * Returns connection instance from the pool.
     *
     * @return connection instance from the pool
     * @throws DaoException if something goes
     * wrong while trying to get a connection
     */
    Connection get() throws DaoException;
    /**
     * Release connection(returns it back to the pool).
     *
     * @param connection connection to return.
     */
    void release(Connection connection);

    /**
     * Releases all connection from pool.
     */
    void shutdown();
}
