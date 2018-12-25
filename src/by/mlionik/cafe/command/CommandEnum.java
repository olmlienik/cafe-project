package by.mlionik.cafe.command;

import by.mlionik.cafe.command.impl.*;

public enum CommandEnum {
    SIGN_UP(new SignUpCommand()),
    LOGIN (new LoginCommand()),
    LOGOUT (new LogoutCommand()),
    CHANGE_LANGUAGE(new ChangeLanguageCommand()),
    FIND_ALL_DISHES(new FindAllDishesCommand()),
    TO_MAIN(new ToMainCommand());

    private ActionCommand command;

    CommandEnum(ActionCommand command) {
        this.command = command;
    }

    public ActionCommand getCurrentCommand() {
        return command;
    }
}
