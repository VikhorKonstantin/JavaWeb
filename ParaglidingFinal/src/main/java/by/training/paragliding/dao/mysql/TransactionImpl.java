package by.training.paragliding.dao.mysql;

import by.training.paragliding.dao.DaoType;
import by.training.paragliding.dao.Repository;
import by.training.paragliding.dao.Transaction;
import by.training.paragliding.dao.exception.DaoException;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;

public class TransactionImpl implements Transaction {

    private static Map<DaoType, Function<Connection, Repository>>
            typeRepositoryMap = new ConcurrentHashMap<>();
    static {
        typeRepositoryMap.put(
                DaoType.SPORTSMAN, DaoCreator::createSportsmanRepository);
        typeRepositoryMap.put(DaoType.USER, DaoCreator::createUserRepository);
        typeRepositoryMap.put(DaoType.COMPETITION,
                DaoCreator::createCompetitionRepository);

    }

    private Connection connection;

    public TransactionImpl(Connection connection) {
        this.connection = connection;
    }

    @SuppressWarnings("unchecked")
    @Override
    public <T>Repository<T> createDao(DaoType type) throws DaoException {
        return typeRepositoryMap.get(type).apply(connection);
    }

    @Override
    public void commit() throws DaoException {
        try {
            connection.commit();
        } catch(SQLException e) {
            throw new DaoException(e);
        }
    }

    @Override
    public void rollback() throws DaoException {
        try {
            connection.rollback();
        } catch(SQLException e) {
            throw new DaoException(e);
        }
    }

}
