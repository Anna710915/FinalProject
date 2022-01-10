package by.epam.finalproject.validator;

import by.epam.finalproject.validator.impl.ValidatorImpl;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static org.testng.Assert.*;
public class ValidatorTest {
    Validator validator;
    @BeforeClass
    public void init(){
        validator = ValidatorImpl.getInstance();
    }

    @Test
    public void isCorrectEmailTest(){
        assertTrue(validator.isCorrectEmail("anna989898@gmail.com"));
    }
    @Test
    public void isCorrectProductNameTest(){
        assertTrue(validator.isCorrectProductName("Три мяса"));
    }

    @Test
    public void isCorrectProductWeight(){
        assertTrue(validator.isCorrectProductDigit("0.7"));
    }

    @Test
    public void isCorrectDiscountTest(){
        assertTrue(validator.isCorrectDiscount("0.5"));
    }

    @Test
    public void isCorrectComposition(){
        assertTrue(validator.isCorrectComposition(""));
    }
    @AfterClass
    public void destroy(){
        validator = null;
    }
}
