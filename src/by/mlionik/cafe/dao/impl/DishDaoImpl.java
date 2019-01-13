package by.mlionik.cafe.dao.impl;

import by.mlionik.cafe.dao.AbstractDao;
import by.mlionik.cafe.dao.DaoException;
import by.mlionik.cafe.dao.DishDao;
import by.mlionik.cafe.entity.Dish;
import by.mlionik.cafe.entity.type.DishType;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static by.mlionik.cafe.dao.impl.DishQuery.*;

/**
 * The type Dish dao.
 */
public class DishDaoImpl extends AbstractDao<Dish> implements DishDao {
    private static final String ID = "id_dish";
    private static final String NAME = "name";
    private static final String COST = "cost";
    private static final String PICTURE = "picture";
    private static final String CATEGORY = "category";

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
            throw new DaoException("Exception while trying to create dish in db", e);
        }
    }

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
            throw new DaoException("Exception while trying to find dish by id = " + id + " in db", e);
        }
    }

    @Override
    public Dish update(Dish dish) throws DaoException {
        return null;
    }

    @Override
    public boolean deleteById(int id) throws DaoException {
        try (PreparedStatement preparedStatement = connection.prepareStatement(SQL_DELETE_DISH_BY_ID)) {
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
            return true;
        } catch (SQLException e) {
            throw new DaoException("Exception while trying to delete dish by id = " + id + " in db", e);
        }
    }

    @Override
    public List<Dish> findAll() throws DaoException {
        List<Dish> dishList = new ArrayList<>();
        try (Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(SQL_SELECT_ALL_DISHES);
            while (resultSet.next()) {
                dishList.add(createDishFromResultSet(resultSet));
            }
            return dishList;
        } catch (SQLException e) {
            throw new DaoException("Exception while finding all dishes in db", e);
        }
    }

    /**
     * Creates the dish from result set.
     *
     * @param resultSet the result set
     * @return the dish
     * @throws SQLException the SQL exception
     */
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
