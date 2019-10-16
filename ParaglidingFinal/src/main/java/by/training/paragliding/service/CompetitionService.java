package by.training.paragliding.service;

import by.training.paragliding.bean.entity.Competition;
import by.training.paragliding.service.exception.ServiceException;

public interface CompetitionService
        extends Service<Competition, CompetitionService.FindByProps> {
    Competition readById(final int id) throws ServiceException;
    boolean update(final Competition newCompetition) throws ServiceException;
    enum FindByProps {
        ALL,
        STATUS
    }
}
