package by.training.paragliding.service;

import by.training.paragliding.bean.entity.Competition;
import by.training.paragliding.service.exception.ServiceException;

public interface CompetitionService
        extends Service<Competition, CompetitionService.FindByProps> {
    Competition readById(int id) throws ServiceException;
    boolean update(Competition newCompetition) throws ServiceException;
    boolean addCompetition(Competition newCompetition) throws ServiceException;
    enum FindByProps {
        ALL,
        STATUS,
        ORGANIZER_AND_STATUS,
        PARTICIPANT
    }
}
