package by.training.task1oop.validator;

import by.training.task1oop.entity.PassengerPlaneType;
import by.training.task1oop.entity.PlaneType;
import by.training.task1oop.exception.WrongArgumentsException;

import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;

public class DataValidator {
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
    public static final int MAX_CARGO_AMOUNT = 1800;
    /**
     * A constant holding the minimum value of cargo hold amount.
     */
    public static final int MIN_CARGO_AMOUNT = 500;
    /**
     * Regular expression for digits.
     */
    public static final String DIGIT_REGEX = "\\d+";
    /**
     * Type of plane to validate.
     */
    private PlaneType planeType;

    /**
     * Validating data from file's line.
     * @param data data from file's line
     * @return true(false) if data valid(invalid)
     */
    public boolean isDataValid(final List<String> data) {
        Iterator<String> dataIterator = data.listIterator();
        try {
            planeType = PlaneType.valueOf(dataIterator.next());
            if (planeType.equals(PlaneType.PASSENGER)) {
                return isPassengerPlaneDataValid(dataIterator);
            } else {
                return isTransportPlaneDataValid(dataIterator);
            }
        } catch (NoSuchElementException
                | IllegalArgumentException | WrongArgumentsException e) {
            return false;
        }

    }

    /**
     * Validating data necessary for creating passenger plane.
     * @param dataIterator
     * iterator of
     * {@link DataValidator#isDataValid(java.util.List) visualLabel} input data.
     * @return true(false) if data valid(invalid)
     * @throws WrongArgumentsException if method catch
     * {@link DataValidator#isDataValid(java.util.List) visualLabel}
     */
    private boolean
    isPassengerPlaneDataValid(final Iterator<String> dataIterator)
            throws WrongArgumentsException {
        boolean isValid;
        try {
            isValid = dataIterator.next().matches(DIGIT_REGEX);
            int seatingCapacity = Integer.valueOf(dataIterator.next());
            int payload = Integer.valueOf(dataIterator.next());
            int fuelConsumption = Integer.valueOf(dataIterator.next());
            PassengerPlaneType type =
                    PassengerPlaneType.valueOf(dataIterator.next());
            isValid = isInRange(seatingCapacity,
                    MIN_SEATING_CAPACITY, MAX_SEATING_CAPACITY)
                    && isInRange(payload,
                    MIN_PAYLOAD, MAX_PAYLOAD)
                    && isInRange(fuelConsumption,
                    MIN_FUEL_CONSUMPTION, MAX_FUEL_CONSUMPTION);
        } catch (NumberFormatException e) {
            throw new WrongArgumentsException(e);
        }
        return isValid;
    }
    /**
     * Validating data necessary for creating passenger plane.
     * @param dataIterator
     * iterator of
     * {@link DataValidator#isDataValid(java.util.List) visualLabel} input data.
     * @return true(false) if data valid(invalid)
     * @throws WrongArgumentsException if method catch
     * {@link DataValidator#isDataValid(java.util.List) visualLabel}
     */
    private boolean
    isTransportPlaneDataValid(final Iterator<String> dataIterator)
            throws WrongArgumentsException {
        boolean isValid;
        try {
            isValid = dataIterator.next().matches(DIGIT_REGEX);
            int seatingCapacity = Integer.valueOf(dataIterator.next());
            int payload = Integer.valueOf(dataIterator.next());
            int fuelConsumption = Integer.valueOf(dataIterator.next());
            int cargoHoldAmount = Integer.valueOf(dataIterator.next());
            isValid = isInRange(seatingCapacity,
                    MIN_SEATING_CAPACITY, MAX_SEATING_CAPACITY)
                    && isInRange(payload,
                    MIN_PAYLOAD, MAX_PAYLOAD)
                    && isInRange(fuelConsumption,
                    MIN_FUEL_CONSUMPTION, MAX_FUEL_CONSUMPTION)
                    && isInRange(cargoHoldAmount,
                    MIN_CARGO_AMOUNT, MAX_CARGO_AMOUNT);
        } catch (NumberFormatException e) {
            throw new WrongArgumentsException(e);
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
    private boolean isInRange(final int value, final int a, final int b) {
        return (value >= a && value <= b);
    }

    /**
     * @return plane type.
     */
    public PlaneType getPlaneType() {
        return planeType;
    }
}

