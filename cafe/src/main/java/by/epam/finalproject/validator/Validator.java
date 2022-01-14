package by.epam.finalproject.validator;

import java.util.Map;

public interface Validator {
    boolean isCorrectName(String name);
    boolean isCorrectLogin(String login);
    boolean isCorrectPassword(String password);
    boolean isCorrectEmail(String gmail);
    boolean isCorrectPhoneNumber(String phoneNumber);
    boolean isCorrectBirthday(String birthday);
    boolean checkRegistration(Map<String, String> map);
    boolean checkProductData(Map<String, String> map);
    boolean isCorrectProductDigit(String digit);
    boolean isCorrectDiscount(String discount);
    boolean isCorrectProductName(String name);
    boolean checkUpdateProfile(Map<String, String> updateData);
    boolean isCorrectComposition(String composition);
    boolean isCorrectAddress(String address);
    boolean isCorrectUserComment(String comment);
    boolean checkOrderInfo(Map<String, String> orderInfo);
    boolean isCorrectSectionName(String sectionName);
}
