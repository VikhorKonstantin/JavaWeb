package by.training.paragliding.service;

import by.training.paragliding.bean.entity.Sportsman;
import by.training.paragliding.service.exception.ServiceException;

public interface SportsmanService
        extends Service<Sportsman, SportsmanService.FindByProps> {
    Sportsman readById(final int civlId) throws ServiceException;
    enum FindByProps {
        ALL,
        COUNTRY_CODE,
        APPLICATION,
        RATING_RANGE,
        COMPETITORS
    }
}
