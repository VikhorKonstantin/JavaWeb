package by.training.task2thread.controller.command;

import by.training.task2thread.service.CreateMatrixFromFileService;
import by.training.task2thread.service.MultiplyMatricesService;
import by.training.task2thread.service.exception.ServiceException;
import by.training.task2thread.service.parser.StringParser;
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
    private static final String LOG_MESSAGE = "Invalid args: ";
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
            final int firstFilenameIndex = 0;
            final int secondFilenameIndex = 1;
            final String firstFilename = argList.get(firstFilenameIndex);
            final String secondFilename = argList.get(secondFilenameIndex);
            var matrix1 = createMatrixFromFileService
                    .createMatrixFromFile(firstFilename);
            var matrix2 = createMatrixFromFileService
                    .createMatrixFromFile(secondFilename);
            var resultMatrix = multiplyMatricesService
                    .multiplyMatrices(matrix1, matrix2);
            return resultMatrix.toString();
        } catch (ServiceException | IndexOutOfBoundsException e) {
            logger.info(LOG_MESSAGE, e);
            return LOG_MESSAGE + e.getMessage();
        }
    }
}
