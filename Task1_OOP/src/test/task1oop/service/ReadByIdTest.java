package test.task1oop.service;

import by.training.task1oop.bean.entity.PassengerPlane;
import by.training.task1oop.bean.entity.PassengerPlaneType;
import by.training.task1oop.bean.entity.Plane;
import by.training.task1oop.bean.entity.TransportPlane;
import by.training.task1oop.dao.factory.RepositoryFactory;
import by.training.task1oop.dao.repository.Repository;
import by.training.task1oop.exception.WrongArgumentsException;
import by.training.task1oop.service.ReadByIdService;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertThrows;

public class ReadByIdTest {
    private static final String NO_SUCH_ELEMENT = "No such plane in repository";
    private ReadByIdService service = new ReadByIdService();
    @BeforeTest
    public void reposInit() {
        RepositoryFactory repositoryFactory = RepositoryFactory.getInstance();
        Repository<Plane> repository = repositoryFactory.getPlaneRepository();
        repository.add( new TransportPlane(1265687556, 100,
                350, 850,
                "Boeing", 500));
        repository.add(new PassengerPlane(1956525556, 50, 350,
                        700, "Boeing",
                        PassengerPlaneType.REGIONAL));
    }
    @DataProvider(name = "ReadByIdPositiveTestData")
    public Object[][] createPositiveTestData() {
        return new Object[][] {
                {"1265687556", (new TransportPlane(1265687556, 100,
                        350, 850,
                        "Boeing", 500)).toString()},
                {"1956525556", (new PassengerPlane(1956525556, 50, 350,
                        700, "Boeing",
                        PassengerPlaneType.REGIONAL)).toString()},
                {"196586598645", NO_SUCH_ELEMENT}
        };
    }
    @DataProvider(name = "ReadByIdNegativeTestData")
    public Object[][] createNegativeTestData() {
        return new Object[][] {
                {null},
                {"aasaasc"},
                {"4454545454544545445454545454545454544"},
                {""},
                {" "}
        };
    }
    @Test(description = "TestPositiveScenario",
    dataProvider = "ReadByIdPositiveTestData")
    public void readByIdPositive(String id, String expectedResponse)
            throws WrongArgumentsException {
        var actualResponse = service.readById(id);
        assertEquals(actualResponse, expectedResponse);
    }

    @Test(description = "Negative scenario test",
    dataProvider = "ReadByIdNegativeTestData")
    public void readByIdNegative(String id) {
        assertThrows(WrongArgumentsException.class,
                () -> service.readById(id));
    }
}
