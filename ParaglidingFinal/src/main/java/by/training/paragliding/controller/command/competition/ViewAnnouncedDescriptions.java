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
import java.util.HashMap;
import java.util.Map;

public class ViewAnnouncedDescriptions implements Executable {
    /**
     * Logger.
     */
    private Logger logger = LogManager.getLogger("main");

    private CompetitionService competitionService;

    public ViewAnnouncedDescriptions(final CompetitionService newService) {
        competitionService = newService;
    }

    /**
     * Execute command.
     * @param req http request
     * @param resp http response
     * @throws ControllerException if something goes wrong
     * while command execution or request invalid
     * @return ExecutionResult
     */
    @Override
    public ExecutionResult execute(final HttpServletRequest req,
                                   final HttpServletResponse resp)
            throws ControllerException {
        try {
            var competitions = competitionService.find("status",
                    Competition.Status.ANNOUNCED);
            final Map<String, String> descriptions = new HashMap<>();
            competitions.forEach(c -> descriptions.put(c.getName(), c.getDescription()));
            req.setAttribute("descriptions", descriptions);
            return new ExecutionResult(true,
                    "/WEB-INF/jsp/main.jsp");
        }
        catch (ServiceException e) {
            throw new ControllerException(e);
        }
    }
}