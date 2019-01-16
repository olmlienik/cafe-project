package by.mlionik.cafe.service.impl;

import by.mlionik.cafe.dao.impl.UserDaoImpl;
import by.mlionik.cafe.dao.DaoException;
import by.mlionik.cafe.entity.User;
import by.mlionik.cafe.dao.TransactionManager;
import by.mlionik.cafe.service.ServiceException;
import by.mlionik.cafe.service.UserService;
import by.mlionik.cafe.util.UserValidator;

import java.util.List;
import java.util.Optional;

/**
 * The type User service.
 */
public class UserServiceImpl implements UserService {

    @Override
    public User create(User user) throws ServiceException {
        UserDaoImpl userDAO = new UserDaoImpl();
        TransactionManager manager = new TransactionManager();
        manager.beginTransaction(userDAO);
        try {
            User currentUser = new User();
            if (UserValidator.isValidUser(user)) {
                currentUser = userDAO.create(user);
            }
            manager.commit();
            return currentUser;
        } catch (DaoException e) {
            manager.rollBack();
            throw new ServiceException("Exception while creating user", e);
        } finally {
            manager.endTransaction();
        }
    }

    @Override
    public User findById(int id) throws ServiceException {
        UserDaoImpl userDAO = new UserDaoImpl();
        TransactionManager manager = new TransactionManager();
        manager.beginTransaction(userDAO);
        try {
            User user = Optional.ofNullable(userDAO.findById(id))
                    .orElseThrow(() -> new ServiceException("Exception while finding user by id = " + id));
            return user;
        } catch (DaoException e) {
            throw new ServiceException("Exception while finding user by id = " + id, e);
        } finally {
            manager.endTransaction();
        }
    }

    @Override
    public User update(User user) throws ServiceException {
        UserDaoImpl userDAO = new UserDaoImpl();
        TransactionManager manager = new TransactionManager();
        manager.beginTransaction(userDAO);
        try {
            User currentUser = new User();
            if (UserValidator.isValidUser(user)) {
                currentUser = userDAO.update(user);
            }
            manager.commit();
            return currentUser;
        } catch (DaoException e) {
            manager.rollBack();
            throw new ServiceException("Exception while updating user id = " + user.getId(), e);
        } finally {
            manager.endTransaction();
        }
    }

    @Override
    public User updateBalance(User user) throws ServiceException {
        UserDaoImpl userDAO = new UserDaoImpl();
        TransactionManager manager = new TransactionManager();
        manager.beginTransaction(userDAO);
        try {
            User currentUser = userDAO.updateBalance(user);
            manager.commit();
            return currentUser;
        } catch (DaoException e) {
            manager.rollBack();
            throw new ServiceException("Exception while updating user balance", e);
        } finally {
            manager.endTransaction();
        }
    }

    @Override
    public User updateLogin(int userId, String newLogin) throws ServiceException {
        UserDaoImpl userDAO = new UserDaoImpl();
        TransactionManager manager = new TransactionManager();
        manager.beginTransaction(userDAO);
        try {
            User previousUser = userDAO.updateLogin(userId, newLogin);
            manager.commit();
            return previousUser;
        } catch (DaoException e) {
            manager.rollBack();
            throw new ServiceException("Exception while updating user login", e);
        } finally {
            manager.endTransaction();
        }
    }

    @Override
    public User updatePassword(int userId, String newPassword) throws ServiceException {
        UserDaoImpl userDAO = new UserDaoImpl();
        TransactionManager manager = new TransactionManager();
        manager.beginTransaction(userDAO);
        try {
            User previousUser = userDAO.updatePassword(userId, newPassword);
            manager.commit();
            return previousUser;
        } catch (DaoException e) {
            manager.rollBack();
            throw new ServiceException("Exception while updating user password", e);
        } finally {
            manager.endTransaction();
        }
    }

    @Override
    public boolean deleteById(int id) throws ServiceException {
        UserDaoImpl userDAO = new UserDaoImpl();
        TransactionManager manager = new TransactionManager();
        manager.beginTransaction(userDAO);
        try {
            boolean isDeleted = userDAO.deleteById(id);
            manager.commit();
            return isDeleted;
        } catch (DaoException e) {
            manager.rollBack();
            throw new ServiceException("Exception while deleting user by id = " + id, e);
        } finally {
            manager.endTransaction();
        }
    }

