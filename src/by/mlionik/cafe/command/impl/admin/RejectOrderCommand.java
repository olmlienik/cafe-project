package by.mlionik.cafe.command.impl.admin;

import by.mlionik.cafe.command.ActionCommand;
import by.mlionik.cafe.controller.Router;
import by.mlionik.cafe.controller.SessionRequestContent;
import by.mlionik.cafe.entity.type.OrderState;
import by.mlionik.cafe.exception.NoSuchRequestParameterException;
import by.mlionik.cafe.manager.ConfigurationManager;
import by.mlionik.cafe.service.ServiceException;
import by.mlionik.cafe.service.impl.OrderService;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class RejectOrderCommand implements ActionCommand {
        private static final Logger logger = LogManager.getLogger();
        private static final String SESSION_LAST_PAGE = "lastPage";
        private static final String ORDER_ID_PARAM = "orderId";
        private static final String ERROR_PAGE_PATH = "path.page.error";

        private OrderService orderService = new OrderService();

        @Override
        public Router execute(SessionRequestContent requestContent) {
            String page;
            try {
                int orderId = Integer.parseInt(requestContent.getParameter(ORDER_ID_PARAM));
                orderService.updateOrderState(orderId, OrderState.MISSED);
                //todo отнять bonuses
                page = (String) requestContent.getSessionAttribute(SESSION_LAST_PAGE);
            } catch (NoSuchRequestParameterException | ServiceException e) {
                logger.log(Level.ERROR, e);
                page =  ConfigurationManager.getProperty(ERROR_PAGE_PATH);
            }
            Router router = new Router();
            router.setPagePath(page);
            router.setRouteType(Router.RouteType.FORWARD);
            return router;
        }
}
