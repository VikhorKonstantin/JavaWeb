package by.training.paragliding.controller;

import by.training.paragliding.controller.command.Executable;
import by.training.paragliding.controller.command.ExecutionResult;
import by.training.paragliding.controller.exception.ControllerException;
import by.training.paragliding.service.ServiceFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Provides connection between view and services.
 */
public class Controller implements AutoCloseable {
    /**
     * Logger.
     */
    private final Logger logger = LogManager.getLogger("Controller");
    /**
     * Command provider.
     */
    private final CommandProvider provider;

    public Controller(final ServiceFactory newServiceFactory)
            throws ControllerException {
        provider = new CommandProvider(newServiceFactory);
    }

    /**
     * Execute task by request.
     *
     * @param req  http request
     * @param resp http response
     * @return ExecutionResult
     * @throws ControllerException if something goes wrong
     *                             while command execution or request invalid
     */
    public ExecutionResult executeTask(final HttpServletRequest req,
                                       final HttpServletResponse resp)
            throws ControllerException {
        try {

            String contextPath = req.getContextPath();
            String uri = req.getRequestURI();
            int beginAction = contextPath.length();
            int endAction = uri.lastIndexOf('.');
            String actionName;
            if (endAction >= 0) {
                actionName = uri.substring(beginAction, endAction);
            } else {
                actionName = uri.substring(beginAction);
            }
            Executable executionCommand;
            switch (req.getMethod()) {
                case "POST" :
                    executionCommand = provider.getPostCommand(actionName);
                    break;
                case  "GET" :
                    executionCommand = provider.getGetCommand(actionName);
                    break;
                default:
                    throw new ControllerException("Unsupported Method");
            }

            if (executionCommand.isAllowed(req)) {
                var commandDbgMsg =
                        String.format("Execution command:%s", executionCommand);
                logger.debug(commandDbgMsg);
                return executionCommand.execute(req, resp);
            } else {
                throw new ControllerException(
                        "You have no access to this action!!");
            }
        } catch (StringIndexOutOfBoundsException | NullPointerException e) {
            throw new ControllerException(e);
        }

    }

    @Override
    public void close() throws Exception {
        provider.close();
    }
}
