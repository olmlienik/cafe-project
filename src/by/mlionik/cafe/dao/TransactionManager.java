package by.mlionik.cafe.dao;

import by.mlionik.cafe.pool.ConnectionPool;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * The type Transaction manager.
 */
public class TransactionManager {
    private static final Logger logger = LogManager.getLogger();
    private Connection connection = ConnectionPool.getInstance().takeConnection();

    /**
     * Begins transaction.
     *
     * @param dao the dao
     * @param daos the daos
     */
    public void beginTransaction(AbstractDao dao, AbstractDao... daos) {
        try {
            connection.setAutoCommit(false);
        } catch (SQLException e) {
            logger.log(Level.ERROR, e);
        }
        dao.setConnection(connection);
        for (AbstractDao d : daos) {
            d.setConnection(connection);
        }
    }

    /**
     * Ends transaction.
     */
    public void endTransaction() {
        try {
            connection.setAutoCommit(true);
        } catch (SQLException e) {
            logger.log(Level.ERROR, e);
        }
        ConnectionPool.getInstance().returnConnection(connection);
    }

    /**
     * Commits changes.
     */
    public void commit() {
        try {
            connection.commit();
        } catch (SQLException e) {
            logger.log(Level.ERROR, e);
        }
    }

    /**
     * Rolls back changes.
     */
    public void rollBack() {
        try {
            connection.rollback();
        } catch (SQLException e) {
            logger.log(Level.ERROR, e);
        }
    }
}
