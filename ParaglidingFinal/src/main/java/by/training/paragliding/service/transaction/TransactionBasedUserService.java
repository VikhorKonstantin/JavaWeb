package by.training.paragliding.service.transaction;

import by.training.paragliding.bean.entity.Sportsman;
import by.training.paragliding.bean.entity.User;
import by.training.paragliding.dao.DaoType;
import by.training.paragliding.dao.Repository;
import by.training.paragliding.dao.Transaction;
import by.training.paragliding.dao.exception.DaoException;
import by.training.paragliding.dao.mysql.Specification;
import by.training.paragliding.dao.mysql.user.FindAllUsersSpecification;
import by.training.paragliding.dao.mysql.user.FindByLogin;
import by.training.paragliding.service.Service;
import by.training.paragliding.service.UserService;
import by.training.paragliding.service.exception.ServiceException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

class TransactionBasedUserService extends AbstractTransactionBasedService
        implements UserService {

    /**
     * Logger.
     */
    private Logger logger = LogManager.getLogger("main");


    TransactionBasedUserService(final Transaction newTransaction) {
        super(newTransaction);
    }

    private static final Map<FindByProps, Service.ThrowingFunction<Object[], Specification,
                ServiceException>> SPECIFICATION_PROVIDER =
            new HashMap<>();
    static {
        SPECIFICATION_PROVIDER.put(FindByProps.LOGIN,
                TransactionBasedUserService::findByLogin);
        SPECIFICATION_PROVIDER.put(FindByProps.ALL, TransactionBasedUserService::findAll);
    }


    @Override
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

    @Override
    public User readByLoginAndPassword(final String login, final String password)
            throws ServiceException {
        try {
            var userList = find(FindByProps.LOGIN, login);
            if(!userList.isEmpty()) {
                var user = userList.get(0);
                var encoded = user.getPassword();
                logger.debug("Encoded {}", encoded);
                var hasher = Argon2Hasher.getInstance();
                if (hasher.verifyPassword(encoded, password)) {
                    transaction.commit();
                    return user;
                }
            }
            return null;
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
    public List<User> find(final FindByProps property, final Object... value)
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
    @Override
    public boolean addUser(final User newUser) throws ServiceException {
        try {
            Repository<User> userRepository =
                    transaction.createDao(DaoType.USER);
            Repository<Sportsman> sportsmanRepository =
                    transaction.createDao(DaoType.SPORTSMAN);
            var hasher = Argon2Hasher.getInstance();
            newUser.setPassword(hasher.hashPassword(newUser.getPassword()));
            var result = userRepository.add(newUser);
            var oSportsman = Optional.ofNullable(newUser.getSportsman());
            if (oSportsman.isPresent()) {
                result &= sportsmanRepository.add(oSportsman.get());
            }
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

    private static Specification findByLogin(
            final Object[] newObjects) throws ServiceException {
       try{
           return new FindByLogin((String) newObjects[0]);
       } catch (ClassCastException | IndexOutOfBoundsException newE) {
           throw new ServiceException(newE);
       }
    }
}
