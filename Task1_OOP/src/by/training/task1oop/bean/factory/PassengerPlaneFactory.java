package by.training.task1oop.bean.factory;

import by.training.task1oop.bean.entity.PassengerPlane;
import by.training.task1oop.bean.entity.PassengerPlaneType;
import by.training.task1oop.bean.exception.BeanException;
import by.training.task1oop.bean.validator.PassengerPlaneValidator;

import java.util.List;

public final class PassengerPlaneFactory extends AbstractFactory {
    /**
     * Passenger Plane Factory instance.
     */
    private static final PassengerPlaneFactory INSTANCE;

    /**
     * index of passengerPlaneType in arguments.
     */
    private static final int PASSENGER_TYPE_INDEX = 6;

    /**
     * Exception message.
     */
    private static final String EXCEPTION_MESSAGE =
            "Wrong passenger plane params: ";
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
            throws BeanException {
        PassengerPlaneValidator passengerPlaneValidator =
                new PassengerPlaneValidator();
        if (passengerPlaneValidator.isValid(planeParams)) {
            return new PassengerPlane(Long.valueOf(planeParams.get(ID_INDEX)),
                    Integer.valueOf(planeParams.get(CAPACITY_INDEX)),
                    Integer.valueOf(planeParams.get(PAYLOAD_INDEX)),
                    Integer.valueOf(planeParams.get(CONSUMPTION_INDEX)),
                    planeParams.get(NAME_INDEX),
                    PassengerPlaneType.valueOf(
                            planeParams.get(PASSENGER_TYPE_INDEX)));
        } else {
            throw new BeanException(EXCEPTION_MESSAGE + planeParams);
        }
    }
}
