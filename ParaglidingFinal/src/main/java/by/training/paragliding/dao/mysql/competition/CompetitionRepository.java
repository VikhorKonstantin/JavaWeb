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
            "SELECT `competitions`.`id`   AS competitionId,\n"
                    + "       `organizer_id`        AS userId,\n"
                    + "       `email`,\n"
                    + "       `password`,\n"
                    + "       `role`,\n"
                    + "       `date`,\n"
                    + "       `competitions`.`name` AS competitionName,\n"
                    + "       `disciplines`.`name`  AS `discipline_name`,\n"
                    + "       `status`,\n"
                    + "       `participation_fee`,\n"
                    + "       `description`,\n"
                    + "       `civl_id`,\n"
                    + "       `user_id`             AS userId,\n"
                    + "       s.`name`                AS sportsmanName,\n"
                    + "       `surname`,\n"
                    + "       `gender`,\n"
                    + "       `country`,\n"
                    + "       `rating`\n"
                    + "FROM `competitions`\n"
                    + "         JOIN `disciplines`"
                    + " ON `discipline_id` = `disciplines`.`id`\n"
                    + "         LEFT JOIN (`users` "
                    + " LEFT JOIN sportsmen s on users.id = s.user_id)\n"
                    + "                   ON `organizer_id` = `users`.`id`\n"
                    + "WHERE `competitions`.`id`  = ?";

    private static final String UPDATE_COMPETITION =
            "UPDATE `competitions`\n"
                    + "    JOIN `disciplines` ON `disciplines`.`name` = ?\n"
                    + "SET `date`                = ?,\n"
                    + "    `competitions`.`name` = ?,\n"
                    + "    `discipline_id`       = `disciplines`.`id`,\n"
                    + "    `status`              = ?,\n"
                    + "    `participation_fee`   = ?,\n"
                    + "    `description`         = ?\n"
                    + "WHERE `competitions`.`id` = ?;";

    private static final String INSERT_COMPETITION =
            "INSERT INTO `competitions`\n"
                    + "    (`date`, `organizer_id`,\n"
                    + "    `competitions`.`name`,\n"
                    + "    `status`, `participation_fee`, `description`,"
                    + " `discipline_id`)\n"
                    + "VALUES (?, ?, ?, ?, ?, ?,"
                    + " (SELECT id FROM disciplines WHERE name = ?));\n";
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
                newCompetition.getOrganizer().getId(),
                newCompetition.getName(),
                newCompetition.getStatus().ordinal(),
                newCompetition.getParticipationFee(),
                newCompetition.getDescription(),
                newCompetition.getDiscipline());
    }

    /**
     * Deletes object from repository.
     *
     * @param newCompetition object to delete
     * @return true if object was deleted
     * @throws DaoException if something goes wrong
     */
    @Override
    public boolean delete(final Competition newCompetition)
            throws DaoException {
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
    public boolean update(final Competition newCompetition)
            throws DaoException {
        return executeUpdate(UPDATE_COMPETITION,
                newCompetition.getDiscipline(),
                Date.valueOf(newCompetition.getDate()),
                newCompetition.getName(),
                newCompetition.getStatus().ordinal(),
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
