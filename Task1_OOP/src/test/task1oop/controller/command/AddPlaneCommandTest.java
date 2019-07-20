package test.task1oop.controller.command;

import by.training.task1oop.controller.command.AddPlaneCommand;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;


import static org.testng.Assert.assertEquals;

public class AddPlaneCommandTest {
    AddPlaneCommand command = new AddPlaneCommand();
    private static final String EXCEPTION_MESSAGE = "Plane creating error";
    private static final String LOG_MESSAGE = "Invalid args in line: ";
    private static final String POSITIVE_MESSAGE = "Plane was added";
    @DataProvider(name = "Positive plane args")
    public Object[][] positivePlanesData() {
        return new Object[][]{
                {"TRANSPORT 1265687556 100 350 850 Boeing 500"},
                {"PASSENGER 1956525556 50  350 700 Boeing REGIONAL"}};
    }

    @DataProvider(name = "negative planes data")
    public Object[][]negativePlanesData() {
        return new Object[][]{
                {"TRANSPORT 1265687556 350 850 Boeing 500"},
                {null}};
    }

    @Test(description = "AddPlaneResponsePositiveTestTest",
    dataProvider = "Positive plane args")
    public void addPlaneResponsePositiveTest(String args) {
        assertEquals(command.execute(args),
                POSITIVE_MESSAGE);
    }



    @Test(description = "negative scenario",
            dataProvider = "negative planes data")
    public void addPlaneNegativeTest(String params) {
        var expected = LOG_MESSAGE + EXCEPTION_MESSAGE;
        assertEquals(command.execute(params),
                expected);
    }
}
