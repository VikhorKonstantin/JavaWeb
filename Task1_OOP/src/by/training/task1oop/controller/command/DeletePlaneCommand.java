package by.training.task1oop.controller.command;

import by.training.task1oop.service.DeleteService;
import by.training.task1oop.service.PlaneCreator;
import by.training.task1oop.service.exception.ServiceException;
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
     * positive scenario message.
     */
    private static final String POSITIVE_MESSAGE = "Plane was deleted";
    /**
     * Negative scenario message.
     */
    private static final String NEGATIVE_MESSAGE =
            "No such plane in the company";
    /**
     * @param args for service method
     * @return response
     */
    @Override
    public String execute(final String args) {
        DeleteService service = new DeleteService();
        try {
            PlaneCreator planeCreator = PlaneCreator.getInstance();
            var plane = planeCreator.createPlane(args);
            return (service.deletePlane(plane)) ? POSITIVE_MESSAGE
                    : NEGATIVE_MESSAGE;
        } catch (ServiceException e) {
            logger.info(LOG_MESSAGE, e);
            return LOG_MESSAGE + e.getMessage();
        }
    }
}
