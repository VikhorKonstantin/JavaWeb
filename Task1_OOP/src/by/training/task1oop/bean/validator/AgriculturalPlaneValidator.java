package by.training.task1oop.bean.validator;

import java.util.List;
import java.util.NoSuchElementException;

public class AgriculturalPlaneValidator extends PlaneValidator {
    /**
     * string represented agricultural plane in input file.
     */
    private static final String AGRICULTURAL = "AGRICULTURAL";
    /**
     * A constant holding the maximum value of chemical tank capacity.
     */
    private static final int MAX_TANK_CAPACITY = 3000;
    /**
     * A constant holding the minimum value of chemical tank capacity.
     */
    private static final int MIN_TANK_CAPACITY = 500;
    /**
     * A constant holding the maximum value of efficiency
     * of processing of crops.
     */
    private static final int MAX_PROC_EFFICIENCY = 150;
    /**
     * A constant holding the minimum value of efficiency
     * of processing of crops.
     */
    private static final int MIN_PROC_EFFICIENCY = 15;
    /**
     * Validating data necessary for creating agricultural plane.
     * @param argsList list of params to validate
     * @return true(false) if data valid(invalid)
     */
    @Override
    public boolean
    isValid(final List<String> argsList) {
        boolean isValid;
        try {
            var dataIterator = argsList.listIterator();
            isValid = dataIterator.next().equals(AGRICULTURAL);
            isValid &= super.isValid(argsList.subList(COMMON_ARGS_INDEX,
                    COMMON_ARGS_INDEX + COMMON_ARGS_NUMBER));
            var uniqueDataIterator = argsList.listIterator(COMMON_ARGS_INDEX
                    + COMMON_ARGS_NUMBER);
            int chemicalTankCapacity = Integer.parseInt(
                    uniqueDataIterator.next());
            int cropsProcessingEfficiency = Integer.parseInt(
                    uniqueDataIterator.next());
            isValid &= isInRange(chemicalTankCapacity,
                    MIN_TANK_CAPACITY, MAX_TANK_CAPACITY)
                    && isInRange(cropsProcessingEfficiency,
                    MIN_PROC_EFFICIENCY, MAX_PROC_EFFICIENCY);
        } catch (IllegalArgumentException | NullPointerException
                | NoSuchElementException e) {
            return false;
        }
        return isValid;
    }
}
