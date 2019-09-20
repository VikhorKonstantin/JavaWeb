package by.training.paragliding.service;

import by.training.paragliding.bean.entity.Sportsman;
import by.training.paragliding.dao.exception.DaoException;
import by.training.paragliding.dao.sql.sportsmen.FindAllSportsmenSpecification;
import by.training.paragliding.dao.sql.sportsmen.SportsmenRepository;
import by.training.paragliding.service.exception.ServiceException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;

public class UserService {
    /**
     * Logger.
     */
    private Logger logger = LogManager.getLogger("main");

    private static final String DB_URL =
            "jdbc:mysql://localhost:3306/paragliding_db?serverTimezone=UTC"
                    + "&useSSL=false&allowPublicKeyRetrieval=true";
    private SportsmenRepository repository = new SportsmenRepository();

    static {
        final String jdbcDriver = "com.mysql.cj.jdbc.Driver";
        try {
            Class.forName(jdbcDriver);
        } catch (ClassNotFoundException newE) {
            newE.printStackTrace();
        }
    }

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

    public List<Sportsman> readAll() throws ServiceException {
        try (Connection connection = DriverManager
                .getConnection(DB_URL, "paragliding_app",
                        "password");) {
            logger.debug(connection);
            repository.setConnection(connection);
            return repository.query(new FindAllSportsmenSpecification());
        } catch (DaoException | SQLException e) {
            throw new ServiceException(e);
        }
    }
}
