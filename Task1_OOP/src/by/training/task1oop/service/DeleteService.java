package by.training.task1oop.service;

import by.training.task1oop.bean.entity.Plane;
import by.training.task1oop.dao.factory.RepositoryFactory;
import by.training.task1oop.dao.repository.Repository;
import by.training.task1oop.service.exception.ServiceException;

import java.util.Optional;

public class DeleteService {

    /**
     * exception message.
     */
    private static final String EXCEPTION_MESSAGE = "Plane wasn't deleted,"
            + " check args";

    /**
     * @param plane plane to delete.
     * @return response
     * @throws ServiceException if request invalid
     */
    public boolean deletePlane(final Plane plane) throws ServiceException {
        RepositoryFactory repositoryFactory = RepositoryFactory.getInstance();
        Repository<Plane> repository = repositoryFactory.getPlaneRepository();
        var safePlane = Optional.ofNullable(plane).orElseThrow(
                () -> new ServiceException(EXCEPTION_MESSAGE)
        );
        return repository.delete(safePlane);
    }
}
