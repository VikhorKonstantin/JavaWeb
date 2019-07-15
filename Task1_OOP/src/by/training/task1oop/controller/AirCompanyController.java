package by.training.task1oop.controller;

import by.training.task1oop.controller.command.Executable;

public class AirCompanyController {
    /**
     * Command provider.
     */
    private final CommandProvider provider = new CommandProvider();

    /**
     * @param commandName name of command to execute.
     * @param args arguments for command.
     * @return response.
     */
    public String executeTask(final String commandName, final String args) {
        Executable executionCommand = provider.getCommand(commandName);
        String response;
        response = executionCommand.execute(args);
        return response;
    }
}
