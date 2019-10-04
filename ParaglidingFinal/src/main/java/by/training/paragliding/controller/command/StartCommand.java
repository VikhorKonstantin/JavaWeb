package by.training.paragliding.controller.command;

import by.training.paragliding.controller.exception.ControllerException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class StartCommand implements Executable {

    /**
     * Logger.
     */
    private Logger logger = LogManager.getLogger("main");

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
        return new ExecutionResult(true, "/WEB-INF/jsp/main.jsp");
    }
}
