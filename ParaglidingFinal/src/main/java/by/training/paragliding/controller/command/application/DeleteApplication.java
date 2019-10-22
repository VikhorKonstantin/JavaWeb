package by.training.paragliding.controller.command.application;

import by.training.paragliding.bean.entity.Application;
import by.training.paragliding.bean.entity.Role;
import by.training.paragliding.bean.entity.User;
import by.training.paragliding.controller.command.Executable;
import by.training.paragliding.controller.command.ExecutionResult;
import by.training.paragliding.controller.exception.ControllerException;
import by.training.paragliding.service.ApplicationService;
import by.training.paragliding.service.exception.ServiceException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class DeleteApplication implements Executable {
    /**
     * Logger.
     */
    private Logger logger = LogManager.getLogger("DeleteApplication");
    private static final String REFERER_HEADER = "Referer";
    private ApplicationService applicationService;

    public DeleteApplication(final ApplicationService newApplicationService) {
        applicationService = newApplicationService;
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
            var competitionIdSting = req.getParameter("competitionId");
            var competitionId = Integer.parseInt(competitionIdSting);
            var sessionUser =
                    (User) req.getSession().getAttribute("User");
            var sportsman = sessionUser.getSportsman();
            var application = new Application();
            application.setSportsmanId(sportsman.getCivlId());
            application.setCompetitionId(competitionId);
            applicationService.deleteApplication(application);
            String referer = req.getHeader(REFERER_HEADER);
            var contextPath = req.getContextPath();
            referer = referer.substring(
                    referer.lastIndexOf(contextPath) + contextPath.length());

            return new ExecutionResult(false,
                    referer);
        } catch (ServiceException newE) {
            throw new ControllerException(newE);
        }
    }

    /**
     * Returns true(false) if command execution allowed(not allowed) for current user.
     *
     * @return true(false) if command execution allowed(not allowed) for current user.
     */
    @Override
    public boolean isAllowed(final HttpServletRequest req) {
        var sessionUser = (User) req.getSession().getAttribute("User");
        return sessionUser != null
                && sessionUser.getRole().equals(Role.REGISTERED_SPORTSMAN);
    }
}