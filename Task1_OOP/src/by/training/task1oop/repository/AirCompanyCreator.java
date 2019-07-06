package by.training.task1oop.repository;

import by.training.task1oop.entity.PassengerPlaneType;
import by.training.task1oop.entity.Plane;
import by.training.task1oop.entity.PlaneType;
import by.training.task1oop.factory.PlaneFactory;
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
     * index of plane type in arguments.
     */
    public static final int TYPE_INDEX = 0;
    /**
     * index of planeId in arguments.
     */
    public static final int ID_INDEX = 1;
    /**
     * index of seatingCapacity in arguments.
     */
    public static final int CAPACITY_INDEX = 2;
    /**
     * index of payload in arguments.
     */
    public static final int PAYLOAD_INDEX = 3;
    /**
     * index of fuelConsumption in arguments.
     */
    public static final int CONSUMPTION_INDEX = 4;
    /**
     * index of cargoHoldAmount in arguments.
     */
    public static final int CARGO_AMOUNT_INDEX = 5;
    /**
     * index of passengerPlaneType in arguments.
     */
    public static final int PASSENGER_TYPE_INDEX = 5;

    /**
     * @param planeParams list parameters necessary for creating planes.
     * @return AirCompany
     */
    public AirCompany createAirCompany(final List<String> planeParams) {
        List<Plane> planes = new ArrayList<>();
        DataValidator dataValidator = new DataValidator();
        for (var paramsString : planeParams) {
            List<String> params = StringParser.parseString(paramsString);
            if (dataValidator.isDataValid(params)) {
                if (dataValidator.getPlaneType().equals(PlaneType.PASSENGER)) {
                    planes.add(PlaneFactory.createPlane(PlaneType.valueOf(
                            params.get(TYPE_INDEX)),
                            Long.valueOf(params.get(ID_INDEX)),
                            Integer.valueOf(params.get(CAPACITY_INDEX)),
                            Integer.valueOf(params.get(PAYLOAD_INDEX)),
                            Integer.valueOf(params.get(CONSUMPTION_INDEX)),
                            PassengerPlaneType.valueOf(
                                    params.get(PASSENGER_TYPE_INDEX))));
                } else {
                    planes.add(PlaneFactory.createPlane(PlaneType.valueOf(
                            params.get(TYPE_INDEX)),
                            Long.valueOf(params.get(ID_INDEX)),
                            Integer.valueOf(params.get(CAPACITY_INDEX)),
                            Integer.valueOf(params.get(PAYLOAD_INDEX)),
                            Integer.valueOf(params.get(CONSUMPTION_INDEX)),
                            Integer.valueOf(params.get(CARGO_AMOUNT_INDEX))));
                }
            } else {
                logger.info("Line invalid: " + paramsString);
            }
        }
        return new AirCompany(planes);
    }
}
