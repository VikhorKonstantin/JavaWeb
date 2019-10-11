package by.training.paragliding.service;

import by.training.paragliding.bean.entity.User;
import by.training.paragliding.dao.DaoType;
import by.training.paragliding.dao.Repository;
import by.training.paragliding.dao.Transaction;
import by.training.paragliding.dao.exception.DaoException;
import by.training.paragliding.dao.mysql.Specification;
import by.training.paragliding.dao.mysql.user.FindAllUsersSpecification;
import by.training.paragliding.dao.mysql.user.FindByLoginAndPasswordSpecification;
import by.training.paragliding.service.exception.ServiceException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UserService implements Service<User> {

    /**
     * Logger.
     */
    private Logger logger = LogManager.getLogger("main");
    /**
     * Transaction.
     */
    private Transaction transaction;

    private static final String ROLL_BACK_EXC_MSG = "Rollback failed";

    UserService(final Transaction newTransaction) {
        transaction = newTransaction;
    }

    private static final Map<String, ThrowingFunction<Object[], Specification,
            ServiceException>> SPECIFICATION_PROVIDER =
            new HashMap<>();

    static {
        SPECIFICATION_PROVIDER.put("loginAndPassword",
                UserService::findByLoginAndPassword);
        SPECIFICATION_PROVIDER.put("all", UserService::findAll);
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
                logger.error(ROLL_BACK_EXC_MSG, rbExc);
            }
            throw new ServiceException(e);
        }
    }

    public User readByEmailAndPassword(final String email,
                                       final String password)
            throws ServiceException {
        try {
            var userList = find("loginAndPassword", email, password);
            transaction.commit();
            if (!userList.isEmpty()) {
                return userList.get(0);
            } else {
                return null;
            }
        } catch (DaoException e) {
            try {
                transaction.rollback();
            } catch (DaoException rbExc) {
                logger.error(ROLL_BACK_EXC_MSG, rbExc);
            }
            throw new ServiceException(e);
        }
    }

    @Override
    public List<User> find(final String property, final Object... value)
            throws ServiceException {
        try {
            var specification = SPECIFICATION_PROVIDER
                    .get(property).apply(value);
            Repository<User> competitionDao =
                    transaction.createDao(DaoType.USER);
            var result = competitionDao.query(specification);
            transaction.commit();
            return result;
        } catch (DaoException e) {
            try {
                transaction.rollback();
            } catch (DaoException rbExc) {
                logger.error(ROLL_BACK_EXC_MSG, rbExc);
            }
            throw new ServiceException(e);
        }
    }

    public boolean addUser(final User newUser) throws ServiceException {
        try {
            Repository<User> userRepository =
                    transaction.createDao(DaoType.USER);
            var result = userRepository.add(newUser);
            transaction.commit();
            return result;
        } catch (DaoException e) {
            try {
                transaction.rollback();
            } catch (DaoException rbExc) {
                logger.error(ROLL_BACK_EXC_MSG, rbExc);
            }
            throw new ServiceException(e);
        }
    }


    private static Specification findAll(final Object[] newObjects) {
        return new FindAllUsersSpecification();
    }

    private static Specification findByLoginAndPassword(
            final Object[] newObjects) throws ServiceException {
       try{
           return new FindByLoginAndPasswordSpecification((String) newObjects[0],
                   (String) newObjects[1]);
       } catch (ClassCastException | IndexOutOfBoundsException newE) {
           throw new ServiceException(newE);
       }
    }
}
