package by.training.paragliding.controller.command.result;

import by.training.paragliding.bean.entity.Competition;
import by.training.paragliding.bean.entity.Result;
import by.training.paragliding.bean.entity.Role;
import by.training.paragliding.bean.entity.User;
import by.training.paragliding.bean.exception.BeanException;
import by.training.paragliding.bean.validator.CompetitionValidator;
import by.training.paragliding.controller.command.Executable;
import by.training.paragliding.controller.command.ExecutionResult;
import by.training.paragliding.controller.exception.ControllerException;
import by.training.paragliding.service.CompetitionService;
import by.training.paragliding.service.ResultService;
import by.training.paragliding.service.SportsmanService;
import by.training.paragliding.service.exception.ServiceException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;

public class SaveResults implements Executable {
    /**
     * Logger.
     */
    private Logger logger = LogManager.getLogger("main");
    private static final String ADD_ERROR =
            "Adding error. result was not added";

    private static final String UPDATE_FAILED = "Updating failed";
    private ResultService resultService;
    private CompetitionService competitionService;
    private SportsmanService sportsmanService;

    public SaveResults(final ResultService newResultService,
                       final CompetitionService newCompetitionService,
                       final SportsmanService newSportsmanService) {
        competitionService = newCompetitionService;
        sportsmanService = newSportsmanService;
        resultService = newResultService;
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
        String competitionIdString = req.getParameter("competitionId");
        int competitionId = Integer.parseInt(competitionIdString);
        try {
            var competition = competitionService.readById(competitionId);
            var participants = sportsmanService.find(
                    SportsmanService.FindByProps.APPLICATION, competition);
            int numberOfParticipants = participants.size();
            String sportsmanIdString;
            String scoreString;
            int sportsmanId;
            int score;
            var results = new ArrayList<Result>(numberOfParticipants);
            for (int i = 0; i < numberOfParticipants; i++) {
                sportsmanIdString = req.getParameter("sportsmanId" + i);
                scoreString = req.getParameter("score" + i);
                sportsmanId = Integer.parseInt(sportsmanIdString);
                score = Integer.parseInt(scoreString);
                results.add(new Result(sportsmanId, competitionId, score));
            }
            for (var res : results) {
                if (!resultService.addResult(res)) {
                    throw new ControllerException(ADD_ERROR);
                }
            }
            competition.setStatus(Competition.Status.FINISHED);
            if (competitionService.update(competition)) {
                req.setAttribute("competition", competition);
                return new ExecutionResult(true,
                        "/WEB-INF/jsp/competitionPage.jsp");
            } else {
                throw new ControllerException(UPDATE_FAILED);
            }
        } catch (ServiceException newE) {
            throw new ControllerException(newE);
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
        if (role.equals(Role.ADMIN)) {
            return true;
        } else {
            if (role.equals(Role.REGISTERED_USER)) {
                var competitionValidator = new CompetitionValidator();
                Competition competition;
                try {
                    competition = competitionValidator.validate(req);
                } catch (BeanException newE) {
                    return false;
                }
                return competition.getOrganizer().getId() ==
                        sessionUser.getId();
            }
            return false;
        }
    }
}
