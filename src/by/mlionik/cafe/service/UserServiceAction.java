package by.mlionik.cafe.service;

import by.mlionik.cafe.entity.User;
import java.util.List;

public interface UserServiceAction {
    User create(User user) throws ServiceException;

    User findById(int id) throws ServiceException;

    User update(User user) throws ServiceException;

    boolean deleteById(int id) throws ServiceException;

    User findByLoginAndPassword(String login, String password) throws ServiceException;

    User findByLogin(String login) throws ServiceException;

    User updateBalance(User user) throws ServiceException;

    User updateLogin(int userId, String newLogin) throws ServiceException;

    User updatePassword(int userId, String newPassword) throws ServiceException;

    List<User> findActiveUsers() throws ServiceException;

    List<User> findBannedUsers() throws ServiceException;

    boolean updateUserBanState(int userId, boolean banState) throws ServiceException;
}
