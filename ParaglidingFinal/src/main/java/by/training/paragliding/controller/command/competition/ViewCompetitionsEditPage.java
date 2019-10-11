package by.training.paragliding.controller.command.competition;

import by.training.paragliding.bean.entity.Competition;
import by.training.paragliding.controller.command.Executable;
import by.training.paragliding.controller.command.ExecutionResult;
import by.training.paragliding.controller.exception.ControllerException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ViewCompetitionsEditPage implements Executable {
    /**
     * Logger.
     */
    private Logger logger = LogManager.getLogger("main");

    /**
     * Execute command.
     *
     * @param req  http request
     * @param resp http response
     * @throws ControllerException if something goes wrong
     *                             while command execution or request invalid
     */
    @Override
    public ExecutionResult execute(final HttpServletRequest req,
                                   final HttpServletResponse resp)
            throws ControllerException {
        String competitionString =  req.getParameter("editedCompetition");
        Competition competition = (Competition) req.getSession().getAttribute(competitionString);
        req.setAttribute("competition", competition);
        req.getSession().removeAttribute(competitionString);
        logger.debug("editedCompetition {} method {}",
                competition, req.getMethod());
        return new ExecutionResult(true,
                "/WEB-INF/jsp/CompetitionEdit.jsp");
    }
}
