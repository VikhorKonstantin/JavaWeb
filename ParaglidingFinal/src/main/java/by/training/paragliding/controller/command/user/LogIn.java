package by.training.paragliding.controller.command.user;

import by.training.paragliding.bean.entity.User;
import by.training.paragliding.controller.command.Executable;
import by.training.paragliding.controller.command.ExecutionResult;
import by.training.paragliding.controller.exception.ControllerException;
import by.training.paragliding.service.UserService;
import by.training.paragliding.service.exception.ServiceException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LogIn implements Executable {
    /**
     * Logger.
     */
    private Logger logger = LogManager.getLogger("LogIn");
    private final UserService userService;

    public LogIn(final UserService newUserService) {
        userService = newUserService;
    }

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
        var email = req.getParameter("email");
        var password = req.getParameter("password");
        try {
            User user = userService.readByLoginAndPassword(email, password);
            if (user != null) {
                req.getSession().setAttribute("User", user);
                logger.debug("Session user: {}", user);
                return new ExecutionResult(
                        false, "/index.html");
            } else {
                return new ExecutionResult(false,
                        "/logIn.html?message=Wrong  login/password");
            }
        } catch (ServiceException newE) {
            throw new ControllerException(newE);
        }

    }
}
