package by.training.paragliding.dao.mysql.sportsmen;

import by.training.paragliding.dao.exception.DaoException;
import by.training.paragliding.dao.mysql.Specification;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class FindAllSportsmenPagableSpecification implements Specification {
    private static final String SQL =
            "SELECT `civl_id`, `name` AS sportsmanName, `surname`, `gender`,"
            + " `country`, `rating` FROM `sportsmen` LIMIT ? OFFSET ?";

    private int limit;
    private int offset;

    public FindAllSportsmenPagableSpecification(final int newLimit,
                                                final int newOffset) {
        limit = newLimit;
        offset = newOffset;
    }

    @Override
    public PreparedStatement createStatement(final Connection connection)
            throws DaoException {
        try {
            var statement = connection.prepareStatement(SQL);
            statement.setInt(1, limit);
            statement.setInt(2, offset);
            return statement;
        } catch (SQLException newE) {
            throw new DaoException(newE);
        }
    }
}
