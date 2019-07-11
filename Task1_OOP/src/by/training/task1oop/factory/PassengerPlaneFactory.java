package by.training.task1oop.factory;

import by.training.task1oop.entity.PassengerPlane;
import by.training.task1oop.entity.PassengerPlaneType;
import by.training.task1oop.exception.WrongArgumentsException;
import by.training.task1oop.validator.PassangerPlaneValivator;

import java.util.List;

public final class PassengerPlaneFactory implements PlaneFactory {
    /**
     * Passenger Plane Factory instance.
     */
    private static final PassengerPlaneFactory INSTANCE;
    /**
     * index of planeId in arguments.
     */
    private static final int ID_INDEX = 1;
    /**
     * index of seatingCapacity in arguments.
     */
    private static final int CAPACITY_INDEX = 2;
    /**
     * index of payload in arguments.
     */
    private static final int PAYLOAD_INDEX = 3;
    /**
     * index of fuelConsumption in arguments.
     */
    private static final int CONSUMPTION_INDEX = 4;
    /**
     * index of name in arguments.
     */
    private static final int NAME_INDEX = 5;
    /**
     * index of passengerPlaneType in arguments.
     */
    private static final int PASSENGER_TYPE_INDEX = 6;
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
    public PassengerPlane createPlane(final List<String> planeParams)
            throws WrongArgumentsException {
        PassangerPlaneValivator passangerPlaneValivator =
                new PassangerPlaneValivator();
        if (passangerPlaneValivator.isValid(planeParams)) {
            return new PassengerPlane(Long.valueOf(planeParams.get(ID_INDEX)),
                    Integer.valueOf(planeParams.get(CAPACITY_INDEX)),
                    Integer.valueOf(planeParams.get(PAYLOAD_INDEX)),
                    Integer.valueOf(planeParams.get(CONSUMPTION_INDEX)),
                    planeParams.get(NAME_INDEX),
                    PassengerPlaneType.valueOf(
                            planeParams.get(PASSENGER_TYPE_INDEX)));
        } else {
            throw new WrongArgumentsException("Input data invalid");
        }
    }
}
