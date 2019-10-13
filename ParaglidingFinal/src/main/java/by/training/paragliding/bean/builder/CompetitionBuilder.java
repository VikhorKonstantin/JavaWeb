package by.training.paragliding.bean.builder;

import by.training.paragliding.bean.entity.Competition;
import by.training.paragliding.bean.entity.User;
import by.training.paragliding.bean.exception.BeanException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

public class CompetitionBuilder implements Builder<Competition> {
    /**
     * Logger.
     */
    private Logger logger = LogManager.getLogger("main");

    /**
     * @param newResultSet resultSet (result of executing sql statements)
     * @return new Competition object.
     * @throws BeanException if some exceptions where thrown while object building.
     */
    @Override
    public Competition buildFromResultSet(final ResultSet newResultSet)
            throws BeanException {
        try {
            var competition = new Competition();
            competition.setId(newResultSet.getInt("competitionId"));
            Date rawDate = newResultSet.getDate("date");
            final LocalDate date = rawDate.toLocalDate();
            competition.setDate(date);
            final var name = newResultSet.getString("competitionName");
            competition.setName(name);
            final var disciplineName =
                    newResultSet.getString("discipline_name");
            competition.setDiscipline(disciplineName);
            final var statusInt =
                    newResultSet.getInt("status");
            final var status = Competition.Status.values()[statusInt];
            competition.setStatus(status);

            final var fee =
                    newResultSet.getFloat("participation_fee");
            competition.setParticipationFee(fee);
            final var description =
                    newResultSet.getString("description");
            competition.setDescription(description);
            var userBuilder = new UserBuilder();

            final User organizer = userBuilder.buildFromResultSet(newResultSet);

            competition.setOrganizer(organizer);
            return competition;
        } catch (SQLException newE) {
            throw new BeanException(EXC_MSG, newE);
        }
    }
}
