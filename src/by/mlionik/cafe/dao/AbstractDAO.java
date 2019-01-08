package by.mlionik.cafe.dao;

import by.mlionik.cafe.entity.Entity;
import by.mlionik.cafe.pool.ProxyConnection;

import java.sql.Connection;

public abstract class AbstractDAO<T extends Entity> {

    protected ProxyConnection connection;

    public AbstractDAO() {
    }

    public void setConnection(Connection connection){
        ProxyConnection proxyConnection = (ProxyConnection) connection;
        this.connection = proxyConnection;
    }

    public abstract T create(T entity) throws DaoException;

    public abstract T findById(int id) throws DaoException;

    public abstract T update(T entity) throws DaoException;

    public abstract boolean deleteById(int id) throws DaoException;
}
