package by.mlionik.cafe.command.impl.user;

import by.mlionik.cafe.command.ActionCommand;
import by.mlionik.cafe.controller.SessionRequestContent;
import by.mlionik.cafe.entity.Order;
import by.mlionik.cafe.entity.User;
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
    private static final String PARAM_BAD_TIME = "badTime";
    private static final String PARAM_BAD_PAY_TYPE = "badPayType";
    private static final String PARAM_NOT_ENOUGH = "notEnough";
    private static final String PARAM_EMPTY_BASKET = "emptyBasket";
    private static final String SESSION_USER = "user";

    private OrderService orderService = new OrderService();
    private UserService userService = new UserService();

    @Override
    public String execute(SessionRequestContent requestContent) {
        String page;
        requestContent.setAttribute(PARAM_BAD_PAY_TYPE, null);
        requestContent.setAttribute(PARAM_BAD_TIME, null);
        requestContent.setAttribute(PARAM_NOT_ENOUGH, null);

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
                            order.setDeliveryTime(gettingTime);
                            order.setPaymentType(paymentType);
                            orderService.create(order);
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
                            requestContent.setAttribute(PARAM_NOT_ENOUGH, "bad");
                        }
                    } else {
                        requestContent.setAttribute(PARAM_BAD_PAY_TYPE, "bad");
                    }
                } else {
                    requestContent.setAttribute(PARAM_BAD_TIME, "bad");
                }
            } else {
                requestContent.setAttribute(PARAM_EMPTY_BASKET, "bad");
            }
            page = (String) requestContent.getSessionAttribute(SESSION_LAST_PAGE);

        } catch (NoSuchRequestParameterException | ServiceException e) {
            logger.log(Level.ERROR, e);
            requestContent.setAttribute(ERROR_ATTR, e.getMessage());
            page = ConfigurationManager.getProperty(ERROR_PAGE_PATH);
        }
        return page;
    }
}
