package by.training.sqltask.controller.command;

import by.training.sqltask.controller.exception.ControllerException;
import by.training.sqltask.service.ViewSportsmanService;
import by.training.sqltask.service.exception.ServiceException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ViewSportsmanByIdCommand implements Executable {
    private Logger logger = LogManager.getLogger("main");
    @Override
    public void execute(final HttpServletRequest req,
                        final HttpServletResponse resp) throws ControllerException {
        var id = req.getParameter("civlId");
        var viewService = new ViewSportsmanService();
        try {
            var sportsman = viewService.readById(Integer.parseInt(id));
            final String logSpMsg = String.format("sportsman: %s", sportsman.toString());
            logger.debug(logSpMsg);
            req.setAttribute("sportsman", sportsman);
        }
        catch (ServiceException e) {
            throw new ControllerException(e);
        }
    }
}
