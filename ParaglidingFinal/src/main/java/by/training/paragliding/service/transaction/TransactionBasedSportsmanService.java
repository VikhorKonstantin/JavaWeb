package by.training.paragliding.service.transaction;

import by.training.paragliding.bean.entity.Competition;
import by.training.paragliding.bean.entity.Sportsman;
import by.training.paragliding.dao.DaoType;
import by.training.paragliding.dao.Repository;
import by.training.paragliding.dao.Transaction;
import by.training.paragliding.dao.exception.DaoException;
import by.training.paragliding.dao.mysql.Specification;
import by.training.paragliding.dao.mysql.sportsmen.FindAllSportsmenSpecification;
import by.training.paragliding.dao.mysql.sportsmen.FindByCountrySpecification;
import by.training.paragliding.dao.mysql.sportsmen.FindSportsmenByApplication;
import by.training.paragliding.dao.mysql.sportsmen.FindSportsmenByRatingRange;
import by.training.paragliding.service.Service;
import by.training.paragliding.service.SportsmanService;
import by.training.paragliding.service.exception.ServiceException;
import com.neovisionaries.i18n.CountryCode;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

class TransactionBasedSportsmanService
        extends AbstractTransactionBasedService implements SportsmanService {
    /**
     * Logger.
     */
    private Logger logger = LogManager.getLogger("main");


    private static final Map<Integer, Service.ThrowingFunction<Object[], Specification,
            ServiceException>> SPECIFICATION_PROVIDER =
            new HashMap<>();

    static {
        SPECIFICATION_PROVIDER.put(COUNTRY_CODE,
                TransactionBasedSportsmanService::findByCountry);
        SPECIFICATION_PROVIDER.put(ALL, TransactionBasedSportsmanService::findAll);
        SPECIFICATION_PROVIDER.put(APPLICATION,
                TransactionBasedSportsmanService::findByApplication);
        SPECIFICATION_PROVIDER.put(RATING_RANGE,
                TransactionBasedSportsmanService::findByRatingRange);
    }

    TransactionBasedSportsmanService(final Transaction newTransaction) {
        super(newTransaction);
    }

    /**
     * Returns Sportsman with the following id if exist, throws ServiceException if not.
     *
     * @param civlId Sportsman id
     * @return Sportsman with the following id
     * @throws ServiceException if something goes wrong.
     */
    public Sportsman readById(final int civlId) throws ServiceException {
        try {
            Repository<Sportsman> sportsmanDao =
                    transaction.createDao(DaoType.SPORTSMAN);
            var result = sportsmanDao.readById(civlId);
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
    public final List<Sportsman> find(final Integer property, Object... value)
            throws ServiceException {
        try {
            var specification = SPECIFICATION_PROVIDER
                    .get(property).apply(value);
            Repository<Sportsman> sportsmanDao =
                    transaction.createDao(DaoType.SPORTSMAN);
            var result = sportsmanDao.query(specification);
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

    /**
     * @param newCountryCode countryCode to find by.
     * @return FindByCountrySpecification
     * @throws ServiceException if method args wrong
     */
    private static Specification findByCountry(final Object[] newCountryCode)
            throws ServiceException {
        try {
            return new FindByCountrySpecification(
                    (CountryCode) newCountryCode[0]);
        } catch (ClassCastException e) {
            throw new ServiceException(e);
        }
    }

    /**
     * @param params Find All Specification has no params.
     * @return FindAllSportsmenSpecification
     * @throws ServiceException if method args wrong
     */
    private static Specification findAll(final Object[] params)
            throws ServiceException {
        try {
            return new FindAllSportsmenSpecification();
        } catch (ClassCastException e) {
            throw new ServiceException(e);
        }
    }

    /**
     * @param range Find All Specification has no params.
     * @return FindSportsmenByRatingRange
     * @throws ServiceException if method args wrong
     */
    private static Specification findByRatingRange(final Object[] range)
            throws ServiceException {
        try {
            return new FindSportsmenByRatingRange((Float) range[0],
                    (Float) range[1]);
        } catch (ClassCastException e) {
            throw new ServiceException(e);
        }
    }

    /**
     * @param application Find All Specification has no params.
     * @return FindSportsmenByApplication
     * @throws ServiceException if method args wrong
     */
    private static Specification findByApplication(final Object[] application)
            throws ServiceException {
        try {
            return new FindSportsmenByApplication((Competition) application[0]);
        } catch (ClassCastException e) {
            throw new ServiceException(e);
        }
    }
}
