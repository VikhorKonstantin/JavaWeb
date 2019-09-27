package by.training.paragliding.controller;

import by.training.paragliding.controller.command.Executable;
import by.training.paragliding.controller.command.StartCommand;
import by.training.paragliding.controller.command.sportsman.ViewAllSportsmen;
import by.training.paragliding.controller.command.sportsman.ViewSportsmanById;
import by.training.paragliding.service.ServiceFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.HashMap;
import java.util.Map;

/**
 * Provides access to commands by CommandName.
 */
final class CommandProvider {
    /**
     * Logger.
     */
    private final Logger logger = LogManager.getLogger("main");
    /**
     * commands map.
     */
    private final Map<String, Executable> executableMap = new HashMap<>();
    /**
     * init executableMap.
     */
    CommandProvider(final ServiceFactory newServiceFactory) {
        executableMap.put("/sportsmen/id", new ViewSportsmanById(
                newServiceFactory.createSportsmanService()));
        executableMap.put("/sportsmen/all", new ViewAllSportsmen(
                newServiceFactory.createSportsmanService()));
        executableMap.put("/index", new StartCommand(
                newServiceFactory.createSportsmanService()));
    }

    /**
     * @param name name of command.
     * @return Command.
     */
    Executable getCommand(final String name) {
        return executableMap.get(name);
    }
}
