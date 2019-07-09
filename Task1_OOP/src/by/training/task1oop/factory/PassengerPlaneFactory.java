package by.training.task1oop.factory;

import by.training.task1oop.entity.PassengerPlane;
import by.training.task1oop.entity.PassengerPlaneType;

import java.util.List;

public final class PassengerPlaneFactory implements PlaneFactory {
    /**
     * Passenger Plane Factory instance.
     */
    private static final PassengerPlaneFactory INSTANCE;
    /**
     * index of planeId in arguments.
     */
    public static final int ID_INDEX = 1;
    /**
     * index of seatingCapacity in arguments.
     */
    public static final int CAPACITY_INDEX = 2;
    /**
     * index of payload in arguments.
     */
    public static final int PAYLOAD_INDEX = 3;
    /**
     * index of fuelConsumption in arguments.
     */
    public static final int CONSUMPTION_INDEX = 4;
    /**
     * index of passengerPlaneType in arguments.
     */
    public static final int PASSENGER_TYPE_INDEX = 5;
    static {
        INSTANCE = new PassengerPlaneFactory();
    }
    /**
     * @return Passenger Plane Factory instance.
     */
    public static PassengerPlaneFactory getInstance() {
        return INSTANCE;
    }
    private PassengerPlaneFactory() {
    }
    @Override
    public PassengerPlane createPlane(final List<String> planeParams) {
        return new PassengerPlane(Long.valueOf(planeParams.get(ID_INDEX)),
                Integer.valueOf(planeParams.get(CAPACITY_INDEX)),
                Integer.valueOf(planeParams.get(PAYLOAD_INDEX)),
                Integer.valueOf(planeParams.get(CONSUMPTION_INDEX)),
                PassengerPlaneType.valueOf(
                        planeParams.get(PASSENGER_TYPE_INDEX)));
    }
}
