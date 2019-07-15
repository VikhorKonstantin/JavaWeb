package by.training.task1oop.dao.factory;

import by.training.task1oop.bean.entity.Plane;
import by.training.task1oop.dao.repository.AirCompany;
import by.training.task1oop.dao.repository.Repository;

public final class RepositoryFactory {
    /**
     * INSTANCE.
     */
    private static final RepositoryFactory INSTANCE = new RepositoryFactory();
    /**
     * planeRepository.
     */
    private final Repository<Plane> planeRepository = new AirCompany();
    private RepositoryFactory() {
    }

    /**
     * @return INSTANCE
     */
    public static RepositoryFactory getInstance() {
        return INSTANCE;
    }

    /**
     * @return plane repository.
     */
    public Repository<Plane> getPlaneRepository() {
        return planeRepository;
    }
}
