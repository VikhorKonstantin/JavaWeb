package test.task1oop.validator;

import by.training.task1oop.bean.validator.AgriculturalPlaneValidator;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.List;

import static org.testng.AssertJUnit.assertEquals;

public class AgriculturalPlaneValidatorTest {
    AgriculturalPlaneValidator planeValidator = new AgriculturalPlaneValidator();
    @DataProvider(name = "dataForValidation")
    public Object[][] createDataForValidator() {
        return new Object[][]{{List.of("AGRICULTURAL", "1265687556",
                "100", "350", "850", "Boeing-247", "1000", "70"), true},
                {List.of("TRANSPORT", "9865687556", "20", "1000",
                        "1000", "Boeing-247", "800"),false},
                {List.of("AGRICULTURAL", "1265687556",
                        "100", "350", "850", "Boeing-247", "aaa", "70"), false},
                {List.of("","","","","", "","",""), false},
                {List.of("AGRICULTURAL", "1265687556",
                        "100", "350", "Boeing-247", "1000", "700"), false},
                {List.of("AGRICULTURAL", "1265687556",
                        "100", "350", "850", "Boeing-247", "1000", "7000"), false},
                {null, false},
                {List.of("PASSENGER", "1265687556", "100", "350",
                        "850", "", "NARROW_BODY"), false}};
    }
    @Test(description = "test of isValid on different lists", dataProvider = "dataForValidation")
    public void readFromFileTest(List<String> list, boolean isValid) {
        assertEquals(planeValidator.isValid(list), isValid);
    }
}
