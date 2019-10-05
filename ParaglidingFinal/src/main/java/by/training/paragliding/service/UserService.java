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

    UserService(final Transaction newTransaction) {
        transaction = newTransaction;
    }

    public User readById(final int id) throws ServiceException {
        try {
            Repository<User> userRepository =
                    transaction.createDao(DaoType.USER);
            var result = userRepository.readById(id);
            transaction.commit();
            return result;
        } catch (DaoException e) {
            try {
                transaction.rollback();
            } catch (DaoException rbExc) {
                logger.error("Rollback failed", rbExc);
            }
            throw new ServiceException(e);
        }
    }

    public List<User> readAll() throws ServiceException {
        try {
            Repository<User> userRepository =
                    transaction.createDao(DaoType.USER);
            var result = userRepository.query(new FindAllUsersSpecification());
            transaction.commit();
            return result;
        } catch (DaoException e) {
            try {
                transaction.rollback();
            } catch (DaoException rbExc) {
                logger.error("Rollback failed", rbExc);
            }
            throw new ServiceException(e);
        }
    }
}
