package by.training.paragliding.service;

import by.training.paragliding.bean.entity.Sportsman;
import by.training.paragliding.service.exception.ServiceException;

public interface SportsmanService extends Service<Sportsman> {
    Sportsman readById(final int civlId) throws ServiceException;

    Integer ALL = 0;
    Integer COUNTRY_CODE = 1;
    Integer APPLICATION = 2;
    Integer RATING_RANGE = 3;
}
