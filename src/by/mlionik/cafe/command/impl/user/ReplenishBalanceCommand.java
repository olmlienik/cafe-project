package by.mlionik.cafe.command.impl.user;

import by.mlionik.cafe.command.ActionCommand;
import by.mlionik.cafe.controller.Router;
import by.mlionik.cafe.controller.SessionRequestContent;
import by.mlionik.cafe.entity.User;
import by.mlionik.cafe.exception.NoSuchRequestParameterException;
import by.mlionik.cafe.manager.ConfigurationManager;
import by.mlionik.cafe.service.ServiceException;
import by.mlionik.cafe.service.impl.UserServiceImpl;
import by.mlionik.cafe.util.UserValidator;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * The type Replenish balance command.
 */
public class ReplenishBalanceCommand implements ActionCommand {
    private static Logger logger = LogManager.getLogger();
    private static final String REPLENISHMENT_PARAM = "replenishment";
    private static final String SESSION_LAST_PAGE = "lastPage";
    private static final String SESSION_USER = "user";
    private static final String WRONG_BALANCE_ATTR = "wrongBalance";
    private static final String ERROR_PAGE_PATH = "path.page.error";
    private static final String ERROR_ATTR = "errorMsg";
    private static UserServiceImpl userService = new UserServiceImpl();

    @Override
    public Router execute(SessionRequestContent requestContent) {
        String page;
        Router router = new Router();
        try {
            User user = (User) requestContent.getSessionAttribute(SESSION_USER);
            String sumParam = requestContent.getParameter(REPLENISHMENT_PARAM);
            if (UserValidator.isValidBalance(sumParam)) {
                double sumToAdd = Double.parseDouble(requestContent.getParameter(REPLENISHMENT_PARAM));
                double newBalance = user.getBalance() + sumToAdd;
                user.setBalance(newBalance);
                userService.updateBalance(user);
                router.setRouteType(Router.RouteType.REDIRECT);
            } else {
                router.setRouteType(Router.RouteType.FORWARD);
                requestContent.setAttribute(WRONG_BALANCE_ATTR, "bad");
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
