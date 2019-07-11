package by.training.task1oop.validator;

import by.training.task1oop.entity.PassengerPlaneType;
import java.util.List;

public class PassangerPlaneValivator extends PlaneValidator {
    /**
     * string represented passenger plane in input file.
     */
    private static final String PASSENGER = "PASSENGER";

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
            isValid = dataIterator.next().equals(PASSENGER);
            isValid &= dataIterator.next().matches(DIGIT_REGEX);
            int seatingCapacity = Integer.parseInt(dataIterator.next());
            int payload = Integer.parseInt(dataIterator.next());
            int fuelConsumption = Integer.parseInt(dataIterator.next());
            String name = dataIterator.next();
            PassengerPlaneType.valueOf(dataIterator.next());
            isValid &= isInRange(seatingCapacity,
                    MIN_SEATING_CAPACITY, MAX_SEATING_CAPACITY)
                    && isInRange(payload,
                    MIN_PAYLOAD, MAX_PAYLOAD)
                    && isInRange(fuelConsumption,
                    MIN_FUEL_CONSUMPTION, MAX_FUEL_CONSUMPTION)
                    && !(name.isEmpty());
        } catch (IllegalArgumentException | NullPointerException  e) {
            return false;
        }
        return isValid;
    }
}
