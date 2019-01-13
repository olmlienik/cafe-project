package by.mlionik.cafe.command;

import by.mlionik.cafe.command.impl.admin.*;
import by.mlionik.cafe.command.impl.base.*;
import by.mlionik.cafe.command.impl.user.*;

/**
 * The type Command enum.
 */
public enum CommandEnum {
    SIGN_UP(new SignUpCommand()),
    LOGIN(new LoginCommand()),
    LOGOUT(new LogoutCommand()),
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
    REJECT_ORDER(new RejectOrderCommand()),
    DELETE_DISH(new DeleteDishCommand()),
    ADD_REVIEW(new AddReviewCommand()),
    FIND_ALL_REVIEWS(new FindAllReviewsCommand()),
    DELETE_REVIEW(new DeleteReviewCommand());

    private ActionCommand command;

    /**
     * Instantiates a new command enum.
     *
     * @param command the command
     */
    CommandEnum(ActionCommand command) {
        this.command = command;
    }

    /**
     * Gets the current command.
     *
     * @return the current command
     */
    public ActionCommand getCurrentCommand() {
        return command;
    }
}
