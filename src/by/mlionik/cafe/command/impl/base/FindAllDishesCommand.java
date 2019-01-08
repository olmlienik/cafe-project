package by.mlionik.cafe.command.impl.base;

import by.mlionik.cafe.command.ActionCommand;
import by.mlionik.cafe.controller.Router;
import by.mlionik.cafe.controller.SessionRequestContent;
import by.mlionik.cafe.entity.Dish;
import by.mlionik.cafe.manager.ConfigurationManager;
import by.mlionik.cafe.service.impl.DishService;
import by.mlionik.cafe.service.ServiceException;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

public class FindAllDishesCommand implements ActionCommand {
    private static Logger logger = LogManager.getLogger();
    private static final String DISH_ATTR = "dish";
    private static final String MENU_PAGE_PATH = "path.page.menu";
    private static final String ERROR_PAGE_PATH = "path.page.error";
    private static final String ERROR_ATTR = "errorMsg";

    private DishService dishService = new DishService();

    @Override
    public Router execute(SessionRequestContent requestContent) {
        String page;
        try {
            List<Dish> dishList = dishService.findAll();
            requestContent.setAttribute(DISH_ATTR, dishList);
            page = ConfigurationManager.getProperty(MENU_PAGE_PATH);
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
