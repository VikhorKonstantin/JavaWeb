package by.training.task4xml.controller;

import by.training.task4xml.controller.command.ChooseLocaleAndViewCommands;
import by.training.task4xml.controller.command.Executable;
import by.training.task4xml.controller.command.ParseCommand;

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
        executableMap.put("CHANGE_LOCALE", new ChooseLocaleAndViewCommands());
    }

    /**
     * @param name name of command.
     * @return Command.
     */
    Executable getCommand(final String name) {
        return executableMap.get(name.toUpperCase());
    }
}
