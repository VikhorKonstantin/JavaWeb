package by.training.task1oop.specification;

import by.training.task1oop.entity.Plane;

public class FindByIdSpecification implements FindSpecification<Plane> {
    /**
     * planeId we are search a plane with.
     */
    private int planeId;

    /**
     * @param newPlaneId planeId.
     */
    public FindByIdSpecification(final int newPlaneId) {
        this.planeId = newPlaneId;
    }
    /**
     * determines which property the object in the list will be searched by.
     * @param plane object to check if it satisfy the property of specification
     * @return does obj satisfy the property
     */
    @Override
    public boolean isSatisfiedBy(final Plane plane) {
        return plane.getPlaneId() == planeId;
    }
}
