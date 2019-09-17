package by.training.sqltask.dao.repository.specification;

import by.training.sqltask.dao.exception.DaoException;

import java.sql.Connection;
import java.sql.PreparedStatement;

public interface Specification {
    PreparedStatement createStatement(Connection connection) throws DaoException;
}
