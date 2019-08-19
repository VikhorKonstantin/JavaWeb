package test.task2thread.controller.command;

import by.training.task2thread.controller.command.FillMatrixCommand;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertFalse;

public class FillMatrixCommandTest {
    /**
     * Command to test.
     */
    private FillMatrixCommand command = new FillMatrixCommand();
    /**
     * logger message.
     */
    private static final String LOG_MESSAGE = "Invalid args: ";
    /**
     * Service exception message.
     */
    private static final String INVALID_ARGS_MSG =
            "Matrix should be square. ";
    /**
     * Name of exception.
     */
    private static final String DAO_EXCEPTION_NAME =
            "by.training.task2thread.dao.exception.DAOException";
    /**
     * Name of exception.
     */
    private static final String BEAN_EXCEPTION_NAME =
            "by.training.task2thread.bean.exception.BeanException";

    /**
     * Creates data for test.
     * @return NegativeScenarioData
     */
    @DataProvider(name = "NegativeScenarioData")
    public Object[][] createNegativeScenarioData() {
        return new Object[][] {
                {"test_input/badName.txt 2",
                        LOG_MESSAGE + DAO_EXCEPTION_NAME
                                + ": File opening error"},
                {"test_input/test_matrix_invalid.txt 2",
                        LOG_MESSAGE + BEAN_EXCEPTION_NAME
                                + ": Invalid matrix params"},
                {"test_input/bad_size_for_filling.txt 2",
                        LOG_MESSAGE + INVALID_ARGS_MSG},
                {"test_input/test_matrix_valid.txt 0",
                        LOG_MESSAGE
                        + "Number of threads should be greater than 0. "},
                {"test_input/test_matrix_valid.txt",
                        LOG_MESSAGE + "Index 1 out-of-bounds for length 1"},
                {"test_input/test_matrix_valid.txt 5555555555555555555555555",
                        LOG_MESSAGE
                        + "For input string: \"5555555555555555555555555\""}
        };
    }

    /**
     * TestOnNegativeScenarioData.
     * @param request request for command
     * @param expectedResponse expected response
     */
    @Test(description = "TestOnNegativeScenarioData",
    dataProvider = "NegativeScenarioData")
    public void fillMatrixCommandNegativeTest(final String request,
                                              final String expectedResponse) {
        var actual = command.execute(request);
        assertEquals(actual, expectedResponse);
    }

    /**
     * Test on valid data.
     */
    @Test(description = "TestOnValidData")
    public void fillMatrixValidDataTest() {
        final String request = "test_input/test_matrix_valid.txt 2";
        var response = command.execute(request);
        var regEx = "[^0-9]0[^0-9]";
        Pattern pattern = Pattern.compile(regEx);
        Matcher matcher = pattern.matcher(response);
        assertFalse(matcher.find());
    }
}
