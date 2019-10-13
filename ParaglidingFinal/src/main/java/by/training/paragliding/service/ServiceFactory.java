package by.training.paragliding.service;

import by.training.paragliding.service.exception.ServiceException;

public interface ServiceFactory extends AutoCloseable {
    //todo: гдето-то всет-таки придется перейти от интерфейса к реализации!
    UserService createUserService() throws ServiceException;
    SportsmanService createSportsmanService() throws ServiceException;
    CompetitionService createCompetitionService() throws ServiceException;
    ApplicationService createApplicationService() throws ServiceException;
}
