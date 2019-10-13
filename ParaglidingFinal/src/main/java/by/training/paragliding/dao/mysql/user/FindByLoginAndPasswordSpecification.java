package by.training.paragliding.dao.mysql.user;

import by.training.paragliding.dao.exception.DaoException;
import by.training.paragliding.dao.mysql.Specification;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class FindByLoginAndPasswordSpecification implements Specification {
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
                    + "       `rating`,\n"
                    + "       `image_path`\n"
                    + "FROM `users`\n"
                    + "         LEFT JOIN sportsmen s on users.id = s.user_id "
                    + " WHERE `password`=? AND `email`=?";

    private final String email;

    private final String password;

    public FindByLoginAndPasswordSpecification(final String newEmail, final String newPassword) {
        email = newEmail;
        password = newPassword;
    }

    @Override
    public PreparedStatement createStatement(final Connection connection) throws DaoException {
        try {
            var statement = connection.prepareStatement(SQL);
            statement.setString(1, password);
            statement.setString(2, email);
            return statement;
        } catch (SQLException newE) {
            throw new DaoException(newE);
        }
    }
}
