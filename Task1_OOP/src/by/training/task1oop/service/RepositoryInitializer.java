package by.training.task1oop.service;

import by.training.task1oop.service.exception.ServiceException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

final class RepositoryInitializer {
    /**
     * Logger.
     */
    private static Logger logger = LogManager.getLogger();


    /**
     * @param planesParams List of params of plane.
     */
    void initAirCompany(final List<String> planesParams) {
        AddService addService = new AddService();
        PlaneCreator planeCreator = PlaneCreator.getInstance();
        for (var paramString : planesParams) {
            try {
                addService.addPlane(
                        planeCreator.createPlane(paramString));
            } catch (ServiceException e) {
                logger.info(e);
            }
        }
    }
}
