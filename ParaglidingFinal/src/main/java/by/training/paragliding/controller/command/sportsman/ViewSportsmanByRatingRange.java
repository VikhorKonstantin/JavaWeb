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

public class ViewSportsmanByRatingRange implements Executable {
    /**
     * Logger.
     */
    private Logger logger = LogManager.getLogger("main");
    private SportsmanService sportsmanService;

    public ViewSportsmanByRatingRange(
            final SportsmanService newSportsmanService) {
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
        var leftBoundString = req.getParameter("leftBound");
        var rightBoundString = req.getParameter("rightBound");
        try {
            var sportsmen = sportsmanService.find(SportsmanService.RATING_RANGE,
                    Float.parseFloat(leftBoundString),
                    Float.parseFloat(rightBoundString));
            req.setAttribute("sportsmen", sportsmen);
            return new ExecutionResult(true,
                    "/WEB-INF/jsp/main.jsp");
        } catch (ServiceException e) {
            throw new ControllerException(e);
        }
    }
}
