package by.training.paragliding.service;

import by.training.paragliding.bean.entity.User;
import by.training.paragliding.service.exception.ServiceException;

public interface UserService extends Service<User, UserService.FindByProps> {
    User readById(final int id) throws ServiceException;
    boolean addUser(final User newUser) throws ServiceException;
    enum FindByProps {
        ALL,
        LOGIN_AND_PASSWORD
    }
}
