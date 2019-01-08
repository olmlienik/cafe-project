package by.mlionik.cafe.command.impl.base;

import by.mlionik.cafe.command.ActionCommand;
import by.mlionik.cafe.controller.Router;
import by.mlionik.cafe.controller.SessionRequestContent;

import by.mlionik.cafe.entity.Order;
import by.mlionik.cafe.entity.User;
import by.mlionik.cafe.exception.NoSuchRequestParameterException;
import by.mlionik.cafe.manager.ConfigurationManager;
import by.mlionik.cafe.service.ServiceException;
import by.mlionik.cafe.service.impl.UserService;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class LoginCommand implements ActionCommand {
    private static final Logger logger = LogManager.getLogger();
    private static final String LOGIN_PARAM = "login";
    private static final String PASSWORD_PARAM = "password";
    private static final String SESSION_USER = "user";
    private static final String INDEX_PAGE_PATH = "path.page.index";
    private static final String ROLE_ATTR = "role";
    private static final String LOGIN_PAGE_PATH = "path.page.login";
    private static final String ERROR_PAGE_PATH = "path.page.error";
    private static final String ERROR_ATTR = "errorMsg";
    private static final String WRONG_LOGIN_OR_PASSWORD = "wrongLoginOrPassword";
    private static final String ATTR_BANNED = "isBanned";
    private static final String SESSION_BASKET = "basket";


    private UserService userService = new UserService();

    @Override
    public Router execute(SessionRequestContent requestContent) {
        String page;
        Router router = new Router();
        router.setRouteType(Router.RouteType.FORWARD);
        requestContent.setSessionAttribute(WRONG_LOGIN_OR_PASSWORD, null);
        try {
            String login = requestContent.getParameter(LOGIN_PARAM);
            String password = requestContent.getParameter(PASSWORD_PARAM);
            User user = userService.findByLoginAndPassword(login, password);
            if (user != null) {
                if (!user.isBanned()) {
                    requestContent.setSessionAttribute(ROLE_ATTR, user.getRole());
                    requestContent.setSessionAttribute(SESSION_USER, user);
                    Order basket = new Order();
                    basket.setIdUser(user.getId());
                    requestContent.setSessionAttribute(SESSION_BASKET, basket);
                    page = ConfigurationManager.getProperty(INDEX_PAGE_PATH);
                } else {
                    requestContent.setAttribute(ATTR_BANNED, "wrong");
                    page = ConfigurationManager.getProperty(LOGIN_PAGE_PATH);
                }
            } else {
                requestContent.setAttribute(WRONG_LOGIN_OR_PASSWORD, "wrong");
                page = ConfigurationManager.getProperty(LOGIN_PAGE_PATH);
            }
        } catch (ServiceException | NoSuchRequestParameterException e) {
            logger.log(Level.ERROR, e);
            requestContent.setAttribute(ERROR_ATTR, e);
            page = ConfigurationManager.getProperty(ERROR_PAGE_PATH);
        }
        router.setPagePath(page);
        return router;
    }
}

