package test.task1oop.service;

import by.training.task1oop.bean.entity.PassengerPlane;
import by.training.task1oop.bean.entity.PassengerPlaneType;
import by.training.task1oop.bean.entity.Plane;
import by.training.task1oop.bean.entity.TransportPlane;
import by.training.task1oop.dao.factory.RepositoryFactory;
import by.training.task1oop.dao.repository.Repository;
import by.training.task1oop.service.DeleteService;
import by.training.task1oop.service.InitFromFileService;
import by.training.task1oop.service.exception.ServiceException;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class DeleteServiceTest {
    DeleteService service = new DeleteService();
    @BeforeClass
    public void reposInit() throws ServiceException {
        RepositoryFactory repositoryFactory = RepositoryFactory.getInstance();
        Repository<Plane> repository = repositoryFactory.getPlaneRepository();
        repository.add(new TransportPlane(1265687556, 100,
                350, 850,
                "Boeing", 500));
        repository.add(new PassengerPlane(1956525556, 50, 350,
                700, "Boeing",
                PassengerPlaneType.REGIONAL));
    }

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

    @Test(description = "Checks if plane was deleted correctly",
            dataProvider = "Positive plane args")
    public void deletePlaneAddingPositiveTest(Plane plane)
            throws ServiceException {
        assertTrue(service.deletePlane(plane));
    }

    @Test(description = "negative scenario")
    public void deletePlaneThrowsTest() {
        assertThrows(ServiceException.class,
                () -> service.deletePlane(null));
    }
}
