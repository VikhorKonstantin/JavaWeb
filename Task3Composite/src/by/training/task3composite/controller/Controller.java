package by.training.task3composite.controller;

import by.training.task3composite.controller.command.Executable;

public class Controller {
    /**
     * Command provider.
     */
    private final CommandProvider provider = new CommandProvider();

    /**
     * Wrong request message.
     */
    private static final String WRONG_REQUEST = "Wrong request";
    /**
     * Delimiter.
     */
    private static final String DELIMITER = " ";
    /**
     * @param request name of command to execute.
     * @return response.
     */
    public String executeTask(final String request) {
        try {
            final String commandName = request.substring(0,
                    request.indexOf(DELIMITER));
            final String args = request.substring(
                    request.indexOf(DELIMITER)).trim();
            Executable executionCommand = provider.getCommand(commandName);
            String response;
            response = executionCommand.execute(args);
            return response;
        } catch (StringIndexOutOfBoundsException | NullPointerException e) {
            return WRONG_REQUEST;
        }

    }
}
