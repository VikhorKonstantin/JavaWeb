package by.training.paragliding.bean.validator;

import by.training.paragliding.bean.entity.Competition;
import by.training.paragliding.bean.exception.BeanException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDate;

public class CompetitionValidator implements Validator<Competition> {
    /**
     * Logger.
     */
    private Logger logger = LogManager.getLogger("main");
    private static final String DATE_PATTERN = "^\\d{4}-\\d{1,2}-\\d{1,2}$";
    private static final String NAME_PATTERN = "^[0-9.?!a-zA-Z\\s]{0,250}$";
    private static final String FEE_PATTERN = "^[0-9]{0,4}.?[0-9]*";
    private static final String ID_PATTERN = "\\d{1,10}";
    @Override
    public Competition validate(final HttpServletRequest newRequest)
            throws BeanException {
        var dateString = newRequest.getParameter("date");
        var name = newRequest.getParameter("name");
        var fee = newRequest.getParameter("fee");
        var statusString = newRequest.getParameter("status");
        var description = newRequest.getParameter("description");

        var idString = newRequest.getParameter("id");
        var discipline   = newRequest.getParameter("discipline");
        boolean isValid;
        isValid = dateString.matches(DATE_PATTERN)
                && name.matches(NAME_PATTERN)
                && fee.matches(FEE_PATTERN)
                && idString.matches(ID_PATTERN);
        if (isValid) {
            Competition competition = new Competition();
            competition.setDate(LocalDate.parse(dateString));
            competition.setDescription(description);
            competition.setId(Integer.parseInt(idString));
            competition.setName(name);
            competition.setParticipationFee(Float.parseFloat(fee));
            competition.setStatus(Competition.Status.valueOf(statusString));
            competition.setDiscipline(discipline);
            return competition;
        } else {
            throw new BeanException(EXC_MSG);
        }

    }
}
