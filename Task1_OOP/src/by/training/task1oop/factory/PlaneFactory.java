package by.training.task1oop.factory;

import by.training.task1oop.entity.PassengerPlane;
import by.training.task1oop.entity.PassengerPlaneType;
import by.training.task1oop.entity.Plane;
import by.training.task1oop.entity.PlaneType;
import by.training.task1oop.entity.TransportPlane;

/**
 * Factory class which is for creating planes objects.
 */
public interface PlaneFactory {
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
