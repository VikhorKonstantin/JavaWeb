package by.training.paragliding.dao.mysql;

import by.training.paragliding.dao.Repository;

import java.sql.Connection;

public abstract class BaseSqlRepository<T> implements Repository<T> {
    /**
     * SQL connection.
     */
    protected Connection connection;

    public BaseSqlRepository(final Connection newConnection) {
        connection = newConnection;
    }
}
