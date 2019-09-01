package by.training.task4xml.controller.command;

import by.training.task4xml.service.LocaleService;

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
