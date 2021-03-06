package by.training.paragliding.controller.command.user;

import by.training.paragliding.bean.entity.Role;
import by.training.paragliding.bean.entity.Sportsman;
import by.training.paragliding.bean.entity.User;
import by.training.paragliding.bean.exception.BeanException;
import by.training.paragliding.bean.validator.SportsmanValidator;
import by.training.paragliding.bean.validator.UserValidator;
import by.training.paragliding.controller.command.Executable;
import by.training.paragliding.controller.command.ExecutionResult;
import by.training.paragliding.controller.exception.ControllerException;
import by.training.paragliding.service.UserService;
import by.training.paragliding.service.exception.ServiceException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class SingUp implements Executable {
    /**
     * Logger.
     */
    private Logger logger = LogManager.getLogger("SingUp");
    private static final String SIN_GUP_ERROR =
            "SingUp error. User was not added.";


    private final UserService userService;

    public SingUp(final UserService newUserService) {
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
        try {
            UserValidator userValidator = new UserValidator();
            User user = userValidator.validate(req);
            logger.debug("Register user {}", user);
            if (user.getRole().equals(Role.REGISTERED_SPORTSMAN)) {
                var sportsmanBuilder = new SportsmanValidator();
                Sportsman sportsman = sportsmanBuilder.validate(req);
                user.setSportsman(sportsman);
            }
            if (!userService.addUser(user)) {
                throw new ControllerException(SIN_GUP_ERROR);
            }
            return new ExecutionResult(false,
                    "/logIn.html");
        } catch (ServiceException | BeanException newE) {
            throw new ControllerException(newE);
        }
    }
}
