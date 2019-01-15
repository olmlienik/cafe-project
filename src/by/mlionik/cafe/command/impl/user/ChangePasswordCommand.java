package by.mlionik.cafe.command.impl.user;

import by.mlionik.cafe.command.ActionCommand;
import by.mlionik.cafe.controller.Router;
import by.mlionik.cafe.controller.SessionRequestContent;
import by.mlionik.cafe.entity.User;
import by.mlionik.cafe.exception.NoSuchRequestParameterException;
import by.mlionik.cafe.manager.ConfigurationManager;
import by.mlionik.cafe.service.ServiceException;
import by.mlionik.cafe.service.impl.UserServiceImpl;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * The type Change password command.
 */
public class ChangePasswordCommand implements ActionCommand {
    private static Logger logger = LogManager.getLogger();
    private static final String NOT_EQUALS_PASS_PARAM = "notEqualsPass";
    private static final String OLD_PASSWORD_PARAM = "old_pass";
    private static final String NEW_PASSWORD_PARAM = "new_pass";
    private static final String SESSION_LAST_PAGE = "lastPage";
    private static final String SESSION_USER = "user";
    private static final String ERROR_PAGE_PATH = "path.page.error";
    private static final String ERROR_ATTR = "errorMsg";
    private static UserServiceImpl userService = new UserServiceImpl();

    @Override
    public Router execute(SessionRequestContent requestContent) {
        String page;
        Router router = new Router();
        try {
            User user = (User) requestContent.getSessionAttribute(SESSION_USER);
            String oldPassword = requestContent.getParameter(OLD_PASSWORD_PARAM);
            String newPassword = requestContent.getParameter(NEW_PASSWORD_PARAM);
            if (DigestUtils.sha256Hex(oldPassword).equals(user.getPassword())) {
                userService.updatePassword(user.getId(), newPassword);
                user = userService.findById(user.getId());
                requestContent.setSessionAttribute(SESSION_USER, user);
                router.setRouteType(Router.RouteType.REDIRECT);
            } else {
                router.setRouteType(Router.RouteType.FORWARD);
                requestContent.setAttribute(NOT_EQUALS_PASS_PARAM, "bad");
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
