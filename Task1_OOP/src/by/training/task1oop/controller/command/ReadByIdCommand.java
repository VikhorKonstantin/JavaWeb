package by.training.task1oop.controller.command;

import by.training.task1oop.service.ReadByIdService;
import by.training.task1oop.service.exception.ServiceException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ReadByIdCommand implements Executable {
    /**
     * Logger.
     */
    private static Logger logger = LogManager.getLogger();
    /**
     * logger message.
     */
    private static final String LOG_MESSAGE = "Invalid args in line: ";
    /**
     * @param args for service method
     * @return response
     */
    @Override
    public String execute(final String args) {
        ReadByIdService service = new ReadByIdService();
        try {
            return service.readById(Long.parseLong(args)).toString();
        } catch (ServiceException | NumberFormatException e) {
            logger.info(LOG_MESSAGE, e);
            return LOG_MESSAGE + e.getMessage();
        }
    }
}
