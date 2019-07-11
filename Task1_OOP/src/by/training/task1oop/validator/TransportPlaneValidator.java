package by.training.task1oop.validator;

import java.util.List;

public class TransportPlaneValidator extends PlaneValidator {
    /**
     * string represented transport plane in input file.
     */
    private static final String TRANSPORT = "TRANSPORT";
    /**
     * A constant holding the maximum value of cargo hold amount.
     */
    public static final int MAX_CARGO_AMOUNT = 1800;
    /**
     * A constant holding the minimum value of cargo hold amount.
     */
    public static final int MIN_CARGO_AMOUNT = 500;
    /**
     * Validating data necessary for creating passenger plane.
     * @param stringList list of params to validate
     * @return true(false) if data valid(invalid)
     */
    @Override
    public boolean
    isValid(final List<String> stringList) {
        boolean isValid;
        try {
            var dataIterator = stringList.listIterator();
            isValid = dataIterator.next().equals(TRANSPORT);
            isValid &= dataIterator.next().matches(DIGIT_REGEX);
            int seatingCapacity = Integer.parseInt(dataIterator.next());
            int payload = Integer.parseInt(dataIterator.next());
            int fuelConsumption = Integer.parseInt(dataIterator.next());
            String name = dataIterator.next();
            int cargoHoldAmount = Integer.parseInt(dataIterator.next());
            isValid &= isInRange(seatingCapacity,
                    MIN_SEATING_CAPACITY, MAX_SEATING_CAPACITY)
                    && isInRange(payload,
                    MIN_PAYLOAD, MAX_PAYLOAD)
                    && isInRange(fuelConsumption,
                    MIN_FUEL_CONSUMPTION, MAX_FUEL_CONSUMPTION)
                    && isInRange(cargoHoldAmount,
                    MIN_CARGO_AMOUNT, MAX_CARGO_AMOUNT)
                    && !(name.isEmpty());
        } catch (IllegalArgumentException | NullPointerException e) {
            return false;
        }
        return isValid;
    }
}
