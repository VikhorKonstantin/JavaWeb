package by.training.paragliding.dao.mysql.connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactoryImpl implements ConnectionFactory {
    private String connectionURL;
    private String userName;
    private String password;

    public ConnectionFactoryImpl(
            String driver,
            String connectionURL,
            String userName,
            String password) {
        super();

        try {
            Class.forName(driver);
        } catch (ClassNotFoundException ce) {
            throw new IllegalArgumentException(
                    "Unable to find driver in classpath", ce);
        }

        this.connectionURL = connectionURL;
        this.userName = userName;
        this.password = password;
    }

    public Connection createNewConnection() {
        try {
            return
                    DriverManager.getConnection(
                            connectionURL,
                            userName,
                            password);
        } catch (SQLException se) {
            throw new IllegalArgumentException(
                    "Unable to create new connection", se);
        }
    }
}
