package by.mlionik.cafe.service;

import by.mlionik.cafe.entity.Dish;

import java.util.List;

public interface DishServiceAction {
    List<Dish> findAll() throws ServiceException;
}
