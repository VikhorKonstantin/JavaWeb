package by.training.paragliding.dao.mysql.connection;

import by.training.paragliding.dao.exception.DaoException;

import java.sql.Connection;

public interface ConnectionFactory {

    /**
     * Creates new connection.
     *
     * @return new connection
     * @throws DaoException if it's impossible to get new connection.
     */
    Connection createNewConnection() throws DaoException;
}
