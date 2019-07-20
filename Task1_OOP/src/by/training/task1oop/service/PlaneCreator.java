package by.training.task1oop.service;

import by.training.task1oop.bean.entity.Plane;
import by.training.task1oop.bean.exception.BeanException;
import by.training.task1oop.bean.factory.AgriculturalPlaneFactory;
import by.training.task1oop.bean.factory.PassengerPlaneFactory;
import by.training.task1oop.bean.factory.TransportPlaneFactory;
import by.training.task1oop.service.exception.ServiceException;
import by.training.task1oop.service.parser.StringParser;

import java.util.Optional;

public final class PlaneCreator {
    /**
     * PlaneCreator instance.
     */
    private static final PlaneCreator INSTANCE = new PlaneCreator();
    /**
     * index of plane type in list of params.
     */
    private static final int TYPE_INDEX = 0;
    /**
     * string represented passenger plane in input file.
     */
    private static final String PASSENGER = "PASSENGER";
    /**
     * string represented transport plane in input file.
     */
    private static final String TRANSPORT = "TRANSPORT";
    /**
     * string represented transport plane in input file.
     */
    private static final String AGRICULTURE = "AGRICULTURE";
    /**
     *Wrong plane type message.
     */
    private static final String WRONG_TYPE = "Wrong plane type";
    /**
     * EXCEPTION message.
     */
    private static final String EXCEPTION_MESSAGE = "Plane creating error";

    private PlaneCreator() {
    }

    /**
     * @return INSTANCE.
     */
    public static PlaneCreator getInstance() {
        return INSTANCE;
    }

    /**
     * @param args args to create a plane.
     * @return Plane
     * @throws ServiceException if plane creation impossible
     */
    public Plane createPlane(final String args) throws ServiceException {
        var safeArgs = Optional.ofNullable(args).orElseThrow(
                () -> new ServiceException(EXCEPTION_MESSAGE)
        );
        var params = StringParser.parseString(safeArgs);
        String planeType = params.get(TYPE_INDEX);
        PassengerPlaneFactory passengerPlaneFactory =
                PassengerPlaneFactory.getInstance();
        TransportPlaneFactory transportPlaneFactory =
                TransportPlaneFactory.getInstance();
        AgriculturalPlaneFactory agriculturalPlaneFactory =
                AgriculturalPlaneFactory.getInstance();
        try {
            switch (planeType) {
                case PASSENGER:
                    return passengerPlaneFactory.createPlane(params);
                case TRANSPORT:
                    return transportPlaneFactory.createPlane(params);
                case AGRICULTURE:
                    return agriculturalPlaneFactory.createPlane(params);
                default:
                    throw new ServiceException(
                            WRONG_TYPE + planeType);
            }
        } catch (BeanException e) {
            throw new ServiceException(EXCEPTION_MESSAGE, e);
        }

    }
}
