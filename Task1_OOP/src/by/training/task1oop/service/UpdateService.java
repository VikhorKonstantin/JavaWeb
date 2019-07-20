package by.training.task1oop.service;

import by.training.task1oop.dao.factory.RepositoryFactory;
import by.training.task1oop.dao.repository.AirCompany;

public class UpdateService {
    /**
     * @param id id of plane to update.
     * @param newPayload new payload value.
     * @return true if plane was updated.
     */
    public boolean updatePayloadById(final long id, final int newPayload) {
        RepositoryFactory repositoryFactory = RepositoryFactory.getInstance();
        AirCompany airCompany =
                (AirCompany) repositoryFactory.getPlaneRepository();
        return airCompany.updatePayloadById(id, newPayload);
    }

    /**
     * @param id id of plane to update.
     * @param newSeatingCapacity new seating capacity value.
     * @return true if plane was updated.
     */
    public boolean updateSeatingCapacityById(final long id,
                                             final int newSeatingCapacity) {
        RepositoryFactory repositoryFactory = RepositoryFactory.getInstance();
        AirCompany airCompany =
                (AirCompany) repositoryFactory.getPlaneRepository();
        return airCompany.updateSeatingCapacityById(id, newSeatingCapacity);
    }
}
