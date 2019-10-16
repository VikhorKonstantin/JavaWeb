package by.training.paragliding.service;

import by.training.paragliding.bean.entity.Result;
import by.training.paragliding.service.exception.ServiceException;

public interface ResultService
        extends Service<Result, ResultService.FindByProps> {
    boolean addResult(final Result newResult) throws ServiceException;
    enum FindByProps {
        IDENTIFIERS
    }
}
