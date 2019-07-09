package test.task1oop.validator;

import by.training.task1oop.validator.DataValidator;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.Arrays;
import java.util.List;

import static org.testng.AssertJUnit.assertEquals;

public class DataValidatorTest {
    DataValidator dataValidator = new DataValidator();
    @DataProvider(name = "dataForRead")
    public Object[][] createDataForSqrt() {
        return new Object[][]{{Arrays.asList("PASSENGER", "1265687556", "100", "350", "850", "NARROW_BODY"), true},
                {Arrays.asList("TRANSPORT", "9865687556", "20", "1000", "1000", "800"),true},
                {Arrays.asList("PASSENGER", "1265687556", "aaa", "350", "850", "NARROW_BODY"), false},
                {Arrays.asList("","","","","",""), false},
                {Arrays.asList("TRANSPORT", "9865687556", "20", "1000", "1000", "NARROW_BODY"),false},
                {Arrays.asList("PASSENGER", "1265687556", "350", "850", "NARROW_BODY"), false},
                {Arrays.asList("PASSENGER", "1265687556", "35655555555555555550", "850", "NARROW_BODY"), false},
                {null, false}};
    }
    @Test(description = "test of readFromFile on different lists", dataProvider = "dataForRead")
    public void readFromFileTest(List<String> list, boolean isValid) {
        assertEquals(dataValidator.isDataValid(list), isValid);
    }
}
