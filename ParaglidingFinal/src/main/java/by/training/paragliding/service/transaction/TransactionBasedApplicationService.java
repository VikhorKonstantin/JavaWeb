package by.training.paragliding.service.transaction;

import by.training.paragliding.bean.entity.Application;
import by.training.paragliding.dao.DaoType;
import by.training.paragliding.dao.Repository;
import by.training.paragliding.dao.Transaction;
import by.training.paragliding.dao.exception.DaoException;
import by.training.paragliding.dao.mysql.Specification;
import by.training.paragliding.dao.mysql.application.FindByIdentifiersSpecification;
import by.training.paragliding.service.ApplicationService;
import by.training.paragliding.service.Service;
import by.training.paragliding.service.exception.ServiceException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

class TransactionBasedApplicationService extends
        AbstractTransactionBasedService implements ApplicationService {
    /**
     * Logger.
     */
    private Logger logger = LogManager.getLogger("main");

    TransactionBasedApplicationService(final Transaction newTransaction) {
        super(newTransaction);
    }

    private static final Map<Integer, Service.ThrowingFunction<Object[], Specification,
                ServiceException>> SPECIFICATION_PROVIDER =
            new HashMap<>();


    static {
        SPECIFICATION_PROVIDER.put(IDENTIFIERS,
                TransactionBasedApplicationService::findByIdentifiers);
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
    @Override
    public boolean addApplication(final Application newApplication)
            throws ServiceException {
        try {
            Repository<Application> applicationRepository =
                    transaction.createDao(DaoType.APPLICATION);
            var result = applicationRepository.add(newApplication);
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
    public List<Application> find(final Integer property,
                                  final Object... values)
            throws ServiceException {
        try {
            var specification = SPECIFICATION_PROVIDER
                    .get(property).apply(values);
            Repository<Application> applicationRepository =
                    transaction.createDao(DaoType.APPLICATION);
            var result = applicationRepository.query(specification);
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
}
