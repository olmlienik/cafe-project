package by.mlionik.cafe.command.impl.user;

import by.mlionik.cafe.command.ActionCommand;
import by.mlionik.cafe.controller.Router;
import by.mlionik.cafe.controller.SessionRequestContent;
import by.mlionik.cafe.entity.Dish;
import by.mlionik.cafe.entity.Order;
import by.mlionik.cafe.exception.NoSuchRequestParameterException;
import by.mlionik.cafe.manager.ConfigurationManager;
import by.mlionik.cafe.service.ServiceException;
import by.mlionik.cafe.service.impl.DishServiceImpl;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * The type Add to basket command.
 */
public class AddToBasketCommand implements ActionCommand {
    private static Logger logger = LogManager.getLogger();
    private static final String SESSION_BASKET = "basket";
    private static final String CURRENT_DISH_PARAM = "currentDishId";
    private static final String SESSION_LAST_PAGE = "lastPage";
    private static final String ERROR_PAGE_PATH = "path.page.error";
    private static final String ERROR_ATTR = "errorMsg";
    private static DishServiceImpl dishService = new DishServiceImpl();

    @Override
    public Router execute(SessionRequestContent requestContent) {
        String page;
        try {
            Order basket = (Order) requestContent.getSessionAttribute(SESSION_BASKET);
            int currentDishId = Integer.valueOf(requestContent.getParameter(CURRENT_DISH_PARAM));
            Dish dish = dishService.findById(currentDishId);
            double finalCost = basket.getPrice() + dish.getCost();
            basket.setPrice(finalCost);
            basket.addToBasket(dish);
            page = (String) requestContent.getSessionAttribute(SESSION_LAST_PAGE);
        } catch (NoSuchRequestParameterException | ServiceException e) {
            logger.log(Level.ERROR, e);
            requestContent.setAttribute(ERROR_ATTR, e.getMessage());
            page = ConfigurationManager.getProperty(ERROR_PAGE_PATH);
        }
        Router router = new Router();
        router.setRouteType(Router.RouteType.REDIRECT);
        router.setPagePath(page);
        return router;
    }
}
