package by.mlionik.cafe.dao;

import by.mlionik.cafe.dao.AbstractDAO;
import by.mlionik.cafe.dao.DaoException;
import by.mlionik.cafe.pool.ConnectionPool;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.SQLException;

public class TransactionManager {
    private static final Logger logger = LogManager.getLogger();
    private Connection connection = ConnectionPool.getInstance().takeConnection();

    public void beginTransaction(AbstractDAO dao, AbstractDAO ... daos){
        try{
            connection.setAutoCommit(false);
        } catch (SQLException e){
            logger.log(Level.ERROR, e);
        }
        dao.setConnection(connection);
        for(AbstractDAO d: daos){
            d.setConnection(connection);
        }
    }

    public void endTransaction() {
        try{
            connection.setAutoCommit(true);
        } catch (SQLException e){
            logger.log(Level.ERROR, e);
        }
        ConnectionPool.getInstance().returnConnection(connection);
    }

    public void commit(){
        try{
            connection.commit();
        } catch (SQLException e){
            logger.log(Level.ERROR, e);
        }
    }

    public void rollBack(){
        try{
            connection.rollback();
        } catch (SQLException e){
            logger.log(Level.ERROR, e);
        }
    }
}
