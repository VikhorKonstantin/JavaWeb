package by.training.task3composite.controller.command;

import by.training.task3composite.service.LocaleService;

public class StartCommand implements Executable {
    /**
     * @param args for service method
     * @return response
     */
    @Override
    public String execute(final String args) {
        LocaleService service = new LocaleService();
        return service.createStartInfo();
    }
}
