package by.training.paragliding.service;

import by.training.paragliding.dao.TransactionFactory;
import by.training.paragliding.dao.exception.DaoException;
import by.training.paragliding.service.exception.ServiceException;

public class ServiceFactoryImpl implements ServiceFactory {
    private final TransactionFactory transactionFactory;

    public ServiceFactoryImpl(final TransactionFactory newTransactionFactory) {
        transactionFactory = newTransactionFactory;
    }

    public SportsmanService createSportsmanService() throws ServiceException {
        try {
            return new SportsmanService(transactionFactory.createTransaction());
        } catch (DaoException newE) {
            throw new ServiceException(newE);
        }
    }

    public UserService createUserService() throws ServiceException {
        try {
            return new UserService(transactionFactory.createTransaction());
        } catch (DaoException newE) {
            throw new ServiceException(newE);
        }
    }
}
