package by.mlionik.cafe.dao;

import by.mlionik.cafe.entity.Dish;
import java.util.List;

/**
 * The interface Dish dao.
 */
public interface DishDao {

    /**
     * Finds all snacks in database.
     *
     * @return the list of dishes
     * @throws DaoException the dao exception
     */
    List<Dish> findSnacks() throws DaoException;

    /**
     * Finds all main dishes in database.
     *
     * @return the list of dishes
     * @throws DaoException the dao exception
     */
    List<Dish> findMainDishes() throws DaoException;

    /**
     * Finds all deserts in database.
     *
     * @return the list of dishes
     * @throws DaoException the dao exception
     */
    List<Dish> findDeserts() throws DaoException;

    /**
     * Finds all drinks in database.
     *
     * @return the list of dishes
     * @throws DaoException the dao exception
     */
    List<Dish> findDrinks() throws DaoException;
}
