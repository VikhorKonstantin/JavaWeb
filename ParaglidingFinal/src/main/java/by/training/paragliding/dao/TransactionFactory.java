package by.training.paragliding.dao;

import by.training.paragliding.dao.exception.DaoException;

public interface TransactionFactory extends AutoCloseable {
    Transaction createTransaction() throws DaoException;
    @Override
    void close();
}
