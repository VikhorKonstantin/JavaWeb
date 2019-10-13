package by.training.paragliding.service;

import by.training.paragliding.dao.TransactionFactory;
import by.training.paragliding.dao.exception.DaoException;
import by.training.paragliding.service.exception.ServiceException;

public final class TransactionBasedServiceFactory implements ServiceFactory {
    private final TransactionFactory transactionFactory;

    public TransactionBasedServiceFactory(final TransactionFactory newTransactionFactory) {
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

    public CompetitionService createCompetitionService()
            throws ServiceException {
        try {
            return new CompetitionService(transactionFactory.createTransaction());
        } catch (DaoException newE) {
            throw new ServiceException(newE);
        }
    }
    public ApplicationService createApplicationService()
            throws ServiceException {
        try {
            return new ApplicationService(transactionFactory.createTransaction());
        } catch (DaoException newE) {
            throw new ServiceException(newE);
        }
    }

    @Override
    public void close() throws Exception {
        transactionFactory.close();
    }
}
