package by.training.task1oop.service;

import by.training.task1oop.dao.factory.RepositoryFactory;
import by.training.task1oop.dao.repository.Repository;
import by.training.task1oop.exception.WrongArgumentsException;



public class AddService {
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
     * @throws WrongArgumentsException if request invalid
     */
    String addPlane(final String args, final Repository repository)
            throws WrongArgumentsException {
        PlaneCreator planeCreator = PlaneCreator.getInstance();
        try {
            repository.add(planeCreator.createPlane(args));
        } catch (WrongArgumentsException e) {
           throw new WrongArgumentsException(NEGATIVE_MESSAGE, e);
        }
        return POSITIVE_MESSAGE;
    }

    /**
     * @param args for creating plane to add.
     * @return response
     * @throws WrongArgumentsException if request invalid
     */
    public String addPlane(final String args) throws WrongArgumentsException {
        RepositoryFactory repositoryFactory = RepositoryFactory.getInstance();
        Repository repository = repositoryFactory.getPlaneRepository();
        return addPlane(args, repository);
    }

}
