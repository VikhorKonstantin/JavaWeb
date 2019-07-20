package by.training.task1oop.controller.command;

import by.training.task1oop.service.UpdateService;
import by.training.task1oop.service.parser.StringParser;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class SeatingCapacityUpdateCommand implements Executable {
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
    private static final String POSITIVE_MESSAGE = "Plane was updated";
    /**
     * Negative scenario message.
     */
    private static final String NEGATIVE_MESSAGE =
            "No such plane in the company";
    /**
     * index of plane's id in args.
     */
    private static final int ID_INDEX = 0;
    /**
     * index of plane's seating capacity in args.
     */
    private static final int SEATING_CAPACITY_INDEX = 1;

    /**
     * @param args for service method
     * @return response
     */
    @Override
    public String execute(final String args) {
        UpdateService service = new UpdateService();
        try {
            var argList = StringParser.parseString(args);
            boolean isUpdated = service.updateSeatingCapacityById(
                    Long.parseLong(argList.get(ID_INDEX)),
                    Integer.parseInt(argList.get(SEATING_CAPACITY_INDEX)));
            if (isUpdated) {
                return POSITIVE_MESSAGE;
            } else {
                return NEGATIVE_MESSAGE;
            }
        } catch (NumberFormatException e) {
            logger.info(LOG_MESSAGE, e);
            return LOG_MESSAGE + e.getMessage();
        }
    }
}
