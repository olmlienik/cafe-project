package by.mlionik.cafe.command.impl.user;

import by.mlionik.cafe.command.ActionCommand;
import by.mlionik.cafe.controller.SessionRequestContent;
import by.mlionik.cafe.entity.Dish;
import by.mlionik.cafe.entity.Order;
import by.mlionik.cafe.exception.NoSuchRequestParameterException;
import by.mlionik.cafe.manager.ConfigurationManager;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class RemoveFromBasketCommand implements ActionCommand {
    private static Logger logger = LogManager.getLogger();
    private static final String DISH_INDEX_PARAM = "dishIndex";
    private static final String SESSION_LAST_PAGE = "lastPage";
    private static final String SESSION_BASKET = "basket";
    private static final String ERROR_PAGE_PATH = "path.page.error";
    private static final String ERROR_ATTR = "errorMsg";

    @Override
    public String execute(SessionRequestContent requestContent) {
        String page;
        try {
            Order basket = (Order) requestContent.getSessionAttribute(SESSION_BASKET);
            int dishIndex = Integer.valueOf(requestContent.getParameter(DISH_INDEX_PARAM));
            Dish removingDish = basket.getDishes().remove(dishIndex);
            double finalCost = basket.getPrice() - removingDish.getCost();
            basket.setPrice(finalCost);
            page = (String) requestContent.getSessionAttribute(SESSION_LAST_PAGE);
        } catch (NoSuchRequestParameterException e) {
            logger.log(Level.ERROR, e);
            requestContent.setAttribute(ERROR_ATTR, e.getMessage());
            page = ConfigurationManager.getProperty(ERROR_PAGE_PATH);
        }
        return page;
    }
}
