package by.training.sqltask.controller.command;

import by.training.sqltask.controller.exception.ControllerException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface Executable {
    /**
     * Execute command.
     * @param req http request
     * @param resp http response
     * @throws ControllerException if something goes wrong
     * while command execution or request invalid
     */
    void execute(HttpServletRequest req,
                 HttpServletResponse resp) throws ControllerException;
}
