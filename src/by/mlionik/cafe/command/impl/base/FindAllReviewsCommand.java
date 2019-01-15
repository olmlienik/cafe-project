package by.mlionik.cafe.command.impl.base;

import by.mlionik.cafe.command.ActionCommand;
import by.mlionik.cafe.controller.Router;
import by.mlionik.cafe.controller.SessionRequestContent;
import by.mlionik.cafe.entity.Review;
import by.mlionik.cafe.manager.ConfigurationManager;
import by.mlionik.cafe.service.ServiceException;
import by.mlionik.cafe.service.impl.ReviewServiceImpl;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Comparator;
import java.util.List;

/**
 * The type Find all reviews command.
 */
public class FindAllReviewsCommand implements ActionCommand {
    private static Logger logger = LogManager.getLogger();
    private static final String REVIEWS_ATTR = "reviews";
    private static final String REVIEWS_PAGE_PATH = "path.page.reviews";
    private static final String ERROR_PAGE_PATH = "path.page.error";
    private static final String ERROR_ATTR = "errorMsg";
    private static ReviewServiceImpl reviewService = new ReviewServiceImpl();

    @Override
    public Router execute(SessionRequestContent requestContent) {
        String page;
        try {
            List<Review> reviewList = reviewService.findAll();
            reviewList.sort(Comparator.comparingInt(Review::getId).reversed());
            requestContent.setAttribute(REVIEWS_ATTR, reviewList);
            page = ConfigurationManager.getProperty(REVIEWS_PAGE_PATH);
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
