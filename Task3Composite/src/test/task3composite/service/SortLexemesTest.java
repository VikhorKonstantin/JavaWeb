package test.task3composite.service;

import by.training.task3composite.service.SortLexemesService;
import by.training.task3composite.service.exception.ServiceException;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static org.testng.Assert.assertThrows;
import static org.testng.AssertJUnit.assertEquals;

public class SortLexemesTest {
    private SortLexemesService service = new SortLexemesService();
    
    @DataProvider(name = "NegativeScenarioData")
    public Object[][] createNegativeScenarioData() {
        return new Object[][]{
                {null}, {"badFile.txt"}
        };
    }
    
    @Test(description = "Test on negative scenario data")
    public void parseTextFromFileTestPositive() throws ServiceException {
        final String fileName = "test_input/lexeme_sort_data.txt";
        final char ch = 'e';
        final String expectedString = "    eee. sentence ee New";
        assertEquals(service.parseAndSortLexemes(fileName, ch).compose()
                , expectedString);
    }

    @Test(description = "Test on empty file")
    public void parseTextFromFileTestEmpty() throws ServiceException {
        final String fileName = "test_input/test_empty.txt";
        final char dummyCharacter = ' ';
        var text = service.parseAndSortLexemes(fileName, dummyCharacter);
        assertEquals(text.compose(), "    ");
    }

    @Test(description = "Test on negative scenario data",
            dataProvider = "NegativeScenarioData")
    public void parseTextFromFileTestNegative(final String fileName) {
        final char dummyCharacter = ' ';
        assertThrows(ServiceException.class,
                () -> service.parseAndSortLexemes(fileName, dummyCharacter));
    }
    
}
