package by.mlionik.cafe.pool;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ResourceBundle;

/**
 * The type Database initializer.
 */
class DatabaseInitializer {
    private static final Logger logger = LogManager.getLogger();
    private static final String DATABASE = "database";
    private static final String DB_URL = "db.url";
    private static final String DB_USER = "db.user";
    private static final String DB_PASSWORD = "db.password";
    private String dbUrl;
    private String user;
    private String password;

    /**
     * Instantiates a new database initializer.
     */
    DatabaseInitializer() {
        initializeDatabase();
    }

    /**
     * Initializes database.
     */
    private void initializeDatabase() {
        try {
            ResourceBundle resource = ResourceBundle.getBundle(DATABASE);
            dbUrl = resource.getString(DB_URL);
            user = resource.getString(DB_USER);
            password = resource.getString(DB_PASSWORD);
            DriverManager.registerDriver(new com.mysql.cj.jdbc.Driver());
        } catch (SQLException e) {
            logger.log(Level.FATAL, e, e);
            throw new RuntimeException("Cannot register database driver : ", e);
        }
    }

    /**
     * Gets the connection with database.
     *
     * @return the connection
     */
    Connection getConnection() {
        try {
            return DriverManager.getConnection(dbUrl, user, password);
        } catch (SQLException e) {
            logger.log(Level.FATAL, e, e);
            throw new RuntimeException("Cannot connect to database : ", e);
        }
    }
}
