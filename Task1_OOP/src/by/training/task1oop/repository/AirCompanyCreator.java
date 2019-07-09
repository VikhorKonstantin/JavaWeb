package by.training.task1oop.repository;

import by.training.task1oop.entity.Plane;
import by.training.task1oop.entity.PlaneType;
import by.training.task1oop.factory.PassengerPlaneFactory;
import by.training.task1oop.factory.TransportPlaneFactory;
import by.training.task1oop.parser.StringParser;
import by.training.task1oop.validator.DataValidator;
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
     * @param planeParams list parameters necessary for creating planes.
     * @return AirCompany
     */
    public AirCompany createAirCompany(final List<String> planeParams) {
        List<Plane> planes = new ArrayList<>();
        DataValidator dataValidator = new DataValidator();
        PassengerPlaneFactory passengerPlaneFactory = PassengerPlaneFactory.getInstance();
        TransportPlaneFactory transportPlaneFactory = TransportPlaneFactory.getInstance();
        for (var paramsString : planeParams) {
            List<String> params = StringParser.parseString(paramsString);
            if (dataValidator.isDataValid(params)) {
                if (dataValidator.getPlaneType().equals(PlaneType.PASSENGER)) {
                    planes.add(passengerPlaneFactory.createPlane(params));
                } else {
                    planes.add(transportPlaneFactory.createPlane(params));
                }
            } else {
                logger.info("Line invalid: " + paramsString);
            }
        }
        return new AirCompany(planes);
    }
}
