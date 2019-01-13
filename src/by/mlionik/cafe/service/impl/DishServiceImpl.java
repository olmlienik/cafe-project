package by.mlionik.cafe.service.impl;

import by.mlionik.cafe.dao.DaoException;
import by.mlionik.cafe.dao.impl.DishDaoImpl;
import by.mlionik.cafe.entity.Dish;
import by.mlionik.cafe.dao.TransactionManager;
import by.mlionik.cafe.service.DishService;
import by.mlionik.cafe.service.ServiceException;

import java.util.List;

/**
 * The type Dish service.
 */
public class DishServiceImpl implements DishService {

    @Override
    public Dish create(Dish dish) throws ServiceException {
        DishDaoImpl dishDAO = new DishDaoImpl();
        TransactionManager manager = new TransactionManager();
        manager.beginTransaction(dishDAO);
        try {
            Dish curDish = dishDAO.create(dish);
            manager.commit();
            return curDish;
        } catch (DaoException e) {
            manager.rollBack();
            throw new ServiceException("Exception while trying to create dish in db", e);
        } finally {
            manager.endTransaction();
        }
    }

    @Override
    public List<Dish> findAll() throws ServiceException {
        DishDaoImpl dishDAO = new DishDaoImpl();
        TransactionManager manager = new TransactionManager();
        manager.beginTransaction(dishDAO);
        try {
            List<Dish> allDishes = dishDAO.findAll();
            manager.commit();
            return allDishes;
        } catch (DaoException e) {
            manager.rollBack();
            throw new ServiceException("Exception while trying to find all dishes in db", e);
        } finally {
            manager.endTransaction();
        }
    }

    @Override
    public Dish findById(int id) throws ServiceException {
        DishDaoImpl dishDAO = new DishDaoImpl();
        TransactionManager manager = new TransactionManager();
        manager.beginTransaction(dishDAO);
        try {
            return dishDAO.findById(id);
        } catch (DaoException e) {
            throw new ServiceException("Exception while trying to find dish by id = " + id + " in db", e);
        } finally {
            manager.endTransaction();
        }
    }

    @Override
    public boolean deleteById(int id) throws ServiceException {
        DishDaoImpl dishDao = new DishDaoImpl();
        TransactionManager manager = new TransactionManager();
        manager.beginTransaction(dishDao);
        try {
            boolean isDeleted = dishDao.deleteById(id);
            manager.commit();
            return isDeleted;
        } catch (DaoException e) {
            manager.rollBack();
            throw new ServiceException("Exception while trying to delete dish by id = " + id, e);
        } finally {
            manager.endTransaction();
        }
    }
}
