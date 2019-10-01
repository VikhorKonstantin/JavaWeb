package by.training.paragliding.dao.mysql.connection;

import java.sql.Connection;

public interface ConnectionValidator {
    boolean isValid(Connection newConnection);
}
