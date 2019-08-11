package test.task2thread.dao;

import by.training.task2thread.dao.exception.DAOException;
import by.training.task2thread.dao.reader.FileStringReader;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertThrows;

public class FileStringReaderTest {
    /**
     * Reader to test.
     */
    private FileStringReader planeReader = new FileStringReader();

    /**
     * Creates data for test.
     * @return positiveDataForRead
     */
    @DataProvider(name = "positiveDataForRead")
    public Object[][] createPositiveDataForSqrt() {
        return new Object[][]{
                {List.of("2 2", "0 2", "2 0"),
                        "test_input/test_matrix_valid.txt"},
                {new ArrayList<>(),
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
     * @param list expected list
     * @param filename file name
     * @throws DAOException if something goes wrong
     */
    @Test(description = "positive scenario of readFromFile",
            dataProvider = "positiveDataForRead")
    public void readFromFilePositive(final List<String> list,
                                     final String filename)
            throws DAOException {
        assertEquals(list, planeReader.readFromFile(filename));
    }
}
