package by.mlionik.cafe.dao.impl;

import by.mlionik.cafe.dao.AbstractDAO;
import by.mlionik.cafe.dao.DaoException;
import by.mlionik.cafe.dao.UserDaoAction;
import by.mlionik.cafe.entity.User;
import by.mlionik.cafe.entity.type.RoleType;
import by.mlionik.cafe.manager.MessageManager;
import org.apache.commons.codec.digest.DigestUtils;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static by.mlionik.cafe.dao.query.UserQuery.*;

public class UserDao extends AbstractDAO<User> implements UserDaoAction {

    private static final String ID_USER = "id_user";
    private static final String IS_BANNED = "is_banned";
    private static final String ROLE = "role";
    private static final String LOGIN = "login";
    private static final String PASSWORD = "password";
    private static final String EMAIL = "email";
    private static final String LOYALTY_POINTS = "loyalty_points";
    private static final String BALANCE = "balance";
    private static final String IS_DELETED = "is_deleted";
    private static final String UPDATE_USER_ERROR_MSG = "msg.update.user.error";
    private static final String FIND_USER_ERROR_MSG = "msg.find.user.error";
    private static final String CREATE_USER_ERROR_MSG = "msg.create.user.error";
    private static final String DELETE_USER_ERROR_MSG = "msg.delete.user.error";

    public UserDao() {
        super();
    }

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
            throw new DaoException(MessageManager.getProperty(CREATE_USER_ERROR_MSG), e);
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
            throw new DaoException(MessageManager.getProperty(FIND_USER_ERROR_MSG), e);
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
            throw new DaoException(MessageManager.getProperty(UPDATE_USER_ERROR_MSG), e);
        }
    }

    public boolean updateBan(int userId, boolean ban) throws DaoException {
        try (PreparedStatement preparedStatement = connection.prepareStatement(SQL_UPDATE_USER_BAN)) {
            preparedStatement.setBoolean(1, ban);
            preparedStatement.setInt(2, userId);
            boolean updated  = preparedStatement.executeUpdate() > 0;
            return updated;
        } catch (SQLException e) {
            throw new DaoException(MessageManager.getProperty(UPDATE_USER_ERROR_MSG), e);
        }
    }

    public User updateBalance(User user) throws DaoException {
        try (PreparedStatement preparedStatement = connection.prepareStatement(SQL_UPDATE_USER_BALANCE)) {
            preparedStatement.setDouble(1, user.getBalance());
            preparedStatement.setInt(2, user.getId());
            preparedStatement.executeUpdate();
            return user;
        } catch (SQLException e) {
            throw new DaoException(MessageManager.getProperty(UPDATE_USER_ERROR_MSG), e);
        }
    }

    public User updateLogin(int userId, String newLogin) throws DaoException {
        User previousUser = findById(userId);
        try (PreparedStatement preparedStatement = connection.prepareStatement(SQL_UPDATE_USER_LOGIN)) {
            preparedStatement.setString(1, newLogin);
            preparedStatement.setInt(2, userId);
            preparedStatement.executeUpdate();
            return previousUser;
        } catch (SQLException e) {
            throw new DaoException(MessageManager.getProperty(UPDATE_USER_ERROR_MSG), e);
        }
    }

    public User updatePassword(int userId, String newPassword) throws DaoException {
        User previousUser = findById(userId);
        try (PreparedStatement preparedStatement = connection.prepareStatement(SQL_UPDATE_USER_PASSWORD)) {
            preparedStatement.setString(1, DigestUtils.sha256Hex(newPassword));
            preparedStatement.setInt(2, userId);
            preparedStatement.executeUpdate();
            return previousUser;
        } catch (SQLException e) {
            throw new DaoException(MessageManager.getProperty(UPDATE_USER_ERROR_MSG), e);
        }
    }

    @Override
    public boolean deleteById(int id) throws DaoException {
        try (PreparedStatement preparedStatement = connection.prepareStatement(SQL_DELETE_USER_BY_ID)) {
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
            return true;
        } catch (SQLException e) {
            throw new DaoException(MessageManager.getProperty(DELETE_USER_ERROR_MSG), e);
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
            throw new DaoException(MessageManager.getProperty(FIND_USER_ERROR_MSG), e);
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
            throw new DaoException(MessageManager.getProperty(FIND_USER_ERROR_MSG), e);
        }
    }

    public List<User> findAll() throws DaoException {
        List<User> userList = new ArrayList<>();
        try (Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(SQL_SELECT_ALL_USERS);
            while (resultSet.next()) {
                userList.add(createUserFromResultSet(resultSet));
            }
            return userList;
        } catch (SQLException e) {
            throw new DaoException("Error while finding all users in db", e);
        }
    }

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

    private User createUserFromResultSet(ResultSet resultSet) throws SQLException {
        User user = new User();
        user.setId(resultSet.getInt(ID_USER));
        user.setRole(RoleType.valueOf(resultSet.getString(ROLE).toUpperCase().replaceAll("\\s", "_")));
        user.setLogin(resultSet.getString(LOGIN));
        user.setPassword(resultSet.getString(PASSWORD));
        user.setEmail(resultSet.getString(EMAIL));
        user.setLoyaltyPoints(resultSet.getInt(LOYALTY_POINTS));
        user.setBalance(resultSet.getDouble(BALANCE));
        user.setIsBanned(resultSet.getBoolean(IS_BANNED));
        user.setDeleted(resultSet.getBoolean(IS_DELETED));
        return user;
    }

}
