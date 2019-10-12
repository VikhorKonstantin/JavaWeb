package by.training.paragliding.controller.command.competition;

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

public class EditCompetition implements Executable {
    /**
     * Logger.
     */
    private Logger logger = LogManager.getLogger("main");
    private static final String UPDATE_FAILED = "Update failed";
    private CompetitionService competitionService;

    public EditCompetition(final CompetitionService newCompetitionService) {
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
            if (competitionService.update(competition)) {
                req.setAttribute("competition", competition);
                return new ExecutionResult(true,
                        "/WEB-INF/jsp/competitionPage.jsp");
            } else {
                throw new ControllerException(UPDATE_FAILED);
            }
        } catch (BeanException | ServiceException newE) {
            throw new ControllerException(UPDATE_FAILED, newE);
        }
    }
}
