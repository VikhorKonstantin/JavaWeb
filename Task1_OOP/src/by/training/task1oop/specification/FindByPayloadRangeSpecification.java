package by.training.task1oop.specification;

import by.training.task1oop.bean.entity.Plane;

public class FindByPayloadRangeSpecification
        implements FindSpecification<Plane> {
    /**
     * min payload value of range we search a plane in.
     */
    private int minPayload;
    /**
     * max payload value of range we search a plane in.
     */
    private  int maxPayload;

    /**
     * @param newMinPayload minPayload.
     * @param newMaxPayload maxPayload.
     */
    public FindByPayloadRangeSpecification(final int newMinPayload,
                                           final int newMaxPayload) {
        minPayload = newMinPayload;
        maxPayload = newMaxPayload;
    }
    /**
     * determines which property the object in the list will be searched by.
     * @param plane object to check if it satisfy the property of specification
     * @return does obj satisfy the property
     */
    @Override
    public boolean isSatisfiedBy(final Plane plane) {
        int payload = plane.getPayload();
        return (payload >= minPayload) && (payload <= maxPayload);
    }
}
