package by.training.paragliding.dao.mysql.connection;

import java.sql.Connection;

public interface ConnectionValidator {
    /**
     * Checks if connection valid.
     * @param newConnection connection to check
     * @return if connection valid.
     */
    boolean isValid(Connection newConnection);
}
