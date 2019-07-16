package test.task1oop.service;

import by.training.task1oop.bean.factory.PassengerPlaneFactory;
import by.training.task1oop.exception.WrongArgumentsException;
import by.training.task1oop.service.FindByService;
import by.training.task1oop.service.InitFromFileService;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.List;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertThrows;

public class FindByServiceTest {
    FindByService service = new FindByService();

    @BeforeTest
    public void reposInit() throws WrongArgumentsException {
        InitFromFileService initFromFileService = new InitFromFileService();
        initFromFileService.initFromFile("input/input.txt");
    }

    @DataProvider( name = "PositiveScenarioData")
    public Object[][] createPositiveScenarioData()
            throws WrongArgumentsException {
        return new Object[][] {
                {"NAME Boeing-247", List.of(PassengerPlaneFactory.getInstance().
                        createPlane(List.of("PASSENGER", "1265687556",
                        "100", "350", "850", "Boeing-247", "NARROW_BODY")))
                        .toString()},
                {"NAME_REGEX B[\\d\\D]*",
                        List.of(PassengerPlaneFactory.getInstance().
                        createPlane(List.of("PASSENGER", "1265687556",
                                "100", "350", "850", "Boeing-247",
                                "NARROW_BODY")).toString(),
                        PassengerPlaneFactory.getInstance().
                                createPlane(List.of("PASSENGER", "1266845575",
                                "100", "350", "850", "Boeing-307",
                                        "NARROW_BODY"))).toString()},
                {"ID 1265687556", List.of(PassengerPlaneFactory.getInstance().
                        createPlane(List.of("PASSENGER", "1265687556",
                                "100", "350", "850", "Boeing-247",
                                "NARROW_BODY"))).toString()},
                {"PAYLOAD_SEATING_CAPACITY 350 100",
                        List.of(PassengerPlaneFactory.getInstance().
                                createPlane(List.of("PASSENGER", "1265687556",
                                        "100", "350", "850", "Boeing-247",
                                        "NARROW_BODY")).toString(),
                        PassengerPlaneFactory.getInstance().
                                createPlane(List.of("PASSENGER", "1266845575",
                                        "100", "350", "850", "Boeing-307",
                                        "NARROW_BODY"))).toString()},
                {"PAYLOAD_RANGE 370 400",
                        List.of(PassengerPlaneFactory.getInstance().
                        createPlane(List.of("PASSENGER", "8656535655",
                                "300", "400", "870", "Antonov-An-2",
                                "WIDE_BODY"))).toString()}
        };
    }
    @DataProvider(name = "NegativeScenarioData")
    public Object[][] createNegativeData() {
        return new Object[][] {
                new Object[]{null},
                new Object[]{""},
                new Object[]{"ID fgdd"},
                new Object[]{"PAYLOAD_SEATING_CAPACITY 850 "},
                new Object[]{"PAYLOAD_RANGE 850 as"}
        };
    }
    @Test(description = "Positive",
    dataProvider = "PositiveScenarioData")
    public void findByPositive(String request, String expectedResult) throws WrongArgumentsException {
            var actual = service.findBy(request);
        assertEquals(actual, expectedResult);
    }
    @Test(description = "Negative",
    dataProvider = "NegativeScenarioData")
    public void findByPositive(String request) {
        assertThrows(WrongArgumentsException.class,
                () -> service.findBy(request));
    }
}
