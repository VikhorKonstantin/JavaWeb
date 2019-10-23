package test.paragliding.service;

import by.training.paragliding.dao.exception.DaoException;
import by.training.paragliding.dao.mysql.connection.*;
import org.testng.annotations.BeforeSuite;

public class BeforeTest {
    @BeforeSuite
    public void init() throws DaoException {
        ConnectionFactory connectionFactory =
                new ConnectionFactoryImpl("database_test.properties");
        ConnectionValidator connectionValidator =
                new ConnectionValidatorImpl();
        ConnectionPoolImpl.getInstance()
                .initialize(5, connectionFactory,
                        connectionValidator);
    }
}
