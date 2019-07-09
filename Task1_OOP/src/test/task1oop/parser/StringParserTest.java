package test.task1oop.parser;

import by.training.task1oop.parser.StringParser;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.Arrays;
import java.util.List;

import static org.testng.AssertJUnit.assertEquals;

public class StringParserTest {
    @DataProvider(name = "generalData")
    public Object[][] createDataForSqrt() {
        return new Object[][]{{null, null},
                {"",Arrays.asList("")},
                {" ", Arrays.asList("")},
                {" WORD ", Arrays.asList("WORD")}};
    }
    @Test(description = "positive scenario of readFromFile")
    public void parseStringPositive() {
        assertEquals(Arrays.asList("PASSENGER", "1265687556", "100", "350", "850", "NARROW_BODY"),
                StringParser.parseString("PASSENGER 1265687556 100 350 850 NARROW_BODY"));
    }
    @Test(description = "positive scenario of readFromFile", dataProvider = "generalData")
    public void parseStringGeneral(String stringToParse, List<String> resultList) {
        assertEquals(StringParser.parseString(stringToParse),
                resultList);
    }
}
