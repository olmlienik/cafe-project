package by.mlionik.cafe.command;

import by.mlionik.cafe.controller.Router;
import by.mlionik.cafe.controller.SessionRequestContent;

/**
 * The interface Action command.
 */
public interface ActionCommand {
    /**
     * Executes command.
     *
     * @param requestContent the request content
     * @return the router
     */
    Router execute(SessionRequestContent requestContent);
}
