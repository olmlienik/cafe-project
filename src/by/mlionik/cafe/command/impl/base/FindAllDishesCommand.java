package by.mlionik.cafe.command.impl.base;

import by.mlionik.cafe.command.ActionCommand;
import by.mlionik.cafe.controller.Router;
import by.mlionik.cafe.controller.SessionRequestContent;
import by.mlionik.cafe.entity.Dish;
import by.mlionik.cafe.entity.RoleType;
import by.mlionik.cafe.exception.NoSuchRequestParameterException;
import by.mlionik.cafe.manager.ConfigurationManager;
import by.mlionik.cafe.service.impl.DishServiceImpl;
import by.mlionik.cafe.service.ServiceException;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

/**
 * The type Find all dishes command.
 */
public class FindAllDishesCommand implements ActionCommand {
    private static Logger logger = LogManager.getLogger();
    private static final String DISH_ATTR = "dish";
    private static final String SNACKS_ATTR = "snacks";
    private static final String MAIN_DISHES_ATTR = "mainDishes";
    private static final String DESERTS_ATTR = "deserts";
    private static final String DRINKS_ATTR = "drinks";
    private static final String ROLE_ATTR = "role";
    private static final String MENU_PAGE_PATH = "path.page.menu";
    private static final String ADMIN_MENU_PAGE_PATH = "path.page.admin.menu";
    private static final String ERROR_PAGE_PATH = "path.page.error";
    private static final String ERROR_ATTR = "errorMsg";
    private static DishServiceImpl dishService = new DishServiceImpl();

    @Override
    public Router execute(SessionRequestContent requestContent) {
        String page;
        try {
            List<Dish> snacks = dishService.findSnacks();
            List<Dish> mainDishes = dishService.findMainDishes();
            List<Dish> deserts = dishService.findDeserts();
            List<Dish> drinks = dishService.findDrinks();
            requestContent.setAttribute(SNACKS_ATTR, snacks);
            requestContent.setAttribute(MAIN_DISHES_ATTR, mainDishes);
            requestContent.setAttribute(DESERTS_ATTR, deserts);
            requestContent.setAttribute(DRINKS_ATTR, drinks);
//            List<Dish> dishList = dishService.findAll();
//            requestContent.setAttribute(DISH_ATTR, dishList);
            RoleType role = RoleType.valueOf(requestContent.getSessionAttribute(ROLE_ATTR).toString().replaceAll("\\s", "_"));
            if (role == RoleType.ADMIN) {
                page = ConfigurationManager.getProperty(ADMIN_MENU_PAGE_PATH);
            } else {
                page = ConfigurationManager.getProperty(MENU_PAGE_PATH);
            }
        } catch (ServiceException | NoSuchRequestParameterException e) {
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
