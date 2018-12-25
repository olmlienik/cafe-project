package by.mlionik.cafe.service.impl;

import by.mlionik.cafe.dao.DaoException;
import by.mlionik.cafe.dao.impl.DishDao;
import by.mlionik.cafe.entity.Dish;
import by.mlionik.cafe.manager.MessageManager;
import by.mlionik.cafe.pool.TransactionManager;
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
            return dishDAO.findAll();
        } catch (DaoException e) {
            throw new ServiceException(MessageManager.getProperty(FIND_DISH_ERROR_MSG), e);
        }
    }

}
