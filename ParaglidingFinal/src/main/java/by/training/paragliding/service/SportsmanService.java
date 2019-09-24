package by.training.paragliding.service;

import by.training.paragliding.bean.entity.Competition;
import by.training.paragliding.bean.entity.Sportsman;
import by.training.paragliding.dao.exception.DaoException;
import by.training.paragliding.dao.sql.Specification;
import by.training.paragliding.dao.sql.sportsmen.*;
import by.training.paragliding.service.exception.ServiceException;
import com.neovisionaries.i18n.CountryCode;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.List;

public class SportsmanService {
    /**
     * Logger.
     */
    private Logger logger = LogManager.getLogger("main");
    /*
    todo: replace
     */
    private static String DB_URL =
            "jdbc:mysql://localhost:3306/paragliding_db?serverTimezone=UTC"
                    + "&useSSL=false&allowPublicKeyRetrieval=true";
    private SportsmenRepository repository = new SportsmenRepository();

    private static final Map<String, ThrowingFunction<Object, Specification,
            ServiceException>> SPECIFICATION_PROVIDER =
            new HashMap<>();
    /*
    todo; replace
    */
    public static void setDbUrl(final String newDbUrl) {
        DB_URL = newDbUrl;
    }

    static {
        /*
        todo:Replace with a connection pull
         */
        final String jdbcDriver = "com.mysql.cj.jdbc.Driver";
        try {
            Class.forName(jdbcDriver);
        } catch (ClassNotFoundException newE) {
            newE.printStackTrace();
        }
        ///
        SPECIFICATION_PROVIDER.put("countryCode",
                SportsmanService::findByCountry);
        SPECIFICATION_PROVIDER.put("all", SportsmanService::findAll);
        SPECIFICATION_PROVIDER.put("APPLICATION",
                SportsmanService::findByApplication);
        SPECIFICATION_PROVIDER.put("RATING_RANGE",
                SportsmanService::findByRatingRange);
    }

    /**
     * Returns Sportsman with the following id if exist, return null if not.
     *
     * @param civlId Sportsman id
     * @return Sportsman with the following id
     * @throws ServiceException if something goes wrong.
     */
    public Sportsman readById(final int civlId) throws ServiceException {
        try (Connection connection = DriverManager
                .getConnection(DB_URL, "paragliding_app",
                        "password");) {

            logger.debug(connection);
            repository.setConnection(connection);
            return repository.readById(civlId);
        } catch (DaoException | SQLException e) {
            throw new ServiceException(e);
        }
    }

    @SafeVarargs
    public final  <T> List<Sportsman> find(String property, T... value) throws ServiceException {
        try (Connection connection = DriverManager
                .getConnection(DB_URL, "paragliding_app",
                        "password");) {
            logger.debug(connection);
            repository.setConnection(connection);
            var specification = SPECIFICATION_PROVIDER
                    .get(property).apply(value);
            return repository.query(specification);
        } catch (DaoException | SQLException e) {
            throw new ServiceException(e);
        }
    }

    /**
     * @param newCountryCode countryCode to find by.
     * @return FindByCountrySpecification
     * @throws ServiceException if method args wrong
     */
    private static Specification findByCountry(final Object... newCountryCode)
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
    private static Specification findAll(final Object... params)
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
    private static Specification findByRatingRange(final Object... range)
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
    private static Specification findByApplication(final Object... application)
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
