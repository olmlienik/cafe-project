package by.mlionik.cafe.exception;

/**
 * The type No such request parameter exception.
 */
public class NoSuchRequestParameterException extends Exception {

    /**
     * Instantiates a new no such request parameter exception.
     */
    public NoSuchRequestParameterException() {
    }

    /**
     * Instantiates a new no such request parameter exception.
     *
     * @param message the message
     */
    public NoSuchRequestParameterException(String message) {
        super(message);
    }

    /**
     * Instantiates a new no such request parameter exception.
     *
     * @param message the message
     * @param cause   the cause
     */
    public NoSuchRequestParameterException(String message, Throwable cause) {
        super(message, cause);
    }

    /**
     * Instantiates a new no such request parameter exception.
     *
     * @param cause the cause
     */
    public NoSuchRequestParameterException(Throwable cause) {
        super(cause);
    }

    /**
     * Instantiates a new no such request parameter exception.
     *
     * @param message            the message
     * @param cause              the cause
     * @param enableSuppression  the enable suppression
     * @param writableStackTrace the writable stack trace
     */
    public NoSuchRequestParameterException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
