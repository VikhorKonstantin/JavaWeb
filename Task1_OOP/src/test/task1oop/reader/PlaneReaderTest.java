package test.task1oop.reader;

import by.training.task1oop.service.reader.PlaneReader;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.List;

import static org.testng.Assert.assertThrows;
import static org.testng.AssertJUnit.assertEquals;

public class PlaneReaderTest {
    private PlaneReader planeReader = new PlaneReader();

    @DataProvider(name = "positiveDataForRead")
    public Object[][] createPositiveDataForSqrt() {
        return new Object[][]{
                {List.of(
                        "TRANSPORT 1265687556 100 350 850 NARROW_BODY",
                                "TRANSPORT 9865687556 20 1000 3200 800",
                                "PASSENGER 1956525556 50  350 700 REGIONAL",
                                "1266845575 100 350 850 NARROW_BODY",
                                "PASSENGER 8656535655 300 400 870 WIDE_BODY",
                                "TRANSPORT 95976558 30 1000 1200 700",
                                "TRANSPORT 126687556 100 350 850 NARROW_BODY",
                                "TRANSPORT 65687556 20 1000 3200",
                                "PASSENGER 19565556 50  350 700 REGIONAL",
                                "PASSENGER 12665575  350 850 NARROW_BODY",
                                "PASSENGER 86565655 300 400 870 WIDE_BODY",
                                "TRANSPORT 98656558 30 1000 1200 700"
                        ),
                        "test_input/test_input.txt"}};
    }

    @Test(description = "negative scenario of readFromFile")
    public void readFromFileNegative() {
        assertThrows(RuntimeException.class, () -> planeReader.readFromFile("dadFilename.txt"));
    }

    @Test(description = "null scenario of readFromFile")
    public void readFromFileNull() {
        assertThrows(RuntimeException.class, () -> planeReader.readFromFile(null));

    }

    @Test(description = "positive scenario of readFromFile", dataProvider = "positiveDataForRead")
    public void readFromFilePositive(List<String> list, String filename) {
        assertEquals(list, planeReader.readFromFile(filename));
    }
}
