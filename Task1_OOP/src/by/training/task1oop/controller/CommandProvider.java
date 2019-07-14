package by.training.task1oop.controller;

import by.training.task1oop.controller.command.*;

import java.util.HashMap;
import java.util.Map;

public class CommandProvider {
    private final Map<String, Executable> executableMap = new HashMap<>();

    public CommandProvider() {
        executableMap.put("ADD", new AddPlaneCommand());
        executableMap.put("DEL", new DeletePlaneCommand());
        executableMap.put("FIND", new FindCommdand());
        executableMap.put("INIT", new InitFromFileCommand());
        executableMap.put("READ", new ReadByIdCommand());
        executableMap.put("SORT", new SortCommand());
    }
    Executable getCommand(String name) {
        return executableMap.get(name.toUpperCase());
    }
}
