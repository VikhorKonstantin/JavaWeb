package by.training.task1oop.reader;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

public class PlaneReader {
    /**
     * Logger.
     */
    private static Logger logger = LogManager.getLogger();

    /**
     * Read data from file.
     * @param filename name of file to read
     * @return list of file lines as String objects
     */
    public List<String> readFromFile(final String filename) {
        List<String> stringList;
        try (FileReader fileReader = new FileReader(filename);
            BufferedReader bufferedReader = new BufferedReader(fileReader)) {
            stringList = bufferedReader.lines().collect(Collectors.toList());
        } catch (IOException e) {
            logger.fatal("File opening error", e);
            throw new RuntimeException("File opening error", e);
        }
        return stringList;
    }
}
