package by.training.sqltask.service;

import by.training.sqltask.bean.entity.Sportsman;
import by.training.sqltask.dao.exception.DaoException;
import by.training.sqltask.dao.repository.SportsmenRepository;
import by.training.sqltask.service.exception.ServiceException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.DriverManager;
import java.sql.SQLException;

public class ViewSportsmanService {
    /**
     * Logger.
     */
    private Logger logger = LogManager.getLogger("main");
    public Sportsman readById(final int civlId) throws ServiceException {
        SportsmenRepository repository = new SportsmenRepository();
        try{
            final String jdbcDriver = "com.mysql.jdbc.Driver";
            Class.forName(jdbcDriver);
        } catch (ClassNotFoundException e) {
            throw new ServiceException(e);
        }
        try(var connection = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/paragliding_db?serverTimezone=UTC",
                "paragliding_app", "password");) {
            logger.debug(connection);
            repository.setConnection(connection);
            return repository.readById(civlId);
        } catch (DaoException | SQLException e) {
            throw new ServiceException(e);
        }
    }
}
