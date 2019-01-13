package by.mlionik.cafe.util;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class ReviewValidatorTest {

    @Test(dataProvider = "dataForReviewCheck")
    public void testCheckReviewBody(String reviewBody, boolean expected) {
        boolean actual = ReviewValidator.checkReviewBody(reviewBody);
        Assert.assertEquals(actual, expected);
    }

    @DataProvider(name = "dataForReviewCheck")
    public Object[][] dataForReviewCheck(){
        return new Object[][]{
                {"bit", false},
                {"zxcvbnm,asdfghjklqwezxcvbnm,asdfghjklqwezxcvbnm,asdfghjklqwezxcvbnm,asdfghjklqwezxcvbnm,asdfghjklqwezxcvbnm,asdfghjklqwezxcvbnm,asdfghjklqwezxcvbnm,asdfghjklqwezxcvbnm,asdfghjklqwezxcvbnm,asdfghjklqwezxcvbnm,asdfghjklqwezxcvbnm,asdfghjklqwezxcvbnm,asdfghjklqwe", false},
                {"good review", true}
        };
    }
}