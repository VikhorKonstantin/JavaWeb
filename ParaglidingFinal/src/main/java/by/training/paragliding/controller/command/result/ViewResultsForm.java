package by.training.paragliding.controller.command.result;

import by.training.paragliding.controller.command.Executable;
import by.training.paragliding.controller.command.ExecutionResult;
import by.training.paragliding.controller.exception.ControllerException;
import by.training.paragliding.service.CompetitionService;
import by.training.paragliding.service.SportsmanService;
import by.training.paragliding.service.exception.ServiceException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ViewResultsForm implements Executable {
    /**
     * Logger.
     */
    private Logger logger = LogManager.getLogger("main");
    private CompetitionService competitionService;
    private SportsmanService sportsmanService;

    public ViewResultsForm(final CompetitionService newCompetitionService,
                           final SportsmanService newSportsmanService) {
        competitionService = newCompetitionService;
        sportsmanService = newSportsmanService;
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
        String competitionIdString =  req.getParameter("competitionId");
        logger.debug("Id {}", competitionIdString);
        int competitionId = Integer.parseInt(competitionIdString);
        try {
            var competition = competitionService.readById(competitionId);
            var participants = sportsmanService.find(
                    SportsmanService.APPLICATION, competition);
            req.setAttribute("participants", participants);
            req.setAttribute("competition", competition);
        } catch (ServiceException newE) {
            throw new ControllerException(newE);
        }
        return new ExecutionResult(true,
                "/WEB-INF/jsp/resultsForm.jsp");
    }
}
