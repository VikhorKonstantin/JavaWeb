package by.training.task1oop.controller;

import by.training.task1oop.controller.command.Executable;

public class AirCompanyController {
    private final CommandProvider provider = new CommandProvider();
    public String executeTask(String commandName, String args) {
        Executable executionCommand = provider.getCommand(commandName);
        String response;
        response = executionCommand.execute(args);
        return response;
    }
}
