package by.mlionik.cafe.dao;

import by.mlionik.cafe.entity.Dish;
import by.mlionik.cafe.entity.Order;
import by.mlionik.cafe.entity.OrderPart;
import by.mlionik.cafe.entity.type.OrderState;
import java.util.List;

/**
 * The interface Order dao.
 */
public interface OrderDao {

    /**
     * Finds orders in process without composition.
     *
     * @return the list of orders in process
     * @throws DaoException the dao exception
     */
    List<Order> findOrdersInProcessWithoutComposition() throws DaoException;

    /**
     * Finds order composition by order id.
     *
     * @param orderId the order id
     * @return the list of components
     * @throws DaoException the dao exception
     */
    List<OrderPart> findOrderCompositionByOrderId(int orderId) throws DaoException;

    /**
     * Finds orders by user id.
     *
     * @param userId the user id
     * @return the list of orders
     * @throws DaoException the dao exception
     */
    List<Order> findOrdersByUserId(int userId) throws DaoException;

    /**
     * Updates order state.
     *
     * @param orderId the order id
     * @param orderState the order state
     * @return true, if successful, false otherwise
     * @throws DaoException the dao exception
     */
    boolean updateOrderState(int orderId, OrderState orderState) throws DaoException;

    /**
     * Inserts dishes to order composition.
     *
     * @param orderId the order id
     * @param dishes the dishes
     * @return true, if successful, false otherwise
     * @throws DaoException the dao exception
     */
    boolean insertDishesToOrderComposition(int orderId, List<Dish> dishes) throws DaoException;
}
