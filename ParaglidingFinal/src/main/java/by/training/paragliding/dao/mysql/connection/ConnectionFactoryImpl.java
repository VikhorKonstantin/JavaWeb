package by.training.paragliding.dao.mysql.connection;

import by.training.paragliding.dao.exception.DaoException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public final class ConnectionFactoryImpl implements ConnectionFactory {

    /**
     * Logger.
     */
    private Logger logger = LogManager.getLogger("ConnectionFactory");
    private String connectionURL;
    private String userName;
    private String userPassword;

    /**
     * Creates new instance of connection pool using
     * configurations from configFile.
     *
     * @param configFile config file
     * @throws DaoException if it's impossible to create ConnectionFactoryImpl
     *                      instance  using configurations from configFile.
     */
    public ConnectionFactoryImpl(final String configFile) throws DaoException {
        super();
        Properties properties = new Properties();
        final String rootPath = Thread.currentThread()
                .getContextClassLoader().getResource("").getPath();
        try {
            var fileFullPath = rootPath + configFile;
            properties.load(new FileInputStream(fileFullPath));
        } catch (IOException newE) {
            throw new DaoException("Wrong config file path");
        }
        var driver = (String) properties.get("driver");
        try {
            Class.forName(driver);
        } catch (ClassNotFoundException e) {
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

    /**
     * Creates new connection.
     *
     * @return new connection
     * @throws DaoException if it's impossible to get new connection.
     */
    public Connection createNewConnection() throws DaoException {
        try {
            return
                    DriverManager.getConnection(
                            connectionURL,
                            userName,
                            userPassword);
        } catch (SQLException se) {
            logger.debug("Connection err {}", se.getMessage(), se);
            throw new DaoException(
                    "Unable to create new connection", se);
        }
    }
}
