package test.task3composite.service;

import by.training.task3composite.service.SortWordsService;
import by.training.task3composite.service.exception.ServiceException;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static org.testng.Assert.assertThrows;
import static org.testng.AssertJUnit.assertEquals;

public class SortWordsServiceTest {
    /**
     * Service to test.
     */
    private SortWordsService service = new SortWordsService();

    /**
     * Creates NegativeScenarioData.
     *
     * @return Object[][] NegativeScenarioData
     */
    @DataProvider(name = "NegativeScenarioData")
    public Object[][] createNegativeScenarioData() {
        return new Object[][]{
                {null}, {"badFile.txt"}
        };
    }

    /**
     * Tests parseAndSortWords() on positive scenario data.
     *
     * @throws ServiceException if something goes wrong
     */
    @Test(description = "Test on positive scenario data")
    public void parseAndSortWordsTestPositive() throws ServiceException {
        final String fileName = "test_input/words_sort_data.txt";
        final String expectedString =
                "    a a It is fact that will. reader established";
        assertEquals(expectedString,
                service.parseAndSortWords(fileName).compose());
    }

    /**
     * Test on empty file.
     *
     * @throws ServiceException if something goes wrong
     */
    @Test(description = "Test on empty file")
    public void parseAndSortWordsTestEmpty() throws ServiceException {
        final String fileName = "test_input/test_empty.txt";
        var text = service.parseAndSortWords(fileName);
        assertEquals(text.compose(), "    ");
    }

    /**
     * Test on negative scenario data.
     *
     * @param fileName name of file with data
     */
    @Test(description = "Test on negative scenario data",
            dataProvider = "NegativeScenarioData")
    public void parseAndSortWordsTestNegative(final String fileName) {
        assertThrows(ServiceException.class,
                () -> service.parseAndSortWords(fileName));
    }
}
