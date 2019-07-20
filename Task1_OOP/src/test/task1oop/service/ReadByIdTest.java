package test.task1oop.service;

import by.training.task1oop.bean.entity.PassengerPlane;
import by.training.task1oop.bean.entity.PassengerPlaneType;
import by.training.task1oop.bean.entity.Plane;
import by.training.task1oop.bean.entity.TransportPlane;
import by.training.task1oop.dao.factory.RepositoryFactory;
import by.training.task1oop.dao.repository.Repository;
import by.training.task1oop.service.ReadByIdService;
import by.training.task1oop.service.exception.ServiceException;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertThrows;

public class ReadByIdTest {
    private ReadByIdService service = new ReadByIdService();
    @BeforeClass
    public void reposInit() {
        RepositoryFactory repositoryFactory = RepositoryFactory.getInstance();
        Repository<Plane> repository = repositoryFactory.getPlaneRepository();
        repository.add( new TransportPlane(556, 100,
                350, 850,
                "Boeing", 500));
        repository.add(new PassengerPlane(5556, 50, 350,
                        700, "Boeing",
                        PassengerPlaneType.REGIONAL));
    }
    @DataProvider(name = "ReadByIdPositiveTestData")
    public Object[][] createPositiveTestData() {
        return new Object[][] {
                {556, (new TransportPlane(556, 100,
                        350, 850,
                        "Boeing", 500))},
                {5556, (new PassengerPlane(5556, 50, 350,
                        700, "Boeing",
                        PassengerPlaneType.REGIONAL))}
        };
    }
    @DataProvider(name = "ReadByIdNegativeTestData")
    public Object[][] createNegativeTestData() {
        return new Object[][] {
                {1756525789},
                {4454545454544545445L},

        };
    }
    @Test(description = "TestPositiveScenario",
    dataProvider = "ReadByIdPositiveTestData")
    public void readByIdPositive(long id, Plane expectedResponse)
            throws ServiceException {
        var actualResponse = service.readById(id);
        assertEquals(actualResponse, expectedResponse);
    }

    @Test(description = "Negative scenario test",
    dataProvider = "ReadByIdNegativeTestData")
    public void readByIdNegative(long id) {
        assertThrows(ServiceException.class, () -> service.readById(id));
    }
}
