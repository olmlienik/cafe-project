package by.mlionik.cafe.pool;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Enumeration;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ConnectionPool {

    private static final Logger logger = LogManager.getLogger();
    private static final int DEFAULT_POOL_SIZE = 10;
    private static ConnectionPool instance;
    private static final Lock createLock = new ReentrantLock();
    private BlockingQueue<ProxyConnection> connections;
    private static AtomicBoolean created = new AtomicBoolean(false);

    private ConnectionPool() {
        connections = new ArrayBlockingQueue<>(DEFAULT_POOL_SIZE);
        this.init();
    }

    public static ConnectionPool getInstance() {
        if (!created.get()) {
            try {
                createLock.lock();
                if (instance == null) {
                    instance = new ConnectionPool();
                    created.set(true);
                }
            } finally {
                createLock.unlock();
            }
        }
        return instance;
    }

    public Connection takeConnection() {
        ProxyConnection proxyConnection = null;
        try {
            proxyConnection = connections.take();
        } catch (InterruptedException e) {
            logger.log(Level.ERROR, e);
            Thread.currentThread().interrupt();
        }
        return proxyConnection;
    }

    public void returnConnection(Connection connection) {
        if (connection instanceof ProxyConnection) {
            ProxyConnection proxyConnection = (ProxyConnection) connection;
            try {
                connections.put(proxyConnection);
            } catch (InterruptedException e) {
                logger.log(Level.ERROR, e);
            }
        }
    }

    private void init() {
        DatabaseInitializer databaseInitializer = new DatabaseInitializer();
        try {
            for (int i = 0; i < DEFAULT_POOL_SIZE; i++) {
                ProxyConnection connection = new ProxyConnection(databaseInitializer.getConnection());
                connections.put(connection);
            }
        } catch (InterruptedException e) {
            logger.log(Level.ERROR, e);
        }
    }

    public void closePool() {
        for (int i = 0; i < DEFAULT_POOL_SIZE; i++) {
            ProxyConnection proxyConnection;
            try {
                proxyConnection = connections.take();
                proxyConnection.closeConnection();
            } catch (InterruptedException | SQLException ex) {
                logger.log(Level.ERROR, ex);
            }
        }
        try {
            Enumeration<Driver> drivers = DriverManager.getDrivers();
            while (drivers.hasMoreElements()) {
                Driver driver = drivers.nextElement();
                DriverManager.deregisterDriver(driver);
            }
        } catch (SQLException ex) {
            logger.log(Level.ERROR, ex);
        }
    }

}
