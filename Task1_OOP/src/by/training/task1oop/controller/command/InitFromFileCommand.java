package by.training.task1oop.controller.command;

import by.training.task1oop.service.InitFromFileService;

public class InitFromFileCommand implements Executable {
    @Override
    public String execute(final String filename) {
        InitFromFileService service = new InitFromFileService();
        return service.initFromFile(filename);
    }
}
