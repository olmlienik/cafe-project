package by.mlionik.cafe.command.impl.user;

import by.mlionik.cafe.command.ActionCommand;
import by.mlionik.cafe.controller.SessionRequestContent;
import by.mlionik.cafe.entity.User;
import by.mlionik.cafe.exception.NoSuchRequestParameterException;
import by.mlionik.cafe.manager.ConfigurationManager;
import by.mlionik.cafe.service.ServiceException;
import by.mlionik.cafe.service.impl.UserService;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ChangeUsernameCommand implements ActionCommand {

    private static final String USERNAME_PARAM = "username";
    private static Logger logger = LogManager.getLogger();
    private static final String SESSION_LAST_PAGE = "lastPage";
    private static final String SESSION_USER = "user";
    private static final String ERROR_PAGE_PATH = "path.page.error";
    private static final String ERROR_ATTR = "errorMsg";
    private static final String LOGIN_USED_ATTR = "loginUsed";

    private UserService userService = new UserService();

    @Override
    public String execute(SessionRequestContent requestContent) {
        String page;
        try {
            User user = (User) requestContent.getSessionAttribute(SESSION_USER);
            String newUsername = requestContent.getParameter(USERNAME_PARAM);
            if (userService.findByLogin(requestContent.getParameter(USERNAME_PARAM)) == null) {
                userService.updateLogin(user.getId(), newUsername);
                user.setLogin(newUsername);
            } else {
                requestContent.setAttribute(LOGIN_USED_ATTR, "wrong");
            }
            page = (String) requestContent.getSessionAttribute(SESSION_LAST_PAGE);
        } catch (NoSuchRequestParameterException | ServiceException e) {
            logger.log(Level.ERROR, e);
            requestContent.setAttribute(ERROR_ATTR, e.getMessage());
            page = ConfigurationManager.getProperty(ERROR_PAGE_PATH);
        }
        return page;
    }
}