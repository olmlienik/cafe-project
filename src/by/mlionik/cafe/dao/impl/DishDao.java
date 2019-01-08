package by.mlionik.cafe.dao.impl;

import by.mlionik.cafe.dao.AbstractDAO;
import by.mlionik.cafe.dao.DaoException;
import by.mlionik.cafe.entity.Dish;
import by.mlionik.cafe.entity.type.DishType;
import by.mlionik.cafe.manager.MessageManager;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static by.mlionik.cafe.dao.query.DishQuery.*;

public class DishDao extends AbstractDAO<Dish> {
    private static final String ID = "id_dish";
    private static final String NAME = "name";
    private static final String COST = "cost";
    private static final String PICTURE = "picture";
    private static final String CATEGORY = "category";
    private static final String IS_DELETED = "is_deleted";
    private static final String UPDATE_DISH_ERROR_MSG = "msg.update.dish.error";
    private static final String FIND_DISH_ERROR_MSG = "msg.find.dish.error";
    private static final String CREATE_DISH_ERROR_MSG = "msg.create.dish.error";
    private static final String DELETE_DISH_ERROR_MSG = "msg.delete.dish.error";

    public DishDao() {
        super();
    }

    @Override
    public Dish create(Dish dish) throws DaoException {
        try (PreparedStatement preparedStatement = connection.prepareStatement(SQL_INSERT_DISH, Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setString(1, dish.getName());
            preparedStatement.setDouble(2,dish.getCost());
            preparedStatement.setString(3,dish.getPicture());
            preparedStatement.setString(4, dish.getCategory().toString().toLowerCase());
            preparedStatement.executeUpdate();
            ResultSet generatedKeys = preparedStatement.getGeneratedKeys();
            if (generatedKeys.next()) {
                dish.setId(generatedKeys.getInt(1));
            }
            return dish;
        } catch (SQLException e) {
            throw new DaoException(MessageManager.getProperty(CREATE_DISH_ERROR_MSG), e);
        }
    }


//    public Dish create(String name, Double cost, String picture, DishType category) throws DaoException {
//        try (PreparedStatement preparedStatement = connection.prepareStatement(SQL_INSERT_DISH, Statement.RETURN_GENERATED_KEYS)) {
//            preparedStatement.setString(1, name);
//            preparedStatement.setDouble(2, cost);
//            preparedStatement.setString(3, picture);
//            preparedStatement.setString(4, category.toString().toLowerCase());
//            preparedStatement.executeUpdate();
//            Dish dish = new Dish();
//            ResultSet generatedKeys = preparedStatement.getGeneratedKeys();
//            if (generatedKeys.next()) {
//              createDishFromResultSet(generatedKeys);
//            }
//            return dish;
//        } catch (SQLException e) {
//            throw new DaoException(MessageManager.getProperty(CREATE_DISH_ERROR_MSG), e);
//        }
//    }

    @Override
    public Dish findById(int id) throws DaoException {
        Dish dish = null;
        try (PreparedStatement preparedStatement = connection.prepareStatement(SQL_SELECT_DISH_BY_ID)) {
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                dish = createDishFromResultSet(resultSet);
            }
            return dish;
        } catch (SQLException e) {
            throw new DaoException(MessageManager.getProperty(FIND_DISH_ERROR_MSG), e);
        }
    }

    @Override
    public Dish update(Dish dish) throws DaoException {
        return null;
    }

    @Override
    public boolean deleteById(int id) throws DaoException {
        return false;
    }

    public List<Dish> findAll() throws DaoException {
        List<Dish> dishList = new ArrayList<>();
        try (Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(SQL_SELECT_ALL_DISHES);
            while (resultSet.next()) {
                dishList.add(createDishFromResultSet(resultSet));
            }
            return dishList;
        } catch (SQLException e) {
            throw new DaoException("Error while finding all dishes in db", e);
        }
    }

    private Dish createDishFromResultSet(ResultSet resultSet) throws SQLException {
        Dish dish = new Dish();
        dish.setId(resultSet.getInt(ID));
        dish.setName(resultSet.getString(NAME));
        dish.setCost(resultSet.getDouble(COST));
        dish.setPicture(resultSet.getString(PICTURE));
        dish.setCategory(DishType.valueOf(resultSet.getString(CATEGORY).toUpperCase()));
        return dish;
    }

}
