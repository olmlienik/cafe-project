package by.mlionik.cafe.command;

import by.mlionik.cafe.controller.SessionRequestContent;

public interface ActionCommand {
    String execute(SessionRequestContent requestContent);
}
