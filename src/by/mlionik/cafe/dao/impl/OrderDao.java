package by.mlionik.cafe.dao.impl;

import by.mlionik.cafe.dao.AbstractDAO;
import by.mlionik.cafe.dao.DaoException;
import by.mlionik.cafe.entity.Dish;
import by.mlionik.cafe.entity.Order;
import by.mlionik.cafe.entity.OrderPart;
import by.mlionik.cafe.entity.type.OrderState;
import by.mlionik.cafe.entity.type.PaymentType;
import by.mlionik.cafe.manager.MessageManager;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import static by.mlionik.cafe.dao.query.OrderQuery.*;

public class OrderDao extends AbstractDAO<Order> {
    private static final String ID = "id_order";
    private static final String ID_USER = "id_user";
    private static final String DELIVERY_TIME = "delivery_time";
    private static final String PAYMENT_TYPE = "payment_type";
    private static final String PRICE = "price";
    private static final String STATE = "state";
    private static final String UPDATE_ORDER_ERROR_MSG = "msg.update.order.error";
    private static final String FIND_ORDER_ERROR_MSG = "msg.find.order.error";
    private static final String CREATE_ORDER_ERROR_MSG = "msg.create.order.error";
    private static final String DELETE_ORDER_ERROR_MSG = "msg.delete.order.error";

    @Override
    public Order create(Order order) throws DaoException {
        try (PreparedStatement preparedStatement = connection.prepareStatement(SQL_INSERT_ORDER, Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setInt(1, order.getIdUser());
            preparedStatement.setString(2, order.getDeliveryTime());
            preparedStatement.setString(3, order.getPaymentType().toString().toLowerCase());
            preparedStatement.setString(4, order.getState().toString().toLowerCase());
            preparedStatement.executeUpdate();
            ResultSet generatedKeys = preparedStatement.getGeneratedKeys();
            if (generatedKeys.next()) {
                order.setId(generatedKeys.getInt(1));
            }
            return order;
        } catch (SQLException e) {
            throw new DaoException(MessageManager.getProperty(CREATE_ORDER_ERROR_MSG), e);
        }
    }

    public boolean insertDishesToOrderComposition(int orderId, List<Dish> dishes) throws DaoException {
        if (orderId > 0 && !dishes.isEmpty()) {
            try (PreparedStatement preparedStatement = connection.prepareStatement(SQL_INSERT_ORDER_COMPOSITION, Statement.RETURN_GENERATED_KEYS)) {
                if (preparedStatement != null) {
                    boolean successfulAdded = true;
                    for (Dish currentDish : dishes) {
                        preparedStatement.setInt(1, orderId);
                        preparedStatement.setInt(2, currentDish.getId());
                        successfulAdded &= preparedStatement.executeUpdate() > 0;
                    }
                    return successfulAdded;
                }
            } catch (SQLException e) {
                throw new DaoException("Exception while trying to add order parts " + dishes.toString() +
                        " to order " + orderId + " in db", e);
            }
        }
        return false;
    }

    private void insertDishIntoOrder(int orderId, Dish dish) throws DaoException {
        try (PreparedStatement preparedStatement = connection.prepareStatement(SQL_INSERT_ORDER_COMPOSITION, Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setInt(1, orderId);
            preparedStatement.setInt(2, dish.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new DaoException(MessageManager.getProperty(CREATE_ORDER_ERROR_MSG), e);
        }
    }

    public List<Order> findOrdersInProcessWithoutComposition() throws DaoException {
        List<Order> orderList = new ArrayList<>();
        try (Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(SQL_SELECT_ORDERS_IN_PROCESS);
            while (resultSet.next()) {
                orderList.add(createOrderFromResultSet(resultSet));
            }
            return orderList;
        } catch (SQLException e) {
            throw new DaoException(MessageManager.getProperty(FIND_ORDER_ERROR_MSG), e);
        }
    }

    public List<OrderPart> findOrderCompositionByOrderId(int orderId) throws DaoException {
        List<OrderPart> orderComposition = new ArrayList<>();
        try (PreparedStatement preparedStatement = connection.prepareStatement(SQL_SELECT_ORDER_COMPOSITION, Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setInt(1, orderId);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                orderComposition.add(createOrderPartFromResultSet(resultSet));
            }
            return orderComposition;
        } catch (SQLException e) {
            throw new DaoException("Exception while trying to find order " + orderId + "composition in db", e);
        }
    }

    public List<Order> findOrdersByUserId(int userId) throws DaoException{
        List<Order> orderList = new ArrayList<>();
        try (PreparedStatement preparedStatement = connection.prepareStatement(SQL_SELECT_ORDERS_BY_CLIENT_ID, Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setInt(1, userId);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                orderList.add(createOrderFromResultSet(resultSet));
            }
            return orderList;
        } catch (SQLException e) {
            throw new DaoException(MessageManager.getProperty(CREATE_ORDER_ERROR_MSG), e);
        }
    }

    public boolean updateOrderState(int orderId, OrderState orderState) throws DaoException{
        try (PreparedStatement preparedStatement = connection.prepareStatement(SQL_UPDATE_ORDER_STATE)) {
            preparedStatement.setString(1, orderState.toString().toLowerCase());
            preparedStatement.setInt(2, orderId);
            return  (preparedStatement.executeUpdate() > 0);
        } catch (SQLException e) {
            throw new DaoException("Exception while trying to update order id = " + orderId + " state", e);
        }
    }

    @Override
    public Order findById(int id) throws DaoException {
        return null;
    }

    @Override
    public Order update(Order entity) throws DaoException {
        return null;
    }

    @Override
    public boolean deleteById(int id) throws DaoException {
        return false;
    }

    private Order createOrderFromResultSet(ResultSet resultSet) throws SQLException {
        Order order = new Order();
        order.setId(resultSet.getInt(ID));
        order.setIdUser(resultSet.getInt(ID_USER));
        order.setDeliveryTime(resultSet.getString(DELIVERY_TIME));
        order.setPaymentType(PaymentType.valueOf(resultSet.getString(PAYMENT_TYPE).toUpperCase()));
        return order;
    }

    private OrderPart createOrderPartFromResultSet(ResultSet resultSet) throws SQLException {
        OrderPart orderPart = new OrderPart();
        orderPart.setId(resultSet.getInt(ID));
        orderPart.setOrderId(resultSet.getInt("id_order"));
        orderPart.setDishId(resultSet.getInt("id_dish"));
        return orderPart;
    }

}
