package test.task2thread.service;

import by.training.task2thread.service.CreateMatrixFromFileService;
import by.training.task2thread.service.exception.ServiceException;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static org.testng.Assert.assertThrows;

public class CreateMatrixFromFileTest {
    /**
     * Service to test.
     */
    private final CreateMatrixFromFileService service =
            new CreateMatrixFromFileService();

    /**
     * Create data for test.
     * @return NegativeScenarioTestData
     */
    @DataProvider(name = "NegativeScenarioTestData")
    public Object[][] createNegativeTestData() {
        return new Object[][] {
                {"test_input/test_empty.txt"},
                {"test_input/test_invalid.txt"}
        };
    }

    /**
     * Test on negative scenario.
     * @param filename file name
     */
    @Test(description = "Negative scenario test",
    dataProvider = "NegativeScenarioTestData")
    public void createMatrixNegativeScenario(final String filename) {
        assertThrows(ServiceException.class,
                () -> service.createMatrixFromFile(filename));
    }
}
