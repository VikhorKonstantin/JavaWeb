package by.training.paragliding.service;

import by.training.paragliding.bean.entity.Competition;
import by.training.paragliding.dao.DaoType;
import by.training.paragliding.dao.Repository;
import by.training.paragliding.dao.Transaction;
import by.training.paragliding.dao.exception.DaoException;
import by.training.paragliding.dao.mysql.Specification;
import by.training.paragliding.dao.mysql.competition.FindAllCompetitionsSpecification;
import by.training.paragliding.dao.mysql.competition.FindByStatusSpecification;
import by.training.paragliding.service.exception.ServiceException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CompetitionService implements Service<Competition> {
    /**
     * Logger.
     */
    private Logger logger = LogManager.getLogger("main");
    /**
     * Transaction.
     */
    private Transaction transaction;

    private static final String ROLL_BACK_EXC_MSG = "Rollback failed";

    CompetitionService(final Transaction newTransaction) {
        transaction = newTransaction;
    }

    private static final Map<String, ThrowingFunction<Object[], Specification,
            ServiceException>> SPECIFICATION_PROVIDER =
            new HashMap<>();

    static {
        SPECIFICATION_PROVIDER.put("status",
                CompetitionService::findByStatus);
        SPECIFICATION_PROVIDER.put("all", CompetitionService::findAll);
    }

    public Competition readById(final int id) throws ServiceException {
        try {
            Repository<Competition> competitionRepository =
                    transaction.createDao(DaoType.COMPETITION);
            var result = competitionRepository.readById(id);
            transaction.commit();
            return result;
        } catch (DaoException e) {
            try {
                transaction.rollback();
            } catch (DaoException rbExc) {
                logger.error(ROLL_BACK_EXC_MSG, rbExc);
            }
            throw new ServiceException(e);
        }
    }

    public boolean update(final Competition newCompetition)
            throws ServiceException {
        try {
            Repository<Competition> competitionRepository =
                    transaction.createDao(DaoType.COMPETITION);
            var result = competitionRepository.update(newCompetition);
            transaction.commit();
            return result;
        } catch (DaoException e) {
            try {
                transaction.rollback();
            } catch (DaoException rbExc) {
                logger.error(ROLL_BACK_EXC_MSG, rbExc);
            }
            throw new ServiceException(e);
        }
    }


    @Override
    public final List<Competition> find(String property, Object... value)
            throws ServiceException {
        try {
            var specification = SPECIFICATION_PROVIDER
                    .get(property).apply(value);
            Repository<Competition> competitionDao =
                    transaction.createDao(DaoType.COMPETITION);
            var result = competitionDao.query(specification);
            transaction.commit();
            return result;
        } catch (DaoException e) {
            try {
                transaction.rollback();
            } catch (DaoException rbExc) {
                logger.error(ROLL_BACK_EXC_MSG, rbExc);
            }
            throw new ServiceException(e);
        }
    }

    private static Specification findAll(final Object[] newObjects) {
        return new FindAllCompetitionsSpecification();
    }

    private static Specification findByStatus(final Object[] status)
            throws  ServiceException{
        try {
            return new FindByStatusSpecification(
                    (Competition.Status) status[0]);
        } catch (ClassCastException e) {
            throw new ServiceException(e);
        }
    }
}
