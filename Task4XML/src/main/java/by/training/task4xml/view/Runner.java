package by.training.task4xml.view;

import by.training.task4xml.service.GemsSaxBuilder;
import by.training.task4xml.service.exception.ServiceException;

public class Runner {
    public static void main(String[] args) {
        GemsSaxBuilder service = new GemsSaxBuilder();
        try {
            var gems = service.buildGemsFromFile("input/gems.xml",
                    "input/gems.xsd");
            gems.forEach(System.out::println);
        } catch (ServiceException e) {
            
        }
    }
}
