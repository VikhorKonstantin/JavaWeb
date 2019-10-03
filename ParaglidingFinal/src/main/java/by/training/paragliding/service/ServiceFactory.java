package by.training.paragliding.service;

import by.training.paragliding.service.exception.ServiceException;

public interface ServiceFactory {

    UserService createUserService() throws ServiceException;

    SportsmanService createSportsmanService() throws ServiceException;
}
