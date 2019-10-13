package by.training.paragliding.controller.command.competition;

import by.training.paragliding.controller.command.Executable;
import by.training.paragliding.controller.command.ExecutionResult;
import by.training.paragliding.controller.exception.ControllerException;
import by.training.paragliding.service.CompetitionService;
import by.training.paragliding.service.exception.ServiceException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ViewCompetitionById implements Executable {
    /**
     * Logger.
     */
    private Logger logger = LogManager.getLogger("main");

    private CompetitionService competitionService;

    public ViewCompetitionById(final CompetitionService newCompetitionService) {
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
            String idString = req.getParameter("id");
            int id = Integer.parseInt(idString);
            var competition = competitionService.readById(id);
            req.setAttribute("competition", competition);
            return new ExecutionResult(true,
                    "/WEB-INF/jsp/competitionPage.jsp");
        }
        catch (ServiceException e) {
            throw new ControllerException(e);
        }
    }
}
