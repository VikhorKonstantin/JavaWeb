package by.training.task1oop.controller.command;

import by.training.task1oop.service.SortByService;

public class SortCommand implements Executable {
    @Override
    public String execute(final String property) {
        SortByService service = new SortByService();
        return service.sortBy(property);
    }
}
