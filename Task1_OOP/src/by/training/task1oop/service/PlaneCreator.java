package by.training.task1oop.service;

import by.training.task1oop.bean.entity.Plane;
import by.training.task1oop.bean.factory.AgriculturalPlaneFactory;
import by.training.task1oop.bean.factory.PassengerPlaneFactory;
import by.training.task1oop.bean.factory.TransportPlaneFactory;
import by.training.task1oop.exception.WrongArgumentsException;
import by.training.task1oop.service.parser.StringParser;

import java.util.Optional;

final class PlaneCreator {
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
     * EXCEPTION message.
     */
    private static final String EXCEPTION_MESSAGE = "Invalid args in line: ";
    private PlaneCreator() {
    }

    /**
     * @return INSTANCE.
     */
    static PlaneCreator getInstance() {
        return INSTANCE;
    }

    /**
     * @param args args to create a plane.
     * @return Plane
     * @throws WrongArgumentsException if plane creation impossible
     */
    Plane createPlane(final String args) throws WrongArgumentsException {
        var safeArgs = Optional.ofNullable(args).orElseThrow(
                () -> new WrongArgumentsException(EXCEPTION_MESSAGE)
        );
        var params = StringParser.parseString(safeArgs);
        String planeType = params.get(TYPE_INDEX);
        PassengerPlaneFactory passengerPlaneFactory =
                PassengerPlaneFactory.getInstance();
        TransportPlaneFactory transportPlaneFactory =
                TransportPlaneFactory.getInstance();
        AgriculturalPlaneFactory agriculturalPlaneFactory =
                AgriculturalPlaneFactory.getInstance();
        switch (planeType) {
            case PASSENGER:
                return passengerPlaneFactory.createPlane(params);
            case TRANSPORT:
                return transportPlaneFactory.createPlane(params);
            case AGRICULTURE:
                return agriculturalPlaneFactory.createPlane(params);
            default:
                throw new WrongArgumentsException(EXCEPTION_MESSAGE + planeType);
        }
    }
}
