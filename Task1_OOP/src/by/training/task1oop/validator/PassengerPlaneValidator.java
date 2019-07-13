package by.training.task1oop.validator;

import by.training.task1oop.entity.PassengerPlaneType;
import java.util.List;
import java.util.NoSuchElementException;

public class PassengerPlaneValidator extends PlaneValidator {
    /**
     * string represented passenger plane in input file.
     */
    private static final String PASSENGER = "PASSENGER";

    /**
     * Validating data necessary for creating passenger plane.
     * @param argsList list of params to validate
     * @return true(false) if data valid(invalid)
     */
    @Override
    public boolean
    isValid(final List<String> argsList) {
        boolean isValid;
        try {
            var dataIterator = argsList.listIterator();
            isValid = dataIterator.next().equals(PASSENGER);
            isValid &= super.isValid(argsList.subList(COMMON_ARGS_INDEX,
                    COMMON_ARGS_INDEX + COMMON_ARGS_NUMBER));
            var uniqueDataIterator = argsList.listIterator(COMMON_ARGS_INDEX
                    + COMMON_ARGS_NUMBER);
            PassengerPlaneType.valueOf(uniqueDataIterator.next());
        } catch (IllegalArgumentException | NullPointerException
                | NoSuchElementException e) {
            return false;
        }
        return isValid;
    }
}
