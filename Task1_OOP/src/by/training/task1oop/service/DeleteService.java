package by.training.task1oop.service;

import by.training.task1oop.dao.factory.RepositoryFactory;
import by.training.task1oop.dao.repository.Repository;
import by.training.task1oop.exception.WrongArgumentsException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class DeleteService {
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
    private static final String NEGATIVE_MESSAGE = "Plane wasn't deleted,"
            + " check args";

    /**
     * @param args of plane to delete.
     * @param repository to delete from.
     * @return response.
     */
    String deletePlane(final String args, final Repository repository) {
        PlaneCreator planeCreator = PlaneCreator.getInstance();
        try {
            repository.delete(planeCreator.createPlane(args));
        } catch (WrongArgumentsException e) {
            logger.error(LOG_MESSAGE + args, e);
            return NEGATIVE_MESSAGE;
        }
        return POSITIVE_MESSAGE;
    }
    /**
     * @param args of plane to delete.
     * @return response.
     */
    public String deletePlane(final String args) {
        RepositoryFactory repositoryFactory = RepositoryFactory.getInstance();
        Repository repository = repositoryFactory.getPlaneRepository();
        return deletePlane(args, repository);
    }
}
