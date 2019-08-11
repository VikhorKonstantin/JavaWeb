package by.training.task2thread.controller;

import by.training.task2thread.controller.command.Executable;

import java.util.concurrent.locks.ReentrantLock;

public final class Controller {
    /**
     * Wrong request message.
     */
    private static final String WRONG_REQUEST = "Wrong request";
    /**
     * Delimiter.
     */
    private static final String DELIMITER = " ";
    /**
     * Instance.
     */
    private static Controller instance = null;
    /**
     * Lock for synchronization.
     */
    private static ReentrantLock lock = new ReentrantLock();
    /**
     * Command provider.
     */
    private static final CommandProvider PROVIDER = new CommandProvider();

    /**
     * Prevents using default constructor.
     */
    private Controller() {
    }

    /**
     * Returns instance of controller.
     * @return instance
     */
    public static Controller getInstance() {
        lock.lock();
        try {
            if (instance == null) {
                instance = new Controller();
            }
        } finally {
            lock.unlock();
        }
        return instance;
    }

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
            Executable executionCommand = PROVIDER.getCommand(commandName);
            String response;
            response = executionCommand.execute(args);
            return response;
        } catch (StringIndexOutOfBoundsException | NullPointerException e) {
            return WRONG_REQUEST;
        }

    }
}
