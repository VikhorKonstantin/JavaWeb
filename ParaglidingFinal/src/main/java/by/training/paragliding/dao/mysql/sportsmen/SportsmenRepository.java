package by.training.paragliding.dao.mysql.sportsmen;

import by.training.paragliding.bean.builder.Builder;
import by.training.paragliding.bean.builder.SportsmanBuilder;
import by.training.paragliding.bean.entity.Sportsman;
import by.training.paragliding.bean.exception.BeanException;
import by.training.paragliding.dao.exception.DaoException;
import by.training.paragliding.dao.mysql.BaseSqlRepository;
import by.training.paragliding.dao.mysql.Specification;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SportsmenRepository extends BaseSqlRepository<Sportsman> {
    /**
     * Logger.
     */
    private final Logger logger = LogManager.getLogger("main");

    private final Builder<Sportsman> sportsmanBuilder = new SportsmanBuilder();

    public SportsmenRepository(final Connection newConnection) {
        super(newConnection);
    }


    /**
     * Returns object by it's id.
     *
     * @param id unique object ID
     * @return object by it's id
     */
    @Override
    public Sportsman readById(final int id) throws DaoException {
        final String sql = "SELECT `civl_id`, `user_id`, `name`, `surname`, `gender`,"
                + " `country`, `rating`, `image_path` FROM `sportsmen` WHERE `civl_id` = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, id);
            Sportsman sportsman;
            try (ResultSet resultSet = statement.executeQuery()) {
                sportsman = null;
                if (resultSet.next()) {
                    sportsman = sportsmanBuilder.build(resultSet);
                }
            }
            return sportsman;
        } catch (SQLException | BeanException e) {
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
        try (PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            statement.setInt(1, newSportsman.getCivlId());
            statement.setString(2, newSportsman.getName());
            statement.setString(3, newSportsman.getSurname());
            statement.setString(4, String.valueOf(newSportsman.getGender()));
            statement.setString(5, String.valueOf(newSportsman.getCountryCode().getAlpha2()));
            statement.setFloat(6, newSportsman.getRating());
            statement.setString(7, newSportsman.getImagePath());
            statement.executeUpdate();
            try (ResultSet resultSet = statement.getGeneratedKeys()) {
                if (resultSet.next()) {
                    return resultSet.getInt(1) > 0;
                }
            }
            return false;
        } catch (SQLException e) {
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
    public boolean delete(final Sportsman sportsman) throws DaoException {
        String sql = "DELETE FROM `sportsmen` WHERE `civl_id` = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, sportsman.getCivlId());
            statement.executeUpdate();
            return true;
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    /**
     * Returns is repository empty.
     *
     * @return is repository empty.
     */
    @Override
    public boolean isEmpty() throws DaoException {
        final String sql = "SELECT NULL FROM `sportsmen` LIMIT 1";
        try (PreparedStatement statement = connection.prepareStatement(sql,
                Statement.RETURN_GENERATED_KEYS)) {
            try (ResultSet resultSet = statement.executeQuery()) {
                return resultSet.next();
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
    public List<Sportsman> query(final Specification specification)
            throws DaoException {
        var resultList = new ArrayList<Sportsman>();
        Sportsman sportsman;
        try (PreparedStatement statement =
                     specification.createStatement(connection);
             ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
                sportsman = sportsmanBuilder.build(resultSet);
                resultList.add(sportsman);
            }
            return resultList;
        } catch (SQLException | BeanException newE) {
            throw new DaoException(newE);
        }
    }

    /**
     * Updates all fields of the newSportsman with the current values.
     *
     * @param newSportsman Sportsman to update
     * @return true if Sportsman was updated, false if not.
     */
    @Override
    public boolean update(final Sportsman newSportsman) throws DaoException {
        String sql = "UPDATE `sportsmen` "
                + "SET `name`=?, `surname`=?,"
                + " `gender`=?, `country`=?, `rating`=?, `image_path`=?"
                + " WHERE `civl_id` = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, newSportsman.getName());
            statement.setString(2, newSportsman.getSurname());
            statement.setString(3,
                    String.valueOf(newSportsman.getGender()));
            statement.setString(4,
                    String.valueOf(newSportsman.getCountryCode().getAlpha2()));
            statement.setFloat(5, newSportsman.getRating());
            statement.setString(6, newSportsman.getImagePath());
            int updated = statement.executeUpdate();
            return updated > 0;
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }
}
