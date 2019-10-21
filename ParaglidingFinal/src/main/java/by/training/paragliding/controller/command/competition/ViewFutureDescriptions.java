package by.training.paragliding.controller.command.competition;

import by.training.paragliding.bean.entity.Competition;
import by.training.paragliding.controller.command.Executable;
import by.training.paragliding.controller.command.ExecutionResult;
import by.training.paragliding.controller.exception.ControllerException;
import by.training.paragliding.service.CompetitionService;
import by.training.paragliding.service.exception.ServiceException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.EnumSet;
import java.util.LinkedList;
import java.util.List;

public class ViewFutureDescriptions implements Executable {
    /**
     * Logger.
     */
    private Logger logger = LogManager.getLogger("ViewAnnouncedDescriptions");

    private CompetitionService competitionService;

    public ViewFutureDescriptions(final CompetitionService newService) {
        competitionService = newService;
    }

    /**
     * Execute command.
     *
     * @param req  http request
     * @param resp http response
     * @return ExecutionResult
     * @throws ControllerException if something goes wrong
     *                             while command execution or request invalid
     */
    @Override
    public ExecutionResult execute(final HttpServletRequest req,
                                   final HttpServletResponse resp)
            throws ControllerException {
        try {
            var futureRange = EnumSet.range(Competition.Status.ANNOUNCED,
                    Competition.Status.UNDERWAY);
            List<Competition> competitions = new LinkedList<>();
            for (var status : futureRange) {
                competitions.addAll(competitionService.find(
                        CompetitionService.FindByProps.STATUS,
                        status));
            }
            req.setAttribute("competitions", competitions);
            return new ExecutionResult(true,
                    "/WEB-INF/jsp/main.jsp");
        } catch (ServiceException e) {
            throw new ControllerException(e);
        }
    }
}
