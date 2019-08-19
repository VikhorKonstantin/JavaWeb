package by.training.task3composite.controller.command;

import by.training.task3composite.service.SortLexemesService;
import by.training.task3composite.service.exception.ServiceException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class SortLexemesCommand implements Executable {
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
        try {
            final String delimiter = " ";
            final int fileNameIndex = 0;
            final int charIndex = 1;
            var serviceParams = args.split(delimiter);
            String filename = serviceParams[fileNameIndex];
            char character = serviceParams[charIndex].charAt(0);
            SortLexemesService service =
                    new SortLexemesService();
            var text = service.parseAndSortLexemes(filename, character);
            return text.compose();
        } catch (ServiceException e) {
            logger.info(LOG_MESSAGE, e);
            return LOG_MESSAGE + e.getMessage();
        }
    }
}
