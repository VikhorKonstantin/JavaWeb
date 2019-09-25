package by.training.paragliding.service;

import by.training.paragliding.bean.entity.Competition;
import by.training.paragliding.bean.entity.Sportsman;
import by.training.paragliding.dao.Repository;
import by.training.paragliding.dao.exception.DaoException;
import by.training.paragliding.dao.mysql.Specification;
import by.training.paragliding.dao.mysql.sportsmen.*;
import by.training.paragliding.service.exception.ServiceException;
import com.neovisionaries.i18n.CountryCode;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SportsmanService {
    /**
     * Logger.
     */
    private Logger logger = LogManager.getLogger("main");
    /**
     * Sportsman DAO.
     */
    private Repository<Sportsman> repository;

    private static final Map<String, ThrowingFunction<Object[], Specification,
            ServiceException>> SPECIFICATION_PROVIDER =
            new HashMap<>();

    static {
        SPECIFICATION_PROVIDER.put("countryCode",
                SportsmanService::findByCountry);
        SPECIFICATION_PROVIDER.put("all", SportsmanService::findAll);
        SPECIFICATION_PROVIDER.put("application",
                SportsmanService::findByApplication);
        SPECIFICATION_PROVIDER.put("ratingRange",
                SportsmanService::findByRatingRange);
    }

    public SportsmanService(final Repository<Sportsman> newRepository) {
        repository = newRepository;
    }

    /**
     * Returns Sportsman with the following id if exist, return null if not.
     *
     * @param civlId Sportsman id
     * @return Sportsman with the following id
     * @throws ServiceException if something goes wrong.
     */
    public Sportsman readById(final int civlId) throws ServiceException {
        try {
            return repository.readById(civlId);
        } catch (DaoException e ) {
            throw new ServiceException(e);
        }
    }

    @SafeVarargs
    public final  <T> List<Sportsman> find(String property, T... value) throws ServiceException {
        try {
            var specification = SPECIFICATION_PROVIDER
                    .get(property).apply(value);
            return repository.query(specification);
        } catch (DaoException e) {
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



    /**
     * @param <T> param type.
     * @param <R> return type.
     * @param <E> exception type.
     */
    @FunctionalInterface
    private interface ThrowingFunction<T, R, E extends Exception> {
        R apply(T t) throws E;
    }


}
