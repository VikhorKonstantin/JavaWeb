package by.training.sqltask.controller;

import by.training.sqltask.controller.command.Executable;
import by.training.sqltask.controller.exception.ControllerException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.logging.log4j.LogManager;

/**
 * Provides connection between view and services.
 */
public class Controller {
    /**
     * Command provider.
     */
    private final CommandProvider provider = new CommandProvider();
    /**
     * Delimiter.
     */
    private static final String DELIMITER = " ";
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
            var actionName = req.getParameter("actionName");
            Executable executionCommand = provider.getCommand(actionName);
            executionCommand.execute(req, resp);
        } catch (StringIndexOutOfBoundsException | NullPointerException e) {
            throw new ControllerException(e);
        }

    }
}