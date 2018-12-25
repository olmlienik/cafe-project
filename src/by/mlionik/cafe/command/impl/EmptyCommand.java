package by.mlionik.cafe.command.impl;

import by.mlionik.cafe.command.ActionCommand;
import by.mlionik.cafe.controller.SessionRequestContent;
import by.mlionik.cafe.manager.ConfigurationManager;

public class EmptyCommand implements ActionCommand {
    private static final String ERROR_PAGE_PATH = "path.page.error";

    @Override
    public String execute(SessionRequestContent requestContent) {
        return ConfigurationManager.getProperty(ERROR_PAGE_PATH);
    }
}
