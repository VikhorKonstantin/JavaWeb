package by.training.paragliding.service.transaction;

import by.training.paragliding.bean.entity.Result;
import by.training.paragliding.dao.DaoType;
import by.training.paragliding.dao.Repository;
import by.training.paragliding.dao.Transaction;
import by.training.paragliding.dao.exception.DaoException;
import by.training.paragliding.dao.mysql.Specification;
import by.training.paragliding.dao.mysql.result.FindByIdentifiersSpecification;
import by.training.paragliding.service.ResultService;
import by.training.paragliding.service.exception.ServiceException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.EnumMap;
import java.util.List;

class TransactionBasedResultService
        extends AbstractTransactionBasedService implements ResultService {
    /**
     * Logger.
     */
    private Logger logger = LogManager.getLogger("TransactionBasedResultService");

    TransactionBasedResultService(final Transaction newTransaction) {
        super(newTransaction);
    }

    private static final EnumMap<FindByProps,
                ThrowingFunction<Object[], Specification, ServiceException>>
            SPECIFICATION_PROVIDER =
            new EnumMap<>(FindByProps.class);
    static {
        SPECIFICATION_PROVIDER.put(FindByProps.IDENTIFIERS,
                TransactionBasedResultService::findByIdentifiers);
    }
    @Override
    public boolean addResult(final Result newResult)
            throws ServiceException {
        try {
            Repository<Result> resultRepository =
                    transaction.createDao(DaoType.RESULT);
            var resList = find(FindByProps.IDENTIFIERS,
                    newResult.getCompetitionId(),
                    newResult.getSportsmanId());
            if (resList.isEmpty()) {
                var result = resultRepository.add(newResult);
                transaction.commit();
                return result;
            } else {
                var result = resultRepository.update(newResult);
                transaction.commit();
                return result;
            }
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
    public List<Result> find(final FindByProps property,
                             final Object... values)
            throws ServiceException {
        try {
            var specification = SPECIFICATION_PROVIDER
                    .get(property).apply(values);
            Repository<Result> resultRepository =
                    transaction.createDao(DaoType.RESULT);
            var result = resultRepository.query(specification);
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

    private static Specification findByIdentifiers(final Object[] newObjects)
            throws ServiceException {
        try {
            return new FindByIdentifiersSpecification((Integer) newObjects[0],
                    (Integer) newObjects[1]);
        } catch (ClassCastException e) {
            throw new ServiceException(e);
        }
    }
}
