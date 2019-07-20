package by.training.task1oop.controller.command;

import by.training.task1oop.service.InitFromFileService;
import by.training.task1oop.service.exception.ServiceException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class InitFromFileCommand implements Executable {
    /**
     * Logger.
     */
    private static Logger logger = LogManager.getLogger();
    /**
     * success message.
     */
    private static final String SUCCESS = "Was initialized successfully";
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
            service.initFromFile(fileName);
            return SUCCESS;
        } catch (ServiceException e) {
            logger.info(LOG_MESSAGE, e);
            return LOG_MESSAGE + e.getMessage();
        }
    }
}
