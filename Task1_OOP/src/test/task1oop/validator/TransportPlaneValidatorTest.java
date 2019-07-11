package test.task1oop.validator;

import by.training.task1oop.validator.TransportPlaneValidator;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.List;

import static org.testng.Assert.assertEquals;

public class TransportPlaneValidatorTest {
    TransportPlaneValidator planeValidator = new TransportPlaneValidator();
    @DataProvider(name = "dataForValidation")
    public Object[][] createDataForValidator() {
        return new Object[][]{{List.of("TRANSPORT", "1265687556",
                "100", "350", "850", "Boeing-247", "800"), true},
                {List.of("TRANSPORT", "9865687556", "20", "1000",
                        "1000", "Boeing-247", "NARROW_BODY"),false},
                {List.of("TRANSPORT", "1265687556", "aaa", "350",
                        "850", "Boeing-247", "800"), false},
                {List.of("","","","","", "",""), false},
                {List.of("TRANSPORT", "1265687556", "350", "850",
                        "Boeing-247", "800"), false},
                {List.of("TRANSPORT", "1265687556", "35655555555555555550",
                        "850", "Boeing-247", "800"), false},
                {null, false},
                {List.of("TRANSPORT", "1265687556", "100", "350",
                        "850", "", "800"), false}};
    }
    @Test(description = "test of isValid on different lists", dataProvider = "dataForValidation")
    public void readFromFileTest(List<String> list, boolean isValid) {
        assertEquals(planeValidator.isValid(list), isValid);
    }
}
