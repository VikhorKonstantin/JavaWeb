package by.training.paragliding.controller.command.user;

import by.training.paragliding.bean.entity.Competition;
import by.training.paragliding.bean.entity.Role;
import by.training.paragliding.bean.entity.User;
import by.training.paragliding.controller.command.Executable;
import by.training.paragliding.controller.command.ExecutionResult;
import by.training.paragliding.controller.exception.ControllerException;
import by.training.paragliding.service.CompetitionService;
import by.training.paragliding.service.SportsmanService;
import by.training.paragliding.service.exception.ServiceException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.EnumSet;
import java.util.List;

public class ViewAccountPage implements Executable {
    /**
     * Logger.
     */
    private Logger logger = LogManager.getLogger("ViewCompetitionList");

    private CompetitionService competitionService;
    private SportsmanService sportsmanService;

    public ViewAccountPage(final CompetitionService newCompetitionService,
                           final SportsmanService newSportsmanService) {
        competitionService = newCompetitionService;
        sportsmanService = newSportsmanService;
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
            if (sessionUser.getRole().equals(Role.REGISTERED_USER)
                    || sessionUser.getRole().equals(Role.ADMIN)) {
                var futureRange = EnumSet.range(Competition.Status.ANNOUNCED,
                        Competition.Status.UNDERWAY);
                List<Competition> futureComps = new ArrayList<>();
                for (var status : futureRange) {
                    futureComps.addAll(competitionService.find(
                            CompetitionService.FindByProps.ORGANIZER_AND_STATUS,
                            sessionUser, status));
                }
                var finishedComps = competitionService.find(
                        CompetitionService.FindByProps.ORGANIZER_AND_STATUS,
                        sessionUser, Competition.Status.FINISHED);
                req.setAttribute("futureComps", futureComps);
                req.setAttribute("finishedComps", finishedComps);
            } else {
                var sportsman = sessionUser.getSportsman();
                var competitions = competitionService.find(
                        CompetitionService.FindByProps.PARTICIPANT,
                        sportsman);
                List<Competition> finishedComps = new ArrayList<>();
                List<Competition> futureComps = new ArrayList<>();
                for (var c : competitions) {
                    if(c.getStatus() == Competition.Status.FINISHED) {
                        finishedComps.add(c);
                    } else {
                        futureComps.add(c);
                    }
                }
                var competitors = sportsmanService.find(
                        SportsmanService.FindByProps.COMPETITORS, sportsman);
                req.setAttribute("competitors", competitors);
                req.setAttribute("futureComps", futureComps);
                req.setAttribute("finishedComps", finishedComps);
            }
            return new ExecutionResult(true,
                    "/WEB-INF/jsp/user/account.jsp");
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
