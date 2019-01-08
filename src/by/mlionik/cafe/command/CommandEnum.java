package by.mlionik.cafe.command;

import by.mlionik.cafe.command.impl.admin.*;
import by.mlionik.cafe.command.impl.base.*;
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
    FIND_ORDERS_IN_PROCESS(new FindOrdersInProcessCommand()),
    FIND_ALL_USERS(new FindAllUsersCommand()),
    BAN_USER(new BanUserCommand()),
    UNBAN_USER(new UnbanUserCommand()),
    ADD_NEW_DISH(new AddNewDishCommand()),
    APPROVE_ORDER(new ApproveOrderCommand()),
    REJECT_ORDER(new RejectOrderCommand());

    private ActionCommand command;

    CommandEnum(ActionCommand command) {
        this.command = command;
    }

    public ActionCommand getCurrentCommand() {
        return command;
    }
}
