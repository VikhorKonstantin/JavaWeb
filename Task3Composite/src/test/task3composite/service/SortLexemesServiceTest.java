package test.task3composite.service;

import by.training.task3composite.service.SortLexemesService;
import by.training.task3composite.service.exception.ServiceException;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static org.testng.Assert.assertThrows;
import static org.testng.AssertJUnit.assertEquals;

public class SortLexemesServiceTest {
    /**
     * Service to test.
     */
    private SortLexemesService service = new SortLexemesService();

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
     * Tests parseAndSortLexemes() on positive scenario data.
     *
     * @throws ServiceException if something goes wrong
     */
    @Test(description = "Test on positive scenario data")
    public void parseAndSortLexemesTestPositive() throws ServiceException {
        final String fileName = "test_input/lexeme_sort_data.txt";
        final char ch = 'e';
        final String expectedString = "    eee. sentence ee New";
        assertEquals(service.parseAndSortLexemes(fileName, ch).compose(),
                expectedString);
    }

    /**
     * Test on empty file.
     *
     * @throws ServiceException if something goes wrong
     */
    @Test(description = "Test on empty file")
    public void parseAndSortLexemesFileTestEmpty() throws ServiceException {
        final String fileName = "test_input/test_empty.txt";
        final char dummyCharacter = ' ';
        var text = service.parseAndSortLexemes(fileName, dummyCharacter);
        assertEquals(text.compose(), "    ");
    }

    /**
     * Test on negative scenario data.
     *
     * @param fileName name of file with data
     */
    @Test(description = "Test on negative scenario data",
            dataProvider = "NegativeScenarioData")
    public void parseAndSortLexemesTestNegative(final String fileName) {
        final char dummyCharacter = ' ';
        assertThrows(ServiceException.class,
                () -> service.parseAndSortLexemes(fileName, dummyCharacter));
    }

}
