package by.mlionik.cafe.service.impl;

import by.mlionik.cafe.dao.DaoException;
import by.mlionik.cafe.dao.TransactionManager;
import by.mlionik.cafe.dao.impl.DishDao;
import by.mlionik.cafe.dao.impl.OrderDao;
import by.mlionik.cafe.entity.Dish;
import by.mlionik.cafe.entity.Order;
import by.mlionik.cafe.entity.OrderPart;
import by.mlionik.cafe.entity.type.OrderState;
import by.mlionik.cafe.entity.type.PaymentType;
import by.mlionik.cafe.service.OrderServiceAction;
import by.mlionik.cafe.service.ServiceException;
import java.util.List;

public class OrderService implements OrderServiceAction {

    @Override
    public Order create(int userId, String deliveryTime, PaymentType paymentType, OrderState state,
                        List<Dish> dishes) throws ServiceException {
        double totalPrice = 0;
        for (Dish currentDish : dishes) {
            totalPrice += currentDish.getCost();
        }
        Order newOrder = new Order(userId, deliveryTime, paymentType, totalPrice, state, dishes);
        OrderDao orderDao = new OrderDao();
        TransactionManager manager = new TransactionManager();
        manager.beginTransaction(orderDao);
        try {
            Order currentOrder = orderDao.create(newOrder);
            if (orderDao.insertDishesToOrderComposition(currentOrder.getId(), dishes)) {
                manager.commit();
            } else {
                manager.rollBack();
                throw new ServiceException("Exception while trying to add dishes to order");
            }
            return currentOrder;
        } catch (DaoException e) {
            manager.rollBack();
            throw new ServiceException("Exception while creating order ", e);
        } finally {
            manager.endTransaction();
        }
    }

    @Override
    public List<Order> findOrdersInProcess() throws ServiceException {
        OrderDao orderDao = new OrderDao();
        TransactionManager manager = new TransactionManager();
        manager.beginTransaction(orderDao);
        try {
            List<Order> ordersInProcess = orderDao.findOrdersInProcessWithoutComposition();
            return ordersInProcess;
        } catch (DaoException e) {
            throw new ServiceException("Exception while finding orders in process ", e);
        } finally {
            manager.endTransaction();
        }
    }

    @Override
    public List<Order> findOrdersInProcessWithComposition() throws ServiceException {
        OrderDao orderDao = new OrderDao();
        DishDao dishDao = new DishDao();
        TransactionManager manager = new TransactionManager();
        manager.beginTransaction(orderDao, dishDao);
        try {
            List<Order> ordersInProcess = orderDao.findOrdersInProcessWithoutComposition();
            if (!ordersInProcess.isEmpty()) {
                for (Order currentOrder : ordersInProcess) {
                    List<OrderPart> orderPartList = orderDao.findOrderCompositionByOrderId(currentOrder.getId());
                    if (!orderPartList.isEmpty()) {
                        for (OrderPart orderPart : orderPartList) {
                            Dish currentDish = dishDao.findById(orderPart.getDishId());
                            currentOrder.addToBasket(currentDish);
                            double currentOrderPrice = currentOrder.getPrice() + currentDish.getCost();
                            currentOrder.setPrice(currentOrderPrice);
                        }
                    }
                }
            }
            return ordersInProcess;
        } catch (DaoException e) {
            throw new ServiceException("Exception while finding orders in process with composition", e);
        } finally {
            manager.endTransaction();
        }
    }

    @Override
    public List<Order> findOrdersByUserId(int userId) throws ServiceException {
        OrderDao orderDao = new OrderDao();
        DishDao dishDao = new DishDao();
        TransactionManager manager = new TransactionManager();
        manager.beginTransaction(orderDao, dishDao);
        try {
            List<Order> orderList = orderDao.findOrdersByUserId(userId);
            if (!orderList.isEmpty()) {
                for (Order currentOrder : orderList) {
                    List<OrderPart> orderPartList = orderDao.findOrderCompositionByOrderId(currentOrder.getId());
                    if (!orderPartList.isEmpty()) {
                        for (OrderPart orderPart : orderPartList) {
                            Dish currentDish = dishDao.findById(orderPart.getDishId());
                            currentOrder.addToBasket(currentDish);
                            double currentOrderPrice = currentOrder.getPrice() + currentDish.getCost();
                            currentOrder.setPrice(currentOrderPrice);
                        }
                    }
                }
            }
            return orderList;
        } catch (DaoException e) {
            throw new ServiceException("Exception while finding orders by client id = " + userId + " with composition", e);
        } finally {
            manager.endTransaction();
        }
    }

    @Override
    public boolean updateOrderState(int orderId, OrderState orderState) throws ServiceException {
        OrderDao orderDao = new OrderDao();
        TransactionManager manager = new TransactionManager();
        manager.beginTransaction(orderDao);
        try {
            boolean updated = orderDao.updateOrderState(orderId, orderState);
            manager.commit();
            return updated;
        } catch (DaoException e) {
            manager.rollBack();
            throw new ServiceException("Exception while trying to update order id = " + orderId + "state ", e);
        } finally {
            manager.endTransaction();
        }
    }

}
