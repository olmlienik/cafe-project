package by.mlionik.cafe.service.impl;

import by.mlionik.cafe.dao.DaoException;
import by.mlionik.cafe.dao.TransactionManager;
import by.mlionik.cafe.dao.impl.ReviewDaoImpl;
import by.mlionik.cafe.dao.impl.UserDaoImpl;
import by.mlionik.cafe.entity.Review;
import by.mlionik.cafe.entity.User;
import by.mlionik.cafe.service.ReviewService;
import by.mlionik.cafe.service.ServiceException;

import java.util.List;

/**
 * The type Review service.
 */
public class ReviewServiceImpl implements ReviewService {

    @Override
    public Review create(Review review) throws ServiceException {
        ReviewDaoImpl reviewDao = new ReviewDaoImpl();
        TransactionManager manager = new TransactionManager();
        manager.beginTransaction(reviewDao);
        try {
            Review currentReview;
            currentReview = reviewDao.create(review);
            manager.commit();
            return currentReview;
        } catch (DaoException e) {
            manager.rollBack();
            throw new ServiceException("Exception while creating review", e);
        } finally {
            manager.endTransaction();
        }
    }

    @Override
    public List<Review> findAll() throws ServiceException {
        ReviewDaoImpl reviewDao = new ReviewDaoImpl();
        UserDaoImpl userDao = new UserDaoImpl();
        TransactionManager manager = new TransactionManager();
        manager.beginTransaction(reviewDao, userDao);
        try {
            List<Review> allReviews = reviewDao.findAll();
            User user;
            for (Review review : allReviews) {
                user = userDao.findById(review.getIdClient());
                review.setLogin(user.getLogin());
            }
            manager.commit();
            return allReviews;
        } catch (DaoException e) {
            manager.rollBack();
            throw new ServiceException("Exception while trying to find all reviews in db", e);
        } finally {
            manager.endTransaction();
        }
    }

    @Override
    public boolean deleteById(int id) throws ServiceException {
        ReviewDaoImpl reviewDao = new ReviewDaoImpl();
        TransactionManager manager = new TransactionManager();
        manager.beginTransaction(reviewDao);
        try {
            boolean isDeleted = reviewDao.deleteById(id);
            manager.commit();
            return isDeleted;
        } catch (DaoException e) {
            manager.rollBack();
            throw new ServiceException("Exception while trying to delete review by id = " + id, e);
        } finally {
            manager.endTransaction();
        }
    }
}
