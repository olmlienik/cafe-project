package by.mlionik.cafe.command.impl.admin;

import by.mlionik.cafe.command.ActionCommand;
import by.mlionik.cafe.controller.Router;
import by.mlionik.cafe.controller.SessionRequestContent;
import by.mlionik.cafe.exception.NoSuchRequestParameterException;
import by.mlionik.cafe.manager.ConfigurationManager;
import by.mlionik.cafe.service.ServiceException;
import by.mlionik.cafe.service.impl.ReviewServiceImpl;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * The type Delete review command.
 */
public class DeleteReviewCommand implements ActionCommand {
    private static Logger logger = LogManager.getLogger();
    private static final String CURRENT_REVIEW_PARAM = "currentReviewId";
    private static final String SESSION_LAST_PAGE = "lastPage";
    private static final String ERROR_PAGE_PATH = "path.page.error";
    private static final String ERROR_ATTR = "errorMsg";
    private static ReviewServiceImpl reviewService = new ReviewServiceImpl();

    @Override
    public Router execute(SessionRequestContent requestContent) {
        String page;
        try {
            int currentReviewId = Integer.valueOf(requestContent.getParameter(CURRENT_REVIEW_PARAM));
            reviewService.deleteById(currentReviewId);
            page = (String) requestContent.getSessionAttribute(SESSION_LAST_PAGE);
        } catch (ServiceException | NoSuchRequestParameterException e) {
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
