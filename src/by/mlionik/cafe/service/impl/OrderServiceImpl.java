package by.mlionik.cafe.service.impl;

import by.mlionik.cafe.dao.DaoException;
import by.mlionik.cafe.dao.TransactionManager;
import by.mlionik.cafe.dao.impl.DishDaoImpl;
import by.mlionik.cafe.dao.impl.OrderDaoImpl;
import by.mlionik.cafe.entity.Dish;
import by.mlionik.cafe.entity.Order;
import by.mlionik.cafe.entity.OrderPart;
import by.mlionik.cafe.entity.type.OrderState;
import by.mlionik.cafe.entity.type.PaymentType;
import by.mlionik.cafe.service.OrderService;
import by.mlionik.cafe.service.ServiceException;
import java.util.List;
/**
 * The type Order service.
 */
public class OrderServiceImpl implements OrderService {

    @Override
    public Order create(int userId, String deliveryTime, PaymentType paymentType, OrderState state,
                        List<Dish> dishes) throws ServiceException {
        double totalPrice = 0;
        for (Dish currentDish : dishes) {
            totalPrice += currentDish.getCost();
        }
        Order newOrder = new Order(userId, deliveryTime, paymentType, totalPrice, state, dishes);
        OrderDaoImpl orderDao = new OrderDaoImpl();
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
    public List<Order> findOrdersInProcessWithComposition() throws ServiceException {
        OrderDaoImpl orderDao = new OrderDaoImpl();
        DishDaoImpl dishDao = new DishDaoImpl();
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
                            currentOrder.setPrice(currentOrder.getPrice() + currentDish.getCost());
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
    public boolean updateOrderState(int orderId, OrderState orderState) throws ServiceException {
        OrderDaoImpl orderDao = new OrderDaoImpl();
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
