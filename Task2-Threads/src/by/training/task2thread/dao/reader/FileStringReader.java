package by.training.task2thread.dao.reader;

import by.training.task2thread.dao.exception.DAOException;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Class which provides reading strings from file.
 */
public class FileStringReader {
    /**
     * Exception message.
     */
    private static final String EXC_MSG = "File opening error";
    /**
     * Read data from file.
     * @param filename name of file to read
     * @return list of file lines as String objects
     * @throws DAOException if it is impossible to open file
     */
    public List<String> readFromFile(final String filename)
            throws DAOException {
        try (FileReader fileReader = new FileReader(filename);
            BufferedReader bufferedReader = new BufferedReader(fileReader)) {
            List<String> stringList;
            stringList = bufferedReader.lines().collect(Collectors.toList());
            return stringList;
        } catch (IOException | NullPointerException e) {
            throw new DAOException(EXC_MSG, e);
        }
    }
}
