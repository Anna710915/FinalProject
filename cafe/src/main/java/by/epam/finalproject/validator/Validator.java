package by.epam.finalproject.validator;

import java.util.Map;

/**
 * The interface Validator.
 */
public interface Validator {
    /**
     * Check name.
     *
     * @param name the name
     * @return the boolean
     */
    boolean isCorrectName(String name);

    /**
     * Check login.
     *
     * @param login the login
     * @return the boolean
     */
    boolean isCorrectLogin(String login);

    /**
     * Check password.
     *
     * @param password the password
     * @return the boolean
     */
    boolean isCorrectPassword(String password);

    /**
     * Check email .
     *
     * @param mail the mail
     * @return the boolean
     */
    boolean isCorrectEmail(String mail);

    /**
     * Check phone number.
     *
     * @param phoneNumber the phone number
     * @return the boolean
     */
    boolean isCorrectPhoneNumber(String phoneNumber);

    /**
     * Check registration.
     *
     * @param map the map
     * @return the boolean
     */
    boolean checkRegistration(Map<String, String> map);

    /**
     * Check product data.
     *
     * @param map the map
     * @return the boolean
     */
    boolean checkProductData(Map<String, String> map);

    /**
     * Check product digit.
     *
     * @param digit the digit
     * @return the boolean
     */
    boolean isCorrectProductDigit(String digit);

    /**
     * Check discount.
     *
     * @param discount the discount
     * @return the boolean
     */
    boolean isCorrectDiscount(String discount);

    /**
     * Check product.
     *
     * @param name the name
     * @return the boolean
     */
    boolean isCorrectProductName(String name);

    /**
     * Check update profile.
     *
     * @param updateData the update data
     * @return the boolean
     */
    boolean checkUpdateProfile(Map<String, String> updateData);

    /**
     * Check composition.
     *
     * @param composition the composition
     * @return the boolean
     */
    boolean isCorrectComposition(String composition);

    /**
     * Check address.
     *
     * @param address the address
     * @return the boolean
     */
    boolean isCorrectAddress(String address);

    /**
     * Check user comment.
     *
     * @param comment the comment
     * @return the boolean
     */
    boolean isCorrectUserComment(String comment);

    /**
     * Check order information.
     *
     * @param orderInfo the order info
     * @return the boolean
     */
    boolean checkOrderInfo(Map<String, String> orderInfo);

    /**
     * Check section name.
     *
     * @param sectionName the section name
     * @return the boolean
     */
    boolean isCorrectSectionName(String sectionName);

    /**
     * Check date.
     *
     * @param date the date
     * @return the boolean
     */
    boolean isCorrectDate(String date);
}
