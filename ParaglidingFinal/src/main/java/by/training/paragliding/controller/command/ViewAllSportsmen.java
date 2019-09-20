package by.training.paragliding.controller.command;

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
    /**
     * Execute command.
     * @param req http request
     * @param resp http response
     * @throws ControllerException if something goes wrong
     * while command execution or request invalid
     */
    @Override
    public void execute(final HttpServletRequest req,
                        final HttpServletResponse resp)
            throws ControllerException {
        var sportsmanService = new SportsmanService();
        try {
            var sportsmen = sportsmanService.find("all");
            req.setAttribute("sportsmen", sportsmen);
        }
        catch (ServiceException e) {
            throw new ControllerException(e);
        }
    }
}
