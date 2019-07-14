package by.training.task1oop.specification;

import by.training.task1oop.bean.entity.Plane;

public class FindByPayloadAndSeatingCapacity
        implements FindSpecification<Plane> {
    /**
     * value of payload we are search a plane with.
     */
    private int payload;
    /**
     * value of seatingCapacity we are search a plane with.
     */
    private int seatingCapacity;

    /**
     * @param newPayload payload.
     * @param newSeatingCapacity seatingCapacity.
     */
    public FindByPayloadAndSeatingCapacity(final int newPayload,
                                           final int newSeatingCapacity) {
        payload = newPayload;
        seatingCapacity = newSeatingCapacity;
    }
    /**
     * determines which property the object in the list will be searched by.
     * @param plane object to check if it satisfy the property of specification
     * @return does obj satisfy the property
     */
    @Override
    public boolean isSatisfiedBy(final Plane plane) {
        return (plane.getPayload() == payload)
                && (plane.getSeatingCapacity() == seatingCapacity);
    }
}
