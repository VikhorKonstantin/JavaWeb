package by.training.task1oop.service;

import by.training.task1oop.bean.entity.Plane;
import by.training.task1oop.dao.exception.DAOException;
import by.training.task1oop.dao.factory.RepositoryFactory;
import by.training.task1oop.dao.repository.Repository;
import by.training.task1oop.service.exception.ServiceException;
import by.training.task1oop.dao.reader.PlaneReader;

import java.util.Optional;

public class InitFromFileService {
    /**
     * service that init repository from file.
     * @param fileName file name
     * @throws ServiceException if request invalid
     */
    public void initFromFile(final String fileName)
            throws ServiceException {
        RepositoryFactory repositoryFactory = RepositoryFactory.getInstance();
        Repository<Plane> repository = repositoryFactory.getPlaneRepository();
        if (!repository.isEmpty()) {
            repository.clear();
        }
        PlaneReader planeReader = new PlaneReader();
        RepositoryInitializer repositoryInitializer =
                new RepositoryInitializer();
        Optional<String> oFilename = Optional.ofNullable(fileName);
        try {
            repositoryInitializer.initAirCompany(
                    planeReader.readFromFile(
                            oFilename.orElseThrow(ServiceException::new)));
        } catch (DAOException e) {
            throw  new ServiceException(e);
        }
    }
}
