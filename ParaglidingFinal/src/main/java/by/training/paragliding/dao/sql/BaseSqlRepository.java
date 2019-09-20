package by.training.paragliding.dao.sql;

import by.training.paragliding.dao.Repository;

import java.sql.Connection;

public abstract class BaseSqlRepository<T> implements Repository<T> {
    /**
     * SQL connection.
     */
    protected Connection connection;

    /**
     * Sets SQL connection into newConnection.
     *
     * @param newConnection new connection
     */
    public void setConnection(final Connection newConnection) {
        connection = newConnection;
    }
}
