package by.training.paragliding.service;

import by.training.paragliding.service.exception.ServiceException;

public interface ServiceFactory extends AutoCloseable {
    UserService createUserService() throws ServiceException;
    SportsmanService createSportsmanService() throws ServiceException;
    CompetitionService createCompetitionService() throws ServiceException;
    ApplicationService createApplicationService() throws ServiceException;
    ResultService createResultService() throws ServiceException;
}
