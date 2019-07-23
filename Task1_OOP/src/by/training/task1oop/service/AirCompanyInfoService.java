package by.training.task1oop.service;

import by.training.task1oop.bean.entity.UpdateSubmissionPublisher;
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

    /**
     * update total seating capacity and payload.
     * @param updateData update data
     */
    public void updateInfo(
            final UpdateSubmissionPublisher.UpdateData updateData) {
        int oldValue = updateData.getBeforeUpdateValue();
        int newValue = updateData.getAfterUpdateValue();
        RepositoryFactory repositoryFactory = RepositoryFactory.getInstance();
        AirCompany repository =
                (AirCompany) repositoryFactory.getPlaneRepository();
        String name = updateData.getFieldName();
        int difference = newValue - oldValue;
        switch (name) {
            case "PAYLOAD":
                repository.setTotalPayload(
                        repository.getTotalPayload()
                        + difference
                );
                break;
            case "SEATING_CAPACITY":
                repository.setTotalPayload(
                        repository.getTotalSeatingCapacity()
                                + difference
                );
                break;
            default:
                return;
        }
    }
}
