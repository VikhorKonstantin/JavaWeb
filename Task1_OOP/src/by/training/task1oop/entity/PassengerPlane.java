package by.training.task1oop.entity;

import java.util.Objects;

public class PassengerPlane extends Plane {
    /**
     * passengerPlaneType type of passenger plane based on body type.
     */
    private PassengerPlaneType passengerPlaneType;

    /**
     * @param id          planeId.
     * @param capacity    seatingCapacity.
     * @param load        payload.
     * @param consumption fuel consumption
     * @param type        passengerPlaneType
     */
    public PassengerPlane(final long id, final int capacity, final int load,
                          final int consumption,
                          final PassengerPlaneType type) {
        super(id, capacity, load, consumption);
        this.passengerPlaneType = type;
    }

    /**
     * @param o object to compare with.
     * @return true(false) if objects equals(not equals)
     */
    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof PassengerPlane)) {
            return false;
        }
        if (!super.equals(o)) {
            return false;
        }
        PassengerPlane that = (PassengerPlane) o;
        return passengerPlaneType == that.passengerPlaneType;
    }
    /**
     * @return hash code  of a plane.
     */
    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), passengerPlaneType);
    }

    /**
     * @return string representing the specified object.
     */
    @Override
    public String toString() {
        return "PassengerPlane{" + super.toString()
                + "passengerPlaneType=" + passengerPlaneType
                + '}';
    }
}
