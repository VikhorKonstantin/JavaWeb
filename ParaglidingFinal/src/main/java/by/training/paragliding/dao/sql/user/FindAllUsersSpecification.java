package by.training.paragliding.dao.sql.user;

import by.training.paragliding.dao.exception.DaoException;
import by.training.paragliding.dao.sql.Specification;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class FindAllUsersSpecification implements Specification {
    private static final String SQL =
            "SELECT `id`, `email`, `password`, `role` FROM `users`";

    @Override
    public PreparedStatement createStatement(final Connection connection) throws DaoException {
        try {
            return connection.prepareStatement(SQL);
        } catch (SQLException newE) {
            throw new DaoException(newE);
        }
    }
}
