package test.task3composite.service;

import by.training.task3composite.service.SortParagraphsService;
import by.training.task3composite.service.exception.ServiceException;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static org.testng.Assert.assertThrows;
import static org.testng.AssertJUnit.assertEquals;

public class SortParagraphsServiceTest {
    /**
     * Service to test.
     */
    private SortParagraphsService service = new SortParagraphsService();

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
     * Tests parseAndSortParagraph() on positive scenario data.
     *
     * @throws ServiceException if something goes wrong
     */
    @Test(description = "Test on positive scenario data")
    public void parseAndSortParagraphTestPositive() throws ServiceException {
        final String fileName = "test_input/paragraph_sort_data.txt";
        final String expectedString =
                "    Bye?!\n"
                        + "    It is a established fact that a reader will"
                        + " be of a page"
                        + " when looking at its layout.\n"
                        + "    It is a long established fact that a reader "
                        + "will be"
                        + " distracted by the readable "
                        + "content of a page when looking at its layout?!"
                        + " The point of"
                        + " using Ipsum is that "
                        + "it has a more-or-less normal distribution of"
                        + " letters, as"
                        + " opposed to using 'Content here, "
                        + "content here', making it look like readable"
                        + " English.";
        assertEquals(expectedString,
                service.parseAndSortParagraph(fileName).compose());
    }

    /**
     * Test on empty file.
     *
     * @throws ServiceException if something goes wrong
     */
    @Test(description = "Test on empty file")
    public void parseAndSortParagraphTestEmpty() throws ServiceException {
        final String fileName = "test_input/test_empty.txt";
        var text = service.parseAndSortParagraph(fileName);
        assertEquals(text.compose(), "    ");
    }

    /**
     * Test on negative scenario data.
     *
     * @param fileName name of file with data
     */
    @Test(description = "Test on negative scenario data",
            dataProvider = "NegativeScenarioData")
    public void parseAndSortParagraphTestNegative(final String fileName) {
        assertThrows(ServiceException.class,
                () -> service.parseAndSortParagraph(fileName));
    }
}
