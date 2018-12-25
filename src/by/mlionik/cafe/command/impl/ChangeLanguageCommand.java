package by.mlionik.cafe.command.impl;

import by.mlionik.cafe.command.ActionCommand;
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
    private static final String INDEX_PAGE_PATH = "path.page.index";

    @Override
    public String execute(SessionRequestContent requestContent) {
        String page = null;
        try {

            String locale = requestContent.getParameter(LANGUAGE_PARAM);
            requestContent.setSessionAttribute(LOCALE_ATTR, locale);
            page = ConfigurationManager.getProperty(INDEX_PAGE_PATH);
        } catch (NoSuchRequestParameterException e) {
            logger.log(Level.ERROR, e);
        }
        return page;
    }
}
