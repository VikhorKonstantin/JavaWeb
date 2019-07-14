package by.training.task1oop.bean.factory;

import by.training.task1oop.bean.entity.Plane;
import by.training.task1oop.exception.WrongArgumentsException;

import java.util.List;

public abstract class AbstractFactory {
    /**
     * index of planeId in arguments.
     */
    protected static final int ID_INDEX = 1;
    /**
     * index of seatingCapacity in arguments.
     */
    protected static final int CAPACITY_INDEX = 2;
    /**
     * index of payload in arguments.
     */
    protected static final int PAYLOAD_INDEX = 3;
    /**
     * index of fuelConsumption in arguments.
     */
    protected static final int CONSUMPTION_INDEX = 4;
    /**
     * index of name in arguments.
     */
    protected static final int NAME_INDEX = 5;
    /**
     * @param planeParams list of params to create a plane.
     * @return {@link by.training.task1oop.bean.entity.Plane visualLabel}
     * @throws WrongArgumentsException if input data invalid
     */
    public abstract Plane createPlane(List<String> planeParams)
            throws WrongArgumentsException;
}
