package by.training.task3composite.controller.command;

import by.training.task3composite.service.SortWordsService;
import by.training.task3composite.service.exception.ServiceException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class SortWordsCommand implements Executable {
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
        SortWordsService service =
                new SortWordsService();
        try {
            var text = service.parseAndSortWords(args);
            return text.compose();
        } catch (ServiceException e) {
            logger.info(LOG_MESSAGE, e);
            return LOG_MESSAGE + e.getMessage();
        }
    }
}
