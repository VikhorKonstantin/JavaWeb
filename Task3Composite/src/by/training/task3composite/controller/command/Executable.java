package by.training.task3composite.controller.command;

public interface Executable {
    /**
     * @param args for service method
     * @return response
     */
    String execute(String args);
}
