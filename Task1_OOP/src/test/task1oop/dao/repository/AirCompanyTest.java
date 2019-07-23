package test.task1oop.dao.repository;

import by.training.task1oop.bean.entity.TransportPlane;
import by.training.task1oop.dao.factory.RepositoryFactory;
import by.training.task1oop.dao.repository.AirCompany;
import by.training.task1oop.service.InitFromFileService;
import by.training.task1oop.service.exception.ServiceException;
import com.sun.jdi.connect.Transport;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;


public class AirCompanyTest {
    AirCompany airCompany =
            (AirCompany) RepositoryFactory.getInstance().getPlaneRepository();
    InitFromFileService service = new InitFromFileService();

    @BeforeClass
    public void initRepos() throws ServiceException {
        service.initFromFile("input/input.txt");
    }
    @DataProvider(name = "updateByIdData")
    public Object[][] createTestData() {
        return new Object[][] {
                {98657556L, false},
                {9865687556L, true}
        };
    }

    @Test(description = "total payload getter test")
    public void getTotalPayloadTest() {
        assertEquals(airCompany.getTotalPayload(), 3450);
    }

    @Test(description = "readByIdTest on positive data")
    public void readByIdTestPositive() {
        TransportPlane expectedPlane = new TransportPlane(9865687556L,
                20,
                1000, 1200,
                "Boeing-C-17", 800);
        assertEquals(expectedPlane, airCompany.readById(9865687556L));
    }

    @Test(description = "readByIdTest on negative data")
    public void readByIdTestNegative() {
        assertEquals(null, airCompany.readById(98657556L));
    }

    @Test(description = "updatePayloadByIdTest", dataProvider = "updateByIdData")
    public void updatePayloadByIdTest(long id, boolean expectedResult) {
        assertEquals(airCompany.updatePayloadById(id,5), expectedResult);
    }
}
