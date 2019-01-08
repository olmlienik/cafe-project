package by.mlionik.cafe.command.impl.user;

import by.mlionik.cafe.command.ActionCommand;
import by.mlionik.cafe.controller.Router;
import by.mlionik.cafe.controller.SessionRequestContent;
import by.mlionik.cafe.entity.Order;
import by.mlionik.cafe.entity.User;
import by.mlionik.cafe.entity.type.OrderState;
import by.mlionik.cafe.entity.type.PaymentType;
import by.mlionik.cafe.exception.NoSuchRequestParameterException;
import by.mlionik.cafe.manager.ConfigurationManager;
import by.mlionik.cafe.service.ServiceException;
import by.mlionik.cafe.service.impl.OrderService;
import by.mlionik.cafe.service.impl.UserService;
import by.mlionik.cafe.util.OrderValidator;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class CreateOrderCommand implements ActionCommand {
    private static Logger logger = LogManager.getLogger();
    private static final String SESSION_LAST_PAGE = "lastPage";
    private static final String SESSION_BASKET = "basket";
    private static final String DELIVERY_TIME_PARAM = "deliveryTime";
    private static final String PAYMENT_TYPE_PARAM = "paymentType";
    private static final String ERROR_PAGE_PATH = "path.page.error";
    private static final String ERROR_ATTR = "errorMsg";
    private static final String ATTR_BAD_TIME = "badTime";
    private static final String ATTR_BAD_PAY_TYPE = "badPayType";
    private static final String ATTR_NOT_ENOUGH = "notEnough";
    private static final String ATTR_EMPTY_BASKET = "emptyBasket";
    private static final String SESSION_USER = "user";

    private OrderService orderService = new OrderService();
    private UserService userService = new UserService();

    @Override
    public Router execute(SessionRequestContent requestContent) {
        String page;
        requestContent.setAttribute(ATTR_BAD_PAY_TYPE, null);
        requestContent.setAttribute(ATTR_BAD_TIME, null);
        requestContent.setAttribute(ATTR_NOT_ENOUGH, null);

        try {
            Order order = (Order) requestContent.getSessionAttribute(SESSION_BASKET);
            String gettingTime = requestContent.getParameter(DELIVERY_TIME_PARAM);

            if (!order.getDishes().isEmpty()) {
                if (OrderValidator.timeCheck(gettingTime)){
                    if (!requestContent.getParameter(PAYMENT_TYPE_PARAM).isEmpty()) {
                        PaymentType paymentType = PaymentType.valueOf(requestContent.getParameter(PAYMENT_TYPE_PARAM).toUpperCase());
                        User user = (User) requestContent.getSessionAttribute(SESSION_USER);
                        order.setIdUser(user.getId());
                        if (OrderValidator.checkPaymentPossibility(paymentType, user, order.getPrice())) {
                            order = orderService.create(user.getId(), gettingTime, paymentType, OrderState.IN_PROCESS, order.getDishes());
                            switch (paymentType) {
                                case FROM_ACCOUNT:
                                    double currentBalance = user.getBalance() - order.getPrice();
                                    user.setBalance(currentBalance);
                                    userService.updateBalance(user);
                                    break;
                                case WHEN_RECEIVING:
                                    break;
                            }
                            requestContent.setSessionAttribute(SESSION_BASKET, new Order());
                        } else {
                            requestContent.setAttribute(ATTR_NOT_ENOUGH, "bad");
                        }
                    } else {
                        requestContent.setAttribute(ATTR_BAD_PAY_TYPE, "bad");
                    }
                } else {
                    requestContent.setAttribute(ATTR_BAD_TIME, "bad");
                }
            } else {
                requestContent.setAttribute(ATTR_EMPTY_BASKET, "bad");
            }
            page = (String) requestContent.getSessionAttribute(SESSION_LAST_PAGE);

        } catch (NoSuchRequestParameterException | ServiceException e) {
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
