package by.training.task1oop.service;

import by.training.task1oop.dao.factory.RepositoryFactory;
import by.training.task1oop.dao.repository.Repository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Optional;

public class ReadByIdService {
    /**
     * Logger.
     */
    private static Logger logger = LogManager.getLogger();
    /**
     * logger message.
     */
    private static final String LOG_MESSAGE = "Invalid args in line: ";
    /**
     * negative scenario message.
     */
    private static final String NEGATIVE_MESSAGE = "Wrong request";
    /**
     * no such element message.
     */
    private static final String NO_SUCH_ELEMENT = "No such plane in repository";

    /**
     * @param args consist plane index.
     * @param repository repository to find in.
     * @return response.
     */
    String readById(final String args, final Repository repository) {
        try {
            var optionalPlane = Optional.ofNullable(repository.readById(
                            Long.parseLong(args)));
            if (optionalPlane.isPresent()) {
                return optionalPlane.get().toString();
            } else {
                return NO_SUCH_ELEMENT;
            }
        } catch (NumberFormatException e) {
            logger.error(LOG_MESSAGE + args, e);
            return NEGATIVE_MESSAGE;
        }
    }

    /**
     * @param args consist plane index.
     * @return response.
     */
    public String readById(final String args) {
        RepositoryFactory repositoryFactory = RepositoryFactory.getInstance();
        Repository repository = repositoryFactory.getPlaneRepository();
        return readById(args, repository);
    }
}
