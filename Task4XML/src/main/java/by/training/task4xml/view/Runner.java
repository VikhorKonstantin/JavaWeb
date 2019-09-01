package by.training.task4xml.view;

<<<<<<< HEAD
import by.training.task4xml.service.GemsSaxBuilder;
import by.training.task4xml.service.exception.ServiceException;

public class Runner {
    public static void main(String[] args) {
        GemsSaxBuilder service = new GemsSaxBuilder();
        try {
            var gems = service.buildGemsFromFile("input/gems.xml",
                    "input/gems.xsd");
            gems.forEach(System.out::println);
        } catch (ServiceException e) {
            
=======
import by.training.task4xml.controller.Controller;
import by.training.task4xml.controller.command.StartCommand;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Scanner;

public class Runner {
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
>>>>>>> task4branch
        }
    }
}
