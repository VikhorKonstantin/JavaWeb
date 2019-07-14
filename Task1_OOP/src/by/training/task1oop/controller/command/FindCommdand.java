package by.training.task1oop.controller.command;

import by.training.task1oop.service.FindByService;

public class FindCommdand implements Executable {
    @Override
    public String execute(final String params) {
        FindByService service = new FindByService();
        return service.findBy(params);
    }
}
