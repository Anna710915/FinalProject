package by.epam.finalproject.validator.impl;

import by.epam.finalproject.validator.Validator;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class UserValidator {
    Validator validator;

    @BeforeClass
    public void setUp(){
        validator = ValidatorImpl.getInstance();
    }

    @AfterClass
    public void end(){
        validator = null;
    }

    @DataProvider(name = "login")
    public Object[][] loginData(){
        return new Object[][]{
                {true, "login"}, {false, "p"}, {false, "log"}, {false, ""}, {true, "1234567812345678"},
                {true, "1_2a3_4_4"}, {false, "ddddddddddddddddddddddddddddddddddddd"}
        };
    }

    @Test(dataProvider = "login")
    public void isCorrectLoginTest(boolean expected, String login){
        boolean actual = validator.isCorrectLogin(login);
        assertEquals(actual, expected);
    }

    @DataProvider(name = "password")
    public Object[][] passwordData(){
        return new Object[][]{
                {true, "password"}, {false, "p"}, {false, ""}, {false, "pass"}, {false, "dscfsdd,vdc-_"},
                {false, "ddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddd"},
                {true, "pass.pass9999"}
        };
    }

    @Test(dataProvider = "password")
    public void isCorrectPasswordTest(boolean expected, String password){
        boolean actual = validator.isCorrectPassword(password);
        assertEquals(actual, expected);
    }

    @DataProvider(name = "mail")
    public Object[][] mailData(){
        return new Object[][]{
                {true, "anna.merkul@bk.ru"}, {false,""}, {false, "a@"}, {false, "a@mail"}, {false, "aa@mail."},
                {false, "anna@mail.r"}, {true, "a888@mail.ru"}
        };
    }

    @Test(dataProvider = "mail")
    public void isCorrectMailTest(boolean expected, String mail){
        boolean actual = validator.isCorrectEmail(mail);
        assertEquals(actual,  expected);
    }

    @DataProvider(name = "phone")
    public Object[][] phoneData(){
        return new Object[][]{
                {true, "291111111"}, {true, "338888888"}, {true, "449999999"}, {true, "254444444"},
                {false, "112222222"}, {false, "2966666666"}
        };
    }

    @Test(dataProvider = "phone")
    public void isCorrectPhoneTest(boolean expected, String phone){
        boolean actual = validator.isCorrectPhoneNumber(phone);
        assertEquals(actual, expected);
    }

    @DataProvider(name = "date")
    public Object[][] dateData(){
        return new Object[][]{
                {true, "1993-03-16"}
        };
    }

    @Test(dataProvider = "date")
    public void isCorrectDataTest(boolean expected, String date){
        boolean actual = validator.isCorrectDate(date);
        assertEquals(actual, expected);
    }
}

