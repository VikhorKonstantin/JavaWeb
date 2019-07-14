package by.training.task1oop.controller.command;

import by.training.task1oop.service.DeleteService;

public class DeletePlaneCommand implements Executable {
    @Override
    public String execute(final String args) {
        DeleteService service = new DeleteService();
        return service.deletePlane(args);
    }
}
