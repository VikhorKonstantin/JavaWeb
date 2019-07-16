package test.task1oop.service;

import by.training.task1oop.bean.entity.PassengerPlane;
import by.training.task1oop.bean.entity.PassengerPlaneType;
import by.training.task1oop.bean.entity.Plane;
import by.training.task1oop.bean.entity.TransportPlane;
import by.training.task1oop.exception.WrongArgumentsException;
import by.training.task1oop.service.AddService;
import by.training.task1oop.service.ReadByIdService;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertThrows;

public class AddServiceTest {
    AddService service = new AddService();
    private static final String POSITIVE_MESSAGE = "Plane was added";
    @DataProvider(name = "Positive plane args")
    public Object[][] positivePlanesData() {
        return new Object[][]{
                {"TRANSPORT 1265687556 100 350 850 Boeing 500",
                        new TransportPlane(1265687556, 100,
                                350, 850,
                                "Boeing", 500),
                        "1265687556"},
                {"PASSENGER 1956525556 50  350 700 Boeing REGIONAL",
                        new PassengerPlane(1956525556, 50, 350,
                                700, "Boeing",
                                PassengerPlaneType.REGIONAL),
                        "1956525556"}};



    }

    @DataProvider(name = "negative planes data")
    public Object[][]negativePlanesData() {
        return new Object[][]{
               new Object[] {"TRANSPORT 1265687556 350 850 Boeing 500"},
                new Object[]{null}};
    }

    @Test(description = "AddPlaneResponsePositiveTestTest")
    public void addPlaneResponsePositiveTest()
            throws WrongArgumentsException {
        assertEquals(service.addPlane(
                "TRANSPORT 1265687556 100 350 850 Boeing 500"),
                POSITIVE_MESSAGE);
    }

    @Test(description = "Checks if plane was added correctly",
    dataProvider = "Positive plane args")
    public void addPlaneAddingPositiveTest(String params, Plane actualPlane, String id)
        throws WrongArgumentsException {
        service.addPlane(params);
        ReadByIdService readByIdService = new ReadByIdService();
        assertEquals(actualPlane.toString(), readByIdService.readById(id) );
    }

    @Test(description = "negative scenario",
            dataProvider = "negative planes data")
    public void addPlaneThrowsTest(String params) {
        assertThrows(WrongArgumentsException.class,
                () -> service.addPlane(params));
    }
}
