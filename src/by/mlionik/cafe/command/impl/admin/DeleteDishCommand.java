package by.mlionik.cafe.command.impl.admin;

import by.mlionik.cafe.command.ActionCommand;
import by.mlionik.cafe.controller.Router;
import by.mlionik.cafe.controller.SessionRequestContent;
import by.mlionik.cafe.exception.NoSuchRequestParameterException;
import by.mlionik.cafe.manager.ConfigurationManager;
import by.mlionik.cafe.service.ServiceException;
import by.mlionik.cafe.service.impl.DishServiceImpl;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * The type Delete dish command.
 */
public class DeleteDishCommand implements ActionCommand {
    private static Logger logger = LogManager.getLogger();
    private static final String CURRENT_DISH_PARAM = "currentDishId";
    private static final String SESSION_LAST_PAGE = "lastPage";
    private static final String ERROR_PAGE_PATH = "path.page.error";
    private static final String ERROR_ATTR = "errorMsg";
    private static DishServiceImpl dishService = new DishServiceImpl();

    @Override
    public Router execute(SessionRequestContent requestContent) {
        String page;
        try {
            int currentDishId = Integer.valueOf(requestContent.getParameter(CURRENT_DISH_PARAM));
            dishService.deleteById(currentDishId);
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
