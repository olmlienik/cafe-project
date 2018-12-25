package by.mlionik.cafe.dao;

import by.mlionik.cafe.entity.User;

public interface UserDaoAction {
    User findByLoginAndPassword(String login, String password) throws DaoException;

    User findByLogin(String login) throws DaoException;
}
