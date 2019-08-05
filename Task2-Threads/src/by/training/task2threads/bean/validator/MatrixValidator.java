package by.training.task2threads.bean.validator;

import by.training.task2threads.service.parser.StringParser;

import java.util.List;
import java.util.NoSuchElementException;

public class MatrixValidator {

    /**
     * Check if matrix valid.
     * @param matrixData data to check
     * @return isValid
     */
    public boolean isMatrixDataValid(final List<String> matrixData) {
        boolean isValid = true;
        var listIterator = matrixData.listIterator();
        try {
            var sizeLine = StringParser.parseString(
                    (listIterator.next().trim()));
            final int rowNumberIndex = 0;
            final int columnNumberIndex = 1;
            final int rowNumber = Integer.parseInt(
                    sizeLine.get(rowNumberIndex));
            final int columnNumber = Integer.parseInt(
                    sizeLine.get(columnNumberIndex));
            int elemCounter = 0;
            while (listIterator.hasNext()) {
                var line = listIterator.next().trim();
                var elems = StringParser.parseString(line);
                var digitNumber = elems.stream()
                        .filter(this::isNumeric).count();
                elemCounter += digitNumber;
                if (elems.size() != digitNumber) {
                    return false;
                }

            }
            isValid &= (elemCounter == (rowNumber * columnNumber));
        } catch (NumberFormatException | NoSuchElementException newE) {
            return false;
        }
        return isValid;
    }
    private boolean isNumeric(final String s) {
        return s.matches("[-+]?\\d+");
    }
}
