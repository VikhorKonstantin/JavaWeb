package by.training.task1oop.controller.command;

import by.training.task1oop.service.AirCompanyInfoService;

public class PayloadRequest implements Executable {
    /**
     * @param args for service method
     * @return response
     */
    @Override
    public String execute(final String args) {
        AirCompanyInfoService service = new AirCompanyInfoService();
        return String.valueOf(service.totalPayloadRequest());
    }
}
