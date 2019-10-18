package by.training.paragliding.dao.mysql.sportsmen;

import by.training.paragliding.dao.exception.DaoException;
import by.training.paragliding.dao.mysql.Specification;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class FindSportsmenByRatingRange implements Specification {

    private static final String SQL =
            "SELECT `civl_id`, `name` AS sportsmanName, `surname`, `gender`,"
            + " `country`, `rating`, `image_path`"
            + " FROM `sportsmen` WHERE `rating` BETWEEN ? AND ?";

    private float leftRatingBound;
    private float rightRatingBound;

    /**
     * Creates FindSpecification.
     *
     * @param newLeftBound  left bound of range to find in
     * @param newRightBound right bound of range to find in
     */
    public FindSportsmenByRatingRange(final float newLeftBound,
                                      final float newRightBound) {
        leftRatingBound = newLeftBound;
        rightRatingBound = newRightBound;
    }

    @Override
    public PreparedStatement createStatement(final Connection connection)
            throws DaoException {
        try {
            PreparedStatement statement = connection.prepareStatement(SQL);
            statement.setFloat(1, leftRatingBound);
            statement.setFloat(2, rightRatingBound);
            return statement;
        } catch (SQLException newE) {
            throw new DaoException(newE);
        }
    }
}
