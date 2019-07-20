package by.training.task1oop.service;

import by.training.task1oop.dao.factory.RepositoryFactory;
import by.training.task1oop.dao.repository.AirCompany;

public class AirCompanyInfoService {

    /**
     * @return totalSeatingCapacity.
     */
    public int totalSeatingCapacityRequest() {
        RepositoryFactory repositoryFactory = RepositoryFactory.getInstance();
        AirCompany repository =
                (AirCompany) repositoryFactory.getPlaneRepository();
        return repository.getTotalSeatingCapacity();
    }

    /**
     * @return totalPayload.
     */
    public int totalPayloadRequest() {
        RepositoryFactory repositoryFactory = RepositoryFactory.getInstance();
        AirCompany repository =
                (AirCompany) repositoryFactory.getPlaneRepository();
        return repository.getTotalPayload();
    }
}
