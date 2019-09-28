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
import java.time.format.DateTimeFormatter;
import java.util.List;

public class CompetitionRepository extends BaseSqlRepository<Competition> {
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
        final String sql = "SELECT `id`, `date`, `name`, `discipline_id`, "
                + "`status`, `participation_fee`, `description`"
                + " FROM `competitions` WHERE `id` = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, id);
            Competition competition;
            try (ResultSet resultSet = statement.executeQuery()) {
                competition = null;
                if (resultSet.next()) {
                    competition = new Competition();
                    competition.setId(id);
                    final var dateString =
                            String.valueOf(resultSet.getInt("date"));
                    final LocalDate date = LocalDate.parse(dateString,
                            DateTimeFormatter.ofPattern("yyyyMMdd"));
                    competition.setDate(date);
                    final var name = resultSet.getString("name");
                    competition.setName(name);
                    //todo: make dao for disciplines and set value to competition.
                    // or make it tiny int and use enum
                    final var disciplineId =
                            resultSet.getInt("discipline_id");
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
    public List<Competition> query(final Specification specification) throws DaoException {
        return null;
    }
}
