package by.training.task1oop.controller.command;

import by.training.task1oop.service.AddService;
import by.training.task1oop.service.PlaneCreator;
import by.training.task1oop.service.exception.ServiceException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class AddPlaneCommand implements Executable {
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
    private static final String POSITIVE_MESSAGE = "Plane was added";
    /**
     * negative scenario message.
     */
    private static final String NEGATIVE_MESSAGE = "Plane wasn't added,"
            + " check args";
    /**
     * @param args for service method
     * @return response
     */
    @Override
    public String execute(final String args) {
        AddService service = new AddService();
        try {
            PlaneCreator planeCreator = PlaneCreator.getInstance();
            var plane = planeCreator.createPlane(args);
            return (service.addPlane(plane)) ? POSITIVE_MESSAGE
                    : NEGATIVE_MESSAGE;
        } catch (ServiceException e) {
            logger.info(LOG_MESSAGE, e);
            return LOG_MESSAGE + e.getMessage();
        }
    }
}
