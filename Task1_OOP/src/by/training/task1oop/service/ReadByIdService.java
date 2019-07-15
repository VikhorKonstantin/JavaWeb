package by.training.task1oop.service;

import by.training.task1oop.dao.factory.RepositoryFactory;
import by.training.task1oop.dao.repository.Repository;
import by.training.task1oop.exception.WrongArgumentsException;

import java.util.Optional;

public class ReadByIdService {
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
     * @throws WrongArgumentsException if request invalid
     */
    String readById(final String args, final Repository repository)
            throws WrongArgumentsException {
        try {
            var optionalPlane = Optional.ofNullable(repository.readById(
                            Long.parseLong(args)));
            if (optionalPlane.isPresent()) {
                return optionalPlane.get().toString();
            } else {
                return NO_SUCH_ELEMENT;
            }
        } catch (NumberFormatException e) {
            throw new WrongArgumentsException(NEGATIVE_MESSAGE, e);
        }
    }

    /**
     * @param args consist plane index.
     * @return response.
     * @throws WrongArgumentsException if request invalid
     */
    public String readById(final String args) throws WrongArgumentsException {
        RepositoryFactory repositoryFactory = RepositoryFactory.getInstance();
        Repository repository = repositoryFactory.getPlaneRepository();
        return readById(args, repository);
    }
}
