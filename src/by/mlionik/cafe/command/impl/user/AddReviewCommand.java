package by.mlionik.cafe.command.impl.user;

import by.mlionik.cafe.command.ActionCommand;
import by.mlionik.cafe.controller.Router;
import by.mlionik.cafe.controller.SessionRequestContent;
import by.mlionik.cafe.entity.Review;
import by.mlionik.cafe.exception.NoSuchRequestParameterException;
import by.mlionik.cafe.manager.ConfigurationManager;
import by.mlionik.cafe.service.ServiceException;
import by.mlionik.cafe.service.impl.ReviewServiceImpl;
import by.mlionik.cafe.util.ReviewValidator;
import by.mlionik.cafe.util.XssPrevention;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * The type Add review command.
 */
public class AddReviewCommand implements ActionCommand {
    private static Logger logger = LogManager.getLogger();
    private static final String USER_ID_PARAM = "userId";
    private static final String BODY_PARAM = "body";
    private static final String SESSION_LAST_PAGE = "lastPage";
    private static final String INVALID_BODY_ATTR = "invalidBody";
    private static final String ERROR_PAGE_PATH = "path.page.error";
    private static final String ERROR_ATTR = "errorMsg";
    private static ReviewServiceImpl reviewService = new ReviewServiceImpl();

    @Override
    public Router execute(SessionRequestContent requestContent) {
        String page;
        try {
            int idUser = Integer.parseInt(requestContent.getParameter(USER_ID_PARAM));
            String body = requestContent.getParameter(BODY_PARAM);
            body = XssPrevention.resetScripts(body);
            if (ReviewValidator.checkReviewBody(body)) {
                String currentDate = new SimpleDateFormat("yyyy-MM-dd HH:mm").format(new Date());
                Review review = new Review(idUser, body, currentDate);
                reviewService.create(review);
            } else {
                requestContent.setAttribute(INVALID_BODY_ATTR, "bad");
            }
            page = (String) requestContent.getSessionAttribute(SESSION_LAST_PAGE);
        } catch (NoSuchRequestParameterException | ServiceException e) {
            logger.log(Level.ERROR, e);
            requestContent.setAttribute(ERROR_ATTR, e.getMessage());
            page = ConfigurationManager.getProperty(ERROR_PAGE_PATH);
        }
        Router router = new Router();
        router.setRouteType(Router.RouteType.REDIRECT);
        router.setPagePath(page);
        return router;
    }
}
