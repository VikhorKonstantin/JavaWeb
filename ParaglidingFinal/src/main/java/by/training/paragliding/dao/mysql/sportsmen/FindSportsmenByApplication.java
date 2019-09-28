package by.training.paragliding.dao.mysql.sportsmen;

import by.training.paragliding.bean.entity.Competition;
import by.training.paragliding.dao.exception.DaoException;
import by.training.paragliding.dao.mysql.Specification;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class FindSportsmenByApplication implements Specification {

    private static final String SQL = "SELECT `civl_id`, `sportsmen`.`name`, `surname`, `gender`,"
            + " `country`, `rating`, `image_path` FROM `sportsmen`"
            + " JOIN `applications` ON `civl_id` = `sportsman_id`"
            + " JOIN `competitions` ON `id` = `competition_id`"
            + " WHERE `competitions`.`id` = ?";

    private Competition competition;

    public FindSportsmenByApplication(final Competition newCompetition) {
        competition = newCompetition;
    }

    @Override
    public PreparedStatement createStatement(final Connection connection) throws DaoException {
        try (PreparedStatement statement = connection.prepareStatement(SQL)){

            statement.setInt(1, competition.getId());
            return statement;
        } catch (SQLException newE) {
            throw new DaoException(newE);
        }
    }
}
