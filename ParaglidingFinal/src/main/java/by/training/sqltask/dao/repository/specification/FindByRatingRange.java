package by.training.sqltask.dao.repository.specification;

import by.training.sqltask.dao.exception.DaoException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

public class FindByRatingRange implements Specification {
    private float leftRatingBound;
    private float rightRatingBound;

    /**
     * Creates FindSpecification
     *
     * @param newLeftBound  left bound of range to find in
     * @param newRightBound right bound of range to find in
     */
    public FindByRatingRange(final float newLeftBound, final float newRightBound) {
        leftRatingBound = newLeftBound;
        rightRatingBound = newRightBound;
    }

    @Override
    public PreparedStatement createStatement(final Connection connection) throws DaoException {
        final String sqlRequest = "SELECT `civl_id`, `name`, `surname`, `gender`,"
                + " `country`, `rating`, `image_path` FROM `sportsmen` WHERE `rating` BETWEEN ? AND ?";
        try (PreparedStatement statement = connection.prepareStatement(sqlRequest,
                Statement.RETURN_GENERATED_KEYS)) {
            statement.setFloat(1, leftRatingBound);
            statement.setFloat(2, rightRatingBound);
            return statement;
        } catch (SQLException newE) {
            throw new DaoException(newE);
        }
    }
}
