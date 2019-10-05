package by.training.paragliding.dao.mysql.competition;

import by.training.paragliding.dao.exception.DaoException;
import by.training.paragliding.dao.mysql.Specification;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class FindAllCompetitionsSpecification implements Specification {
    private static final String SQL = "SELECT `id`, `date` ,`name`,"
            + " `discipline_id`, `status`, `participation_fee`, `description`"
            + "FROM `competitions`";

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
