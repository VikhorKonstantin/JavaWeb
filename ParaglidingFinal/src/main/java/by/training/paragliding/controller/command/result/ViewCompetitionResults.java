package by.training.paragliding.controller.command.result;

import by.training.paragliding.bean.entity.Sportsman;
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
import java.util.HashMap;

public class ViewCompetitionResults implements Executable {
    /**
     * Logger.
     */
    private Logger logger = LogManager.getLogger("main");
    private ResultService resultService;
    private CompetitionService competitionService;
    private SportsmanService sportsmanService;

    public ViewCompetitionResults(final ResultService newResultService,
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
        String competitionIdString =  req.getParameter("competitionId");
        int competitionId = Integer.parseInt(competitionIdString);
        try {
            var competition = competitionService.readById(competitionId);
            var participants = sportsmanService.find(
                    SportsmanService.FindByProps.APPLICATION, competition);
            var resultData = new HashMap<Sportsman, Integer>(
                    participants.size());
            for (var p : participants) {
                var resultList = resultService.find(
                        ResultService.FindByProps.IDENTIFIERS, p.getCivlId(),
                        competitionId);
                if (!resultList.isEmpty()) {
                    resultData.put(p, resultList.get(0).getScore());
                }
            }
            req.setAttribute("resultsData", resultData);
            return new ExecutionResult(true,
                    "/WEB-INF/jsp/resultsPage.jsp");
        } catch (ServiceException newE) {
            throw new ControllerException(newE);
        }
    }
}
