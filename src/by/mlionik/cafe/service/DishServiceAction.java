package by.mlionik.cafe.service;

import by.mlionik.cafe.entity.Dish;
import java.util.List;

public interface DishServiceAction {

    Dish create(Dish dish) throws ServiceException;

    List<Dish> findAll() throws ServiceException;

    Dish findById(int id) throws ServiceException;


}
