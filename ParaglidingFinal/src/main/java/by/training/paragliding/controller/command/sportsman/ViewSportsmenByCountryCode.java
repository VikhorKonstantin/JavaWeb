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

public class ViewSportsmenByCountryCode implements Executable {
    /**
     * Logger.
     */
    private Logger logger = LogManager.getLogger("main");

    private SportsmanService sportsmanService;

    public ViewSportsmenByCountryCode(final SportsmanService newService) {
        sportsmanService = newService;
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
        var countryCode = req.getParameter("countryCode");
        try {
            var sportsmen = sportsmanService.find(
                    SportsmanService.FindByProps.COUNTRY_CODE,
                    countryCode);
            req.setAttribute("sportsman", sportsmen);
            return new ExecutionResult(true,
                    "/WEB-INF/jsp/main.jsp");
        }
        catch (ServiceException e) {
            throw new ControllerException(e);
        }
    }
}
