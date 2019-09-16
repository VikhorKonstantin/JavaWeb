package by.training.sqltask.dao.repository;

import java.sql.Connection;

abstract class BaseSqlRepository<T> implements Repository<T> {
    protected Connection connection;

    public void setConnection(final Connection newConnection) {
        connection = newConnection;
    }
}
