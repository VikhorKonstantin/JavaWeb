package by.training.task1oop.view;

import by.training.task1oop.controller.AirCompanyController;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Scanner;


public final class Main {
    /**
     * commands information.
     */
    private static final String COMMANDS_INFO =
              "Command\tArgs\n"
            + "ADD\tPlaneParams\n"
            + "DEL\tPlaneParams\n"
            + "FIND\tProperty Value\n"
            + "INIT\tFileName\n"
            + "READ\tID\n"
            + "SORT\tProperty\n"
            + "GET_PAYLOAD\t\"\"\n"
            + "GET_SEATING_CAPACITY\t\"\"\n"
            + "UPDATE_PAYLOAD_BY_ID\tID newVal\n"
            + "UPDATE_SEATING_CAPACITY_BY_ID\t ID newVal\n"
            + "EXIT";
    /**
     * Logger.
     */
    private static Logger logger = LogManager.getLogger("STDOUT");
    private Main() {
    }

    /**
     * @param args command line args.
     */
    public static void main(final String[] args) {
        AirCompanyController airCompanyController = new AirCompanyController();
        var scanner = new Scanner(System.in);
        String command;
        String response;
        logger.info(COMMANDS_INFO);
        command = scanner.nextLine().toUpperCase();
        while (!command.equals("EXIT")) {
            response = airCompanyController.executeTask(command);
            logger.info(response);
            command = scanner.nextLine();
        }
    }
}
