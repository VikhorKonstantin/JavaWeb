package by.training.task1oop.dao.factory;

import by.training.task1oop.bean.entity.Plane;
import by.training.task1oop.dao.repository.AirCompany;
import by.training.task1oop.dao.repository.Repository;

public class RepositoryFactory {
    private static final RepositoryFactory INSTANCE = new RepositoryFactory();
    private final Repository<Plane> planeRepository = new AirCompany();
    private RepositoryFactory(){
    }

    public static RepositoryFactory getInstance() {
        return INSTANCE;
    }

    public Repository<Plane> getPlaneRepository() {
        return planeRepository;
    }
}
