package by.training.task2thread.bean.validator;

import by.training.task2thread.service.parser.StringParser;

import java.util.List;
import java.util.NoSuchElementException;

public class MatrixValidator {
    /**
     * Cause first line should contain number of elems in columns and rows
     * it should have 2 number inside.
     */
    private static final int FIRST_LINE_ELEM_NUMBER = 2;
    /**
     * Regular expression to check is string is numeric.
     */
    private static final String NUMERIC_REGEX = "[-+]?\\d+";
    /**
     * Check if matrix valid.
     * @param matrixData data to check
     * @return isValid
     */
    public boolean isMatrixDataValid(final List<String> matrixData) {
        try {
            var listIterator = matrixData.listIterator();
            var sizeLine = StringParser.parseString(
                    (listIterator.next().trim()));
            if (sizeLine.size() != FIRST_LINE_ELEM_NUMBER) {
                return false;
            }
            final int rowNumberIndex = 0;
            final int columnsNumberIndex = 1;
            final int rowsNumber = Integer.parseInt(
                    sizeLine.get(rowNumberIndex));
            final int columnNumber = Integer.parseInt(
                    sizeLine.get(columnsNumberIndex));
            final int actualRowsNumber = matrixData.size() - 1;
            if (rowsNumber != actualRowsNumber) {
                return false;
            }
            while (listIterator.hasNext()) {
                var line = listIterator.next().trim();
                var elems = StringParser.parseString(line);
                var elmsNumber = elems.size();
                var digitNumber = elems.stream()
                        .filter(this::isNumeric).count();
                if (elmsNumber != digitNumber || elmsNumber != columnNumber) {
                    return false;
                }
            }
        } catch (NullPointerException
                | NumberFormatException | NoSuchElementException newE) {
            return false;
        }
        return true;
    }
    private boolean isNumeric(final String s) {
        return s.matches(NUMERIC_REGEX);
    }
}
