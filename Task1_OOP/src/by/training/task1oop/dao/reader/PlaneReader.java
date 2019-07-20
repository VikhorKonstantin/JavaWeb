package by.training.task1oop.dao.reader;

import by.training.task1oop.dao.exception.DAOException;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

public class PlaneReader {
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
            throw new DAOException("File opening error", e);
        }
    }
}
