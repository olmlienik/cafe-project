package by.mlionik.cafe.dao.impl;

import by.mlionik.cafe.dao.AbstractDao;
import by.mlionik.cafe.dao.DaoException;
import by.mlionik.cafe.dao.ReviewDao;
import by.mlionik.cafe.entity.Review;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import static by.mlionik.cafe.dao.impl.ReviewQuery.*;

/**
 * The type Review dao.
 */
public class ReviewDaoImpl extends AbstractDao<Review> implements ReviewDao {
    private static final String ID_REVIEW = "id_review";
    private static final String ID_USER = "id_client";
    private static final String LOGIN = "login";
    private static final String BODY = "body";
    private static final String DATE = "date";

    @Override
    public Review create(Review review) throws DaoException {
        try (PreparedStatement preparedStatement = connection.prepareStatement(SQL_INSERT_REVIEW, Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setInt(1, review.getIdClient());
            preparedStatement.setString(2, review.getBody());
            preparedStatement.setString(3, review.getDate());
            preparedStatement.executeUpdate();
            ResultSet generatedKeys = preparedStatement.getGeneratedKeys();
            if (generatedKeys.next()) {
                review = findById(generatedKeys.getInt(1));
            }
            return review;
        } catch (SQLException e) {
            throw new DaoException("Exception while trying to create review in db", e);
        }
    }

    @Override
    public Review findById(int id) throws DaoException {
        Review review = null;
        try (PreparedStatement preparedStatement = connection.prepareStatement(SQL_SELECT_REVIEW_BY_ID)) {
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                review = createReviewFromResultSet(resultSet);
            }
            return review;
        } catch (SQLException e) {
            throw new DaoException("Exception while trying to find review by id = " + id + " in db", e);
        }
    }

    @Override
    public Review update(Review review) throws DaoException {
        return null;
    }

    @Override
    public boolean deleteById(int id) throws DaoException {
        try (PreparedStatement preparedStatement = connection.prepareStatement(SQL_DELETE_REVIEW_BY_ID)) {
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
            return true;
        } catch (SQLException e) {
            throw new DaoException("Exception while trying to delete review by id = " + id + " in db", e);
        }
    }

    @Override
    public List<Review> findAll() throws DaoException {
        List<Review> reviewList = new ArrayList<>();
        try (Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(SQL_SELECT_ALL_REVIEWS);
            while (resultSet.next()) {
                reviewList.add(createReviewFromResultSet(resultSet));
            }
            return reviewList;
        } catch (SQLException e) {
            throw new DaoException("Exception while finding all reviews in db", e);
        }
    }

    /**
     * Creates the review from result set.
     *
     * @param resultSet the result set
     * @return the review
     * @throws SQLException the SQL exception
     */
    private Review createReviewFromResultSet(ResultSet resultSet) throws SQLException {
        Review review = new Review();
        review.setId(resultSet.getInt(ID_REVIEW));
        review.setIdClient(resultSet.getInt(ID_USER));
        review.setLogin(resultSet.getString(LOGIN));
        review.setBody(resultSet.getString(BODY));
        review.setDate(resultSet.getString(DATE));
        return review;
    }
}
