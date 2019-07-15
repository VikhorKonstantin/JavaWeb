package by.training.task1oop.controller.command;

import by.training.task1oop.exception.WrongArgumentsException;
import by.training.task1oop.service.DeleteService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class DeletePlaneCommand implements Executable {
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
        DeleteService service = new DeleteService();
        try {
            return service.deletePlane(args);
        } catch (WrongArgumentsException e) {
            logger.info(LOG_MESSAGE, e);
            return LOG_MESSAGE;
        }
    }
}
