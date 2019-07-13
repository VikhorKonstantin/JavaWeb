package by.training.task1oop.factory;

import by.training.task1oop.entity.AgriculturalPlane;
import by.training.task1oop.exception.WrongArgumentsException;
import by.training.task1oop.validator.AgriculturalPlaneValidator;

import java.util.List;

public final class AgriculturalPlaneFactory extends PlaneFactory {
    /**
     * Agricultural Plane Factory instance.
     */
    private static final AgriculturalPlaneFactory INSTANCE;
    /**
     * index of chemicalTankCapacity in arguments.
     */
    private static final int TANK_CAPACITY_INDEX = 6;

    /**
     * index of cropsProcessingEfficiency in arguments.
     */
    private static final int PROC_EFFICIENCY_INDEX = 7;
    static {
        INSTANCE = new AgriculturalPlaneFactory();
    }
    /**
     * @return Agricultural Plane Factory instance.
     */
    public static AgriculturalPlaneFactory getInstance() {
        return INSTANCE;
    }
    private AgriculturalPlaneFactory() {
    }
    @Override
    public AgriculturalPlane createPlane(final List<String> planeParams)
            throws WrongArgumentsException {
        AgriculturalPlaneValidator agriculturalPlaneValidator =
                new AgriculturalPlaneValidator();
        if (agriculturalPlaneValidator.isValid(planeParams)) {
            return new AgriculturalPlane(
                    Long.parseLong(planeParams.get(ID_INDEX)),
                    Integer.parseInt(planeParams.get(CAPACITY_INDEX)),
                    Integer.parseInt(planeParams.get(PAYLOAD_INDEX)),
                    Integer.parseInt(planeParams.get(CONSUMPTION_INDEX)),
                    planeParams.get(NAME_INDEX),
                    Integer.parseInt(planeParams.get(TANK_CAPACITY_INDEX)),
                    Integer.parseInt(planeParams.get(PROC_EFFICIENCY_INDEX)));
        } else {
            throw new WrongArgumentsException("Input data invalid");
        }
    }
}
