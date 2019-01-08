package by.mlionik.cafe.command.impl.base;

import by.mlionik.cafe.command.ActionCommand;
import by.mlionik.cafe.controller.Router;
import by.mlionik.cafe.controller.SessionRequestContent;
import by.mlionik.cafe.exception.NoSuchRequestParameterException;
import by.mlionik.cafe.manager.ConfigurationManager;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ChangeLanguageCommand implements ActionCommand {
    private static final Logger logger = LogManager.getLogger();
    private static final String LANGUAGE_PARAM = "language";
    private static final String LOCALE_ATTR = "locale";
    private static final String SESSION_LAST_PAGE = "lastPage";
    private static final String ERROR_PAGE_PATH = "path.page.error";

    @Override
    public Router execute(SessionRequestContent requestContent) {
        String page;
        try {
            String locale = requestContent.getParameter(LANGUAGE_PARAM);
            requestContent.setSessionAttribute(LOCALE_ATTR, locale);
            page = (String) requestContent.getSessionAttribute(SESSION_LAST_PAGE);
        } catch (NoSuchRequestParameterException e) {
            logger.log(Level.ERROR, e);
            page =  ConfigurationManager.getProperty(ERROR_PAGE_PATH);
        }
        Router router = new Router();
        router.setRouteType(Router.RouteType.REDIRECT);
        router.setPagePath(page);
        return router;
    }
}
