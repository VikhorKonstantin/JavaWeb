package by.training.paragliding.dao.mysql.connection;

import java.sql.Connection;
import java.sql.SQLException;

public class ConnectionValidatorImpl implements ConnectionValidator {
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

