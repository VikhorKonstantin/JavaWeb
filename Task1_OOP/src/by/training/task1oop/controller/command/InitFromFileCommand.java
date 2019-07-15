package by.training.task1oop.controller.command;

import by.training.task1oop.exception.WrongArgumentsException;
import by.training.task1oop.service.InitFromFileService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class InitFromFileCommand implements Executable {
    /**
     * Logger.
     */
    private static Logger logger = LogManager.getLogger();
    /**
     * logger message.
     */
    private static final String LOG_MESSAGE = "Invalid args in line: ";
    /**
     * @param fileName for service method
     * @return response
     */
    @Override
    public String execute(final String fileName) {
        InitFromFileService service = new InitFromFileService();
        try {
            return service.initFromFile(fileName);
        } catch (WrongArgumentsException e) {
            logger.info(LOG_MESSAGE, e);
            return LOG_MESSAGE;
        }
    }
}
