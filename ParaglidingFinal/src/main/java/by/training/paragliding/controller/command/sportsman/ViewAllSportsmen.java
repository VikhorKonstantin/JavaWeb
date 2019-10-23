package by.training.paragliding.controller.command.sportsman;

import by.training.paragliding.controller.command.Executable;
import by.training.paragliding.controller.command.ExecutionResult;
import by.training.paragliding.controller.exception.ControllerException;
import by.training.paragliding.controller.PaginationHelper;
import by.training.paragliding.service.SportsmanService;
import by.training.paragliding.service.exception.ServiceException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ViewAllSportsmen implements Executable {
    private static final int ON_PAGE = 5;
    /**
     * Logger.
     */
    private Logger logger = LogManager.getLogger("ViewAllSportsmen");
    private SportsmanService sportsmanService;

    public ViewAllSportsmen(final SportsmanService newSportsmanService) {
        sportsmanService = newSportsmanService;
    }

    /**
     * Execute command.
     *
     * @param req  http request
     * @param resp http response
     * @return ExecutionResult
     * @throws ControllerException if something goes wrong
     *                             while command execution or request invalid
     */
    @Override
    public ExecutionResult execute(final HttpServletRequest req,
                                   final HttpServletResponse resp)
            throws ControllerException {
        try {
            var size = sportsmanService.size();
            var offset = PaginationHelper
                    .calcOffsetAndSetPageIndex(req, size, ON_PAGE);
            var sportsmen = sportsmanService.find(
                    SportsmanService.FindByProps.ALL,
                    ON_PAGE, offset);
            req.setAttribute("sportsmen", sportsmen);
            req.setAttribute("pagable", true);
            return new ExecutionResult(true,
                    "/WEB-INF/jsp/user/sportsmenList.jsp");
        } catch (ServiceException e) {
            throw new ControllerException(e);
        }
    }
}
