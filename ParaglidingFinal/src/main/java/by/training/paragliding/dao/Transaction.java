package by.training.paragliding.dao;

import by.training.paragliding.dao.exception.DaoException;

public interface Transaction {
    <T> Repository<T> createDao(DaoType type)
            throws DaoException;

    void commit() throws DaoException;

    void rollback() throws DaoException;
}
