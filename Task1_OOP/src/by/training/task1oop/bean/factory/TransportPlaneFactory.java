package by.training.task1oop.bean.factory;

import by.training.task1oop.bean.entity.TransportPlane;
import by.training.task1oop.bean.exception.BeanException;
import by.training.task1oop.bean.validator.TransportPlaneValidator;


import java.util.List;

public final class TransportPlaneFactory extends AbstractFactory {
    /**
     * Transport Plane Factory instance.
     */
    private static final TransportPlaneFactory INSTANCE;
    /**
     * index of cargoHoldAmount in arguments.
     */
    private static final int CARGO_AMOUNT_INDEX = 6;
    /**
     * Exception message.
     */
    private static final String EXCEPTION_MESSAGE =
            "Wrong transport plane params: ";
    static {
        INSTANCE = new TransportPlaneFactory();
    }
    /**
     * @return Transport Plane Factory instance.
     */
    public static TransportPlaneFactory getInstance() {
        return INSTANCE;
    }
    private TransportPlaneFactory() {
    }
    @Override
    public TransportPlane createPlane(final List<String> planeParams)
            throws BeanException {
        TransportPlaneValidator transportPlaneValidator =
                new TransportPlaneValidator();
        if (transportPlaneValidator.isValid(planeParams)) {
            return new TransportPlane(Long.valueOf(planeParams.get(ID_INDEX)),
                    Integer.valueOf(planeParams.get(CAPACITY_INDEX)),
                    Integer.valueOf(planeParams.get(PAYLOAD_INDEX)),
                    Integer.valueOf(planeParams.get(CONSUMPTION_INDEX)),
                    planeParams.get(NAME_INDEX),
                    Integer.valueOf(planeParams.get(CARGO_AMOUNT_INDEX)));
        } else {
            throw new BeanException(EXCEPTION_MESSAGE + planeParams);
        }
    }
}
