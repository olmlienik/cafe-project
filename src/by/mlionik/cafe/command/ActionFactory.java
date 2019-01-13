package by.mlionik.cafe.command;

import by.mlionik.cafe.command.impl.base.EmptyCommand;
import javax.servlet.http.HttpServletRequest;

/**
 * A factory for creating Action command objects.
 */
public class ActionFactory {
    private static final String COMMAND_PARAM = "command";

    /**
     * Defines command.
     *
     * @param request the request
     * @return the action command
     */
    public static ActionCommand defineCommand(HttpServletRequest request) {
        ActionCommand current;
        String userCommand = request.getParameter(COMMAND_PARAM);
        if (userCommand == null || userCommand.isEmpty()) {
            return new EmptyCommand();
        }
        CommandEnum currentEnum = CommandEnum.valueOf(userCommand.toUpperCase());
        current = currentEnum.getCurrentCommand();
        return current;
    }
}
