package by.training.task1oop.main;

import by.training.task1oop.reader.PlaneReader;
import by.training.task1oop.repository.AirCompany;
import by.training.task1oop.service.AirCompanyCreator;
import by.training.task1oop.validator.PassengerPlaneValidator;

import java.util.List;

//will be replaced by using tests..

public class Main {
    public static void main(String[] args) {
        PlaneReader planeReader = new PlaneReader();
        AirCompanyCreator airCompanyCreator = new AirCompanyCreator();
        AirCompany airCompany =airCompanyCreator.createAirCompany(
                planeReader.readFromFile("input/input.txt"));
        System.out.println(airCompany.readById(9865976558L));
        var v = new PassengerPlaneValidator();
        v.isValid(List.of("PASSENGER", "1265687556",
                "100", "350", "850", "Boeing-247", "NARROW_BODY"));


    }
}
