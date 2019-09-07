package by.training.task4web.controller;

import by.training.task4web.controller.command.Executable;
import by.training.task4web.controller.exception.ControllerException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
     * @param request name of command to execute.
     * @param req http request
     * @param resp http response
     * @throws ControllerException if something goes wrong
     * while command execution or request invalid
     */
    public void executeTask(final String request, final HttpServletRequest req,
                            final HttpServletResponse resp)
            throws ControllerException {
        try {
            final String commandName = request.substring(0,
                    request.indexOf(DELIMITER)).toUpperCase();
            final String args = request.substring(
                    request.indexOf(DELIMITER)).trim();
            Executable executionCommand = provider.getCommand(commandName);
            executionCommand.execute(args, req, resp);
        } catch (StringIndexOutOfBoundsException | NullPointerException e) {
            throw new ControllerException(e);
        }

    }
}
