package by.training.paragliding.dao.mysql.competition;

import by.training.paragliding.bean.entity.Competition;
import by.training.paragliding.dao.exception.DaoException;
import by.training.paragliding.dao.mysql.BaseSqlRepository;
import by.training.paragliding.dao.mysql.Specification;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class CompetitionRepository extends BaseSqlRepository<Competition> {

    private static final String SELECT_COMP_BY_ID =
            "SELECT `id`, `date`, `name`, `discipline_id`, "
                    + "`status`, `participation_fee`, `description`"
                    + " FROM `competitions` WHERE `id` = ?";
    private static final String SELECT_DISCIPLINE =
            "SELECT `name` FROM `disciplines` WHERE `id` = ?";

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
                    competition = new Competition();
                    competition.setId(id);
                    Date rawDate = resultSet.getDate("date");
                    final LocalDate date = rawDate.toLocalDate();
                    competition.setDate(date);
                    final var name = resultSet.getString("name");
                    competition.setName(name);
                    final var discipline = resultSet.getString("discipline_name");
                    competition.setDiscipline(discipline);
                    final var statusInt =
                            resultSet.getInt("status");
                    final var status = Competition.Status.values()[statusInt];
                    competition.setStatus(status);
                    final var fee =
                            resultSet.getFloat("participation_fee");
                    competition.setParticipationFee(fee);
                    final var description =
                            resultSet.getString("description");
                    competition.setDescription(description);
                }
            }
            return competition;
        } catch (SQLException e) {
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
        return false;
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
                competition = new Competition();
                competition.setId(resultSet.getInt("id"));
                Date rawDate = resultSet.getDate("date");
                final LocalDate date = rawDate.toLocalDate();
                competition.setDate(date);
                final var name = resultSet.getString("name");
                competition.setName(name);
                final var disciplineName =
                        resultSet.getString("discipline_name");
                competition.setDiscipline(disciplineName);
                final var statusInt =
                        resultSet.getInt("status");
                final var status = Competition.Status.values()[statusInt];
                competition.setStatus(status);
                final var fee =
                        resultSet.getFloat("participation_fee");
                competition.setParticipationFee(fee);
                final var description =
                        resultSet.getString("description");
                competition.setDescription(description);
                resultList.add(competition);
            }
            return resultList;
        } catch (SQLException newE) {
            throw new DaoException(newE);
        }
    }
}
