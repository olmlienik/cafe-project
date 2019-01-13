package by.mlionik.cafe.command.impl.admin;

import by.mlionik.cafe.command.ActionCommand;
import by.mlionik.cafe.controller.Router;
import by.mlionik.cafe.controller.SessionRequestContent;
import by.mlionik.cafe.entity.User;
import by.mlionik.cafe.manager.ConfigurationManager;
import by.mlionik.cafe.service.ServiceException;
import by.mlionik.cafe.service.impl.UserServiceImpl;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

/**
 * The type Find all users command.
 */
public class FindAllUsersCommand implements ActionCommand {
    private static Logger logger = LogManager.getLogger();
    private static final String ATTR_ACTIVE_USERS = "activeUsers";
    private static final String ATTR_BANNED_USERS = "bannedUsers";
    private static final String USERS_PAGE_PATH = "path.page.users";
    private static final String ERROR_PAGE_PATH = "path.page.error";
    private static final String ERROR_ATTR = "errorMsg";
    private static UserServiceImpl userService = new UserServiceImpl();

    @Override
    public Router execute(SessionRequestContent requestContent) {
        String page;
        try {
            List<User> activeUsers = userService.findActiveUsers();
            List<User> bannedUsers = userService.findBannedUsers();
            requestContent.setAttribute(ATTR_ACTIVE_USERS, activeUsers);
            requestContent.setAttribute(ATTR_BANNED_USERS, bannedUsers);
            page = ConfigurationManager.getProperty(USERS_PAGE_PATH);
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
