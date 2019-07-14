package by.training.task1oop.controller.command;

import by.training.task1oop.service.ReadByIdService;

public class ReadByIdCommand implements Executable {
    @Override
    public String execute(final String args) {
        ReadByIdService service = new ReadByIdService();
        return service.readById(args);
    }
}
