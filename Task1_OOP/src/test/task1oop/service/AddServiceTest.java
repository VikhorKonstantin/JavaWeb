package test.task1oop.service;

import by.training.task1oop.bean.entity.PassengerPlane;
import by.training.task1oop.bean.entity.PassengerPlaneType;
import by.training.task1oop.bean.entity.Plane;
import by.training.task1oop.bean.entity.TransportPlane;
import by.training.task1oop.service.AddService;
import by.training.task1oop.service.exception.ServiceException;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;


import static org.testng.Assert.*;

public class AddServiceTest {
    AddService service = new AddService();
    @DataProvider(name = "Positive plane args")
    public Object[][] positivePlanesData() {
        return new Object[][]{
                {new TransportPlane(1265687556, 100,
                350, 850,
                "Boeing", 500)},
                {new PassengerPlane(1956525556, 50, 350,
                                700, "Boeing",
                                PassengerPlaneType.REGIONAL)}};
    }

    @Test(description = "Checks if plane was added correctly",
            dataProvider = "Positive plane args")
    public void addPlaneAddingPositiveTest(Plane plane)
            throws ServiceException {
       assertTrue(service.addPlane(plane));
    }

    @Test(description = "negative scenario")
    public void addPlaneThrowsTest() {
        assertThrows(ServiceException.class,
                () -> service.addPlane(null));
    }
}
