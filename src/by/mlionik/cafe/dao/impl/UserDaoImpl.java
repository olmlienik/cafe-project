package by.mlionik.cafe.dao.impl;

import by.mlionik.cafe.dao.AbstractDao;
import by.mlionik.cafe.dao.DaoException;
import by.mlionik.cafe.dao.UserDao;
import by.mlionik.cafe.entity.User;
import by.mlionik.cafe.entity.RoleType;
import org.apache.commons.codec.digest.DigestUtils;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static by.mlionik.cafe.dao.impl.UserQuery.*;

/**
 * The type User dao.
 */
public class UserDaoImpl extends AbstractDao<User> implements UserDao {
    private static final String ID_USER = "id_user";
    private static final String IS_BANNED = "is_banned";
    private static final String ROLE = "role";
    private static final String LOGIN = "login";
    private static final String PASSWORD = "password";
    private static final String EMAIL = "email";
    private static final String LOYALTY_POINTS = "loyalty_points";
    private static final String BALANCE = "balance";
    private static final String IS_DELETED = "is_deleted";

    @Override
    public User create(User user) throws DaoException {
        try (PreparedStatement preparedStatement = connection.prepareStatement(SQL_INSERT_USER, Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setString(1, user.getLogin());
            preparedStatement.setString(2, DigestUtils.sha256Hex(user.getPassword()));
            preparedStatement.setString(3, user.getEmail());
            preparedStatement.executeUpdate();
            ResultSet generatedKeys = preparedStatement.getGeneratedKeys();
            if (generatedKeys.next()) {
                user.setId(generatedKeys.getInt(1));
            }
            return user;
        } catch (SQLException e) {
            throw new DaoException("Exception while trying to create user in db", e);
        }
    }

    @Override
    public User findById(int id) throws DaoException {
        User user = null;
        try (PreparedStatement preparedStatement = connection.prepareStatement(SQL_SELECT_USER_BY_ID)) {
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                user = createUserFromResultSet(resultSet);
            }
            return user;
        } catch (SQLException e) {
            throw new DaoException("Exception while trying to find user by id = " + id + " in db", e);
        }
    }

    @Override
    public User update(User user) throws DaoException {
        try (PreparedStatement preparedStatement = connection.prepareStatement(SQL_UPDATE_USER)) {
            preparedStatement.setString(1, user.getEmail());
            preparedStatement.setInt(4, user.getId());
            preparedStatement.executeUpdate();
            return user;
        } catch (SQLException e) {
            throw new DaoException("Exception while trying to update user id = " + user.getId() + " in db", e);
        }
    }

    public boolean updateBan(int userId, boolean ban) throws DaoException {
        try (PreparedStatement preparedStatement = connection.prepareStatement(SQL_UPDATE_USER_BAN)) {
            preparedStatement.setBoolean(1, ban);
            preparedStatement.setInt(2, userId);
            boolean updated = preparedStatement.executeUpdate() > 0;
            return updated;
        } catch (SQLException e) {
            throw new DaoException("Exception while trying to update user id = " + userId + " ban state in db", e);
        }
    }

    @Override
    public User updateBalance(User user) throws DaoException {
        try (PreparedStatement preparedStatement = connection.prepareStatement(SQL_UPDATE_USER_BALANCE)) {
            preparedStatement.setDouble(1, user.getBalance());
            preparedStatement.setInt(2, user.getId());
            preparedStatement.executeUpdate();
            return user;
        } catch (SQLException e) {
            throw new DaoException("Exception while trying to update user id = " + user.getId() + " balance in db", e);
        }
    }

    @Override
    public User updateLogin(int userId, String newLogin) throws DaoException {
        User previousUser = findById(userId);
        try (PreparedStatement preparedStatement = connection.prepareStatement(SQL_UPDATE_USER_LOGIN)) {
            preparedStatement.setString(1, newLogin);
            preparedStatement.setInt(2, userId);
            preparedStatement.executeUpdate();
            return previousUser;
        } catch (SQLException e) {
            throw new DaoException("Exception while trying to update user id = " + userId + " login in db", e);
        }
    }

