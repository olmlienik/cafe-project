package by.mlionik.cafe.service.impl;

import by.mlionik.cafe.dao.DaoException;
import by.mlionik.cafe.dao.impl.DishDao;
import by.mlionik.cafe.entity.Dish;
import by.mlionik.cafe.dao.TransactionManager;
import by.mlionik.cafe.service.DishServiceAction;
import by.mlionik.cafe.service.ServiceException;

import java.util.List;

public class DishService implements DishServiceAction {

//    public Dish create(String name, Double cost, String picture, DishType category) throws ServiceException {
//        DishDao dishDAO = new DishDao();
//        TransactionManager manager = new TransactionManager();
//        manager.beginTransaction(dishDAO);
//        try {
//            Dish dish = dishDAO.create(name, cost, picture, category);
//            manager.commit();
//            return dish;
//        } catch (DaoException e) {
//            manager.rollBack();
//            throw new ServiceException("Exception while trying to create dish in db", e);
//        } finally {
//            manager.endTransaction();
//        }
//    }

    @Override
    public Dish create(Dish dish) throws ServiceException {
        DishDao dishDAO = new DishDao();
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
        DishDao dishDAO = new DishDao();
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
        DishDao dishDAO = new DishDao();
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

}
