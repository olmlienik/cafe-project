package by.mlionik.cafe.command;

import by.mlionik.cafe.controller.Router;
import by.mlionik.cafe.controller.SessionRequestContent;

public interface ActionCommand {
    Router execute(SessionRequestContent requestContent);
}
