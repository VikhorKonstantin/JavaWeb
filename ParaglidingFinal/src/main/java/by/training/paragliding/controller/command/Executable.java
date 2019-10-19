package by.training.paragliding.controller.command;

import by.training.paragliding.controller.exception.ControllerException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface Executable {
    /**
     * Execute command.
     *
     * @param req  http request
     * @param resp http response
     * @return execution result
     * @throws ControllerException if something goes wrong
     *                             while command execution or request invalid
     */
    ExecutionResult execute(HttpServletRequest req,
                            HttpServletResponse resp) throws ControllerException;

    /**
     * Returns true(false) if command execution
     * allowed(not allowed) for current user.
     *
     * @param req <tt>HttpServletRequest</tt>
     *            using for getting session and request attributes.
     * @return true(false) if command execution allowed(not allowed) for current user.
     */
    default boolean isAllowed(HttpServletRequest req) {
        return true;
    }

}
