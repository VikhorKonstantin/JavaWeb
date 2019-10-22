package by.training.paragliding.controller;

import by.training.paragliding.controller.command.*;
import by.training.paragliding.controller.command.application.ApplyCompetition;
import by.training.paragliding.controller.command.application.DeleteApplication;
import by.training.paragliding.controller.command.competition.*;
import by.training.paragliding.controller.command.result.SaveResults;
import by.training.paragliding.controller.command.result.ViewCompetitionResults;
import by.training.paragliding.controller.command.result.ViewResultsForm;
import by.training.paragliding.controller.command.sportsman.ViewAllSportsmen;
import by.training.paragliding.controller.command.sportsman.ViewParticipants;
import by.training.paragliding.controller.command.sportsman.ViewSportsmanById;
import by.training.paragliding.controller.command.user.LogIn;
import by.training.paragliding.controller.command.user.LogOut;
import by.training.paragliding.controller.command.user.SingUp;
import by.training.paragliding.controller.command.user.ViewAccountPage;
import by.training.paragliding.controller.exception.ControllerException;
import by.training.paragliding.service.ServiceFactory;
import by.training.paragliding.service.exception.ServiceException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.HashMap;
import java.util.Map;

/**
 * Provides access to commands by CommandName.
 */
final class CommandProvider implements AutoCloseable {
    /**
     * Logger.
     */
    private final Logger logger = LogManager.getLogger("CommandProvider");
    /**
     * Post commands map.
     */
    private final Map<String, Executable> postMap =
            new HashMap<>();
    /**
     * Get commands map.
     */
    private final Map<String, Executable> getMap =
            new HashMap<>();

    /**
     * Service factory.
     */
    private ServiceFactory serviceFactory;

    /**
     * Init executableMap with services created by serviceFactory.
     *
     * @param newServiceFactory serviceFactory used
     *                          to create new service instances
     * @throws ControllerException if ServiceException was thrown
     *                             while creating new service instances
     */
    CommandProvider(final ServiceFactory newServiceFactory)
            throws ControllerException {
        serviceFactory = newServiceFactory;
        try {
            getMap.put("/sportsmen/id", new ViewSportsmanById(
                    serviceFactory.createSportsmanService()));
            getMap.put("/sportsmen/all", new ViewAllSportsmen(
                    serviceFactory.createSportsmanService()));
            getMap.put("/index", new StartCommand(serviceFactory
                    .createCompetitionService()));
            getMap.put("/singUp", new ViewSingUpPage());
            getMap.put("/logIn", new ViewLogInPage());
            postMap.put("/user/singUp",
                    new SingUp(serviceFactory.createUserService()));
            postMap.put("/user/logIn",
                    new LogIn(serviceFactory.createUserService()));
            getMap.put("/user/logOut", new LogOut());
            getMap.put("/competition/all",
                    new ViewCompetitionList(
                            serviceFactory.createCompetitionService()));
            getMap.put("/competition", new ViewCompetitionById(
                    serviceFactory.createCompetitionService()
            ));
            getMap.put("/competition/edit", new ViewCompetitionsEditPage(
                    serviceFactory.createCompetitionService()
            ));
            postMap.put("/competition/edit", new EditCompetition(
                    serviceFactory.createCompetitionService()
            ));
            getMap.put("/sportsmen/participants", new ViewParticipants(
                    serviceFactory.createCompetitionService(),
                    serviceFactory.createSportsmanService()));
            postMap.put("/application",
                    new ApplyCompetition(
                            serviceFactory.createApplicationService(),
                            serviceFactory.createCompetitionService()));
            getMap.put("/resultsPage", new ViewResultsForm(
                    serviceFactory.createCompetitionService(),
                    serviceFactory.createSportsmanService()));
            postMap.put("/results", new SaveResults(
                    serviceFactory.createResultService(),
                    serviceFactory.createCompetitionService(),
                    serviceFactory.createSportsmanService()
            ));
            getMap.put("/results", new ViewCompetitionResults(
                    serviceFactory.createResultService(),
                    serviceFactory.createCompetitionService(),
                    serviceFactory.createSportsmanService()
            ));
            postMap.put("/localeChange", new ChangeLocale());
            getMap.put("/user/account", new ViewAccountPage(
                    serviceFactory.createCompetitionService(),
                    serviceFactory.createSportsmanService()));
            postMap.put("/competition/add", new AddCompetition(
                    serviceFactory.createCompetitionService()
            ));
            postMap.put("/application/del", new DeleteApplication(
                    serviceFactory.createApplicationService()
            ));
        } catch (ServiceException newE) {
            throw new ControllerException(newE);
        }
    }

    @Override
    public void close() throws Exception {
        serviceFactory.close();
    }

    /**
     * @param name name of command.
     * @return Command.
     */
    Executable getPostCommand(final String name) {
        logger.debug("Command name: {}", name);
        return postMap.get(name);
    }

    /**
     * @param name name of command.
     * @return Command.
     */
    Executable getGetCommand(final String name) {
        logger.debug("Command name: {}", name);
        return getMap.get(name);
    }
}


