package by.mlionik.cafe.command.impl.base;

import by.mlionik.cafe.command.ActionCommand;
import by.mlionik.cafe.controller.Router;
import by.mlionik.cafe.controller.SessionRequestContent;
import by.mlionik.cafe.entity.RoleType;
import by.mlionik.cafe.manager.ConfigurationManager;

/**
 * The type Logout command.
 */
public class LogoutCommand implements ActionCommand {
    private static final String USER_ATTR = "user";
    private static final String ROLE_ATTR = "role";
    private static final String INDEX_PAGE_PATH = "path.page.index";

    @Override
    public Router execute(SessionRequestContent requestContent) {
        requestContent.setSessionAttribute(USER_ATTR, null);
        requestContent.setSessionAttribute(ROLE_ATTR, RoleType.GUEST);
        Router router = new Router();
        router.setRouteType(Router.RouteType.FORWARD);
        router.setPagePath(ConfigurationManager.getProperty(INDEX_PAGE_PATH));
        return router;
    }
}
