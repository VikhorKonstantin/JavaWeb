package by.training.paragliding.dao.mysql.connection;

import by.training.paragliding.dao.exception.DaoException;
import org.apache.logging.log4j.LogManager;

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
            var fileFullPath = rootPath + configFile;
            LogManager.getLogger("main").debug(fileFullPath);
            properties.load(new FileInputStream(fileFullPath));
        } catch (IOException newE) {
            throw new DaoException("Wrong config file path");
        }
        var driver = (String) properties.get("driver");
        try {
            Class.forName(driver);
        } catch (ClassNotFoundException e) {
            //todo remove
            LogManager.getLogger("main").debug(driver, e);
            throw new DaoException(
                    "Unable to find driver in classpath", e);
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
            //todo remove
            LogManager.getLogger("main").debug(se);
            throw new DaoException(
                    "Unable to create new connection", se);
        }
    }
}
