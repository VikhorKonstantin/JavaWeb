package by.training.paragliding.dao.mysql.competition;

import by.training.paragliding.bean.entity.Sportsman;
import by.training.paragliding.dao.exception.DaoException;
import by.training.paragliding.dao.mysql.Specification;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class FindByParticipant implements Specification {
    private static final String SQL =
            "SELECT `competitions`.`id`   AS competitionId,\n"
                    + "       `organizer_id`        AS userId,\n"
                    + "       `email`,\n"
                    + "       `password`,\n"
                    + "       `role`,\n"
                    + "       `date`,\n"
                    + "       `competitions`.`name` AS competitionName,\n"
                    + "       `disciplines`.`name`  AS `discipline_name`,\n"
                    + "       `status`,\n"
                    + "       `participation_fee`,\n"
                    + "       `description`,\n"
                    + "       `civl_id`,\n"
                    + "       `user_id`             AS userId,\n"
                    + "       s.`name`              AS sportsmanName,\n"
                    + "       `surname`,\n"
                    + "       `gender`,\n"
                    + "       `country`,\n"
                    + "       `rating`\n"
                    + "FROM `competitions`\n"
                    + "         JOIN `disciplines`\n"
                    + "              ON `discipline_id` = `disciplines`.`id`\n"
                    + "         LEFT JOIN (`users`\n"
                    + "    LEFT JOIN sportsmen s on users.id = s.user_id)\n"
                    + "                   ON `organizer_id` = `users`.`id`\n"
                    + "LEFT JOIN applications a"
                    + " on competitions.id = a.competition_id\n"
                    + "WHERE a.sportsman_id = ?";

    private Sportsman sportsman;

    public FindByParticipant(final Sportsman newSportsman) {
        sportsman = newSportsman;
    }

    @Override
    public PreparedStatement createStatement(final Connection connection)
            throws DaoException {
        try {
            PreparedStatement statement = connection.prepareStatement(SQL);
            statement.setInt(1, sportsman.getCivlId());
            return statement;
        } catch (SQLException newE) {
            throw new DaoException(newE);
        }
    }
}
