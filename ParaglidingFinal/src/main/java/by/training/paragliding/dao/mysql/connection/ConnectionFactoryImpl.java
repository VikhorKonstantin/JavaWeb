package by.training.paragliding.dao.mysql.connection;

import by.training.paragliding.dao.exception.DaoException;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class ConnectionFactoryImpl implements ConnectionFactory {


    private String connectionURL;
    private String userName;
    private String userPassword;

    public ConnectionFactoryImpl(String configFile) throws DaoException {
        super();
        Properties properties = new Properties();
        final String rootPath = Thread.currentThread()
                .getContextClassLoader().getResource("").getPath();
        try {
            properties.load(new FileInputStream(rootPath + configFile));
        } catch (IOException newE) {
            throw new DaoException("Wrong config file path");
        }
        var driver = (String) properties.get("driver");
        try {
            Class.forName(driver);
        } catch (ClassNotFoundException ce) {
            throw new DaoException(
                    "Unable to find driver in classpath", ce);
        }
        var url = (String) properties.get("url");
        var name = (String) properties.get("user");
        var password = (String) properties.get("password");
        this.connectionURL = url;
        this.userName = name;
        this.userPassword = password;
    }

    public Connection createNewConnection() throws DaoException {
        try {
            return
                    DriverManager.getConnection(
                            connectionURL,
                            userName,
                            userPassword);
        } catch (SQLException se) {
            throw new DaoException(
                    "Unable to create new connection", se);
        }
    }
}
