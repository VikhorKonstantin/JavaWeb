package test.task3composite.service;

import by.training.task3composite.service.SortParagraphsService;
import by.training.task3composite.service.exception.ServiceException;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static org.testng.Assert.assertThrows;
import static org.testng.AssertJUnit.assertEquals;

public class SortParagraphTest {
    private SortParagraphsService service = new SortParagraphsService();

    @DataProvider(name = "NegativeScenarioData")
    public Object[][] createNegativeScenarioData() {
        return new Object[][]{
                {null}, {"badFile.txt"}
        };
    }

    @Test(description = "Test on positive scenario data")
    public void parseTextFromFileTestPositive() throws ServiceException {
        final String fileName = "test_input/paragraph_sort_data.txt";
        final String expectedString =
                  "    Bye?!\n"
                + "    It is a established fact that a reader will be of a page" 
                        + " when looking at its layout.\n"
                + "    It is a long established fact that a reader will be" 
                        + " distracted by the readable \n"
                + "content of a page when looking at its layout?! The point of" 
                        + " using Ipsum is that \n"
                + "it has a more-or-less normal distribution of letters, as" 
                        + " opposed to using 'Content here, \n"
                + "content here', making it look like readable English.";
        assertEquals(expectedString
                , service.parseAndSortParagraph(fileName).compose());
    }

    @Test(description = "Test on empty file")
    public void parseTextFromFileTestEmpty() throws ServiceException {
        final String fileName = "test_input/test_empty.txt";
        var text = service.parseAndSortParagraph(fileName);
        assertEquals(text.compose(), "    ");
    }

    @Test(description = "Test on negative scenario data",
            dataProvider = "NegativeScenarioData")
    public void parseTextFromFileTestNegative(final String fileName) {
        assertThrows(ServiceException.class,
                () -> service.parseAndSortParagraph(fileName));
    }
}
