package by.training.task1oop.bean.factory;

import by.training.task1oop.bean.entity.AgriculturalPlane;
import by.training.task1oop.bean.exception.BeanException;
import by.training.task1oop.bean.validator.AgriculturalPlaneValidator;

import java.util.List;

public final class AgriculturalPlaneFactory extends AbstractFactory {
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
    /**
     * Exception message.
     */
    private static final String EXCEPTION_MESSAGE =
            "Wrong agricultural plane params: ";
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
            throws BeanException {
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
            throw new BeanException(EXCEPTION_MESSAGE + planeParams);
        }
    }
}
