package by.training.task2threads.controller;

import by.training.task2threads.controller.command.Executable;
import by.training.task2threads.controller.command.FillMatrixCommand;
import by.training.task2threads.controller.command.MultiplyMatricesCommand;

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
        executableMap.put("FILL", new FillMatrixCommand());
        executableMap.put("MULTIPLY", new MultiplyMatricesCommand());
    }

    /**
     * @param name name of command.
     * @return Command.
     */
    Executable getCommand(final String name) {
        return executableMap.get(name.toUpperCase());
    }
}
