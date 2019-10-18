package by.training.paragliding.dao.mysql.connection;

import java.sql.Connection;
import java.sql.SQLException;

public final class ConnectionValidatorImpl implements ConnectionValidator {
    /**
     * Checks if connection valid.
     * It should me not null and shouldn't be closed.
     *
     * @param newConnection connection to check
     * @return if connection valid.
     */
    @Override
    public boolean isValid(final Connection newConnection) {
        if (newConnection == null) {
            return false;
        }
        try {
            return !newConnection.isClosed();
        } catch (SQLException se) {
            return false;
        }
    }
}

