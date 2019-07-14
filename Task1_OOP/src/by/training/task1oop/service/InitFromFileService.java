package by.training.task1oop.service;

import by.training.task1oop.bean.entity.Plane;
import by.training.task1oop.dao.factory.RepositoryFactory;
import by.training.task1oop.dao.repository.Repository;
import by.training.task1oop.service.reader.PlaneReader;

public class InitFromFileService {
    public String initFromFile(String fileName) {
        RepositoryFactory repositoryFactory = RepositoryFactory.getInstance();
        Repository<Plane> repository = repositoryFactory.getPlaneRepository();
        PlaneReader planeReader = new PlaneReader();
        AirCompanyInitializer airCompanyInitializer = new AirCompanyInitializer();
        airCompanyInitializer.initAirCompany(
                planeReader.readFromFile(fileName), repository);
        StringBuilder result = new StringBuilder();
        for (var plane : repository.getAll()) {
            result.append(plane);
            result.append('\n');
        }
        return result.toString();
    }
}
