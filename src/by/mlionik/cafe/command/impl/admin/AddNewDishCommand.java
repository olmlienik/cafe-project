package by.mlionik.cafe.command.impl.admin;

import by.mlionik.cafe.command.ActionCommand;
import by.mlionik.cafe.controller.Router;
import by.mlionik.cafe.controller.SessionRequestContent;
import by.mlionik.cafe.entity.Dish;
import by.mlionik.cafe.entity.DishType;
import by.mlionik.cafe.exception.NoSuchRequestParameterException;
import by.mlionik.cafe.manager.ConfigurationManager;
import by.mlionik.cafe.service.ServiceException;
import by.mlionik.cafe.service.impl.DishServiceImpl;
import by.mlionik.cafe.util.DishValidator;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * The type Add new dish command.
 */
public class AddNewDishCommand implements ActionCommand {
    private static final Logger logger = LogManager.getLogger();
    private static final String DISH_ATTR = "newDish";
    private static final String NAME_PARAM = "name";
    private static final String COST_PARAM = "cost";
    private static final String CATEGORY_PARAM = "category";
    private static final String PICTURE_NAME_PARAM = "picture_name";
    private static final String ERROR_PAGE_PATH = "path.page.error";
    private static final String ERROR_ATTR = "errorMsg";
    private static final String WRONG_NAME_ATTR = "wrongName";
    private static final String WRONG_COST_ATTR = "wrongCost";
    private static final String WRONG_PICTURE_NAME_ATTR = "wrongPictureName";
    private static final String SESSION_LAST_PAGE = "lastPage";
    private static final String IMAGES_PATH = "/images/";
    private static DishServiceImpl dishService = new DishServiceImpl();

    @Override
    public Router execute(SessionRequestContent requestContent) {
        String page;
        Router router = new Router();
        router.setRouteType(Router.RouteType.FORWARD);
        requestContent.setSessionAttribute(WRONG_NAME_ATTR, null);
        requestContent.setSessionAttribute(WRONG_COST_ATTR, null);
        requestContent.setSessionAttribute(WRONG_PICTURE_NAME_ATTR, null);
        try {
            String dishName = requestContent.getParameter(NAME_PARAM);
            String cost = requestContent.getParameter(COST_PARAM);
            String category = requestContent.getParameter(CATEGORY_PARAM);
            String pictureName = requestContent.getParameter(PICTURE_NAME_PARAM);
            if (DishValidator.isValidDishName(dishName)) {
                if (DishValidator.isValidCost(cost)) {
                    if (DishValidator.isExistingPicture(pictureName)) {
                        Dish newDish = dishService.create(new Dish(dishName, Double.parseDouble(cost), IMAGES_PATH + pictureName, DishType.valueOf(category.toUpperCase())));
                        requestContent.setAttribute(DISH_ATTR, newDish);
                        router.setRouteType(Router.RouteType.REDIRECT);
                    } else {
                        requestContent.setAttribute(WRONG_PICTURE_NAME_ATTR, "bad");
                    }
                } else {
                    requestContent.setAttribute(WRONG_COST_ATTR, "bad");
                }
            } else {
                requestContent.setAttribute(WRONG_NAME_ATTR, "bad");
            }
            page = (String) requestContent.getSessionAttribute(SESSION_LAST_PAGE);
        } catch (NoSuchRequestParameterException | ServiceException e) {
            logger.log(Level.ERROR, e);
            requestContent.setAttribute(ERROR_ATTR, e.getMessage());
            page = ConfigurationManager.getProperty(ERROR_PAGE_PATH);
        }
        router.setPagePath(page);
        return router;
    }
}
