package by.training.paragliding.controller.command.user;

import by.training.paragliding.bean.entity.Competition;
import by.training.paragliding.bean.entity.User;
import by.training.paragliding.controller.command.Executable;
import by.training.paragliding.controller.command.ExecutionResult;
import by.training.paragliding.controller.exception.ControllerException;
import by.training.paragliding.service.CompetitionService;
import by.training.paragliding.service.UserService;
import by.training.paragliding.service.exception.ServiceException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ViewAccountPage implements Executable {
    /**
     * Logger.
     */
    private Logger logger = LogManager.getLogger("ViewCompetitionList");

    private CompetitionService competitionService;
    private UserService userService;

    public ViewAccountPage(final CompetitionService newCompetitionService,
                           final UserService newUserService) {
        competitionService = newCompetitionService;
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
            var sessionUser =
                    (User) req.getSession().getAttribute("User");

            var futureComps = competitionService.find(
                    CompetitionService.FindByProps.ORGANIZER_AND_STATUS,
                    sessionUser,
                    Competition.Status.ANNOUNCED);
            var finishedComps = competitionService.find(
                    CompetitionService.FindByProps.ORGANIZER_AND_STATUS,
                    sessionUser,
                    Competition.Status.FINISHED);
            req.setAttribute("futureComps", futureComps);
            req.setAttribute("finishedComps", finishedComps);
            return new ExecutionResult(true,
                    "/WEB-INF/jsp/account.jsp");
        } catch (ServiceException e) {
            throw new ControllerException(e);
        }
    }

    /**
     * Returns true(false) if command execution
     * allowed(not allowed) for current user.
     *
     * @param req <tt>HttpServletRequest</tt>
     *            using for getting session and request attributes.
     * @return true(false) if command execution allowed(not allowed) for current user.
     */
    @Override
    public boolean isAllowed(final HttpServletRequest req) {
        var sessionUser = (User) req.getSession().getAttribute("User");
        return sessionUser != null;
    }
}
