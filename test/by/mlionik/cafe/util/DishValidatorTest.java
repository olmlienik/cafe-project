package by.mlionik.cafe.util;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class DishValidatorTest {

    @Test(dataProvider = "dataForDishName")
    public void testIsValidDishName(String name, boolean expected) {
        boolean actual = DishValidator.isValidDishName(name);
        Assert.assertEquals(actual, expected);
    }

    @DataProvider(name = "dataForDishName")
    public Object[][] dataForDishName(){
        return new Object[][]{
                {"Rizotto", true},
                {"Latte new", true},
                {"Latte !", false},
                {"-56", false}
        };
    }


    @Test(dataProvider = "dataForValidCost")
    public void testIsValidCost(String cost, boolean expected) {
        boolean actual = DishValidator.isValidCost(cost);
        Assert.assertEquals(actual, expected);
    }

    @DataProvider(name = "dataForValidCost")
    public Object[][] dataForValidCost(){
        return new Object[][]{
                {"23", true},
                {"23.55", true},
                {"23.333", false},
                {"-56", false},
                {"10000", false},
                {"5f6", false}
        };
    }

}