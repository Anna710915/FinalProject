package by.epam.finalproject.validator.impl;

import by.epam.finalproject.validator.Validator;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class MenuValidator {

    Validator validator;

    @BeforeClass
    public void setUp(){
        validator = ValidatorImpl.getInstance();
    }

    @AfterClass
    public void end(){
        validator = null;
    }

    @DataProvider(name = "productName")
    public Object[][] productNameData(){
        return new Object[][]{
            {false, "A"}, {true, "Name"}, {false, ""}, {false, "8f8f88f"}, {true, "Product name"}
        };
    }

    @Test(dataProvider = "productName")
    public void isCorrectNameTest(boolean expected, String name){
        boolean actual = validator.isCorrectProductName(name);
        assertEquals(actual, expected);
    }

    @DataProvider(name = "productDigit")
    public Object[][] digitData(){
        return new Object[][]{
                {true, "400"}, {true, "400.4"}, {true, "400.44"}, {false, "400."}, {false, "400.444"},
                {true, "44.44"}, {false, "400,4"}, {false, "1111111.10"}
        };
    }

    @Test(dataProvider = "productDigit")
    public void isCorrectDigitTest(boolean expected, String productDigit){
        boolean actual = validator.isCorrectProductDigit(productDigit);
        assertEquals(actual, expected);
    }

    @DataProvider(name = "discount")
    public Object[][] discountData(){
        return new Object[][]{
                {true, "0.1"}, {true, "0.11"}, {true, "0"}, {false, "0."}, {false, "1.10"}, {false, "0.111"},
                {false, "0,11"}
        };
    }

    @Test(dataProvider = "discount")
    public void isCorrectDiscountTest(boolean expected, String discount){
        boolean actual = validator.isCorrectDiscount(discount);
        assertEquals(actual, expected);
    }
}