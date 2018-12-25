package by.mlionik.cafe.util;

import by.mlionik.cafe.entity.User;
import java.util.ArrayList;
import java.util.List;

public class UserValidator {
    private static final String LOGIN_PATTERN = "^([a-zA-Z]+)[a-zA-Z\\d_]{4,}$";
    private static final String PASSWORD_PATTERN = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)[a-zA-Z\\d]{6,}$";
    private static final String EMAIL_PATTERN = "[a-zA-Z0-9]+@[a-zA-Z]+\\.[a-zA-Z]+";
    private static final int MAX_LOGIN_LENGTH = 45;
    private static final int MAX_PASSWORD_LENGTH = 100;
    private static final int MAX_EMAIL_LENGTH = 100;

    private List<String> invalidUserInfo = new ArrayList<>();

    public List<String> getInvalidUserInfo() {
        return invalidUserInfo;
    }

    public boolean isValid(User user) {
        return isValidLogin(user.getLogin()) && isValidPassword(user.getPassword()) && isValidEmail(user.getEmail());
    }

    private boolean isValidLogin(String login) {
        if (login.matches(LOGIN_PATTERN) && login.length() < MAX_LOGIN_LENGTH) {
            return true;
        } else {
            invalidUserInfo.add("User login is not valid.");
            return false;
        }
    }

    private boolean isValidPassword(String password) {
        if (password.matches(PASSWORD_PATTERN) && password.length() < MAX_PASSWORD_LENGTH) {
            return true;
        } else {
            invalidUserInfo.add("User password is not valid.");
            return false;
        }
    }

    private boolean isValidEmail(String email) {
        if (email.matches(EMAIL_PATTERN) && email.length() < MAX_EMAIL_LENGTH) {
            return true;
        } else {
            invalidUserInfo.add("User email is not valid.");
            return false;
        }
    }
}
