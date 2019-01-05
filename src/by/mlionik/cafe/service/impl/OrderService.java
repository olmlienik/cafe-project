package by.mlionik.cafe.service.impl;

import by.mlionik.cafe.dao.DaoException;
import by.mlionik.cafe.dao.TransactionManager;
import by.mlionik.cafe.dao.impl.OrderDao;
import by.mlionik.cafe.entity.Order;
import by.mlionik.cafe.manager.MessageManager;
import by.mlionik.cafe.service.ServiceException;

import java.util.List;

public class OrderService {
    private static final String CREATE_ORDER_ERROR_MSG = "msg.create.order.error";
    private static final String FIND_ORDER_ERROR_MSG = "msg.find.order.error";
    private static final String DELETE_ORDER_ERROR_MSG = "msg.delete.order.error";
    private static final String UPDATE_ORDER_ERROR_MSG = "msg.update.order.error";

    public Order create(Order order) throws ServiceException {
        OrderDao orderDao = new OrderDao();
        TransactionManager manager = new TransactionManager();
        manager.beginTransaction(orderDao);
        try {
            Order currentOrder = orderDao.create(order);
            manager.commit();
            return currentOrder;
        } catch (DaoException e) {
            manager.rollBack();
            throw new ServiceException(MessageManager.getProperty(CREATE_ORDER_ERROR_MSG), e);
        } finally {
            manager.endTransaction();
        }
    }

    public List<Order> findOrdersInProcess() throws ServiceException {
        OrderDao orderDao = new OrderDao();
        TransactionManager manager = new TransactionManager();
        manager.beginTransaction(orderDao);
        try {
            List<Order> ordersInProcess = orderDao.findOrdersInProcess();
            manager.commit();
            return ordersInProcess;
        } catch (DaoException e) {
            manager.rollBack();
            throw new ServiceException(MessageManager.getProperty(FIND_ORDER_ERROR_MSG), e);
        }
    }

}
