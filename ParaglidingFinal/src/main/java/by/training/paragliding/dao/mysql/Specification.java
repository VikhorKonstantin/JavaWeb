package by.training.paragliding.dao.mysql;

import by.training.paragliding.dao.exception.DaoException;

import java.sql.Connection;
import java.sql.PreparedStatement;

public interface Specification {
    PreparedStatement createStatement(Connection connection)
            throws DaoException;
}
