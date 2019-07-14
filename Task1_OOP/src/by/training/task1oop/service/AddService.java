package by.training.task1oop.service;

import by.training.task1oop.bean.factory.AgriculturalPlaneFactory;
import by.training.task1oop.bean.factory.PassengerPlaneFactory;
import by.training.task1oop.bean.factory.TransportPlaneFactory;
import by.training.task1oop.dao.factory.RepositoryFactory;
import by.training.task1oop.dao.repository.Repository;
import by.training.task1oop.exception.WrongArgumentsException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


public class AddService {
    /**
     * Logger.
     */
    private static Logger logger = LogManager.getLogger();
    /**
     * logger message.
     */
    private static final String LOG_MESSAGE = "Invalid args in line: ";
    private static final String POSITIVE_MESSAGE = "Plane was added";
    private static final String NEGATIVE_MESSAGE = "Plane wasn't added," +
            " check args";
    public String addPlane(String args, Repository repository) {
        PlaneCreator planeCreator = PlaneCreator.getInstance();
        try {
            repository.add(planeCreator.createPlane(args));
        } catch (WrongArgumentsException e) {
            logger.error(LOG_MESSAGE + args, e);
            return NEGATIVE_MESSAGE;
        }
        return POSITIVE_MESSAGE;
    }

    public String addPlane(String args) {
        RepositoryFactory repositoryFactory = RepositoryFactory.getInstance();
        Repository repository = repositoryFactory.getPlaneRepository();
        return addPlane(args, repository);
    }

}
