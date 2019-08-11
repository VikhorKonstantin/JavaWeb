package test.task2thread.bean;

import by.training.task2thread.bean.entity.Matrix;
import org.testng.Assert;
import org.testng.annotations.Test;

public class MatrixTest {
    /**
     * Test data.
     */
    private static final int[][] CONTENT = new int[][]{{1, 1, 1, 1},
            {2, 2, 2, 2},
            {3, 3, 3, 3}};

    /**
     * Constructor test.
     */
    @Test(description = "Constructor test")
    public void matrixConstructorTest() {
        final var rowNumber = 3;
        final var columnNumber = 4;
        Matrix matrix = new Matrix(rowNumber, columnNumber, CONTENT);
        boolean isValid = matrix.getColumnNumber() == columnNumber
                &&  matrix.getRowNumber() == rowNumber;
        Assert.assertTrue(isValid);
    }

    /**
     * Negative scenario test.
     */
    @Test(description = "Negative scenario test")
    public void writeElementNegativeTest() {
        final var rowNumber = 3;
        final var columnNumber = 4;
        Matrix matrix = new Matrix(rowNumber, columnNumber, CONTENT);
        final var wrongIndex1 = 5;
        final var wrongIndex2 = 0;
        final var dummyNumber = 10;
        Assert.assertThrows(ArrayIndexOutOfBoundsException.class,
                () -> matrix.writeElement(wrongIndex1, wrongIndex2,
                        dummyNumber));
    }

    /**
     * Setter and getter positive scenario test.
     */
    @Test(description = "Setter and getter positive scenario test")
    public void readWritePositiveTest() {
        final var rowNumber = 3;
        final var columnNumber = 4;
        Matrix matrix = new Matrix(rowNumber, columnNumber, CONTENT);
        final var newVal = 500;
        matrix.writeElement(0, 0, newVal);
        var actualVal = matrix.readElement(0, 0);
        Assert.assertEquals(newVal, actualVal);
    }
}
