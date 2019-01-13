package by.mlionik.cafe.util;

import by.mlionik.cafe.entity.User;
import by.mlionik.cafe.entity.type.PaymentType;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * The type Order validator.
 */
public class OrderValidator {
    private static Logger logger = LogManager.getLogger();
    private static final String MAX_TIME = "23:00";
    private static final String MIN_TIME = "9:00";

    /**
     * Checks time.
     *
     * @param gettingTime the getting time
     * @return true, if successful
     */
    public static boolean timeCheck(String gettingTime) {
        if (!gettingTime.isEmpty()) {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH:mm");
            String currTime = simpleDateFormat.format(new Date());
            try {
                Date gettingDate = simpleDateFormat.parse(gettingTime);
                Date currDate = simpleDateFormat.parse(currTime);
                Date maxDate = simpleDateFormat.parse(MAX_TIME);
                Date minDate = simpleDateFormat.parse(MIN_TIME);
                return ((gettingDate.before(maxDate)) && (gettingDate.after(minDate))
                        && (currDate.after(minDate)) && (currDate.before(maxDate))
                        && ((gettingDate.after(currDate)) || gettingDate.equals(currDate)));
            } catch (ParseException e) {
                logger.log(Level.ERROR, e);
            }
        }
        return false;
    }

    /**
     * Checks payment possibility.
     *
     * @param paymentType the payment type
     * @param user the user
     * @param totalCost the total cost
     * @return true, if successful
     */
    public static boolean checkPaymentPossibility(PaymentType paymentType, User user, double totalCost) {
        switch (paymentType) {
            case FROM_ACCOUNT:
                return (user.getBalance() >= totalCost);
            case WHEN_RECEIVING:
                return true;
            case BY_POINTS:
                return (user.getLoyaltyPoints() >= totalCost);
            default:
                return false;
        }
    }
}
