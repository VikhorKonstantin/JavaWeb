package by.training.task1oop.controller.command;

import by.training.task1oop.service.AddService;

public class AddPlaneCommand implements Executable {
    /**
     * @param args for service method
     * @return response
     */
    @Override
    public String execute(final String args) {
        AddService service = new AddService();
        return service.addPlane(args);
    }
}
