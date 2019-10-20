package by.training.paragliding.dao.mysql;

import by.training.paragliding.bean.builder.Builder;
import by.training.paragliding.bean.exception.BeanException;
import by.training.paragliding.dao.Repository;
import by.training.paragliding.dao.exception.DaoException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public abstract class BaseSqlRepository<T> implements Repository<T> {
    /**
     * Logger.
     */
    private Logger logger = LogManager.getLogger("TransactionBasedCompetitionService");
    /**
     * SQL connection.
     */
    private Connection connection;
    private Builder<T> tBuilder;

    private static final String IS_EMPTY =
            "SELECT NULL FROM %s LIMIT 1";

    public BaseSqlRepository(final Connection newConnection,
                             final Builder<T> newTBuilder) {
        connection = newConnection;
        tBuilder = newTBuilder;
    }

    protected T readByProperties(final String query, final Object... args)
            throws DaoException {
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            fillStatement(statement, args);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    return tBuilder.buildFromResultSet(resultSet);
                } else {
                    return null;
                }
            }
        } catch (SQLException | BeanException newE) {
            throw new DaoException(newE);
        }
    }

    protected boolean executeUpdate(final String query, final Object... args)
            throws DaoException {
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            fillStatement(statement, args);
            return statement.executeUpdate() > 0;
        } catch (SQLException e) {
            logger.debug("Adding rep comp{}", e);
            throw new DaoException(e);
        }
    }

    protected List<T> executeQuery(final Specification newSpecification)
            throws DaoException {
        var resultList = new ArrayList<T>();
        try (PreparedStatement statement =
                     newSpecification.createStatement(connection);
             ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
                var object = tBuilder.buildFromResultSet(resultSet);
                resultList.add(object);
            }
            return resultList;
        } catch (SQLException | BeanException newE) {
            throw new DaoException(newE);
        }
    }

    protected boolean isEmpty(final String tableName) throws DaoException {
        try (Statement statement = connection.createStatement()) {
            try (ResultSet resultSet = statement.executeQuery(
                    String.format(IS_EMPTY, tableName))) {
                return resultSet.next();
            }
        } catch (SQLException newE) {
            throw new DaoException(newE);
        }
    }

    private void fillStatement(
            final PreparedStatement newStatement, final Object[] newArgs)
            throws SQLException {
        for (int i = 0; i < newArgs.length; i++) {
            newStatement.setObject(i + 1, newArgs[i]);
        }
    }
}
