package by.training.task1oop.service;

import by.training.task1oop.bean.entity.Plane;
import by.training.task1oop.dao.factory.RepositoryFactory;
import by.training.task1oop.dao.repository.Repository;
import by.training.task1oop.exception.WrongArgumentsException;
import by.training.task1oop.service.reader.PlaneReader;

import java.util.Optional;

public class InitFromFileService {
    /**
     * default file name.
     */
    private static final String DEFAULT_FILE_NAME = "input/input.txt";
    /**
     * service that init repository from file.
     * @param fileName file name
     * @return response
     * @throws WrongArgumentsException if request invalid
     */
    public String initFromFile(final String fileName)
            throws WrongArgumentsException {

        RepositoryFactory repositoryFactory = RepositoryFactory.getInstance();
        Repository<Plane> repository = repositoryFactory.getPlaneRepository();
        PlaneReader planeReader = new PlaneReader();
        AirCompanyInitializer airCompanyInitializer =
                new AirCompanyInitializer();
        Optional<String> oFilename = Optional.ofNullable(fileName);
        airCompanyInitializer.initAirCompany(
                planeReader.readFromFile(
                        oFilename.orElse(DEFAULT_FILE_NAME)),
                repository);
        StringBuilder result = new StringBuilder();
        for (var plane : repository.getAll()) {
            result.append(plane);
            result.append('\n');
        }
        return result.toString();
    }
}
