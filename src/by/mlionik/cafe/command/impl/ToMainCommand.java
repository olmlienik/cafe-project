package by.mlionik.cafe.command.impl;

import by.mlionik.cafe.command.ActionCommand;
import by.mlionik.cafe.controller.SessionRequestContent;
import by.mlionik.cafe.manager.ConfigurationManager;

public class ToMainCommand implements ActionCommand {
    private static final String MAIN_PAGE_PATH = "/jsp/mainWithRoles.jsp";

    @Override
    public String execute(SessionRequestContent requestContent) {
        return MAIN_PAGE_PATH;
    }
}
