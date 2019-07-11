package by.training.task1oop.repository;

import by.training.task1oop.entity.Plane;
import java.util.List;

public class AirCompany implements Repository {
    /**
     * List of planes of a company.
     */
    private List<Plane> planes;
    /**
     * Constructor with List of planes as a param.
     *
     * @param planeList list of planes of a company.
     */
    public AirCompany(final List<Plane> planeList) {
        this.planes = planeList;
    }
    /**
     * @param id unique plane ID.
     * @return plane by it's id.
     */
    @Override
    public Plane readById(final long id) {
        for (var plane : planes) {
            if (plane.getPlaneId() == id) {
                return plane;
            }
        }
        return null;
    }

    /**
     * Add plane to repository.
     * @param plane plane to add
     */
    @Override
    public void add(final Plane plane) {
        planes.add(plane);
    }

    /**
     * Delete plane from repository.
     * @param plane plane to delete
     */
    @Override
    public void delete(final Plane plane) {
        planes.remove(plane);
    }
}
