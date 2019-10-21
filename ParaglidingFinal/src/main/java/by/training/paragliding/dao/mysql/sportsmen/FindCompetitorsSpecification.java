package by.training.paragliding.dao.mysql.sportsmen;

import by.training.paragliding.bean.entity.Sportsman;
import by.training.paragliding.dao.exception.DaoException;
import by.training.paragliding.dao.mysql.Specification;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class FindCompetitorsSpecification implements Specification {
    private static final String SQL =
            "((SELECT `civl_id`, `name` AS sportsmanName,\n"
                    + "        `surname`, `gender`,\n"
                    + "        `country`, `rating`\n"
                    + " FROM `sportsmen` WHERE `rating` >= ? LIMIT 2)\n"
                    + "UNION\n"
                    + "(SELECT `civl_id`, `name` AS sportsmanName,\n"
                    + "        `surname`, `gender`,\n"
                    + "        `country`, `rating`\n"
                    + " FROM `sportsmen` WHERE `rating` < ? LIMIT 1))"
                    + " ORDER BY rating DESC";
    /**
     * CountryCode of sportsmen to find.
     */
    private Sportsman sportsman;

    /**
     * Creates new FindSpecification.
     * @param newSportsman new sportsman
     */
    public FindCompetitorsSpecification(final Sportsman newSportsman) {
        sportsman = newSportsman;
    }

    @Override
    public PreparedStatement createStatement(final Connection connection)
            throws DaoException {
        try {
            PreparedStatement statement = connection.prepareStatement(SQL);
            statement.setFloat(1, sportsman.getRating());
            statement.setFloat(2, sportsman.getRating());
            return statement;
        } catch (SQLException newE) {
            throw new DaoException(newE);
        }
    }
}
