package by.training.paragliding.service;

import by.training.paragliding.bean.entity.Application;
import by.training.paragliding.service.exception.ServiceException;

public interface ApplicationService extends Service<Application> {
    boolean addApplication(final Application newApplication)
            throws ServiceException;
    Integer IDENTIFIERS = 1;
}
