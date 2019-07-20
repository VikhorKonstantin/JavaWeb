package by.training.task1oop.controller.command;

import by.training.task1oop.service.SortByService;
import by.training.task1oop.service.exception.ServiceException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class SortCommand implements Executable {
    /**
     * Logger.
     */
    private static Logger logger = LogManager.getLogger();
    /**
     * logger message.
     */
    private static final String LOG_MESSAGE = "Invalid args in line: ";
    /**
     * @param property to sort by.
     * @return response
     */
    @Override
    public String execute(final String property) {
        SortByService service = new SortByService();
        try {
            return service.sortBy(property).toString();
        } catch (ServiceException e) {
            logger.info(LOG_MESSAGE, e);
            return LOG_MESSAGE + e.getMessage();
        }
    }
}
