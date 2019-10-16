package by.training.paragliding.controller.command.application;

import by.training.paragliding.bean.entity.Application;
import by.training.paragliding.controller.command.Executable;
import by.training.paragliding.controller.command.ExecutionResult;
import by.training.paragliding.controller.exception.ControllerException;
import by.training.paragliding.service.ApplicationService;
import by.training.paragliding.service.exception.ServiceException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ApplyCompetition implements Executable {
    /**
     * Logger.
     */
    private Logger logger = LogManager.getLogger("main");
    private static final String APPLY_ERROR =
            "Apply error. Application was not added";
    private ApplicationService applicationService;

    public ApplyCompetition(final ApplicationService newApplicationService) {
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
            var sportsmanIdSting = req.getParameter("sportsmanId");
            var sportsmanId = Integer.parseInt(sportsmanIdSting);
            var application = new Application();
            application.setSportsmanId(sportsmanId);
            application.setCompetitionId(competitionId);
            logger.debug("Application {}", application);
            var appList =applicationService.find(ApplicationService.IDENTIFIERS,
                    sportsmanId, competitionId);
            logger.debug("ApplicationList {}", appList);
            if (appList.isEmpty()) {
                if (!applicationService.addApplication(application)) {
                    throw new ControllerException(APPLY_ERROR);
                }
                return new ExecutionResult(false,
                        "/competition.html?id="
                        + competitionId
                        +"&message=You have applied the competition!");
            } else {
                return new ExecutionResult(false,
                        "/competition.html?id="
                        + competitionId
                        +"&message=You had already been"
                        + " applied this competition!");
            }

        } catch (ServiceException newE) {
            throw new ControllerException(newE);
        }

    }
}
