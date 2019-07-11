package test.task1oop.factory;

import by.training.task1oop.entity.TransportPlane;
import by.training.task1oop.exception.WrongArgumentsException;
import by.training.task1oop.factory.TransportPlaneFactory;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.List;

import static org.testng.Assert.assertThrows;
import static org.testng.AssertJUnit.assertEquals;

public class TransportPlaneFactoryTest {

    @DataProvider(name = "NegativeScenarioData")
    public Object[][] createDataForFactory() {
        return new Object[][]{
                new Object[]{List.of("TRANSPORT","15151515", "50",
                        "500", "500", "Boeing-247", "800000")},
                new Object[]{List.of("1265687556", "aaa", "350",
                        "850", "Boeing-247", "500")}
        };
    }
    @Test(description = "createPlaneTestPositive")
    public void createPlaneTestPositive() throws WrongArgumentsException {
        TransportPlane expected = new TransportPlane(15151515, 50,
                500, 500, "Boeing-247", 800);
        TransportPlaneFactory transportPlaneFactory = TransportPlaneFactory.getInstance();
        var actual = transportPlaneFactory.createPlane(List.of("TRANSPORT","15151515", "50",
                "500", "500", "Boeing-247", "800"));
        assertEquals(actual,expected);
    }

    @Test(description = "createPlaneTestNegative", dataProvider = "NegativeScenarioData")
    public void createPlaneTestNegative(List<String> params) {
        TransportPlaneFactory transportPlaneFactory = TransportPlaneFactory.getInstance();
        assertThrows(WrongArgumentsException.class,()->transportPlaneFactory.createPlane(params) );
    }
}
