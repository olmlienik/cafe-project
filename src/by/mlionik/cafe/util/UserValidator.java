package by.mlionik.cafe.util;

import by.mlionik.cafe.entity.User;
import java.util.regex.Pattern;

public class UserValidator {
    private static final String LOGIN_PATTERN = "^([a-zA-Z]+)[a-zA-Z\\d_]{4,}$";
    private static final String PASSWORD_PATTERN = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)[a-zA-Z\\d]{6,}";
    private static final String EMAIL_PATTERN = "[a-zA-Z0-9]+@[a-zA-Z]+\\.[a-zA-Z]+";
    private static final int MAX_LOGIN_LENGTH = 25;
    private static final int MAX_PASSWORD_LENGTH = 25;
    private static final int MAX_EMAIL_LENGTH = 25;
//    private static final String BALANCE_PATTERN = "(\\d+((\\.)?\\d+)?)";
private static final String BALANCE_PATTERN = "([\\d]{1,3}((\\.)?([\\d]){1,2})?)";


    public static boolean isValidUser(User user) {
        return isValidLogin(user.getLogin()) && isValidPassword(user.getPassword()) && isValidEmail(user.getEmail());
    }

    public static boolean isValidBalance(String balance){
        return Pattern.matches(BALANCE_PATTERN, balance);
    }

    public static boolean isValidLogin(String login) {
        return  (login.matches(LOGIN_PATTERN) && login.length() < MAX_LOGIN_LENGTH);
    }

    public static boolean isValidPassword(String password) {
        return (password.matches(PASSWORD_PATTERN) && password.length() < MAX_PASSWORD_LENGTH);
    }

    public static boolean isValidEmail(String email) {
        return (email.matches(EMAIL_PATTERN) && email.length() < MAX_EMAIL_LENGTH);
    }


}
