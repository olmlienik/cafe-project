package by.mlionik.cafe.dao;

import by.mlionik.cafe.entity.Dish;
import java.util.List;

/**
 * The interface Dish dao.
 */
public interface DishDao {

    /**
     * Finds all dishes in database.
     *
     * @return the list of dishes
     * @throws DaoException the dao exception
     */
    List<Dish> findAll() throws DaoException;
}
