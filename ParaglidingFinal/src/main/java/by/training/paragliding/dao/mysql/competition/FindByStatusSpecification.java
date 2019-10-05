package by.training.paragliding.dao.mysql.competition;

import by.training.paragliding.bean.entity.Competition;
import by.training.paragliding.dao.exception.DaoException;
import by.training.paragliding.dao.mysql.Specification;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class FindByStatusSpecification implements Specification {
    private static final String SQL = "SELECT `id`, `date` ,`name`,"
            + " `discipline_id`, `status`, `participation_fee`, `description`"
            + "FROM `competitions` WHERE `status` = ?";

    private Competition.Status status;

    public FindByStatusSpecification(final Competition.Status newStatus) {
        status = newStatus;
    }

    @Override
    public PreparedStatement createStatement(final Connection connection) throws DaoException {
        try {
            PreparedStatement statement = connection.prepareStatement(SQL);
            statement.setInt(1, status.ordinal());
            return statement;
        } catch (SQLException newE) {
            throw new DaoException(newE);
        }
    }
}
