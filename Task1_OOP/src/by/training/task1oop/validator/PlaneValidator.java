package by.training.task1oop.validator;
import java.util.List;

public abstract class PlaneValidator {
    /**
     * A constant holding the maximum value of seating capacity.
     */
    public static final int MAX_SEATING_CAPACITY = 500;
    /**
     * A constant holding the minimum value of seating capacity.
     */
    public static final int MIN_SEATING_CAPACITY = 2;
    /**
     * A constant holding the maximum value of payload.
     */
    public static final int MAX_PAYLOAD = 2000;
    /**
     * A constant holding the minimum value of payload.
     */
    public static final int MIN_PAYLOAD = 5;
    /**
     * A constant holding the maximum value of fuel consumption.
     */
    public static final int MAX_FUEL_CONSUMPTION = 2000;
    /**
     * A constant holding the minimum value of fuel consumption.
     */
    public static final int MIN_FUEL_CONSUMPTION = 300;
    /**
     * A constant holding the maximum value of cargo hold amount.
     */

    public static final String DIGIT_REGEX = "\\d+";

    /**
     * Validating data from file's line.
     * @param data data from file's line
     * @return true(false) if data valid(invalid)
     */
    public  abstract boolean isValid(List<String> data);


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

