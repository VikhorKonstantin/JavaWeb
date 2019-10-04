package by.training.paragliding.dao.mysql.competition;

import by.training.paragliding.bean.entity.Competition;
import by.training.paragliding.dao.exception.DaoException;
import by.training.paragliding.dao.mysql.BaseSqlRepository;
import by.training.paragliding.dao.mysql.Specification;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
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
                    final LocalDate date = LocalDate.ofInstant(
                            rawDate.toInstant(), ZoneId.systemDefault());
                    competition.setDate(date);
                    final var name = resultSet.getString("name");
                    competition.setName(name);
                    final var disciplineId =
                            resultSet.getInt("discipline_id");
                    try (PreparedStatement disciplineStatement = connection
                            .prepareStatement(SELECT_DISCIPLINE)) {
                        disciplineStatement
                                .setInt(1, disciplineId);
                        try (var disciplineResultSet =
                                     disciplineStatement.executeQuery()) {
                            final var discipline =
                                    disciplineResultSet
                                            .getString("name");
                            competition.setDiscipline(discipline);
                        }
                    }
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
        return null;
    }
}
