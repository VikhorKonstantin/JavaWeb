package by.training.paragliding.service;

import by.training.paragliding.bean.entity.User;
import by.training.paragliding.dao.DaoType;
import by.training.paragliding.dao.Repository;
import by.training.paragliding.dao.Transaction;
import by.training.paragliding.dao.exception.DaoException;
import by.training.paragliding.dao.mysql.user.FindAllUsersSpecification;
import by.training.paragliding.service.exception.ServiceException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

public class UserService {
    /**
     * Logger.
     */
    private Logger logger = LogManager.getLogger("main");
    /**
     * Transaction.
     */
    private Transaction transaction;

    public UserService(final Transaction newTransaction) {
        transaction = newTransaction;
    }

    public User readById(final int id) throws ServiceException {
        try {
            Repository<User> userRepository =
                    transaction.createDao(DaoType.USER);
            return userRepository.readById(id);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    public List<User> readAll() throws ServiceException {
        try {
            Repository<User> userRepository =
                    transaction.createDao(DaoType.USER);
            return userRepository.query(new FindAllUsersSpecification());
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }
}
