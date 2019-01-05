package by.mlionik.cafe.command.impl;

import by.mlionik.cafe.command.ActionCommand;
import by.mlionik.cafe.controller.SessionRequestContent;
import by.mlionik.cafe.entity.type.RoleType;
import by.mlionik.cafe.manager.ConfigurationManager;

public class LogoutCommand implements ActionCommand {
    private static final String USER_ATTR = "user";
    private static final String ROLE_ATTR = "role";
    private static final String INDEX_PAGE_PATH = "path.page.index";

    @Override
    public String execute(SessionRequestContent requestContent) {
        requestContent.setSessionAttribute(USER_ATTR, null);
        requestContent.setSessionAttribute(ROLE_ATTR, RoleType.GUEST);
        return ConfigurationManager.getProperty(INDEX_PAGE_PATH);
    }
}
