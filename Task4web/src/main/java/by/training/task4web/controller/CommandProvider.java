package by.training.task4web.controller;

import by.training.task4web.controller.command.Executable;
import by.training.task4web.controller.command.ParseCommand;

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
        executableMap.put("PARSE", new ParseCommand());
    }

    /**
     * @param name name of command.
     * @return Command.
     */
    Executable getCommand(final String name) {
        return executableMap.get(name.toUpperCase());
    }
}
