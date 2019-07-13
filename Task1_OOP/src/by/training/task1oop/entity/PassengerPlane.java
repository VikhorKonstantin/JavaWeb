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
     * @param consumption fuel consumption.
     * @param planeName plane's name.
     * @param type        passengerPlaneType.
     */
    public PassengerPlane(final long id, final int capacity, final int load,
                          final int consumption, final String planeName,
                          final PassengerPlaneType type) {
        super(id, capacity, load, consumption, planeName);
        this.passengerPlaneType = type;
    }

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

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), passengerPlaneType);
    }

    @Override
    public String toString() {
        return "PassengerPlane{" + super.toString()
                + "passengerPlaneType=" + passengerPlaneType
                + '}';
    }
}
