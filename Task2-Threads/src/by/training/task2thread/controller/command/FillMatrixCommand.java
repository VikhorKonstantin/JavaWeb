package by.training.task2thread.controller.command;


import by.training.task2thread.service.CreateMatrixFromFileService;
import by.training.task2thread.service.MatrixFillingService;
import by.training.task2thread.service.exception.ServiceException;
import by.training.task2thread.service.parser.StringParser;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class FillMatrixCommand implements Executable {
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
        CreateMatrixFromFileService matrixFromFileService =
                new CreateMatrixFromFileService();
        try {
            var argList = StringParser.parseString(args);
            final int fileNameIndex = 0;
            final int numberOfThreadsIndex = 1;
            final String fileName = argList.get(fileNameIndex);
            final String stringNumberOfThreads =
                    argList.get(numberOfThreadsIndex);
            var matrix = matrixFromFileService
                    .createMatrixFromFile(fileName);
            var numberOfThreads = Integer.parseInt(stringNumberOfThreads);
            MatrixFillingService service = new MatrixFillingService();
            return service.fillDiagonal(matrix, numberOfThreads).toString();
        } catch (ServiceException | IndexOutOfBoundsException
                | NumberFormatException e) {
            logger.info(LOG_MESSAGE, e);
            return LOG_MESSAGE + e.getMessage();
        }
    }
}
