package by.mlionik.cafe.util;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class XssPreventionTest {

    @Test(dataProvider = "dataForXssPrevention")
    public void testResetScripts(String inputString, String expected) {
        String actual = XssPrevention.resetScripts(inputString);
        Assert.assertEquals(actual, expected);
    }

    @DataProvider(name = "dataForXssPrevention")
    public Object[][] dataForXssPrevention(){
        return new Object[][]{
                {"<script> sth else</script>", " sth else"},
                {"<h2> sth else</h2>", " sth else"},
                {"Latte !", "Latte !"},
                {"<sbr>-sth else", "-sth else"}
        };
    }
}