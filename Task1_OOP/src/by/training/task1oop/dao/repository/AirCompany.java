package by.training.task1oop.dao.repository;

import by.training.task1oop.bean.entity.Plane;
import by.training.task1oop.bean.entity.UpdateSubmissionPublisher.UpdateData;
import by.training.task1oop.service.AirCompanyInfoService;
import by.training.task1oop.dao.repository.specification.FindSpecification;
import by.training.task1oop.dao.repository.specification.SortSpecification;
import by.training.task1oop.dao.repository.specification.Specification;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.Flow;

public class AirCompany implements Repository<Plane>,
        Flow.Subscriber<UpdateData> {
    /**
     * Logger.
     */
    private static Logger logger = LogManager.getLogger();
    /**
     * List of planes of a company.
     */
    private List<Plane> planes = new ArrayList<>();

    /**
     * total seating capacity of air company.
     */
    private int totalSeatingCapacity;
    /**
     * total payload of air company.
     */
    private int totalPayload;

    /**
     * empty constructor.
     */
    public AirCompany() {
        totalSeatingCapacity = 0;
        totalPayload = 0;
    }


    /**
     * @param id unique plane ID.
     * @return plane by it's id.
     */
    @Override
    public Plane readById(final long id) {
        Plane resultPlane = null;
        for (var plane : planes) {
            if (plane.getPlaneId() == id) {
                resultPlane = plane;
            }
        }
        return resultPlane;
    }

    /**
     * Add plane to repository.
     * @param plane plane to add
     * @return is plane was added
     */
    @Override
    public boolean add(final Plane plane) {
        totalPayload += plane.getPayload();
        totalSeatingCapacity += plane.getSeatingCapacity();
        plane.subscribe(this);
        return planes.add(plane);
    }

    /**
     * Delete plane from repository.
     * @param plane plane to delete
     * @return is plane was deleted
     */
    @Override
    public boolean delete(final Plane plane) {
        totalPayload -= plane.getPayload();
        totalSeatingCapacity -= plane.getSeatingCapacity();
        return planes.remove(plane);
    }

    /**
     * @return is plane list empty;
     */
    @Override
    public boolean isEmpty() {
        return planes.isEmpty();
    }

    /**
     * clear repository.
     */
    @Override
    public void clear() {
        planes.clear();
        totalSeatingCapacity = 0;
        totalPayload = 0;
    }

    /**
     * @return List of planes.
     */
    @Override
    public List<Plane> getAll() {
        return planes;
    }

    /**
     * @param specification specification of a query.
     * @return List of objects satisfied specification.
     */
    @Override
    public List<Plane> query(final Specification specification) {
        ArrayList<Plane> planeList = new ArrayList<>();
        if (specification instanceof FindSpecification) {
            for (var plane: planes) {
                if (((FindSpecification<Plane>) specification)
                        .isSatisfiedBy(plane)) {
                    planeList.add(plane);
                }
            }
        } else {
            if (specification instanceof SortSpecification) {
                planeList.addAll(planes);
                planeList.sort(((SortSpecification<Plane>) specification)
                        .sortBy());
            }
        }
        return planeList;
    }

    /**
     * @return totalSeatingCapacity.
     */
    public int getTotalSeatingCapacity() {
        return totalSeatingCapacity;
    }

    /**
     * @return totalPayload.
     */
    public int getTotalPayload() {
        return totalPayload;
    }

    /**
     * @param newTotalSeatingCapacity updated total seating capacity.
     */
    public void setTotalSeatingCapacity(final int newTotalSeatingCapacity) {
        totalSeatingCapacity = newTotalSeatingCapacity;
    }

    /**
     * @param newTotalPayload updated total payload.
     */
    public void setTotalPayload(final int newTotalPayload) {
        totalPayload = newTotalPayload;
    }

    /**
     * @param id id of plane to update.
     * @param newPayload new payload value.
     * @return true if plane was updated.
     */
    public boolean updatePayloadById(final long id, final int newPayload) {
        Optional<Plane> planeOptional = Optional.ofNullable(readById(id));
        if (planeOptional.isPresent()) {
            planeOptional.get().setPayload(newPayload);
            return true;
        } else {
            return false;
        }
    }
    /**
     * @param id id of plane to update.
     * @param newSeatingCapacity new seating capacity value.
     * @return true if plane was updated.
     */
    public boolean updateSeatingCapacityById(final long id,
                                             final int newSeatingCapacity) {
        Optional<Plane> planeOptional = Optional.ofNullable(readById(id));
        if (planeOptional.isPresent()) {
            planeOptional.get().setSeatingCapacity(newSeatingCapacity);
            return true;
        } else {
            return false;
        }
    }

    /**
     * @param subscription new subscription.
     */
    @Override
    public void onSubscribe(final Flow.Subscription subscription) {
        logger.info("New subscription");
    }

    /**
     * @param newUpdateData updated item.
     */
    @Override
    public void onNext(final UpdateData newUpdateData) {
        AirCompanyInfoService service = new AirCompanyInfoService();
        service.updateInfo(newUpdateData);
        logger.info(newUpdateData + " was updated");
    }

    /**
     * @param throwable cause of problem.
     */
    @Override
    public void onError(final Throwable throwable) {
        logger.error(throwable);
    }

    /**
     * invoked when data ends.
     */
    @Override
    public void onComplete() {
        logger.info("AirCompany info updated");
    }


}
