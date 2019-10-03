package by.training.paragliding.dao.mysql;

import by.training.paragliding.dao.Transaction;
import by.training.paragliding.dao.TransactionFactory;
import by.training.paragliding.dao.exception.DaoException;
import by.training.paragliding.dao.mysql.connection.ConnectionPoolImpl;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.concurrent.TimeUnit;

public class TransactionFactoryImpl implements TransactionFactory {
    private Connection connection;

    //todo: move somewhere
    private static final int TIME_OUT = 25;

    public TransactionFactoryImpl() throws DaoException {
        connection = ConnectionPoolImpl.getInstance()
                .get(TIME_OUT, TimeUnit.SECONDS);
        try {
            connection.setAutoCommit(false);
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    @Override
    public Transaction createTransaction() throws DaoException {
        return new TransactionImpl(connection);
    }

    @Override
    public void close() {
        try {
            connection.close();
        } catch (SQLException e) {
            //todo: logger
        }
    }
}
