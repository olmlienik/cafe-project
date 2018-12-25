package by.mlionik.cafe.command.impl;

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

public class LoginCommand implements ActionCommand {
    private static final Logger logger = LogManager.getLogger();
    private static final String LOGIN_PARAM = "login";
    private static final String PASSWORD_PARAM = "password";
    private static final String USER_ATTR = "user";
    private static final String INDEX_PAGE_PATH = "path.page.index";
    private static final String LOGIN_ERROR_ATTR = "errorLoginPassMsg";
    private static final String LOGIN_ERROR_MSG = "message.loginerror";
    private static final String ROLE_ATTR = "role";
    private static final String LOGIN_PAGE_PATH = "path.page.login";
    private static final String ERROR_PAGE_PATH = "path.page.error";
    private static final String ERROR_ATTR = "errorMsg";
    private static final String WRONG_LOGIN_OR_PASSWORD = "wrongLoginOrPassword";

    
    private UserService userService = new UserService();

    @Override
    public String execute(SessionRequestContent requestContent) {
        String page;
        requestContent.setSessionAttribute(WRONG_LOGIN_OR_PASSWORD, null);
        try {
            String login = requestContent.getParameter(LOGIN_PARAM);
            String password = requestContent.getParameter(PASSWORD_PARAM);
            User user = userService.findByLoginAndPassword(login, password);
            if (user != null) {
                requestContent.setSessionAttribute(ROLE_ATTR, user.getRole());
                requestContent.setSessionAttribute(USER_ATTR, user);
                page = ConfigurationManager.getProperty(INDEX_PAGE_PATH);
                //todo return to previous page
            } else {
                requestContent.setSessionAttribute(WRONG_LOGIN_OR_PASSWORD, "wrongLoginOrPassword");
                page = ConfigurationManager.getProperty(LOGIN_PAGE_PATH);
            }
        } catch (ServiceException | NoSuchRequestParameterException e) {
            logger.log(Level.ERROR, e);
            requestContent.setAttribute(ERROR_ATTR, e);
            page = ConfigurationManager.getProperty(ERROR_PAGE_PATH);
        }
        return page;
    }
}

