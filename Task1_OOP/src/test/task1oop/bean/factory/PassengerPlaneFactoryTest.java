package test.task1oop.bean.factory;

import by.training.task1oop.bean.entity.PassengerPlane;
import by.training.task1oop.bean.entity.PassengerPlaneType;
import by.training.task1oop.exception.WrongArgumentsException;
import by.training.task1oop.bean.factory.PassengerPlaneFactory;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.List;

import static org.testng.Assert.assertThrows;
import static org.testng.AssertJUnit.assertEquals;

public class PassengerPlaneFactoryTest {

    @DataProvider(name = "NegativeScenarioData")
    public Object[][] createDataForFactory() {
        return new Object[][]{
                new Object[]{List.of("PASSENGER","15151515", "50",
                        "500", "500", "Boeing-247", "NARrROW_BODY")},
                new Object[]{List.of("1265687556", "aaa", "350",
                        "850", "Boeing-247", "NARROW_BODY")}
        };
    }
    @Test(description = "createPlaneTestPositive")
    public void createPlaneTestPositive() throws WrongArgumentsException {
        PassengerPlane expected = new PassengerPlane(15151515, 50,
                500, 500, "Boeing-247", PassengerPlaneType.NARROW_BODY);
        PassengerPlaneFactory passengerPlaneFactory = PassengerPlaneFactory.getInstance();
        var actual = passengerPlaneFactory.createPlane(List.of("PASSENGER","15151515", "50",
                "500", "500", "Boeing-247", "NARROW_BODY"));
        assertEquals(actual,expected);
    }

    @Test(description = "createPlaneTestNegative", dataProvider = "NegativeScenarioData")
    public void createPlaneTestNegative(List<String> params) {
        PassengerPlaneFactory passengerPlaneFactory = PassengerPlaneFactory.getInstance();
        assertThrows(WrongArgumentsException.class,()->passengerPlaneFactory.createPlane(params) );
    }
}
