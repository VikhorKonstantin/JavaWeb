package by.training.paragliding.dao.mysql.user;

import by.training.paragliding.dao.exception.DaoException;
import by.training.paragliding.dao.mysql.Specification;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class FindAllUsersSpecification implements Specification {
    private static final String SQL =
            "SELECT `id` as userId,\n"
                    + "       `email`,\n"
                    + "       `password`,\n"
                    + "       `role`,\n"
                    + "       `civl_id`,\n"
                    + "       `name` AS sportsmanName,\n"
                    + "       `surname`,\n"
                    + "       `gender`,\n"
                    + "       `country`,\n"
                    + "       `rating`\n"
                    + "FROM `users`\n"
                    + "         LEFT JOIN sportsmen s on users.id = s.user_id";

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
