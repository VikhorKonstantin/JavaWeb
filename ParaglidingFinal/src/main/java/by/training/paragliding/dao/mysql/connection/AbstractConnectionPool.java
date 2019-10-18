package by.training.paragliding.dao.mysql.connection;

import java.sql.Connection;

public abstract class AbstractConnectionPool implements ConnectionPool {

    /**
     * Release connection(returns it back to the pool.).
     *
     * @param connection connection to return.
     */
    @Override
    public void release(final Connection connection) {
        if (isValid(connection)) {
            returnToPool(connection);
        } else {
            handleInvalidReturn(connection);
        }
    }

    protected abstract void handleInvalidReturn(Connection connection);

    protected abstract void returnToPool(Connection connection);

    protected abstract boolean isValid(Connection t);
}