    @Override
    public User updatePassword(int userId, String newPassword) throws DaoException {
        User previousUser = findById(userId);
        try (PreparedStatement preparedStatement = connection.prepareStatement(SQL_UPDATE_USER_PASSWORD)) {
            preparedStatement.setString(1, DigestUtils.sha256Hex(newPassword));
            preparedStatement.setInt(2, userId);
            preparedStatement.executeUpdate();
            return previousUser;
        } catch (SQLException e) {
            throw new DaoException("Exception while trying to update user id = " + userId + " password in db", e);
        }
    }

    @Override
    public boolean updateLoyaltyPoints(int userId, double newPoints) throws DaoException {
        try (PreparedStatement preparedStatement = connection.prepareStatement(SQL_UPDATE_USER_LOYALTY_POINTS)) {
            preparedStatement.setDouble(1, newPoints);
            preparedStatement.setInt(2, userId);
            boolean updated = preparedStatement.executeUpdate() > 0;
            return updated;
        } catch (SQLException e) {
            throw new DaoException("Exception while trying to update user id = " + userId + " loyalty points in db", e);
        }
    }

    @Override
    public boolean deleteById(int id) throws DaoException {
        try (PreparedStatement preparedStatement = connection.prepareStatement(SQL_DELETE_USER_BY_ID)) {
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
            return true;
        } catch (SQLException e) {
            throw new DaoException("Exception while trying to delete user by id = " + id + " in db", e);
        }
    }

    @Override
    public User findByLoginAndPassword(String login, String password) throws DaoException {
        User user = null;
        try (PreparedStatement preparedStatement = connection.prepareStatement(SQL_SELECT_USER_BY_LOGIN_AND_PASSWORD)) {
            preparedStatement.setString(1, login);
            preparedStatement.setString(2, DigestUtils.sha256Hex(password));
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                user = createUserFromResultSet(resultSet);
            }
            return user;
        } catch (SQLException e) {
            throw new DaoException("Exception while trying to find user by login and password in db", e);
        }
    }

    @Override
    public User findByLogin(String login) throws DaoException {
        User user = null;
        try (PreparedStatement preparedStatement = connection.prepareStatement(SQL_SELECT_USER_BY_LOGIN)) {
            preparedStatement.setString(1, login);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                user = createUserFromResultSet(resultSet);
            }
            return user;
        } catch (SQLException e) {
            throw new DaoException("Exception while trying to find user by login in db", e);
        }
    }

    @Override
    public List<User> findActiveUsers() throws DaoException {
        List<User> userList = new ArrayList<>();
        try (Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(SQL_SELECT_UNBANNED_USERS);
            while (resultSet.next()) {
                userList.add(createUserFromResultSet(resultSet));
            }
            return userList;
        } catch (SQLException e) {
            throw new DaoException("Error while finding unbanned users in db", e);
        }
    }

    @Override
    public List<User> findBannedUsers() throws DaoException {
        List<User> userList = new ArrayList<>();
        try (Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(SQL_SELECT_BANNED_USERS);
            while (resultSet.next()) {
                userList.add(createUserFromResultSet(resultSet));
            }
            return userList;
        } catch (SQLException e) {
            throw new DaoException("Error while finding banned users in db", e);
        }
    }

    /**
     * Creates the user from result set.
     *
     * @param resultSet the result set
     * @return the user
     * @throws SQLException the SQL exception
     */
    private User createUserFromResultSet(ResultSet resultSet) throws SQLException {
        User user = new User();
        user.setId(resultSet.getInt(ID_USER));
        user.setRole(RoleType.valueOf(resultSet.getString(ROLE).toUpperCase().replaceAll("\\s", "_")));
        user.setLogin(resultSet.getString(LOGIN));
        user.setPassword(resultSet.getString(PASSWORD));
        user.setEmail(resultSet.getString(EMAIL));
        user.setLoyaltyPoints(resultSet.getDouble(LOYALTY_POINTS));
        user.setBalance(resultSet.getDouble(BALANCE));
        user.setIsBanned(resultSet.getBoolean(IS_BANNED));
        user.setDeleted(resultSet.getBoolean(IS_DELETED));
        return user;
    }
}
