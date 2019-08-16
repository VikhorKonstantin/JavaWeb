package by.training.task3composite.view;

import by.training.task3composite.controller.Controller;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Scanner;

public final class Runner {
    /**
     * commands information.
     */
    private static final String COMMANDS_INFO =
            "Command\tArgs\n" 
            + "PARSE\tFILENAME\n"
            +"SORT_PARAGRAPH\tFILENAME\n"
            +"SORT_WORDS\tFILENAME\n";

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
        Controller controller = new Controller();
        var scanner = new Scanner(System.in);
        String command;
        String response;
        logger.info(COMMANDS_INFO);
        command = scanner.nextLine().toUpperCase();
        while (!command.equals("EXIT")) {
            response = controller.executeTask(command);
            logger.info(response);
            command = scanner.nextLine();
        }
    }
}
