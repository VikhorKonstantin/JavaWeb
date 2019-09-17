package by.training.sqltask.dao.repository;

import java.sql.Connection;

abstract class BaseSqlRepository<T> implements Repository<T> {
    /**
     * SQL connection.
     */
    Connection connection;

    /**
     * Sets SQL connection into newConnection.
     *
     * @param newConnection new connection
     */
    public void setConnection(final Connection newConnection) {
        connection = newConnection;
    }
}
