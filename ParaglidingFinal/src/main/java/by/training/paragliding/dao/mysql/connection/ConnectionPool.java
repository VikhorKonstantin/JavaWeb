package by.training.paragliding.dao.mysql.connection;

import java.sql.Connection;
import java.util.concurrent.TimeUnit;

public interface ConnectionPool {

    Connection get(long timeOut, TimeUnit unit) throws InterruptedException;

    Connection get();

    void releaseConnection(Connection connection);

    void shutdown();
}
