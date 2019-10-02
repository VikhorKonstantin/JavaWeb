package by.training.paragliding.service;

import by.training.paragliding.dao.TransactionFactory;

public class ServiceFactoryImpl implements ServiceFactory {
    private final TransactionFactory transactionFactory;

    public ServiceFactoryImpl(final TransactionFactory newTransactionFactory) {
        transactionFactory = newTransactionFactory;
    }

    public SportsmanService createSportsmanService() {
        return new SportsmanService(daoFactory.getSportsmanRepository());
    }

    public UserService createUserService() {
        return new UserService(daoFactory.getUserRepository());
    }
}
