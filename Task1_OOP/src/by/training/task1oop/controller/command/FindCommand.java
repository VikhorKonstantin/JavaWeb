package by.training.task1oop.controller.command;

import by.training.task1oop.service.FindByService;

public class FindCommand implements Executable {
    /**
     * @param args for service method
     * @return response
     */
    @Override
    public String execute(final String args) {
        FindByService service = new FindByService();
        return service.findBy(args);
    }
}
