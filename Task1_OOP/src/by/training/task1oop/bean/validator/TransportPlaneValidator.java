package by.training.task1oop.bean.validator;

import java.util.List;
import java.util.NoSuchElementException;

public class TransportPlaneValidator extends PlaneValidator {
    /**
     * string represented transport plane in input file.
     */
    private static final String TRANSPORT = "TRANSPORT";
    /**
     * A constant holding the maximum value of cargo hold amount.
     */
    private static final int MAX_CARGO_AMOUNT = 1800;
    /**
     * A constant holding the minimum value of cargo hold amount.
     */
    private static final int MIN_CARGO_AMOUNT = 500;
    /**
     * Validating data necessary for creating transport plane.
     * @param argsList list of params to validate
     * @return true(false) if data valid(invalid)
     */
    @Override
    public boolean
    isValid(final List<String> argsList) {
        boolean isValid;
        try {
            var dataIterator = argsList.listIterator();
            isValid = dataIterator.next().equals(TRANSPORT);
            isValid &= super.isValid(argsList.subList(COMMON_ARGS_INDEX,
                    COMMON_ARGS_INDEX + COMMON_ARGS_NUMBER));
            var uniqueDataIterator = argsList.listIterator(COMMON_ARGS_INDEX
                    + COMMON_ARGS_NUMBER);
            int cargoHoldAmount = Integer.parseInt(uniqueDataIterator.next());
            isValid &= isInRange(cargoHoldAmount,
                    MIN_CARGO_AMOUNT, MAX_CARGO_AMOUNT);
        } catch (IllegalArgumentException | NullPointerException
                | NoSuchElementException e) {
            return false;
        }
        return isValid;
    }
}
