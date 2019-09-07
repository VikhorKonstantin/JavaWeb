package by.training.task4web.controller.command;

import by.training.task4web.controller.exception.ControllerException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface Executable {
    /**
     * Execute command.
     * @param args for service method
     * @param req http request
     * @param resp http response
     * @throws ControllerException if something goes wrong
     * while command execution or request invalid
     */
    void execute(String args, HttpServletRequest req,
                 HttpServletResponse resp) throws ControllerException;
}
