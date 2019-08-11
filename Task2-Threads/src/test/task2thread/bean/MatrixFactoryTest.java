package test.task2thread.bean;

import by.training.task2thread.bean.entity.Matrix;
import by.training.task2thread.bean.exception.BeanException;
import by.training.task2thread.bean.factory.MatrixFactory;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.List;

import static org.testng.Assert.assertThrows;
import static org.testng.AssertJUnit.assertEquals;

public class MatrixFactoryTest {
    /**
     * Factory to test.
     */
    private MatrixFactory matrixFactory = MatrixFactory.getInstance();

    /**
     * Creates data for test.
     * @return NegativeScenarioData
     */
    @DataProvider(name = "NegativeScenarioData")
    public Object[][] createNegativeScenarioData() {
        return new Object[][]{
            {List.of("2 2")}, {null}, {List.of("2 2", "1 1", "a 1")}
        };
    }

    /**
     * NegativeScenarioTest.
     * @param matrixParams params for factory
     */
    @Test(description = "NegativeScenarioTest",
    dataProvider = "NegativeScenarioData")
    public void createMatrixNegativeTest(final List<String> matrixParams) {
        assertThrows(BeanException.class,
                () -> matrixFactory.createMatrix(matrixParams));
    }

    /**
     * PositiveScenarioTest.
     * @throws BeanException if something goes wrong
     */
    @Test(description = "PositiveScenarioTest")
    public void createMatrixPositiveTest() throws BeanException {
        final var matrixParams = List.of("3 3", "1 1 1", "2 2 2", "3 3 3");
        final int[][] content = new int[][]{{1, 1, 1}, {2, 2, 2}, {3, 3, 3}};
        final int rowAndColumnNumber = 3;
        var expectedMatrix =
                new Matrix(rowAndColumnNumber, rowAndColumnNumber, content);
        var actualMatrix = matrixFactory.createMatrix(matrixParams);
        assertEquals(expectedMatrix, actualMatrix);
    }
}
