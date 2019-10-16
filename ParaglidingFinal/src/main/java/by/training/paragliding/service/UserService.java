package by.training.paragliding.service;

import by.training.paragliding.bean.entity.User;
import by.training.paragliding.service.exception.ServiceException;

public interface UserService extends Service<User> {
    User readById(final int id) throws ServiceException;
    boolean addUser(final User newUser) throws ServiceException;
    Integer LOGIN_AND_PASSWORD = 1;
    Integer ALL = 0;
}
