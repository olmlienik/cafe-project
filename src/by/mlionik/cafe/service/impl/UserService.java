package by.mlionik.cafe.service.impl;

import by.mlionik.cafe.dao.impl.UserDao;
import by.mlionik.cafe.dao.DaoException;
import by.mlionik.cafe.entity.User;
import by.mlionik.cafe.manager.MessageManager;
import by.mlionik.cafe.dao.TransactionManager;
import by.mlionik.cafe.service.ServiceException;
import by.mlionik.cafe.service.UserServiceAction;
import by.mlionik.cafe.util.UserValidator;

import java.util.List;
import java.util.Optional;

public class UserService implements UserServiceAction {
    private static final String CREATE_USER_ERROR_MSG = "msg.create.user.error";
    private static final String FIND_USER_ERROR_MSG = "msg.find.user.error";
    private static final String DELETE_USER_ERROR_MSG = "msg.delete.user.error";
    private static final String UPDATE_USER_ERROR_MSG = "msg.update.user.error";

    @Override
    public User create(User user) throws ServiceException {
        UserDao userDAO = new UserDao();
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
            throw new ServiceException(MessageManager.getProperty(CREATE_USER_ERROR_MSG), e);
        } finally {
            manager.endTransaction();
        }
    }

    @Override
    public User findById(int id) throws ServiceException {
        UserDao userDAO = new UserDao();
        TransactionManager manager = new TransactionManager();
        manager.beginTransaction(userDAO);
        try {
            User user = Optional.ofNullable(userDAO.findById(id))
                    .orElseThrow(() -> new ServiceException(MessageManager.getProperty(FIND_USER_ERROR_MSG)));
            //todo add reviews to list
            return user;
        } catch (DaoException e) {
            throw new ServiceException(MessageManager.getProperty(FIND_USER_ERROR_MSG), e);
        } finally {
            manager.endTransaction();
        }
    }

    @Override
    public User update(User user) throws ServiceException {
        UserDao userDAO = new UserDao();
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
            throw new ServiceException(MessageManager.getProperty(UPDATE_USER_ERROR_MSG), e);
        } finally {
            manager.endTransaction();
        }
    }

    @Override
    public User updateBalance(User user) throws ServiceException {
        UserDao userDAO = new UserDao();
        TransactionManager manager = new TransactionManager();
        manager.beginTransaction(userDAO);
        try {
            User currentUser = userDAO.updateBalance(user);
            manager.commit();
            return currentUser;
        } catch (DaoException e) {
            manager.rollBack();
            throw new ServiceException(MessageManager.getProperty(UPDATE_USER_ERROR_MSG), e);
        } finally {
            manager.endTransaction();
        }
    }

    @Override
    public User updateLogin(int userId, String newLogin) throws ServiceException {
        UserDao userDAO = new UserDao();
        TransactionManager manager = new TransactionManager();
        manager.beginTransaction(userDAO);
        try {
            User previousUser = userDAO.updateLogin(userId, newLogin);
            manager.commit();
            return previousUser;
        } catch (DaoException e) {
            manager.rollBack();
            throw new ServiceException(MessageManager.getProperty(UPDATE_USER_ERROR_MSG), e);
        } finally {
            manager.endTransaction();
        }
    }

    @Override
    public User updatePassword(int userId, String newPassword) throws ServiceException {
        UserDao userDAO = new UserDao();
        TransactionManager manager = new TransactionManager();
        manager.beginTransaction(userDAO);
        try {
            User previousUser = userDAO.updatePassword(userId, newPassword);
            manager.commit();
            return previousUser;
        } catch (DaoException e) {
            manager.rollBack();
            throw new ServiceException(MessageManager.getProperty(UPDATE_USER_ERROR_MSG), e);
        } finally {
            manager.endTransaction();
        }
    }

    @Override
    public boolean deleteById(int id) throws ServiceException {
        UserDao userDAO = new UserDao();
        TransactionManager manager = new TransactionManager();
        manager.beginTransaction(userDAO);
        try {
            boolean isDeleted = userDAO.deleteById(id);
            manager.commit();
            return isDeleted;
        } catch (DaoException e) {
            manager.rollBack();
            throw new ServiceException(MessageManager.getProperty(DELETE_USER_ERROR_MSG), e);
        } finally {
            manager.endTransaction();
        }
    }

    @Override
    public User findByLoginAndPassword(String login, String password) throws ServiceException {
        UserDao userDAO = new UserDao();
        TransactionManager manager = new TransactionManager();
        manager.beginTransaction(userDAO);
        try {
            User user = userDAO.findByLoginAndPassword(login, password);
            return user;
        } catch (DaoException e) {
            throw new ServiceException(MessageManager.getProperty(FIND_USER_ERROR_MSG), e);
        } finally {
            manager.endTransaction();
        }
    }

    @Override
    public User findByLogin(String login) throws ServiceException {
        UserDao userDAO = new UserDao();
        TransactionManager manager = new TransactionManager();
        manager.beginTransaction(userDAO);
        try {
            User user = userDAO.findByLogin(login);
            return user;
        } catch (DaoException e) {
            throw new ServiceException(MessageManager.getProperty(FIND_USER_ERROR_MSG), e);
        } finally {
            manager.endTransaction();
        }
    }

    @Override
    public List<User> findActiveUsers() throws ServiceException {
        UserDao userDao = new UserDao();
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
        UserDao userDao = new UserDao();
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
        UserDao userDao = new UserDao();
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

}
