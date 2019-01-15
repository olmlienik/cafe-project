package by.mlionik.cafe.util;

import by.mlionik.cafe.entity.User;
import by.mlionik.cafe.entity.PaymentType;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class OrderValidatorTest {

    @Test(dataProvider = "dataForCheckPaymentPossibility")
    public void testCheckPaymentPossibility(PaymentType paymentType, User user, double totalCost, boolean expected) {
        boolean actual = OrderValidator.checkPaymentPossibility(paymentType, user, totalCost);
        Assert.assertEquals(actual, expected);
    }

    @DataProvider(name = "dataForCheckPaymentPossibility")
    Object[][] dataForCheckPaymentPossibility() {
        User user = new User();
        user.setBalance(10);
        user.setLoyaltyPoints(10);
        return new Object[][]{
                {PaymentType.WHEN_RECEIVING, user, 100, true},
                {PaymentType.BY_POINTS, user, 100, false},
                {PaymentType.BY_POINTS, user, 8, true},
                {PaymentType.FROM_ACCOUNT, user, 8, true},
                {PaymentType.FROM_ACCOUNT, user, 11, false}
        };
    }
}