package by.training.sqltask.dao.repository;

import by.training.sqltask.bean.entity.Sportsman;
import by.training.sqltask.dao.exception.DaoException;
import by.training.sqltask.dao.repository.specification.Specification;
import com.neovisionaries.i18n.CountryCode;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class SportsmenRepository extends BaseSqlRepository<Sportsman> {
    /**
     * Logger.
     */
    private Logger logger = LogManager.getLogger("main");
    /**
     * Returns object by it's id.
     *
     * @param id unique object ID
     * @return object by it's id
     */
    @Override
    public Sportsman readById(final int id) throws DaoException {
        final String sql = "SELECT `civl_id`, `name`, `surname`, `gender`,"
                + " `country`, `rating`, `image_path` FROM `sportsmen` WHERE `civl_id` = ?";
        try(PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, id);
            Sportsman sportsman;
            try (ResultSet resultSet = statement.executeQuery()) {
                sportsman = null;
                if (resultSet.next()) {
                    sportsman = new Sportsman();
                    sportsman.setCivlId(id);
                    var surname = resultSet.getString("surname");
                    final String logSnMsg = String.format("Surname: %s", surname);
                    logger.debug(logSnMsg);
                    sportsman.setSurname(surname);
                    var name = resultSet.getString("name");
                    sportsman.setName(name);
                    var gender = resultSet.getString("gender").charAt(0);
                    sportsman.setGender(gender);
                    var countryCode = CountryCode.valueOf(resultSet.getString("country"));
                    sportsman.setCountryCode(countryCode);
                    var rating = resultSet.getFloat("rating");
                    sportsman.setRating(rating);
                    var imagePath = resultSet.getString("image_path");
                    sportsman.setImagePath(imagePath);
                }
            }
            return sportsman;
        } catch(SQLException e) {
            throw new DaoException(e);
        }
    }
    /**
     * Adds object to repository.
     *
     * @param newSportsman plane to add
     * @return is object was added
     */
    @Override
    public boolean add(final Sportsman newSportsman) throws DaoException {
        String sql = "INSERT INTO `sportsmen` (`civl_id`, `name`, `surname`,"
                + " `gender`, `country`, `rating`, `image_path`) VALUES (?, ?, ?, ?, ?, ?, ?)";
        try(PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            statement.setInt(1, newSportsman.getCivlId());
            statement.setString(2, newSportsman.getName());
            statement.setString(3, newSportsman.getSurname());
            statement.setString(4, String.valueOf(newSportsman.getGender()));
            statement.setString(5, String.valueOf(newSportsman.getCountryCode().getAlpha2()));
            statement.setFloat(6, newSportsman.getRating());
            statement.setString(7, newSportsman.getImagePath());
            statement.executeUpdate();
            try (ResultSet resultSet = statement.getGeneratedKeys()) {
                if(resultSet.next()) {
                   return resultSet.getInt(1) > 0;
                }
            }
            return false;
        } catch(SQLException e) {
            throw new DaoException(e);
        }
    }
    /**
     * Deletes object from repository.
     *
     * @param sportsman object to delete
     * @return true if object was deleted
     */
    @Override
    public boolean delete(final Sportsman sportsman) {
        return false;
    }
    /**
     * Returns is repository empty.
     *
     * @return is repository empty.
     */
    @Override
    public boolean isEmpty() throws DaoException {
        final String sql = "SELECT COUNT(`civl_id`) AS `count` FROM `sportsmen`";

        try (PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            try (ResultSet resultSet = statement.executeQuery()) {
                if(resultSet.next()) {
                    return resultSet.getInt("`count`") > 0;
                } else {
                    throw new DaoException("ResultSet has no next");
                }
            }
        } catch (SQLException newE) {
            throw new DaoException(newE);
        }
    }
    /**
     * Execute query.
     *
     * @param specification specification of a query.
     * @return List of objects satisfied specification.
     */
    @Override
    public List<Sportsman> query(final Specification specification) throws DaoException {
        var resultList = new ArrayList<Sportsman>();
        Sportsman sportsman;
        try (ResultSet resultSet = specification.createStatement(connection).executeQuery()) {
            while (resultSet.next()) {
                sportsman = new Sportsman();
                sportsman.setCivlId(resultSet.getInt("`civl_id`"));
                sportsman.setName(resultSet.getString("`name`"));
                sportsman.setSurname(resultSet.getString("`surname`"));
                sportsman.setGender(resultSet.getString("`gender").charAt(0));
                sportsman.setRating(resultSet.getFloat("`rating`"));
                sportsman.setCountryCode(CountryCode.valueOf(resultSet.getString("`country`")));
                sportsman.setImagePath(resultSet.getString("`image_path`"));
                resultList.add(sportsman);
            }
            return resultList;
        } catch (SQLException newE) {
            throw new DaoException(newE);
        }
    }
}
