package by.training.paragliding.controller.command;

import by.training.paragliding.controller.exception.ControllerException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Objects;

public class ViewLogInPage implements Executable {
    private static final String LOGIN_MSG = "login.msg";

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
        var msg = req.getParameter("message");
        req.setAttribute("message",
                Objects.requireNonNullElse(msg,
                        LOGIN_MSG));
        return new ExecutionResult(true,
                "/WEB-INF/jsp/user/logIn.jsp");
    }
}
