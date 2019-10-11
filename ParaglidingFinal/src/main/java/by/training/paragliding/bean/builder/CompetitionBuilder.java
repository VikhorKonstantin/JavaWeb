package by.training.paragliding.bean.builder;

import by.training.paragliding.bean.entity.Competition;
import by.training.paragliding.bean.entity.Role;
import by.training.paragliding.bean.entity.User;
import by.training.paragliding.bean.exception.BeanException;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

public class CompetitionBuilder implements Builder<Competition> {

    /**
     * @param newResultSet resultSet (result of executing sql statements)
     * @return new Competition object.
     * @throws BeanException if some exceptions where thrown while object building.
     */
    @Override
    public Competition buildFromResultSet(final ResultSet newResultSet) throws BeanException {
        try {
            var competition = new Competition();
            competition.setId(newResultSet.getInt("id"));
            Date rawDate = newResultSet.getDate("date");
            final LocalDate date = rawDate.toLocalDate();
            competition.setDate(date);
            final var name = newResultSet.getString("name");
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
            final User organizer = new User();
            var orgId = newResultSet.getInt("organizer_id");
            var orgPassword = newResultSet.getString("password");
            var orgEmail = newResultSet.getString("email");
            var roleInt = newResultSet.getInt("role");
            var orgRole = Role.values()[roleInt];

            organizer.setId(orgId);
            organizer.setRole(orgRole);
            organizer.setPassword(orgPassword);
            organizer.setEmail(orgEmail);
            competition.setOrganizer(organizer);
            return competition;
        } catch (SQLException newE) {
            throw new BeanException(EXC_MSG, newE);
        }
    }
}
