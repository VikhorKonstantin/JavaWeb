package by.training.task1oop.service;

import by.training.task1oop.entity.Plane;
import by.training.task1oop.exception.WrongArgumentsException;
import by.training.task1oop.factory.AgriculturalPlaneFactory;
import by.training.task1oop.factory.PassengerPlaneFactory;
import by.training.task1oop.factory.TransportPlaneFactory;
import by.training.task1oop.parser.StringParser;
import by.training.task1oop.repository.AirCompany;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.List;

public class AirCompanyCreator {
    /**
     * Logger.
     */
    private static Logger logger = LogManager.getLogger();
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
    private static final String LOG_MESSAGE = "Invalid args in line: ";
    /**
     * @param planesParams list parameters necessary for creating planes.
     * @return AirCompany
     */
    public AirCompany createAirCompany(final List<String> planesParams) {
        List<Plane> planes = new ArrayList<>();
        PassengerPlaneFactory passengerPlaneFactory =
                PassengerPlaneFactory.getInstance();
        TransportPlaneFactory transportPlaneFactory =
                TransportPlaneFactory.getInstance();
        AgriculturalPlaneFactory agriculturalPlaneFactory =
                AgriculturalPlaneFactory.getInstance();
        for (var paramString : planesParams) {
            List<String> params = StringParser.parseString(paramString);
            String planeType = params.get(TYPE_INDEX);
            switch (planeType) {
                case PASSENGER:
                    try {
                        planes.add(passengerPlaneFactory.createPlane(params));
                    } catch (WrongArgumentsException e) {
                        logger.info(LOG_MESSAGE + paramString, e);
                    }
                    break;

                case TRANSPORT:
                    try {
                        planes.add(transportPlaneFactory.createPlane(params));
                    } catch (WrongArgumentsException e) {
                        logger.info(LOG_MESSAGE + paramString, e);
                    }
                    break;

                case AGRICULTURE:
                    try {
                        planes.add(
                                agriculturalPlaneFactory.createPlane(params));
                    } catch (WrongArgumentsException e) {
                        logger.info(LOG_MESSAGE + paramString, e);
                    }
                    break;

                default:
                    logger.info(LOG_MESSAGE + planeType);

            }
        }
        return new AirCompany(planes);
    }
}
