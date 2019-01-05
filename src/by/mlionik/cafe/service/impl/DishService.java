package by.mlionik.cafe.service.impl;

import by.mlionik.cafe.dao.DaoException;
import by.mlionik.cafe.dao.impl.DishDao;
import by.mlionik.cafe.entity.Dish;
import by.mlionik.cafe.manager.MessageManager;
import by.mlionik.cafe.dao.TransactionManager;
import by.mlionik.cafe.service.DishServiceAction;
import by.mlionik.cafe.service.ServiceException;

import java.util.List;

public class DishService implements DishServiceAction {
    private static final String UPDATE_DISH_ERROR_MSG = "msg.update.dish.error";
    private static final String FIND_DISH_ERROR_MSG = "msg.find.dish.error";
    private static final String CREATE_DISH_ERROR_MSG = "msg.create.dish.error";
    private static final String DELETE_DISH_ERROR_MSG = "msg.delete.dish.error";

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
            throw new ServiceException(MessageManager.getProperty(FIND_DISH_ERROR_MSG), e);
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
            throw new ServiceException(MessageManager.getProperty(FIND_DISH_ERROR_MSG), e);
        }
    }

//    public static void main(String[] args) {
//        DishService service = new DishService();
//        try {
//            Dish dish = service.findById(1);
//            System.out.println(dish);
//        } catch (ServiceException e) {
//            e.printStackTrace();
//        }
//
//    }
}
