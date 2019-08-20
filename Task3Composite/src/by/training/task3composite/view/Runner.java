package by.training.task3composite.view;

import by.training.task3composite.controller.Controller;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Locale;
import java.util.ResourceBundle;
import java.util.Scanner;
import java.util.StringJoiner;

public final class Runner {
    /**
     * Choose language message.
     */
    private static final String CHOOSE_LANG =
            "1 - английский\n2 - белорусский\nЛюбой - русский";
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
        logger.info(CHOOSE_LANG);
        String chosenNumber = scanner.nextLine();
        String country = "";
        String language = "";
        switch (chosenNumber) {
            case "1":
                country = "US";
                language = "EN";
                break;
            case "2":
                country = "BY";
                language = "BE";
                break;
            default:
        }
        Locale current = new Locale(language, country);
        ResourceManager.INSTANCE.changeResource(current);
        final String commandsInfo = createCommandsInfo();
        logger.info(commandsInfo);
        command = scanner.nextLine();
        while (!command.equals("EXIT")) {
            response = controller.executeTask(command);
            logger.info(response);
            command = scanner.nextLine();
        }
    }

    private static String createCommandsInfo() {
        ResourceManager resourceManager = ResourceManager.INSTANCE;
        final String startInfoKey = "startInfo";
        final String startInfo = resourceManager.getString(startInfoKey);
        final String fileNameKey = "nameOfFile";
        final String fileName = resourceManager.getString(fileNameKey);
        final String charKey = "character";
        final String character = resourceManager.getString(charKey);
        StringJoiner joiner = new StringJoiner("\n");
        joiner.add(startInfo).add("PARSE " + fileName)
                .add("SORT_PARAGRAPH\t" + fileName)
                .add("SORT_SENTENCES\t" + fileName)
                .add("SORT_WORDS\t" + fileName)
                .add("SORT_LEXEMES\t" + fileName + '\t' + character);
        return joiner.toString();
    }

    private enum ResourceManager {
        /**
         * Instance.
         */
        INSTANCE;
        /**
         * Base name of resources.
         */
        private final String resourceName = "property.text";
        /**
         * Instance of ResourceBundle.
         */
        private ResourceBundle resourceBundle;

        /**
         * Creates new ResourceManager.
         */
        ResourceManager() {
            resourceBundle = ResourceBundle.getBundle(resourceName,
                    Locale.getDefault());
        }

        /**
         * Changes locale.
         *
         * @param locale new locale
         */
        public void changeResource(final Locale locale) {
            resourceBundle = ResourceBundle.getBundle(resourceName, locale);
        }

        /**
         * Returns String by key.
         *
         * @param key key of required String
         * @return String
         */
        public String getString(final String key) {
            return resourceBundle.getString(key);
        }
    }
}
