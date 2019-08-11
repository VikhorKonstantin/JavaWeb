package test.task2thread.service;

import by.training.task2thread.service.parser.StringParser;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;

import static org.testng.Assert.assertEquals;

public class StringParserTest {
    /**
     * Creates data for test.
     * @return generalData
     */
    @DataProvider(name = "generalData")
    public Object[][] createDataForParer() {
        return new Object[][]{{null, new ArrayList<>()},
                {"", List.of("")},
                {" ", List.of("")},
                {" WORD ", List.of("WORD")}};
    }

    /**
     * Test of positive scenario of readFromFile.
     */
    @Test(description = "positive scenario of readFromFile")
    public void parseStringPositive() {
        assertEquals(List.of("PASSENGER", "1265687556", "100",
                "350", "850", "NARROW_BODY"),
                StringParser.parseString(
                        "PASSENGER 1265687556 100 350 850 NARROW_BODY"));
    }

    /**
     * Test general scenario readFromFile.
     * @param stringToParse string to parse
     * @param resultList expected result
     */
    @Test(description = "general readFromFile", dataProvider = "generalData")
    public void parseStringGeneral(final String stringToParse,
                                   final List<String> resultList) {
        assertEquals(StringParser.parseString(stringToParse),
                resultList);
    }
}
