package by.training.task1oop.view;

import by.training.task1oop.controller.AirCompanyController;

//will be replaced by using tests..

public class Main {
    public static void main(String[] args) {
        AirCompanyController airCompanyController = new AirCompanyController();
        String response = airCompanyController.executeTask("INIT",
                "input/input.txt");
        System.out.println(response);
        String response1 = airCompanyController.executeTask("SORT",
                "NAME");
        System.out.println(response1);


    }
}
