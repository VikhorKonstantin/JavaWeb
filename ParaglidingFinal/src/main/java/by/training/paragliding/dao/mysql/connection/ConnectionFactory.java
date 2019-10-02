package by.training.paragliding.dao.mysql.connection;

import by.training.paragliding.dao.exception.DaoException;

import java.sql.Connection;

public interface ConnectionFactory {

    Connection createNewConnection() throws DaoException;
}
