package by.training.paragliding.dao.mysql.competition;

import by.training.paragliding.bean.builder.CompetitionBuilder;
import by.training.paragliding.bean.entity.Competition;
import by.training.paragliding.dao.exception.DaoException;
import by.training.paragliding.dao.mysql.BaseSqlRepository;
import by.training.paragliding.dao.mysql.Specification;

import java.sql.Connection;
import java.sql.Date;
import java.util.List;

public class CompetitionRepository extends BaseSqlRepository<Competition> {

    private static final String SELECT_COMP_BY_ID =
            "SELECT `id`, `date`, `name`, `discipline_id` , "
                    + "`status`, `participation_fee`, `description`"
                    + " FROM `competitions` WHERE `discipline_id`  = ?";

    private static final String UPDATE_COMPETITION =
            "UPDATE `competitions`"
                    + "    JOIN `disciplines` ON `disciplines`.`name` = ? "
                    + "SET `date`                = ? "
                    + "    `competitions`.`name` = ? "
                    + "    `discipline_id`       = `disciplines`.`id`, "
                    + "    `status`              = ? "
                    + "    `participation_fee`   = ? "
                    + "    `description`         = ? "
                    + "WHERE `competitions`.`id` = ? ";

    private static final String INSERT_COMPETITION =
            "INSERT INTO `competitions` "
                    + "(`date`, `competitions`.`name`, `discipline_id`,"
                    + " `status`, `participation_fee`, `description`) "
                    + "VALUES (?, ?, `disciplines`.`id`, ?, ?, ?)";
    private static final String TABLE_NAME = "competitions";

    private static final String DELETE_COMPETITION =
            "DELETE FROM `competitions` WHERE `id` = ?";


    public CompetitionRepository(final Connection newConnection) {
        super(newConnection, new CompetitionBuilder());
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
        return readByProperties(SELECT_COMP_BY_ID, id);
    }

    /**
     * Adds object to repository.
     *
     * @param newCompetition object to add
     * @return is object was added
     * @throws DaoException if something goes wrong
     */
    @Override
    public boolean add(final Competition newCompetition) throws DaoException {
        return executeUpdate(INSERT_COMPETITION,
                Date.valueOf(newCompetition.getDate()),
                newCompetition.getName(), newCompetition.getDiscipline(),
                newCompetition.getStatus().ordinal(),
                newCompetition.getParticipationFee(),
                newCompetition.getDescription());
    }

    /**
     * Deletes object from repository.
     *
     * @param newCompetition object to delete
     * @return true if object was deleted
     * @throws DaoException if something goes wrong
     */
    @Override
    public boolean delete(final Competition newCompetition) throws DaoException {
        return executeUpdate(DELETE_COMPETITION, newCompetition.getId());
    }

    /**
     * Returns is repository empty.
     *
     * @return is repository empty.
     * @throws DaoException if something goes wrong
     */
    @Override
    public boolean isEmpty() throws DaoException {
        return isEmpty(TABLE_NAME);
    }

    /**
     * Updates all fields of the object with the current values.
     *
     * @param newCompetition object to update
     * @return true if object was updated, false if not.
     * @throws DaoException if something goes wrong
     */
    @Override
    public boolean update(final Competition newCompetition) throws DaoException {
        return executeUpdate(UPDATE_COMPETITION,
                newCompetition.getDiscipline(),
                Date.valueOf(newCompetition.getDate()),
                newCompetition.getName(), newCompetition.getStatus().ordinal(),
                newCompetition.getParticipationFee(),
                newCompetition.getDescription(), newCompetition.getId());
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
       return executeQuery(specification);
    }
}
