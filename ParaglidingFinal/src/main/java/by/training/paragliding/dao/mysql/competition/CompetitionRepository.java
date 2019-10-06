package by.training.paragliding.dao.mysql.competition;

import by.training.paragliding.bean.builder.Builder;
import by.training.paragliding.bean.builder.CompetitionBuilder;
import by.training.paragliding.bean.entity.Competition;
import by.training.paragliding.bean.exception.BeanException;
import by.training.paragliding.dao.exception.DaoException;
import by.training.paragliding.dao.mysql.BaseSqlRepository;
import by.training.paragliding.dao.mysql.Specification;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CompetitionRepository extends BaseSqlRepository<Competition> {

    private static final String SELECT_COMP_BY_ID =
            "SELECT `id`, `date`, `name`, `discipline_id`, "
                    + "`status`, `participation_fee`, `description`"
                    + " FROM `competitions` WHERE `id` = ?";

    private static final String IS_EMPTY = "SELECT NULL FROM `competitions` LIMIT 1";


    private final Builder<Competition> competitionBuilder = new CompetitionBuilder();

    public CompetitionRepository(final Connection newConnection) {
        super(newConnection);
    }

    /**
     * Returns object by it's id.
     *
     * @param id unique object identifier
     * @return object by it's id
     * @throws DaoException if something goes wrong
     */
    @Override
    public Competition readById(final int id) throws DaoException {
        try (PreparedStatement statement =
                     connection.prepareStatement(SELECT_COMP_BY_ID)) {
            statement.setInt(1, id);
            Competition competition;
            try (ResultSet resultSet = statement.executeQuery()) {
                competition = null;
                if (resultSet.next()) {
                    competition = competitionBuilder.build(resultSet);
                }
            }
            return competition;
        } catch (SQLException | BeanException e) {
            throw new DaoException(e);
        }
    }

    /**
     * Adds object to repository.
     *
     * @param object object to add
     * @return is object was added
     * @throws DaoException if something goes wrong
     */
    @Override
    public boolean add(final Competition object) throws DaoException {
        return false;
    }

    /**
     * Deletes object from repository.
     *
     * @param object object to delete
     * @return true if object was deleted
     * @throws DaoException if something goes wrong
     */
    @Override
    public boolean delete(final Competition object) throws DaoException {
        return false;
    }

    /**
     * Returns is repository empty.
     *
     * @return is repository empty.
     * @throws DaoException if something goes wrong
     */
    @Override
    public boolean isEmpty() throws DaoException {

        try (Statement statement = connection.createStatement()) {
            try (ResultSet resultSet = statement.executeQuery(IS_EMPTY)) {
                return resultSet.next();
            }
        } catch (SQLException newE) {
            throw new DaoException(newE);
        }
    }

    /**
     * Updates all fields of the object with the current values.
     *
     * @param object object to update
     * @return true if object was updated, false if not.
     * @throws DaoException if something goes wrong
     */
    @Override
    public boolean update(final Competition object) throws DaoException {
        return false;
    }

    /**
     * Execute query.
     *
     * @param specification specification of a query.
     * @return List of objects satisfied specification.
     * @throws DaoException if something goes wrong
     */
    @Override
    public List<Competition> query(final Specification specification)
            throws DaoException {
        var resultList = new ArrayList<Competition>();
        Competition competition;
        try (PreparedStatement statement =
                     specification.createStatement(connection);
             ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
                competition = competitionBuilder.build(resultSet);
                resultList.add(competition);
            }
            return resultList;
        } catch (SQLException | BeanException newE) {
            throw new DaoException(newE);
        }
    }
}
