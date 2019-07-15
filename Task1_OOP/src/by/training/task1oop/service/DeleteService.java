package by.training.task1oop.service;

import by.training.task1oop.dao.factory.RepositoryFactory;
import by.training.task1oop.dao.repository.Repository;
import by.training.task1oop.exception.WrongArgumentsException;

public class DeleteService {
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
     * @throws WrongArgumentsException if request invalid
     */
    String deletePlane(final String args, final Repository repository)
            throws WrongArgumentsException {

        PlaneCreator planeCreator = PlaneCreator.getInstance();
        try {
            repository.delete(planeCreator.createPlane(args));
        } catch (WrongArgumentsException e) {
            throw new WrongArgumentsException(NEGATIVE_MESSAGE, e);
        }
        return POSITIVE_MESSAGE;
    }
    /**
     * @param args of plane to delete.
     * @return response.
     * @throws WrongArgumentsException if request invalid
     */
    public String deletePlane(final String args)
            throws WrongArgumentsException {
        RepositoryFactory repositoryFactory = RepositoryFactory.getInstance();
        Repository repository = repositoryFactory.getPlaneRepository();
        return deletePlane(args, repository);
    }
}
