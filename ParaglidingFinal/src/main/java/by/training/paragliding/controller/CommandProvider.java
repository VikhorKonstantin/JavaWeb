package by.training.paragliding.controller;

import by.training.paragliding.controller.command.Executable;
import by.training.paragliding.controller.command.ViewAllSportsmen;
import by.training.paragliding.controller.command.ViewSportsmanById;

import java.util.HashMap;
import java.util.Map;

/**
 * Provides access to commands by CommandName.
 */
final class CommandProvider {
    /**
     * commands map.
     */
    private final Map<String, Executable> executableMap = new HashMap<>();

    /**
     * init executableMap.
     */
    CommandProvider() {
        executableMap.put("READ_BY_ID", new ViewSportsmanById());
        executableMap.put("READ_ALL", new ViewAllSportsmen());
    }

    /**
     * @param name name of command.
     * @return Command.
     */
    Executable getCommand(final String name) {
        return executableMap.get(name.toUpperCase());
    }
}
