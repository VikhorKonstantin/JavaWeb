package by.training.paragliding.service;

import by.training.paragliding.bean.entity.Application;
import by.training.paragliding.service.exception.ServiceException;

public interface ApplicationService
        extends Service<Application, ApplicationService.FindByProps> {
    boolean addApplication(final Application newApplication)
            throws ServiceException;
    enum FindByProps {
        IDENTIFIERS
    }
}
