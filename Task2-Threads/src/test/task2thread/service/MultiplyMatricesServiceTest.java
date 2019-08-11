package test.task2thread.service;

import by.training.task2thread.bean.entity.Matrix;
import by.training.task2thread.service.MultiplyMatricesService;
import by.training.task2thread.service.exception.ServiceException;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertThrows;

public class MultiplyMatricesServiceTest {
    /**
     * Service to test.
     */
    private MultiplyMatricesService service = new MultiplyMatricesService();

    /**
     * Creates data for test.
     * @return NegativeScenarioData
     */
    @DataProvider(name = "NegativeScenarioData")
    public Object[][] createNegativeScenarioData() {
        final var rowsAndColumnsNumber = 2;
        final var content1 = new int[][]{{1, 0}, {0, 1}};
        var matrix1 = new Matrix(rowsAndColumnsNumber,
                rowsAndColumnsNumber, content1);
        final var content2 = new int[][]{{1, 2, 3}, {1, 2, 3}, {1, 2, 3}};
        final var rowsAndColumnsNumber2 = 3;
        var matrix2 = new Matrix(rowsAndColumnsNumber2,
                rowsAndColumnsNumber2, content2);
        return new Object[][]{
                {matrix1, matrix2},
                {matrix1, null}
        };
    }

    /**
     * PositiveScenarioTest.
     * @throws ServiceException if something goes wrong
     */
    @Test(description = "PositiveScenarioTest")
    public void multiplyPositiveScenarioTest() throws ServiceException {
        final var content1 = new int[][]{{1, 0}, {0, 1}};
        final var rowsAndColumnsNumber = 2;
        var matrix1 = new Matrix(rowsAndColumnsNumber,
                rowsAndColumnsNumber, content1);
        final var content2 = new int[][]{{1, 2, 3}, {1, 2, 3}};
        final var rNumber = 2;
        final var cNumber = 3;
        var matrix2 = new Matrix(rNumber, cNumber, content2);
        var expectedMatrix = matrix2;
        var actualMatrix = service.multiplyMatrices(matrix1, matrix2);
        assertEquals(expectedMatrix, actualMatrix);
    }

    /**
     * NegativeScenarioTest.
     * @param newMatrix1 first matrix to multiply
     * @param newMatrix2 second matrix to multiply
     */
    @Test(description = "NegativeScenarioTest",
            dataProvider = "NegativeScenarioData")
    public void multiplyNegativeScenarioTest(final Matrix newMatrix1,
                                             final Matrix newMatrix2) {
        assertThrows(ServiceException.class,
                () -> service.multiplyMatrices(newMatrix1, newMatrix2));

    }
}
