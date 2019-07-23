package test.task1oop.controller.command;

import by.training.task1oop.bean.entity.PassengerPlane;
import by.training.task1oop.bean.entity.PassengerPlaneType;
import by.training.task1oop.bean.entity.Plane;
import by.training.task1oop.bean.entity.TransportPlane;
import by.training.task1oop.controller.command.ReadByIdCommand;
import by.training.task1oop.dao.factory.RepositoryFactory;
import by.training.task1oop.dao.repository.Repository;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

public class ReadByIdCommandTest {
    private static final String NO_SUCH_ELEMENT = "No such plane in repository";
    private static final String LOG_MESSAGE = "Invalid args in line: ";
    private ReadByIdCommand command = new ReadByIdCommand();
    @BeforeClass
    public void reposInit() {
        RepositoryFactory repositoryFactory = RepositoryFactory.getInstance();
        Repository<Plane> repository = repositoryFactory.getPlaneRepository();
        repository.add( new TransportPlane(12873187556L, 100,
                350, 850,
                "Boeing", 500));
        repository.add(new PassengerPlane(19579251556L, 50, 350,
                700, "Boeing",
                PassengerPlaneType.REGIONAL));
    }
    @DataProvider(name = "ReadByIdPositiveTestData")
    public Object[][] createPositiveTestData() {
        return new Object[][] {
                {"12873187556", (new TransportPlane(12873187556L, 100,
                        350, 850,
                        "Boeing", 500)).toString()},
                {"19579251556", (new PassengerPlane(19579251556L, 50, 350,
                        700, "Boeing",
                        PassengerPlaneType.REGIONAL)).toString()},
                {"168586598645", LOG_MESSAGE + NO_SUCH_ELEMENT}
        };
    }
    @DataProvider(name = "ReadByIdNegativeTestData")
    public Object[][] createNegativeTestData() {
        return new Object[][] {
                {null, LOG_MESSAGE + "null" },
                {"aasaasc", LOG_MESSAGE + "For input string: \"aasaasc\""},
                {"4454545454544545445454545454545454544", LOG_MESSAGE
                + "For input string: \"4454545454544545445454545454545454544\""},
                {"", LOG_MESSAGE + "For input string: \"\""},
                {" ", LOG_MESSAGE + "For input string: \" \""}
        };
    }
    @Test(description = "TestPositiveScenario",
            dataProvider = "ReadByIdPositiveTestData")
    public void readByIdPositive(String id, String expectedResponse) {
        var actualResponse = command.execute(id);
        assertEquals(actualResponse, expectedResponse);
    }

    @Test(description = "Negative scenario test",
            dataProvider = "ReadByIdNegativeTestData")
    public void readByIdNegative(String id, String expectedResponse) {
        var actualResponse = command.execute(id);
        assertEquals(actualResponse, expectedResponse);
    }
}
