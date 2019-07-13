package by.training.task1oop.repository;

import by.training.task1oop.entity.Plane;
import by.training.task1oop.specification.FindSpecification;
import by.training.task1oop.specification.SortSpecification;
import by.training.task1oop.specification.Specification;

import java.util.ArrayList;
import java.util.List;

public class AirCompany implements Repository<Plane> {
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
    /**
     * @param specification specification of a query.
     * @return List of objects satisfied specification.
     */
    @Override
    public List<Plane> query(final Specification specification) {
        ArrayList<Plane> planeList = new ArrayList<>();
        if (specification instanceof FindSpecification) {
            for (var plane: planes) {
                if (((FindSpecification<Plane>) specification).isSatisfiedBy(plane)) {
                    planeList.add(plane);
                }
            }
        } else {
            if (specification instanceof SortSpecification) {
                planeList.addAll(planes);
                planeList.sort(((SortSpecification<Plane>) specification).sortBy());
            }
        }
        return planeList;
    }
}
