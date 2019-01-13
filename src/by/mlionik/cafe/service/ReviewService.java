package by.mlionik.cafe.service;

import by.mlionik.cafe.entity.Review;
import java.util.List;

/**
 * The interface Review service.
 */
public interface ReviewService {

    /**
     * Creates the review.
     *
     * @param review the review
     * @return the review
     * @throws ServiceException the service exception
     */
    Review create(Review review) throws ServiceException;

    /**
     * Deletes review by id.
     *
     * @param id the id
     * @return true, if successful
     * @throws ServiceException the service exception
     */
    boolean deleteById(int id) throws ServiceException;

    /**
     * Finds all reviews.
     *
     * @return the list of reviews
     * @throws ServiceException the service exception
     */
    List<Review> findAll() throws ServiceException;
}
