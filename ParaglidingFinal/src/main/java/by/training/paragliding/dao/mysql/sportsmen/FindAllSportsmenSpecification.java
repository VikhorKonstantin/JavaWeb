package by.training.paragliding.dao.mysql.sportsmen;

import by.training.paragliding.dao.exception.DaoException;
import by.training.paragliding.dao.mysql.Specification;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class FindAllSportsmenSpecification implements Specification {
    private static final String SQL = "SELECT `civl_id`, `name`, `surname`, `gender`,"
            + " `country`, `rating`, `image_path` FROM `sportsmen`";

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
