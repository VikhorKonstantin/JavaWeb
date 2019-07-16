package test.task1oop.service.parser;

import by.training.task1oop.service.parser.StringParser;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;

import static org.testng.AssertJUnit.assertEquals;

public class StringParserTest {
    @DataProvider(name = "generalData")
    public Object[][] createDataForParer() {
        return new Object[][]{{null, new ArrayList<>()},
                {"",List.of("")},
                {" ", List.of("")},
                {" WORD ", List.of("WORD")}};
    }
    @Test(description = "positive scenario of readFromFile")
    public void parseStringPositive() {
        assertEquals(List.of("PASSENGER", "1265687556", "100", "350", "850", "NARROW_BODY"),
                StringParser.parseString("PASSENGER 1265687556 100 350 850 NARROW_BODY"));
    }
    @Test(description = "positive scenario of readFromFile", dataProvider = "generalData")
    public void parseStringGeneral(String stringToParse, List<String> resultList) {
        assertEquals(StringParser.parseString(stringToParse),
                resultList);
    }
}
