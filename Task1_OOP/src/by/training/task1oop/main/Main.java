package by.training.task1oop.main;

import by.training.task1oop.reader.PlaneReader;
import by.training.task1oop.repository.AirCompany;
import by.training.task1oop.repository.AirCompanyCreator;
//will be replaced by using tests..

public class Main {
    public static void main(String[] args) {
        PlaneReader planeReader = new PlaneReader();
        AirCompanyCreator airCompanyCreator = new AirCompanyCreator();
        AirCompany airCompany =airCompanyCreator.createAirCompany(
                planeReader.readFromFrile("input/input.txt"));
        System.out.println(airCompany.readById(9865976558L));

    }
}
