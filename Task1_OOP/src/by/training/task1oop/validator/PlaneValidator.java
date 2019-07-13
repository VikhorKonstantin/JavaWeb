package by.training.task1oop.validator;
import java.util.List;

public abstract class PlaneValidator {
    /**
     * A constant holding the maximum value of seating capacity.
     */
    private static final int MAX_SEATING_CAPACITY = 500;
    /**
     * A constant holding the minimum value of seating capacity.
     */
    private static final int MIN_SEATING_CAPACITY = 1;
    /**
     * A constant holding the maximum value of payload.
     */
    private static final int MAX_PAYLOAD = 2000;
    /**
     * A constant holding the minimum value of payload.
     */
    private static final int MIN_PAYLOAD = 5;
    /**
     * A constant holding the maximum value of fuel consumption.
     */
    private static final int MAX_FUEL_CONSUMPTION = 2000;
    /**
     * A constant holding the minimum value of fuel consumption.
     */
    private static final int MIN_FUEL_CONSUMPTION = 300;
    /**
     * regular expression for digits.
     */
    private static final String DIGIT_REGEX = "\\d+";
    /**
     * number of arguments in argsList.
     */
    protected static final int COMMON_ARGS_NUMBER = 5;
    /**
     * number of unique args before common's.
     */
    protected static final int COMMON_ARGS_INDEX = 1;
    /**
     * Validating data from file's line.
     * @param argsList data from file's line
     * @return true(false) if data valid(invalid)
     */
    public boolean isValid(final List<String> argsList) {
        boolean isValid;
        try {
            var dataIterator = argsList.listIterator();
            isValid = dataIterator.next().matches(DIGIT_REGEX);
            int seatingCapacity = Integer.parseInt(dataIterator.next());
            int payload = Integer.parseInt(dataIterator.next());
            int fuelConsumption = Integer.parseInt(dataIterator.next());
            String name = dataIterator.next();
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


    /**
     * Util method checking if value is in [a, b] range.
     * @param value value to check.
     * @param a left bound of range.
     * @param b right bound of range.
     * @return true(false) if value is in [a, b] range(not in range).
     */
    protected boolean isInRange(final int value, final int a, final int b) {
        return (value >= a && value <= b);
    }
}

