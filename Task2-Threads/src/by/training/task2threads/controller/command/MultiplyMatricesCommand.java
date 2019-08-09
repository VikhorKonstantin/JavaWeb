package by.training.task2threads.controller.command;

import by.training.task2threads.service.CreateMatrixFromFileService;
import by.training.task2threads.service.MultiplyMatricesService;
import by.training.task2threads.service.exception.ServiceException;
import by.training.task2threads.service.parser.StringParser;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class MultiplyMatricesCommand implements Executable {
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
        MultiplyMatricesService multiplyMatricesService =
                new MultiplyMatricesService();
        CreateMatrixFromFileService createMatrixFromFileService =
                new CreateMatrixFromFileService();
        try {
            var argList = StringParser.parseString(args);
            var matrix1 = createMatrixFromFileService
                    .createMatrixFromFile(argList.get(0));
            var matrix2 = createMatrixFromFileService
                    .createMatrixFromFile(argList.get(1));
            return multiplyMatricesService
                    .multiplyMatrices(matrix1, matrix2)
                    .toString();
        } catch (ServiceException | IndexOutOfBoundsException e) {
            logger.info(LOG_MESSAGE, e);
            return LOG_MESSAGE + e.getMessage();
        }
    }
}
