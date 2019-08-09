package by.training.task2threads.controller.command;


import by.training.task2threads.service.CreateMatrixFromFileService;
import by.training.task2threads.service.MatrixFillingService;
import by.training.task2threads.service.exception.ServiceException;
import by.training.task2threads.service.parser.StringParser;
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
    private static final String LOG_MESSAGE = "Invalid args in line: ";
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
            var matrix = matrixFromFileService
                    .createMatrixFromFile(argList.get(0));
            var numberOfThreads = Integer.parseInt(argList.get(1).trim());
            MatrixFillingService service = new MatrixFillingService(matrix);
            return service.fillDiagonal(numberOfThreads).toString();
        } catch (ServiceException | IndexOutOfBoundsException
                | NumberFormatException e) {
            logger.info(LOG_MESSAGE, e);
            return LOG_MESSAGE + e.getMessage();
        }
    }
}
