package by.mlionik.cafe.service;

import by.mlionik.cafe.entity.User;
import java.util.List;

/**
 * The interface User service.
 */
public interface UserService {

    /**
     * Creates the user.
     *
     * @param user the user
     * @return the user
     * @throws ServiceException the service exception
     */
    User create(User user) throws ServiceException;

    /**
     * Finds user by id.
     *
     * @param id the id
     * @return the user
     * @throws ServiceException the service exception
     */
    User findById(int id) throws ServiceException;

    /**
     * Updates user.
     *
     * @param user the user
     * @return the previous user
     * @throws ServiceException the service exception
     */
    User update(User user) throws ServiceException;

    /**
     * Deletes user by id.
     *
     * @param id the id
     * @return true, if successful
     * @throws ServiceException the service exception
     */
    boolean deleteById(int id) throws ServiceException;

    /**
     * Finds user by login and password.
     *
     * @param login the login
     * @param password the password
     * @return the user
     * @throws ServiceException the service exception
     */
    User findByLoginAndPassword(String login, String password) throws ServiceException;

    /**
     * Finds user by login.
     *
     * @param login the login
     * @return the user
     * @throws ServiceException the service exception
     */
    User findByLogin(String login) throws ServiceException;

    /**
     * Updates balance.
     *
     * @param user the user
     * @return the user
     * @throws ServiceException the service exception
     */
    User updateBalance(User user) throws ServiceException;

    /**
     * Updates login.
     *
     * @param userId the user id
     * @param newLogin the new login
     * @return the user
     * @throws ServiceException the service exception
     */
    User updateLogin(int userId, String newLogin) throws ServiceException;

    /**
     * Updates password.
     *
     * @param userId the user id
     * @param newPassword the new password
     * @return the user
     * @throws ServiceException the service exception
     */
    User updatePassword(int userId, String newPassword) throws ServiceException;

    /**
     * Finds active users.
     *
     * @return the list
     * @throws ServiceException the service exception
     */
    List<User> findActiveUsers() throws ServiceException;

    /**
     * Finds banned users.
     *
     * @return the list
     * @throws ServiceException the service exception
     */
    List<User> findBannedUsers() throws ServiceException;

    /**
     * Updates user ban state.
     *
     * @param userId the user id
     * @param banState the ban state
     * @return true, if successful
     * @throws ServiceException the service exception
     */
    boolean updateUserBanState(int userId, boolean banState) throws ServiceException;

    /**
     * Adds the user loyalty points.
     *
     * @param userId the user id
     * @param pointsToAdd the points to add
     * @return true, if successful
     * @throws ServiceException the service exception
     */
    boolean addUserLoyaltyPoints(int userId, int pointsToAdd) throws ServiceException;

    /**
     * Subtracts user loyalty points.
     *
     * @param userId the user id
     * @param pointsToSubtract the points to subtract
     * @return true, if successful
     * @throws ServiceException the service exception
     */
    boolean subtractUserLoyaltyPoints(int userId, int pointsToSubtract) throws ServiceException;

    /**
     * Updates user loyalty points.
     *
     * @param userId the user id
     * @param pointsToSubtract the points to subtract
     * @return true, if successful
     * @throws ServiceException the service exception
     */
    boolean updateUserLoyaltyPoints(int userId, double pointsToSubtract) throws ServiceException;
}
