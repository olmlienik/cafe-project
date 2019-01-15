package by.mlionik.cafe.service;

import by.mlionik.cafe.entity.Dish;
import by.mlionik.cafe.entity.Order;
import by.mlionik.cafe.entity.OrderState;
import by.mlionik.cafe.entity.PaymentType;

import java.util.List;

/**
 * The interface Order service.
 */
public interface OrderService {

    /**
     * Creates the order.
     *
     * @param userId the user id
     * @param deliveryTime the delivery time
     * @param paymentType the payment type
     * @param state the state
     * @param dishes the dishes
     * @return the order
     * @throws ServiceException the service exception
     */
    Order create(int userId, String deliveryTime, PaymentType paymentType, OrderState state,
                 List<Dish> dishes) throws ServiceException;

    /**
     * Finds orders in process with composition.
     *
     * @return the list of orders in process
     * @throws ServiceException the service exception
     */
    List<Order> findOrdersInProcessWithComposition() throws ServiceException;

    /**
     * Updates order state.
     *
     * @param orderId the order id
     * @param orderState the order state
     * @return true, if successful
     * @throws ServiceException the service exception
     */
    boolean updateOrderState(int orderId, OrderState orderState) throws ServiceException;
}
