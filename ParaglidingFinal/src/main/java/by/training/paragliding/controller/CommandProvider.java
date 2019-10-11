package by.training.paragliding.controller;

import by.training.paragliding.controller.command.Executable;
import by.training.paragliding.controller.command.StartCommand;
import by.training.paragliding.controller.command.ViewLogInPage;
import by.training.paragliding.controller.command.ViewSingUpPage;
import by.training.paragliding.controller.command.competition.ViewCompetitionById;
import by.training.paragliding.controller.command.competition.ViewCompetitionList;
import by.training.paragliding.controller.command.competition.ViewCompetitionsEditPage;
import by.training.paragliding.controller.command.sportsman.ViewAllSportsmen;
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
     * commands map.
     */
    private final Map<String, Executable> executableMap =
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
            executableMap.put("/sportsmen/id", new ViewSportsmanById(
                    serviceFactory.createSportsmanService()));
            executableMap.put("/sportsmen/all", new ViewAllSportsmen(
                    serviceFactory.createSportsmanService()));
            executableMap.put("/index", new StartCommand(serviceFactory
                    .createCompetitionService()));
            executableMap.put("/singUp", new ViewSingUpPage());
            executableMap.put("/logIn", new ViewLogInPage());
            executableMap.put("/user/singUp",
                    new SingUp(serviceFactory.createSportsmanService(),
                            serviceFactory.createUserService()));
            executableMap.put("/user/logIn",
                    new LogIn(serviceFactory.createUserService()));
            executableMap.put("/user/logOut", new LogOut());
            executableMap.put("/competition/all",
                    new ViewCompetitionList(
                            serviceFactory.createCompetitionService()));
            executableMap.put("/competition", new ViewCompetitionById(
                    serviceFactory.createCompetitionService()
            ));
            executableMap.put("/competition/edit", new ViewCompetitionsEditPage());
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
    Executable getCommand(final String name) {
        logger.debug("Command name: {}", name);
        return executableMap.get(name);
    }
}
