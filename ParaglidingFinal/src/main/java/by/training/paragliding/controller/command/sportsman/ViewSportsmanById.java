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

public class ViewSportsmanById implements Executable {
    /**
     * Logger.
     */
    private Logger logger = LogManager.getLogger("main");
    private SportsmanService sportsmanService;

    public ViewSportsmanById(final SportsmanService newSportsmanService) {
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
        var id = req.getParameter("civlId");
        try {
            var sportsman = sportsmanService.readById(Integer.parseInt(id));
            final String logSpMsg = String.format("sportsman: %s",
                    sportsman.toString());
            logger.debug(logSpMsg);
            req.setAttribute("sportsman", sportsman);
            return new ExecutionResult(true, "/index.jsp");
        }
        catch (ServiceException e) {
            throw new ControllerException(e);
        }
    }
}
