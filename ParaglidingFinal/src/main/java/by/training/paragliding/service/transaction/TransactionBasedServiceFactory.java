package by.training.paragliding.service.transaction;

import by.training.paragliding.dao.TransactionFactory;
import by.training.paragliding.dao.exception.DaoException;
import by.training.paragliding.service.ApplicationService;
import by.training.paragliding.service.CompetitionService;
import by.training.paragliding.service.ResultService;
import by.training.paragliding.service.ServiceFactory;
import by.training.paragliding.service.SportsmanService;
import by.training.paragliding.service.UserService;
import by.training.paragliding.service.exception.ServiceException;

public final class TransactionBasedServiceFactory implements ServiceFactory {
    private final TransactionFactory transactionFactory;

    public TransactionBasedServiceFactory(
            final TransactionFactory newTransactionFactory) {
        transactionFactory = newTransactionFactory;
    }

    public SportsmanService createSportsmanService() throws ServiceException {
        try {
            return new TransactionBasedSportsmanService(
                    transactionFactory.createTransaction());
        } catch (DaoException newE) {
            throw new ServiceException(newE);
        }
    }

    public UserService createUserService() throws ServiceException {
        try {
            return new TransactionBasedUserService(
                    transactionFactory.createTransaction());
        } catch (DaoException newE) {
            throw new ServiceException(newE);
        }
    }

    public CompetitionService createCompetitionService()
            throws ServiceException {
        try {
            return new TransactionBasedCompetitionService(
                    transactionFactory.createTransaction());
        } catch (DaoException newE) {
            throw new ServiceException(newE);
        }
    }

    public ApplicationService createApplicationService()
            throws ServiceException {
        try {
            return new TransactionBasedApplicationService(
                    transactionFactory.createTransaction());
        } catch (DaoException newE) {
            throw new ServiceException(newE);
        }
    }

    public ResultService createResultService()
            throws ServiceException {
        try {
            return new TransactionBasedResultService(
                    transactionFactory.createTransaction());
        } catch (DaoException newE) {
            throw new ServiceException(newE);
        }
    }

    @Override
    public void close() throws Exception {
        transactionFactory.close();
    }
}
