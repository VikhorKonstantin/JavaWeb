package by.training.paragliding.controller.command.competition;

import by.training.paragliding.bean.entity.Role;
import by.training.paragliding.bean.entity.User;
import by.training.paragliding.bean.exception.BeanException;
import by.training.paragliding.bean.validator.CompetitionValidator;
import by.training.paragliding.controller.command.Executable;
import by.training.paragliding.controller.command.ExecutionResult;
import by.training.paragliding.controller.exception.ControllerException;
import by.training.paragliding.service.CompetitionService;
import by.training.paragliding.service.exception.ServiceException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class AddCompetition implements Executable {
    /**
     * Logger.
     */
    private Logger logger = LogManager.getLogger("EditCompetition");
    private static final String ADDING_FAILED = "Adding failed";
    private CompetitionService competitionService;

    public AddCompetition(final CompetitionService newCompetitionService) {
        competitionService = newCompetitionService;
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
            var competitionValidator = new CompetitionValidator();
            var competition = competitionValidator.validate(req);
            var sessionUser = (User) req.getSession()
                    .getAttribute("User");
            competition.setOrganizer(sessionUser);
            if (competitionService.addCompetition(competition)) {
                req.setAttribute("competition", competition);
                return new ExecutionResult(true,
                        "/WEB-INF/jsp/competition/competitionPage.jsp");
            } else {
                throw new ControllerException(ADDING_FAILED);
            }
        } catch (BeanException | ServiceException newE) {
            throw new ControllerException(ADDING_FAILED, newE);
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
        var role = sessionUser.getRole();
        return role.equals(Role.ADMIN) || role.equals(Role.REGISTERED_USER);
    }
}
