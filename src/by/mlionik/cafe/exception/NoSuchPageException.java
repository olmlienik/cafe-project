package by.mlionik.cafe.exception;

public class NoSuchPageException extends Exception {
    public NoSuchPageException() {
    }

    public NoSuchPageException(String message) {
        super(message);
    }

    public NoSuchPageException(String message, Throwable cause) {
        super(message, cause);
    }

    public NoSuchPageException(Throwable cause) {
        super(cause);
    }

    public NoSuchPageException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
