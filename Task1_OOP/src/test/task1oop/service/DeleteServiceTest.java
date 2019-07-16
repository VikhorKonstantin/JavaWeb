package test.task1oop.service;

import by.training.task1oop.exception.WrongArgumentsException;
import by.training.task1oop.service.DeleteService;
import by.training.task1oop.service.ReadByIdService;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertThrows;

public class DeleteServiceTest {
    DeleteService service = new DeleteService();
    private static final String POSITIVE_MESSAGE = "Plane was deleted";
    private static final String NO_SUCH_ELEMENT = "No such plane in repository";
    @DataProvider(name = "Plane args")
    public Object[][] planesData() {
        return new Object[][]{
                {"TRANSPORT 1265687556 100 350 850 Boeing 500",
                        "1265687556"},
                {"PASSENGER 1956525556 50  350 700 Boeing REGIONAL",
                        "1956525556"}};
    }
    @DataProvider(name = "negative planes data")
    public Object[][]negativePlanesData() {
        return new Object[][]{
                new Object[] {"TRANSPORT 1265687556 350 850 Boeing 500"},
                new Object[]{null}};
    }

    @Test(description = "DeletePlaneResponsePositiveTestTest")
    public void addPlaneResponsePositiveTest()
            throws WrongArgumentsException {
        assertEquals(service.deletePlane(
                "TRANSPORT 1265687556 100 350 850 Boeing 500"),
                POSITIVE_MESSAGE);
    }

    @Test(description = "Checks if plane was deleted correctly",
            dataProvider = "Plane args")
    public void addPlaneAddingPositiveTest(String params, String id)
            throws WrongArgumentsException {
        service.deletePlane(params);
        ReadByIdService readByIdService = new ReadByIdService();
        assertEquals(NO_SUCH_ELEMENT, readByIdService.readById(id) );
    }

    @Test(description = "negative scenario",
            dataProvider = "negative planes data")
    public void deletePlaneThrowsTest(String params) {
        assertThrows(WrongArgumentsException.class,
                () -> service.deletePlane(params));
    }
}