    @Override
    public User findByLoginAndPassword(String login, String password) throws ServiceException {
        UserDaoImpl userDAO = new UserDaoImpl();
        TransactionManager manager = new TransactionManager();
        manager.beginTransaction(userDAO);
        try {
            User user = userDAO.findByLoginAndPassword(login, password);
            return user;
        } catch (DaoException e) {
            throw new ServiceException("Exception while finding user by login and password", e);
        } finally {
            manager.endTransaction();
        }
    }

    @Override
    public User findByLogin(String login) throws ServiceException {
        UserDaoImpl userDAO = new UserDaoImpl();
        TransactionManager manager = new TransactionManager();
        manager.beginTransaction(userDAO);
        try {
            User user = userDAO.findByLogin(login);
            return user;
        } catch (DaoException e) {
            throw new ServiceException("Exception while finding user login", e);
        } finally {
            manager.endTransaction();
        }
    }

    @Override
    public List<User> findActiveUsers() throws ServiceException {
        UserDaoImpl userDao = new UserDaoImpl();
        TransactionManager manager = new TransactionManager();
        manager.beginTransaction(userDao);
        try {
            List<User> activeUsers = userDao.findActiveUsers();
            manager.commit();
            return activeUsers;
        } catch (DaoException e) {
            manager.rollBack();
            throw new ServiceException("Exception while finding active users", e);
        } finally {
            manager.endTransaction();
        }
    }

    @Override
    public List<User> findBannedUsers() throws ServiceException {
        UserDaoImpl userDao = new UserDaoImpl();
        TransactionManager manager = new TransactionManager();
        manager.beginTransaction(userDao);
        try {
            List<User> activeUsers = userDao.findBannedUsers();
            manager.commit();
            return activeUsers;
        } catch (DaoException e) {
            manager.rollBack();
            throw new ServiceException("Exception while finding banned users", e);
        } finally {
            manager.endTransaction();
        }
    }

    @Override
    public boolean updateUserBanState(int userId, boolean banState) throws ServiceException {
        UserDaoImpl userDao = new UserDaoImpl();
        TransactionManager manager = new TransactionManager();
        manager.beginTransaction(userDao);
        try {
            boolean updated = userDao.updateBan(userId, banState);
            manager.commit();
            return updated;
        } catch (DaoException e) {
            manager.rollBack();
            throw new ServiceException("Exception while trying to change ban state of user " + userId, e);
        } finally {
            manager.endTransaction();
        }
    }

    @Override
    public boolean addUserLoyaltyPoints(int userId, int pointsToAdd) throws ServiceException {
        UserDaoImpl userDao = new UserDaoImpl();
        TransactionManager manager = new TransactionManager();
        manager.beginTransaction(userDao);
        try {
            User currentUser = userDao.findById(userId);
            double newPoints = currentUser.getLoyaltyPoints() + pointsToAdd;
            boolean updated = userDao.updateLoyaltyPoints(userId, newPoints);
            manager.commit();
            return updated;
        } catch (DaoException e) {
            manager.rollBack();
            throw new ServiceException("Exception while trying to add loyalty points to user id = " + userId, e);
        } finally {
            manager.endTransaction();
        }
    }

    @Override
    public boolean subtractUserLoyaltyPoints(int userId, int pointsToSubtract) throws ServiceException {
        UserDaoImpl userDao = new UserDaoImpl();
        TransactionManager manager = new TransactionManager();
        manager.beginTransaction(userDao);
        try {
            User currentUser = userDao.findById(userId);
            double newPoints;
            if (currentUser.getLoyaltyPoints() - pointsToSubtract > 0) {
                newPoints = currentUser.getLoyaltyPoints() - pointsToSubtract;
            } else {
                newPoints = 0;
            }
            boolean updated = userDao.updateLoyaltyPoints(userId, newPoints);
            manager.commit();
            return updated;
        } catch (DaoException e) {
            manager.rollBack();
            throw new ServiceException("Exception while trying to subtract loyalty points to user id = " + userId, e);
        } finally {
            manager.endTransaction();
        }
    }

    @Override
    public boolean updateUserLoyaltyPoints(int userId, double newPoints) throws ServiceException {
        UserDaoImpl userDao = new UserDaoImpl();
        TransactionManager manager = new TransactionManager();
        manager.beginTransaction(userDao);
        try {
            boolean updated = false;
            if (newPoints >= 0) {
                updated = userDao.updateLoyaltyPoints(userId, newPoints);
            }
            manager.commit();
            return updated;
        } catch (DaoException e) {
            manager.rollBack();
            throw new ServiceException("Exception while trying to update user " + userId + " loyalty points", e);
        } finally {
            manager.endTransaction();
        }
    }
}
