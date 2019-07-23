package test.task1oop.controller.command;

import by.training.task1oop.controller.command.DeletePlaneCommand;
import by.training.task1oop.service.InitFromFileService;
import by.training.task1oop.service.exception.ServiceException;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

public class DeletePlaneCommandTest {
    DeletePlaneCommand command = new DeletePlaneCommand();
    private static final String LOG_MESSAGE = "Invalid args in line: ";
    private static final String POSITIVE_MESSAGE = "Plane was deleted";
    private static final String NEGATIVE_MESSAGE =
            "No such plane in the company";
    private static final String EXCEPTION_MESSAGE = "Plane creating error";

    @BeforeClass
    public void reposInit() throws ServiceException {
        InitFromFileService initFromFileService = new InitFromFileService();
        initFromFileService.initFromFile("input/input.txt");
    }
    @DataProvider(name = "Positive plane args")
    public Object[][] positivePlanesData() {
        return new Object[][]{
                {"TRANSPORT 9865687556 20 1000 1200 Boeing-C-17 800", POSITIVE_MESSAGE},
                {"PASSENGER 1265687556 100  350 850 Boeing-247 NARROW_BODY", POSITIVE_MESSAGE},
                {"TRANSPORT 9875687556 20 1000 1200 Boeing-C-17 800", NEGATIVE_MESSAGE}};
    }

    @DataProvider(name = "negative planes data")
    public Object[][]negativePlanesData() {
        return new Object[][]{
                {"TRANSPORT 1265687556 350 850 Boeing 500",
                        LOG_MESSAGE + EXCEPTION_MESSAGE},
                {null, LOG_MESSAGE + EXCEPTION_MESSAGE}};
    }

    @Test(description = "DeletePlaneResponsePositiveTestTest",
    dataProvider = "Positive plane args")
    public void deletePlaneResponsePositiveTest(String params, String response) {
        assertEquals(command.execute(params), response);
    }



    @Test(description = "negative scenario",
            dataProvider = "negative planes data")
    public void deletePlaneNegativeTest(String params, String response) {
        assertEquals(command.execute(params), response);
    }
}
