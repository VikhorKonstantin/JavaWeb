package by.training.paragliding.dao.mysql.connection;

import by.training.paragliding.dao.exception.DaoException;

import java.sql.Connection;
import java.util.concurrent.TimeUnit;

public interface ConnectionPool {

    Connection get(long timeOut, TimeUnit unit) throws DaoException;

    Connection get() throws DaoException;

    void release(Connection connection);

    void shutdown();
}
