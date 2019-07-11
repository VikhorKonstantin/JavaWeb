package by.training.task1oop.factory;

import by.training.task1oop.entity.Plane;
import by.training.task1oop.exception.WrongArgumentsException;

import java.util.List;

public interface PlaneFactory {
    /**
     * @param planeParams list of params to create a plane.
     * @return {@link by.training.task1oop.entity.Plane visualLabel}
     * @throws WrongArgumentsException if input data invalid
     */
    Plane createPlane(List<String> planeParams) throws WrongArgumentsException;
}
