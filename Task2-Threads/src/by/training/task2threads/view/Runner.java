package by.training.task2threads.view;

import by.training.task2threads.controller.Controller;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Scanner;

public final class Runner {
    /**
     * commands information.
     */
    private static final String COMMANDS_INFO =
            "Command\tArgs\n"
                    + "FILL\tFILENAME\tNUMBER\n"
                    + "MULTIPLY\tFILENAME№1\tFILENAME№2\n";

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
        Controller controller = Controller.getInstance();
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
