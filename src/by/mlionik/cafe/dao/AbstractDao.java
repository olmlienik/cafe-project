package by.mlionik.cafe.dao;

import by.mlionik.cafe.entity.Entity;
import by.mlionik.cafe.pool.ProxyConnection;

import java.sql.Connection;

/**
 * The type Abstract DAO.
 *
 * @param <T> the generic type
 */
public abstract class AbstractDao<T extends Entity> {
    protected ProxyConnection connection;

    /**
     * Instantiates a new abstract dao.
     */
    public AbstractDao() {
    }

    /**
     * Sets the connection with database.
     *
     * @param connection the connection
     */
    public void setConnection(Connection connection){
        ProxyConnection proxyConnection = (ProxyConnection) connection;
        this.connection = proxyConnection;
    }

    /**
     * Creates new entity and inserts it in database.
     *
     * @param entity the entity
     * @return the new entity
     * @throws DaoException the dao exception
     */
    public abstract T create(T entity) throws DaoException;

    /**
     * Finds the entity by id.
     *
     * @param id the id
     * @return the found entity
     * @throws DaoException the dao exception
     */
    public abstract T findById(int id) throws DaoException;

    /**
     * Updates the entity in database
     *
     * @param entity the entity
     * @return the previous entity
     * @throws DaoException the dao exception
     */
    public abstract T update(T entity) throws DaoException;

    /**
     * Delete the entity by id.
     *
     * @param id the id
     * @return true, if the entity was successful deleted, false otherwise
     * @throws DaoException the dao exception
     */
    public abstract boolean deleteById(int id) throws DaoException;
}
