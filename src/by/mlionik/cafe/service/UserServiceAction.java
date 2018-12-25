package by.mlionik.cafe.service;

import by.mlionik.cafe.entity.User;

public interface UserServiceAction {
    User create(User user) throws ServiceException;
    User findById(int id) throws ServiceException;
    User update(User user) throws ServiceException;
    boolean deleteById(int id) throws ServiceException;
    User findByLoginAndPassword(String login, String password) throws ServiceException;
    User findByLogin(String login) throws ServiceException;
}
