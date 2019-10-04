package by.training.paragliding.dao;

import by.training.paragliding.dao.exception.DaoException;

public interface TransactionFactory {
    Transaction createTransaction() throws DaoException;
    void close();
}