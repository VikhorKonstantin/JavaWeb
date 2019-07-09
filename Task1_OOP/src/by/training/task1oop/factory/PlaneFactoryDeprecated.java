package by.training.task1oop.factory;

import by.training.task1oop.entity.PassengerPlane;
import by.training.task1oop.entity.PassengerPlaneType;
import by.training.task1oop.entity.Plane;
import by.training.task1oop.entity.PlaneType;
import by.training.task1oop.entity.TransportPlane;

/**
 * @deprecated since 09.07.2019.
 * Factory class which is for creating planes objects.
 */
@Deprecated(since = "09.07.2019")
public interface PlaneFactoryDeprecated {
    /**
     * @param planeType creating plane's type
     * @param id        planeId.
     * @param capacity  seatingCapacity.
     * @param load      payload.
     * @param consumption fuel consumption
     * @param param     unique param.
     * @return plane object.
     */
    static Plane createPlane(final PlaneType planeType, final long id,
                                    final int capacity, final int load,
                                    final int consumption,
                                    final Object param) {
        Plane plane;
        switch (planeType) {
            case PASSENGER:
                PassengerPlaneType type = (PassengerPlaneType) param;
                plane = new PassengerPlane(id, capacity,
                        load, consumption, type);
                break;
            case TRANSPORT:
                int cargoAmount = (int) param;
                plane = new TransportPlane(id, capacity,
                        load, consumption, cargoAmount);
                break;
            default:
                return null;
        }
        return plane;
    }
}
