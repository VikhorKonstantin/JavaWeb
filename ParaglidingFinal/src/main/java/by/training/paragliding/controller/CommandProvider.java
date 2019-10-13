package by.training.paragliding.controller;

import by.training.paragliding.controller.command.Executable;
import by.training.paragliding.controller.command.StartCommand;
import by.training.paragliding.controller.command.ViewLogInPage;
import by.training.paragliding.controller.command.ViewSingUpPage;
import by.training.paragliding.controller.command.application.ApplyCompetition;
import by.training.paragliding.controller.command.competition.EditCompetition;
import by.training.paragliding.controller.command.competition.ViewCompetitionById;
import by.training.paragliding.controller.command.competition.ViewCompetitionList;
import by.training.paragliding.controller.command.competition.ViewCompetitionsEditPage;
import by.training.paragliding.controller.command.sportsman.ViewAllSportsmen;
import by.training.paragliding.controller.command.sportsman.ViewParticipants;
import by.training.paragliding.controller.command.sportsman.ViewSportsmanById;
import by.training.paragliding.controller.command.user.LogIn;
import by.training.paragliding.controller.command.user.LogOut;
import by.training.paragliding.controller.command.user.SingUp;
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
    private final Logger logger = LogManager.getLogger("main");
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
     * init executableMap.
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
                            serviceFactory.createCompetitionService(),
                            serviceFactory.createSportsmanService()));
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


