package by.epam.finalproject.validator;

import java.util.Map;

/**
 * The interface Validator.
 */
public interface Validator {
    /**
     * Is correct name boolean.
     *
     * @param name the name
     * @return the boolean
     */
    boolean isCorrectName(String name);

    /**
     * Is correct login boolean.
     *
     * @param login the login
     * @return the boolean
     */
    boolean isCorrectLogin(String login);

    /**
     * Is correct password boolean.
     *
     * @param password the password
     * @return the boolean
     */
    boolean isCorrectPassword(String password);

    /**
     * Is correct email boolean.
     *
     * @param gmail the gmail
     * @return the boolean
     */
    boolean isCorrectEmail(String gmail);

    /**
     * Is correct phone number boolean.
     *
     * @param phoneNumber the phone number
     * @return the boolean
     */
    boolean isCorrectPhoneNumber(String phoneNumber);

    /**
     * Check registration boolean.
     *
     * @param map the map
     * @return the boolean
     */
    boolean checkRegistration(Map<String, String> map);

    /**
     * Check product data boolean.
     *
     * @param map the map
     * @return the boolean
     */
    boolean checkProductData(Map<String, String> map);

    /**
     * Is correct product digit boolean.
     *
     * @param digit the digit
     * @return the boolean
     */
    boolean isCorrectProductDigit(String digit);

    /**
     * Is correct discount boolean.
     *
     * @param discount the discount
     * @return the boolean
     */
    boolean isCorrectDiscount(String discount);

    /**
     * Is correct product name boolean.
     *
     * @param name the name
     * @return the boolean
     */
    boolean isCorrectProductName(String name);

    /**
     * Check update profile boolean.
     *
     * @param updateData the update data
     * @return the boolean
     */
    boolean checkUpdateProfile(Map<String, String> updateData);

    /**
     * Is correct composition boolean.
     *
     * @param composition the composition
     * @return the boolean
     */
    boolean isCorrectComposition(String composition);

    /**
     * Is correct address boolean.
     *
     * @param address the address
     * @return the boolean
     */
    boolean isCorrectAddress(String address);

    /**
     * Is correct user comment boolean.
     *
     * @param comment the comment
     * @return the boolean
     */
    boolean isCorrectUserComment(String comment);

    /**
     * Check order info boolean.
     *
     * @param orderInfo the order info
     * @return the boolean
     */
    boolean checkOrderInfo(Map<String, String> orderInfo);

    /**
     * Is correct section name boolean.
     *
     * @param sectionName the section name
     * @return the boolean
     */
    boolean isCorrectSectionName(String sectionName);
}
