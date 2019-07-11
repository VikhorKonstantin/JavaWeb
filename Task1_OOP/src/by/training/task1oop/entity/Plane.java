package by.training.task1oop.entity;

import java.util.Objects;

/**
 * Abstract Class that describe a plane.
 */

public abstract class Plane {
    /**
     * unique Id of each plane.
     */
    private long planeId;
    /**
     * number of seats in a plane.
     */
    private int seatingCapacity;
    /**
     * carrying capacity of a plane (ton).
     */
    private int payload;
    /**
     * fuel consumption per hour.
     */
    private int fuelConsumption;
    /**
     * plane's name.
     */
    private String name;

    /**
     * @param id          planeId.
     * @param capacity    seatingCapacity.
     * @param load        payload.
     * @param consumption fuel consumption.
     * @param planeName plane's name.
     */
    public Plane(final long id, final int capacity,
                 final int load, final int consumption,
                 final String planeName) {
        this.planeId = id;
        this.seatingCapacity = capacity;
        this.payload = load;
        this.fuelConsumption = consumption;
        this.name = planeName;
    }

    /**
     * @return planeId unique Id of each plane.
     */
    public long getPlaneId() {
        return planeId;
    }

    /**
     * @return seatingCapacity number of seats in a plane.
     */
    public int getSeatingCapacity() {
        return seatingCapacity;
    }

    /**
     * @return payload carrying capacity of a plane.
     */
    public int getPayload() {
        return payload;
    }

    /**
     * @return fuel consumption
     */
    public int getFuelConsumption() {
        return fuelConsumption;
    }
    /**
     * @return name
     */
    public String getName() {
        return name;
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
        if (!(o instanceof Plane)) {
            return false;
        }
        Plane plane = (Plane) o;
        return planeId == plane.planeId
                && seatingCapacity == plane.seatingCapacity
                && payload == plane.payload
                && fuelConsumption == plane.fuelConsumption
                && name.equals(plane.name);
    }

    /**
     * @return hash code  of a plane.
     */
    @Override
    public int hashCode() {
        return Objects.hash(planeId, seatingCapacity,
                payload, fuelConsumption, name);
    }

    /**
     * @return string representing the specified object.
     */
    @Override
    public String toString() {
        return "Plane{" + "planeId=" + planeId
                + ", seatingCapacity=" + seatingCapacity
                + ", payload=" + payload
                + ", fuelConsumption=" + fuelConsumption
                + ", name='" + name + '\'' + '}';
    }
}
