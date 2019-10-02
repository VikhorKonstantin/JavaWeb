package by.training.paragliding.service;

public class ServiceFactoryImpl implements ServiceFactory {
    private final DaoFactory daoFactory;

    public ServiceFactoryImpl(final DaoFactory newDaoFactory) {
        daoFactory = newDaoFactory;
    }

    public SportsmanService createSportsmanService() {
        return new SportsmanService(daoFactory.getSportsmanRepository());
    }

    public UserService createUserService() {
        return new UserService(daoFactory.getUserRepository());
    }
}
