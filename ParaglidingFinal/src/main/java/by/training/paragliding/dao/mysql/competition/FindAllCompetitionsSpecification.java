package by.training.paragliding.dao.mysql.competition;

import by.training.paragliding.dao.exception.DaoException;
import by.training.paragliding.dao.mysql.Specification;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class FindAllCompetitionsSpecification implements Specification {
    private static final String SQL =
            "SELECT `competitions`.`id` AS `id`, `organizer_id`,"
                    + " `email`, `password`, `role`, `date`,"
                    + " `competitions`.`name` AS `name`,"
                    + " `disciplines`.`name` AS `discipline_name`, "
                    + " `status`, `participation_fee`, `description`"
                    + "FROM `competitions`"
                    + "JOIN `disciplines` "
                    + "ON `discipline_id` = `disciplines`.`id`"
                    + "JOIN `users` ON `organizer_id` = `users`.`id`";


    @Override
    public PreparedStatement createStatement(final Connection connection)
            throws DaoException {
        try {
            return connection.prepareStatement(SQL);
        } catch (SQLException newE) {
            throw new DaoException(newE);
        }
    }
}
