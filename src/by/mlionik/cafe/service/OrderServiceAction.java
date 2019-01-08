package by.mlionik.cafe.service;

import by.mlionik.cafe.entity.Dish;
import by.mlionik.cafe.entity.Order;
import by.mlionik.cafe.entity.type.OrderState;
import by.mlionik.cafe.entity.type.PaymentType;

import java.util.List;

public interface OrderServiceAction {

    Order create(int userId, String deliveryTime, PaymentType paymentType, OrderState state,
                 List<Dish> dishes) throws ServiceException;

    List<Order> findOrdersInProcess() throws ServiceException;

    List<Order> findOrdersInProcessWithComposition() throws ServiceException;

    List<Order> findOrdersByUserId(int userId) throws ServiceException;

    boolean updateOrderState(int orderId, OrderState orderState) throws ServiceException;
}
