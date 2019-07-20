package by.training.task1oop.dao.repository;

import by.training.task1oop.bean.entity.Plane;
import by.training.task1oop.specification.FindSpecification;
import by.training.task1oop.specification.SortSpecification;
import by.training.task1oop.specification.Specification;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class AirCompany implements Repository<Plane> {
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


}
