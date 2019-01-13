package by.mlionik.cafe.command.impl.base;

import by.mlionik.cafe.command.ActionCommand;
import by.mlionik.cafe.controller.Router;
import by.mlionik.cafe.controller.SessionRequestContent;
import by.mlionik.cafe.entity.User;
import by.mlionik.cafe.exception.NoSuchRequestParameterException;
import by.mlionik.cafe.service.impl.UserServiceImpl;
import by.mlionik.cafe.service.ServiceException;
import by.mlionik.cafe.manager.ConfigurationManager;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * The type Sign up command.
 */
public class SignUpCommand implements ActionCommand {
    private static final Logger logger = LogManager.getLogger();

    private static final String USER_ATTR = "user";
    private static final String INDEX_PAGE_PATH = "path.page.index";
    private static final String REGISTRATION_PAGE_PATH = "path.page.registration";
    private static final String LOGIN_PARAM = "login";
    private static final String PASSWORD_PARAM = "password";
    private static final String CONFIRM_PASSWORD_PARAM = "confirm-password";
    private static final String EMAIL_PARAM = "email";
    private static final String ERROR_PAGE_PATH = "path.page.error";
    private static final String ERROR_ATTR = "errorMsg";
    private static final String LOGIN_USED_ATTR = "loginUsed";
    private static final String NOT_EQUALS_PASSWORD_ATTR = "notEqualsPassword";

    private static UserServiceImpl userService = new UserServiceImpl();

    @Override
    public Router execute(SessionRequestContent requestContent) {
        String page;
        Router router = new Router();
        router.setRouteType(Router.RouteType.FORWARD);
        requestContent.setSessionAttribute(LOGIN_USED_ATTR, null);
        requestContent.setSessionAttribute(NOT_EQUALS_PASSWORD_ATTR, null);
        try {
            if (userService.findByLogin(requestContent.getParameter(LOGIN_PARAM)) == null) {
                if (requestContent.getParameter(PASSWORD_PARAM).equals(requestContent.getParameter(CONFIRM_PASSWORD_PARAM))) {
                    User user = userService.create(convertToUser(requestContent));
                    requestContent.setSessionAttribute(USER_ATTR, user);
                    page = ConfigurationManager.getProperty(INDEX_PAGE_PATH);
                    router.setRouteType(Router.RouteType.REDIRECT);
                } else {
                    requestContent.setAttribute(NOT_EQUALS_PASSWORD_ATTR, "wrong");
                    page = ConfigurationManager.getProperty(REGISTRATION_PAGE_PATH);
                }
            } else {
                requestContent.setAttribute(LOGIN_USED_ATTR, "wrong");
                page = ConfigurationManager.getProperty(REGISTRATION_PAGE_PATH);
            }
        } catch (ServiceException | NoSuchRequestParameterException e) {
            logger.log(Level.ERROR, e);
            requestContent.setAttribute(ERROR_ATTR, e.getMessage());
            page = ConfigurationManager.getProperty(ERROR_PAGE_PATH);
        }
        router.setPagePath(page);
        return router;
    }

    private User convertToUser(SessionRequestContent requestContent) throws NoSuchRequestParameterException {
        User user = new User();
        user.setLogin(requestContent.getParameter(LOGIN_PARAM));
        user.setPassword(requestContent.getParameter(PASSWORD_PARAM));
        user.setEmail(requestContent.getParameter(EMAIL_PARAM));
        return user;
    }
}
