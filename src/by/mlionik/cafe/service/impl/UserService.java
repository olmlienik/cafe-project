package by.mlionik.cafe.service.impl;

import by.mlionik.cafe.dao.impl.UserDao;
import by.mlionik.cafe.dao.DaoException;
import by.mlionik.cafe.entity.User;
import by.mlionik.cafe.manager.MessageManager;
import by.mlionik.cafe.pool.TransactionManager;
import by.mlionik.cafe.service.ServiceException;
import by.mlionik.cafe.service.UserServiceAction;
import by.mlionik.cafe.util.UserValidator;
import java.util.Optional;

public class UserService implements UserServiceAction {
    private static final String CREATE_USER_ERROR_MSG = "msg.create.user.error";
    private static final String FIND_USER_ERROR_MSG = "msg.find.user.error";
    private static final String DELETE_USER_ERROR_MSG = "msg.delete.user.error";
    private static final String UPDATE_USER_ERROR_MSG = "msg.update.user.error";

    private UserValidator userValidator = new UserValidator();

    @Override
    public User create(User user) throws ServiceException {
        UserDao userDAO = new UserDao();
        TransactionManager manager = new TransactionManager();
        manager.beginTransaction(userDAO);
        try {
            User currentUser = new User();
            if (userValidator.isValid(user)) {
                currentUser = userDAO.create(user);
            } else {
                currentUser.setInvalidUserInfo(userValidator.getInvalidUserInfo());
            }
            return currentUser;
        } catch (DaoException e) {
            throw new ServiceException(MessageManager.getProperty(CREATE_USER_ERROR_MSG), e);
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
        }
    }

    @Override
    public User update(User user) throws ServiceException {
        UserDao userDAO = new UserDao();
        TransactionManager manager = new TransactionManager();
        manager.beginTransaction(userDAO);
        try {
            User currentUser = new User();
            if (userValidator.isValid(user)) {
                currentUser = userDAO.update(user);
            } else {
                currentUser.setInvalidUserInfo(userValidator.getInvalidUserInfo());
            }
            return currentUser;
        } catch (DaoException e) {
            throw new ServiceException(MessageManager.getProperty(UPDATE_USER_ERROR_MSG), e);
        }
    }

    @Override
    public boolean deleteById(int id) throws ServiceException {
        UserDao userDAO = new UserDao();
        TransactionManager manager = new TransactionManager();
        manager.beginTransaction(userDAO);
        try {
            return userDAO.deleteById(id);
        } catch (DaoException e) {
            throw new ServiceException(MessageManager.getProperty(DELETE_USER_ERROR_MSG), e);
        }
    }

    @Override
    public User findByLoginAndPassword(String login, String password) throws ServiceException {
        UserDao userDAO = new UserDao();
        TransactionManager manager = new TransactionManager();
        manager.beginTransaction(userDAO);
        try {
            return userDAO.findByLoginAndPassword(login, password);
        } catch (DaoException e) {
            throw new ServiceException(MessageManager.getProperty(FIND_USER_ERROR_MSG), e);
        }
    }

    @Override
    public User findByLogin(String login) throws ServiceException {
        UserDao userDAO = new UserDao();
        TransactionManager manager = new TransactionManager();
        manager.beginTransaction(userDAO);
        try {
            return userDAO.findByLogin(login);
        } catch (DaoException e) {
            throw new ServiceException(MessageManager.getProperty(FIND_USER_ERROR_MSG), e);
        }
    }

}
