package by.mlionik.cafe.util;

/**
 * The type Review validator.
 */
public class ReviewValidator {
    private static final int MIN_LENGTH = 4;
    private static final int MAX_LENGTH = 256;

    /**
     * Checks if review body is valid.
     *
     * @param body the body
     * @return true, if successful
     */
    public static boolean checkReviewBody(String body){
        int length = body.trim().length();
        return (length >= MIN_LENGTH && length <= MAX_LENGTH);
    }
}
