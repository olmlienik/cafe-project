package by.mlionik.cafe.util;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class UserValidatorTest {

    @Test
    public void testIsValidUser() {
    }

    @Test(dataProvider = "dataForBalanceCheck")
    public void testIsValidBalance(String inputBalance, boolean expected) {
        boolean actual = UserValidator.isValidBalance(inputBalance);
        Assert.assertEquals(actual, expected);
    }

    @DataProvider(name = "dataForBalanceCheck")
    public Object[][] dataForBalanceCheck(){
        return new Object[][]{
                {"d3r4", false},
                {"34", true},
                {"334.35", true},
                {"-56", false},
                {"-67.7", false},
                {"some", false},
                {"123.546", false},
                {"1111.56", false}
        };
    }

    @Test(dataProvider = "dataForLoginCheck")
    public void testIsValidLogin(String inputLogin, boolean expected) {
        boolean actual = UserValidator.isValidLogin(inputLogin);
        Assert.assertEquals(actual, expected);
    }

    @DataProvider(name = "dataForLoginCheck")
    public Object[][] dataForLoginCheck(){
        return new Object[][]{
                {"four", false},
                {"d3r4_", true},
                {"d3r4_d", true},
                {"5userg", false},
                {"-67.7", false},
                {"too_long_login_to_be_wright_actually", false}
        };
    }

    @Test(dataProvider = "dataForPasswordCheck")
    public void testIsValidPassword(String inputPassword, boolean expected) {
        boolean actual = UserValidator.isValidPassword(inputPassword);
        Assert.assertEquals(actual, expected);
    }

    @DataProvider(name = "dataForPasswordCheck")
    public Object[][] dataForPasswordCheck(){
        return new Object[][]{
                {"H6our", false},
                {"H6our7", true},
                {"25Password8", true},
                {"password", false},
                {"too_long_login_to_be_wright_actually", false}
        };
    }

    @Test(dataProvider = "dataForEmailCheck")
    public void testIsValidEmail(String inputEmail, boolean expected) {
        boolean actual = UserValidator.isValidEmail(inputEmail);
        Assert.assertEquals(actual, expected);
    }

    @DataProvider(name = "dataForEmailCheck")
    public Object[][] dataForEmailCheck(){
        return new Object[][]{
                {"email", false},
                {"email67@", false},
                {"email67@gmail", false},
                {"email67@gmail.", false},
                {"email67@gmail.com", true},
                {"7email67@gmail.com", true},
                {"too_long_login_to_be_wright_actually", false}
        };
    }
}