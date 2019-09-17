package by.training.sqltask.dao.repository.specification;

import by.training.sqltask.bean.entity.Competition;
import by.training.sqltask.dao.exception.DaoException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class FindByApplication implements Specification {
    private Competition competition;

    public FindByApplication(final Competition newCompetition) {
        competition = newCompetition;
    }

    @Override
    public PreparedStatement createStatement(final Connection connection) throws DaoException {
        final String sqlRequest = "SELECT `civl_id`, `name`, `surname`, `gender`,"
                + " `country`, `rating`, `image_path` FROM `sportsmen`"
                + " JOIN `applications` ON `civl_id` = `sportsman_id`"
                + " JOIN `competitions` ON `id` = `competition_id`"
                + " WHERE `competitions`.`id` = ?";
        try (PreparedStatement statement = connection.prepareStatement(sqlRequest)) {
            statement.setInt(1, competition.getId());
            return statement;
        } catch (SQLException newE) {
            throw new DaoException(newE);
        }
    }
}
