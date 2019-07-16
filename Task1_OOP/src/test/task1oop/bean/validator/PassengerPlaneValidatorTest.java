package test.task1oop.bean.validator;

import by.training.task1oop.bean.validator.PassengerPlaneValidator;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.List;

import static org.testng.AssertJUnit.assertEquals;

public class PassengerPlaneValidatorTest {
    private PassengerPlaneValidator planeValidator = new PassengerPlaneValidator();
    @DataProvider(name = "dataForValidation")
    public Object[][] createDataForValidator() {
        return new Object[][]{{List.of("PASSENGER", "1265687556",
                "100", "350", "850", "Boeing-247", "NARROW_BODY"), true},
                {List.of("TRANSPORT", "9865687556", "20", "1000",
                        "1000", "Boeing-247", "800"),false},
                {List.of("PASSENGER", "1265687556", "aaa", "350",
                        "850", "Boeing-247", "NARROW_BODY"), false},
                {List.of("","","","","", "",""), false},
                {List.of("PASSENGER", "1265687556", "350", "850",
                        "Boeing-247", "NARROW_BODY"), false},
                {List.of("PASSENGER", "1265687556", "35655555555555555550",
                        "850", "Boeing-247", "NARROW_BODY"), false},
                {null, false},
                {List.of("PASSENGER", "1265687556", "100", "350",
                        "850", "", "NARROW_BODY"), false}};
    }
    @Test(description = "test of isValid on different lists", dataProvider = "dataForValidation")
    public void readFromFileTest(List<String> list, boolean isValid) {
        assertEquals(planeValidator.isValid(list), isValid);
    }
}
