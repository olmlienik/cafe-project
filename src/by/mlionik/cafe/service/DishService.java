package by.mlionik.cafe.service;

import by.mlionik.cafe.entity.Dish;

import java.util.List;

/**
 * The interface Dish service.
 */
public interface DishService {

    /**
     * Creates the dish.
     *
     * @param dish the dish
     * @return the dish
     * @throws ServiceException the service exception
     */
    Dish create(Dish dish) throws ServiceException;

    /**
     * Finds all snacks.
     *
     * @return the list of dishes
     * @throws ServiceException the service exception
     */
    List<Dish> findSnacks() throws ServiceException;

    /**
     * Finds all main dishes.
     *
     * @return the list of dishes
     * @throws ServiceException the service exception
     */
    List<Dish> findMainDishes() throws ServiceException;

    /**
     * Finds all deserts.
     *
     * @return the list of dishes
     * @throws ServiceException the service exception
     */
    List<Dish> findDeserts() throws ServiceException;

    /**
     * Finds all drinks.
     *
     * @return the list of dishes
     * @throws ServiceException the service exception
     */
    List<Dish> findDrinks() throws ServiceException;

    /**
     * Finds dish by id.
     *
     * @param id the id
     * @return the dish
     * @throws ServiceException the service exception
     */
    Dish findById(int id) throws ServiceException;

    /**
     * Deletes dish by id.
     *
     * @param id the id
     * @return true, if successful
     * @throws ServiceException the service exception
     */
    boolean deleteById(int id) throws ServiceException;
}
