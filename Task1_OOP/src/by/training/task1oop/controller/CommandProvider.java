package by.training.task1oop.controller;

import by.training.task1oop.controller.command.AddPlaneCommand;
import by.training.task1oop.controller.command.DeletePlaneCommand;
import by.training.task1oop.controller.command.Executable;
import by.training.task1oop.controller.command.FindCommand;
import by.training.task1oop.controller.command.InitFromFileCommand;
import by.training.task1oop.controller.command.ReadByIdCommand;
import by.training.task1oop.controller.command.SortCommand;

import java.util.HashMap;
import java.util.Map;

final class CommandProvider {
    /**
     * commands map.
     */
    private final Map<String, Executable> executableMap = new HashMap<>();

    /**
     * init executableMap.
     */
    CommandProvider() {
        executableMap.put("ADD", new AddPlaneCommand());
        executableMap.put("DEL", new DeletePlaneCommand());
        executableMap.put("FIND", new FindCommand());
        executableMap.put("INIT", new InitFromFileCommand());
        executableMap.put("READ", new ReadByIdCommand());
        executableMap.put("SORT", new SortCommand());
    }

    /**
     * @param name name of command.
     * @return Command.
     */
    Executable getCommand(final String name) {
        return executableMap.get(name.toUpperCase());
    }
}
