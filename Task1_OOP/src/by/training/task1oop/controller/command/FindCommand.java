package by.training.task1oop.controller.command;

import by.training.task1oop.service.FindByService;
import by.training.task1oop.service.exception.ServiceException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class FindCommand implements Executable {
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
        FindByService service = new FindByService();
        try {
            return service.findBy(args).toString();
        } catch (ServiceException e) {
            logger.info(LOG_MESSAGE, e);
            return LOG_MESSAGE + e.getMessage();
        }
    }
}
