package by.training.paragliding.dao.mysql.result;

import by.training.paragliding.dao.exception.DaoException;
import by.training.paragliding.dao.mysql.Specification;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class FindByIdentifiersSpecification implements Specification {
    private static final String SQL =
            "SELECT competition_id as competitionId,"
                    + " sportsman_id as civl_id, score"
                    + " FROM results"
                    + " WHERE competition_id =? AND sportsman_id =?"
                    + " ORDER BY score DESC";

    private int competitionId;
    private int civlId;

    public FindByIdentifiersSpecification(final int newCompetitionId,
                                          final int newCivlId) {
        competitionId = newCompetitionId;
        civlId = newCivlId;
    }

    @Override
    public PreparedStatement createStatement(final Connection connection)
            throws DaoException {
        try {
            var statement = connection.prepareStatement(SQL);
            statement.setInt(1, civlId);
            statement.setInt(2, competitionId);
            return statement;
        } catch (SQLException newE) {
            throw new DaoException(newE);
        }
    }
}
