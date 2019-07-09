package by.training.task1oop.factory;

import by.training.task1oop.entity.TransportPlane;


import java.util.List;

public final class TransportPlaneFactory implements PlaneFactory {
    /**
     * Transport Plane Factory instance.
     */
    private static final TransportPlaneFactory INSTANCE;
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
    static {
        INSTANCE = new TransportPlaneFactory();
    }
    /**
     * @return Transport Plane Factory instance.
     */
    public static TransportPlaneFactory getInstance() {
        return INSTANCE;
    }
    private TransportPlaneFactory() {
    }
    @Override
    public TransportPlane createPlane(final List<String> planeParams) {
        return new TransportPlane(Long.valueOf(planeParams.get(ID_INDEX)),
                Integer.valueOf(planeParams.get(CAPACITY_INDEX)),
                Integer.valueOf(planeParams.get(PAYLOAD_INDEX)),
                Integer.valueOf(planeParams.get(CONSUMPTION_INDEX)),
                Integer.valueOf(planeParams.get(CARGO_AMOUNT_INDEX)));
    }
}
