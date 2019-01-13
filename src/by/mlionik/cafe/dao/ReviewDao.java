package by.mlionik.cafe.dao;

import by.mlionik.cafe.entity.Review;
import java.util.List;

/**
 * The interface Review dao.
 */
public interface ReviewDao {

    /**
     * Finds all reviews
     *
     * @return the list od reviews
     * @throws DaoException the dao exception
     */
    List<Review> findAll() throws DaoException;
}
