package by.mlionik.cafe.dao;

import by.mlionik.cafe.entity.User;
import java.util.List;

/**
 * The interface User dao.
 */
public interface UserDao {

    /**
     * Finds user by login and password.
     *
     * @param login the login
     * @param password the password
     * @return the user
     * @throws DaoException the dao exception
     */
    User findByLoginAndPassword(String login, String password) throws DaoException;

    /**
     * Finds user by login.
     *
     * @param login the login
     * @return the user
     * @throws DaoException the dao exception
     */
    User findByLogin(String login) throws DaoException;

    /**
     * Updates ban state.
     *
     * @param userId the user id
     * @param ban the new ban state
     * @return true, if successful
     * @throws DaoException the dao exception
     */
    boolean updateBan(int userId, boolean ban) throws DaoException;

    /**
     * Updates balance.
     *
     * @param user the user
     * @return the user
     * @throws DaoException the dao exception
     */
    User updateBalance(User user) throws DaoException;

    /**
     * Updates login.
     *
     * @param userId the user id
     * @param newLogin the new login
     * @return the user
     * @throws DaoException the dao exception
     */
    User updateLogin(int userId, String newLogin) throws DaoException;

    /**
     * Updates password.
     *
     * @param userId the user id
     * @param newPassword the new password
     * @return the user
     * @throws DaoException the dao exception
     */
    User updatePassword(int userId, String newPassword) throws DaoException;

    /**
     * Updates loyalty points.
     *
     * @param userId the user id
     * @param newPoints the new points
     * @return true, if successful
     * @throws DaoException the dao exception
     */
    boolean updateLoyaltyPoints(int userId, double newPoints) throws DaoException;

    /**
     * Finds active users.
     *
     * @return the list of active users
     * @throws DaoException the dao exception
     */
    List<User> findActiveUsers() throws DaoException;

    /**
     * Finds banned users.
     *
     * @return the list of banned users
     * @throws DaoException the dao exception
     */
    List<User> findBannedUsers() throws DaoException;
}
