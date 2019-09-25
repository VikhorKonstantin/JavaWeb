package by.training.paragliding.controller;

import by.training.paragliding.controller.command.Executable;
import by.training.paragliding.controller.exception.ControllerException;
import by.training.paragliding.service.ServiceFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Provides connection between view and services.
 */
public class Controller {
    /**
     * Logger.
     */
    private final Logger logger = LogManager.getLogger("main");
    /**
     * Command provider.
     */
    private final CommandProvider provider;

    public Controller(final ServiceFactory  newServiceFactory) {
        provider = new CommandProvider(newServiceFactory);
    }

    /**
     * Execute task by request.
     * @param req http request
     * @param resp http response
     * @throws ControllerException if something goes wrong
     * while command execution or request invalid
     */
    public void executeTask(final HttpServletRequest req,
                            final HttpServletResponse resp)
            throws ControllerException {
        try {

			String contextPath = req.getContextPath();
			String uri = req.getRequestURI();
			int beginAction = contextPath.length();
			int endAction = uri.lastIndexOf('.');
			String actionName;
			if(endAction >= 0) {
				actionName = uri.substring(beginAction, endAction);
			} else {
				actionName = uri.substring(beginAction);
			}
            Executable executionCommand = provider.getCommand(actionName);
            executionCommand.execute(req, resp);
        } catch (StringIndexOutOfBoundsException | NullPointerException e) {
            throw new ControllerException(e);
        }

    }
}
