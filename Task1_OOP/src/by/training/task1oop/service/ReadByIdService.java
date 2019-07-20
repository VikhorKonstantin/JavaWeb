package by.training.task1oop.service;

import by.training.task1oop.bean.entity.Plane;
import by.training.task1oop.dao.factory.RepositoryFactory;
import by.training.task1oop.dao.repository.Repository;
import by.training.task1oop.service.exception.ServiceException;

import java.util.Optional;

public class ReadByIdService {
    /**
     * no such element message.
     */
    private static final String NO_SUCH_ELEMENT = "No such plane in repository";


    /**
     * @param id consist plane index.
     * @return response.
     * @throws ServiceException if request invalid
     */
    public Plane readById(final long id) throws ServiceException {
        RepositoryFactory repositoryFactory = RepositoryFactory.getInstance();
        Repository repository = repositoryFactory.getPlaneRepository();
        return Optional.ofNullable(repository.readById(id)).orElseThrow(
                () -> new ServiceException(NO_SUCH_ELEMENT)
        );
    }
}
