package test.task3composite.service;

import by.training.task3composite.bean.entity.TextComponent;
import by.training.task3composite.service.ParseTextFromFileService;
import by.training.task3composite.service.exception.ServiceException;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static org.testng.Assert.assertThrows;
import static org.testng.AssertJUnit.assertEquals;

public class ParseTextFromFileServiceTest {
    /**
     * Service to test.
     */
    private ParseTextFromFileService service = new ParseTextFromFileService();

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
     * Tests parseTextFromFile() on positive scenario data.
     *
     * @throws ServiceException if something goes wrong
     */
    @Test(description = "Test on positive scenario data")
    public void parseTextFromFileTestPositive() throws ServiceException {
        final String fileName = "test_input/correct.txt";
        TextComponent textComponent = service.parseTextFromFile(fileName);
        final String expectedString = "    It is a long established fact that"
                + " a reader will be distracted by the readable "
                + "content of a page when looking at its layout?! The point of"
                + " using Ipsum is that "
                + "it has a more-or-less normal distribution of letters, as"
                + " opposed to using 'Content here, "
                + "content here', making it look like readable English.\n"
                + "    It is a established fact that a reader will be of a page"
                + " when looking at its layout.\n"
                + "    Bye?!";
        assertEquals(textComponent.compose(), expectedString);
    }

    /**
     * Test on empty file.
     *
     * @throws ServiceException if something goes wrong
     */
    @Test(description = "Test on empty file")
    public void parseTextFromFileTestEmpty() throws ServiceException {
        final String fileName = "test_input/test_empty.txt";
        var text = service.parseTextFromFile(fileName);
        assertEquals(text.compose(), "    ");
    }

    /**
     * Test on negative scenario data.
     *
     * @param fileName name of file with data
     */
    @Test(description = "Test on negative scenario data",
            dataProvider = "NegativeScenarioData")
    public void parseTextFromFileTestNegative(final String fileName) {
        assertThrows(ServiceException.class,
                () -> service.parseTextFromFile(fileName));
    }

}
