package by.training.paragliding.controller.command.competition;

import by.training.paragliding.bean.entity.Competition;
import by.training.paragliding.controller.command.Executable;
import by.training.paragliding.controller.command.ExecutionResult;
import by.training.paragliding.controller.exception.ControllerException;
import by.training.paragliding.service.CompetitionService;
import by.training.paragliding.service.exception.ServiceException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ViewCompetitionList implements Executable {
    /**
     * Logger.
     */
    private Logger logger = LogManager.getLogger("main");

    private CompetitionService competitionService;

    public ViewCompetitionList(final CompetitionService newCompetitionService) {
        competitionService = newCompetitionService;
    }

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
        try {
            var futureComps = competitionService.find(CompetitionService.STATUS,
                    Competition.Status.ANNOUNCED);
            var finishedComps = competitionService.find(
                    CompetitionService.STATUS, Competition.Status.FINISHED);
            req.setAttribute("futureComps", futureComps);
            req.setAttribute("finishedComps", finishedComps);
            return new ExecutionResult(true,
                    "/WEB-INF/jsp/competitionsList.jsp");
        }
        catch (ServiceException e) {
            throw new ControllerException(e);
        }
    }
}
