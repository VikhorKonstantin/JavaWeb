package test.task3composite.dao;

import by.training.task3composite.dao.exception.DAOException;
import by.training.task3composite.dao.reader.FileStringReader;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertThrows;

public class FileStringReaderTest {
    /**
     * Reader to test.
     */
    private FileStringReader planeReader = new FileStringReader();

    /**
     * Creates data for test.
     *
     * @return positiveDataForRead
     */
    @DataProvider(name = "positiveDataForRead")
    public Object[][] createPositiveDataForSqrt() {
        return new Object[][]{
                {"    It is a long established fact that a reader will be"
                        + " distracted by the readable \n"
                        + "content of a page when looking at its layout?! The"
                        + " point of using Ipsum is that \n"
                        + "it has a more-or-less normal distribution of"
                        + " letters, as opposed to using 'Content here, \n"
                        + "content here', making it look like readable"
                        + " English.\n"
                        + "    It is a established fact that a reader"
                        + " will be of a page when looking at its layout.\n"
                        + "    Bye?!", "test_input/correct.txt"},
                {"",
                        "test_input/test_empty.txt"}};
    }

    /**
     * negative scenario of readFromFile.
     */
    @Test(description = "negative scenario of readFromFile")
    public void readFromFileNegative() {
        assertThrows(DAOException.class,
                () -> planeReader.readFromFile("badFilename.txt"));
    }

    /**
     * null scenario of readFromFile.
     */
    @Test(description = "null scenario of readFromFile")
    public void readFromFileNull() {
        assertThrows(DAOException.class,
                () -> planeReader.readFromFile(null));

    }

    /**
     * positive scenario of readFromFile.
     *
     * @param expected     expected string
     * @param filename file name
     * @throws DAOException if something goes wrong
     */
    @Test(description = "positive scenario of readFromFile",
            dataProvider = "positiveDataForRead")
    public void readFromFilePositive(final String expected,
                                     final String filename)
            throws DAOException {
        assertEquals(expected, planeReader.readFromFile(filename));
    }
}
