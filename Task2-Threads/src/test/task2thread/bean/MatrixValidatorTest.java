package test.task2thread.bean;

import by.training.task2thread.bean.validator.MatrixValidator;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.List;

import static org.testng.Assert.assertEquals;

public class MatrixValidatorTest {
    /**
     * Creates data for test.
     * @return MatrixValidatorTestData
     */
    @DataProvider(name = "MatrixValidatorTestData")
    public Object[][] createTestData() {
        return new Object[][] {
                {List.of("2 2"), false}, {null, false},
                {List.of("2 2", "1 1", "a 1"), false}, {List.of(""), false},
                {List.of("2 2", "2 1 2", "2 2"), false},
                {List.of("2 2 2", "2 2 ", "2 2"), false},
                {List.of("2 2", "2", "2 2"), false},
                {List.of("2 2", "2", "2 3 2"), false},
                {List.of("2 2", "1 2", "1 3"), true},
        };
    }

    /**
     * ValidatorTest.
     * @param matrixData matrix data to test
     * @param expectedValue expected value
     */
    @Test(description = "ValidatorTest",
            dataProvider = "MatrixValidatorTestData")
    public void isValidTest(final List<String> matrixData,
                            final boolean expectedValue) {
        MatrixValidator validator = new MatrixValidator();
        var actualValue = validator.isMatrixDataValid(matrixData);
        assertEquals(actualValue, expectedValue);
    }
}
