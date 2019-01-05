package by.mlionik.cafe.command;

import by.mlionik.cafe.command.impl.*;
import by.mlionik.cafe.command.impl.user.*;

public enum CommandEnum {
    SIGN_UP(new SignUpCommand()),
    LOGIN (new LoginCommand()),
    LOGOUT (new LogoutCommand()),
    CHANGE_LANGUAGE(new ChangeLanguageCommand()),
    FIND_ALL_DISHES(new FindAllDishesCommand()),
    ADD_TO_BASKET(new AddToBasketCommand()),
    REMOVE_FROM_BASKET(new RemoveFromBasketCommand()),
    CANCEL_ORDER(new CancelOrderCommand()),
    CREATE_ORDER(new CreateOrderCommand()),
    REPLENISH_BALANCE(new ReplenishBalanceCommand()),
    CHANGE_USERNAME(new ChangeUsernameCommand()),
    CHANGE_PASSWORD(new ChangePasswordCommand()),
    TO_MAIN(new ToMainCommand());

    private ActionCommand command;

    CommandEnum(ActionCommand command) {
        this.command = command;
    }

    public ActionCommand getCurrentCommand() {
        return command;
    }
}
