package by.training.paragliding.controller.command;

import by.training.paragliding.controller.command.competition.ViewFutureDescriptions;
import by.training.paragliding.controller.exception.ControllerException;
import by.training.paragliding.service.CompetitionService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class StartCommand implements Executable {

    /**
     * Logger.
     */
    private Logger logger = LogManager.getLogger("StartCommand");

    private CompetitionService competitionService;

    public StartCommand(final CompetitionService newCompetitionService) {
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
        var competitionCommand =
                new ViewFutureDescriptions(competitionService);
        return competitionCommand.execute(req, resp);
    }
}
