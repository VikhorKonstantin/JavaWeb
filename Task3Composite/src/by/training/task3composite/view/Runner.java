package by.training.task3composite.view;

import by.training.task3composite.controller.Controller;
import by.training.task3composite.controller.command.StartCommand;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Scanner;

public final class Runner {
    /**
     * Logger.
     */
    private static Logger logger = LogManager.getLogger("STDOUT");
    private Runner() {
    }

    /**
     * @param args command line args.
     */
    public static void main(final String[] args) {
        startDialog();
    }
    private static void startDialog() {
        Controller controller = new Controller();
        var scanner = new Scanner(System.in);
        String userInput;
        String response;
        StartCommand startCommand = new StartCommand();
        var startInfo = startCommand.execute("");
        logger.info(startInfo);
        userInput = scanner.nextLine();
        while (!userInput.equals("EXIT")) {
            response = controller.executeTask(userInput);
            logger.info(response);
            userInput = scanner.nextLine();
        }
    }
}
