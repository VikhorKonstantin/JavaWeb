package by.training.task1oop.service;

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
     * @param args for creating plane to add.
     * @param repository repository to add plane to.
     * @return response
     */
    String addPlane(final String args, final Repository repository) {
        PlaneCreator planeCreator = PlaneCreator.getInstance();
        try {
            repository.add(planeCreator.createPlane(args));
        } catch (WrongArgumentsException e) {
            logger.error(LOG_MESSAGE + args, e);
            return NEGATIVE_MESSAGE;
        }
        return POSITIVE_MESSAGE;
    }

    /**
     * @param args for creating plane to add.
     * @return response
     */
    public String addPlane(final String args) {
        RepositoryFactory repositoryFactory = RepositoryFactory.getInstance();
        Repository repository = repositoryFactory.getPlaneRepository();
        return addPlane(args, repository);
    }

}
