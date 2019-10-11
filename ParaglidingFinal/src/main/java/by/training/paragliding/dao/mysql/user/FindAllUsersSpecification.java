package by.training.paragliding.dao.mysql.user;

import by.training.paragliding.dao.exception.DaoException;
import by.training.paragliding.dao.mysql.Specification;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class FindAllUsersSpecification implements Specification {
    private static final String SQL =
            "SELECT `id`, `email`, `password`, `role`, `civl_id`, `name`,"
                    + " `surname`, `gender`, `country`," 
                    + " `rating`, `image_path` "
                    + "FROM `users` "
                    + "         LEFT JOIN sportsmen s on users.id = s.user_id";

    @Override
    public PreparedStatement createStatement(final Connection connection) throws DaoException {
        try {
            return connection.prepareStatement(SQL);
        } catch (SQLException newE) {
            throw new DaoException(newE);
        }
    }
}
