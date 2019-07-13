package by.training.task1oop.entity;

import java.util.Objects;

public class TransportPlane extends Plane {
    /**
     * Quality of transport planes that
     * describe capacity of plane's cargo hold in  tons.
     */
    private int cargoHoldAmount;
    /**
     * @param newId planeId.
     * @param newCapacity seatingCapacity.
     * @param newPayload  payload.
     * @param newConsumption fuel consumption
     * @param newName plane's name
     * @param newCargoHoldAmount cargoHoldAmount
     */
    public TransportPlane(final long newId, final int newCapacity,
                          final int newPayload, final int newConsumption,
                          final String newName,
                          final  int newCargoHoldAmount) {
        super(newId, newCapacity, newPayload, newConsumption, newName);
        this.cargoHoldAmount = newCargoHoldAmount;
    }

    /**
     * @return cargoHoldAmount.
     */
    public int getCargoHoldAmount() {
        return cargoHoldAmount;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof TransportPlane)) {
            return false;
        }
        if (!super.equals(o)) {
            return false;
        }
        TransportPlane that = (TransportPlane) o;
        return cargoHoldAmount == that.cargoHoldAmount;
    }
    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), cargoHoldAmount);
    }

    @Override
    public String toString() {
        return "TransportPlane{" + super.toString()
                + "cargoHoldAmount=" + cargoHoldAmount
                + '}';
    }
}
