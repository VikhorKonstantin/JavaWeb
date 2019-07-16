package by.training.task1oop.service;

import by.training.task1oop.bean.entity.Plane;
import by.training.task1oop.dao.repository.Repository;
import by.training.task1oop.exception.WrongArgumentsException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

final class AirCompanyInitializer {
    /**
     * Logger.
     */
    private static Logger logger = LogManager.getLogger();

    /**
     * @param planesParams List of params of plane.
     * @param repository repository to init.
     * @throws WrongArgumentsException if request invalid
     */
    void initAirCompany(final List<String> planesParams,
                              final Repository<Plane> repository) {
        AddService addService = new AddService();
        for (var paramString : planesParams) {
            try {
                addService.addPlane(paramString, repository);
            } catch (WrongArgumentsException e) {
                logger.info(e);
            }
        }
    }
}
