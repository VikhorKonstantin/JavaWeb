package by.training.paragliding.controller.command.sportsman;

import by.training.paragliding.controller.command.Executable;
import by.training.paragliding.controller.command.ExecutionResult;
import by.training.paragliding.controller.exception.ControllerException;
import by.training.paragliding.service.SportsmanService;
import by.training.paragliding.service.exception.ServiceException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ViewAllSportsmen implements Executable {
    /**
     * Logger.
     */
    private Logger logger = LogManager.getLogger("main");
    private SportsmanService sportsmanService;

    public ViewAllSportsmen(final SportsmanService newSportsmanService) {
        sportsmanService = newSportsmanService;
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
            var sportsmen = sportsmanService.find("all");
            req.setAttribute("sportsmen", sportsmen);
            return new ExecutionResult(true, "/WEB-INF/jsp/sportsmenList.jsp");
        }
        catch (ServiceException e) {
            throw new ControllerException(e);
        }
    }
}
