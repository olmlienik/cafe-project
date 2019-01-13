package by.mlionik.cafe.command.impl.base;

import by.mlionik.cafe.command.ActionCommand;
import by.mlionik.cafe.controller.Router;
import by.mlionik.cafe.controller.SessionRequestContent;
import by.mlionik.cafe.manager.ConfigurationManager;

/**
 * The type Empty command.
 */
public class EmptyCommand implements ActionCommand {
    private static final String ERROR_PAGE_PATH = "path.page.error";

    @Override
    public Router execute(SessionRequestContent requestContent) {
        Router router = new Router();
        router.setRouteType(Router.RouteType.FORWARD);
        String page = ConfigurationManager.getProperty(ERROR_PAGE_PATH);
        router.setPagePath(page);
        return router;
    }
}
