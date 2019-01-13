package by.mlionik.cafe.command.impl.admin;

import by.mlionik.cafe.command.ActionCommand;
import by.mlionik.cafe.controller.Router;
import by.mlionik.cafe.controller.SessionRequestContent;
import by.mlionik.cafe.entity.Order;
import by.mlionik.cafe.manager.ConfigurationManager;
import by.mlionik.cafe.service.ServiceException;
import by.mlionik.cafe.service.impl.OrderServiceImpl;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

/**
 * The type Find orders in process command.
 */
public class FindOrdersInProcessCommand implements ActionCommand {
    private static Logger logger = LogManager.getLogger();
    private static final String ORDERS_IN_PROCESS_ATTR = "ordersInProcess";
    private static final String ATTR_NO_ORDERS_IN_PROCESS = "noOrdersInProcess";
    private static final String ERROR_PAGE_PATH = "path.page.error";
    private static final String ERROR_ATTR = "errorMsg";
    private static final String ORDERS_PAGE_PATH = "path.page.orders";
    private static OrderServiceImpl orderService = new OrderServiceImpl();

    @Override
    public Router execute(SessionRequestContent requestContent) {
        String page;
        try {
            List<Order> unfinishedOrders = orderService.findOrdersInProcessWithComposition();
            if (!unfinishedOrders.isEmpty()){
                requestContent.setAttribute(ORDERS_IN_PROCESS_ATTR, unfinishedOrders);
            } else {
                requestContent.setAttribute(ATTR_NO_ORDERS_IN_PROCESS, "bad");
            }
            page = ConfigurationManager.getProperty(ORDERS_PAGE_PATH);
        } catch (ServiceException e) {
            logger.log(Level.ERROR, e);
            requestContent.setAttribute(ERROR_ATTR, e.getMessage());
            page = ConfigurationManager.getProperty(ERROR_PAGE_PATH);
        }
        Router router = new Router();
        router.setRouteType(Router.RouteType.FORWARD);
        router.setPagePath(page);
        return router;
    }
}
