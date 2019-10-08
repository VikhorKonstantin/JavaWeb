package by.training.paragliding.controller.command.user;

import by.training.paragliding.bean.entity.Role;
import by.training.paragliding.bean.entity.User;
import by.training.paragliding.controller.command.Executable;
import by.training.paragliding.controller.command.ExecutionResult;
import by.training.paragliding.controller.exception.ControllerException;
import by.training.paragliding.service.SportsmanService;
import by.training.paragliding.service.UserService;
import by.training.paragliding.service.exception.ServiceException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class SingUp implements Executable {

    private final SportsmanService sportsmanService;

    private final UserService userService;

    public SingUp(final SportsmanService newSportsmanService,
                  final UserService newUserService) {
        sportsmanService = newSportsmanService;
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
            String login = req.getParameter("email");
            String password = req.getParameter("password");
            boolean isSportsman =
                    Boolean.parseBoolean(req.getParameter("isSportsman"));
            Role role;
            if(isSportsman) {
                role = Role.REGISTERED_SPORTSMAN;
            } else {
                role = Role.REGISTERED_USER;
            }
            User user = new User();
            user.setRole(role);
            user.setEmail(login);
            user.setPassword(password);
            userService.addUser(user);
            //todo: ask about REDIRECTION!!!!
            return new ExecutionResult(true, "/index.html");
        } catch (ServiceException newE) {
            throw new ControllerException(newE);
        }
    }
}
