package by.epam.finalproject.util;

import org.apache.commons.codec.digest.DigestUtils;

/**
 * The type Password encryption.
 */
public class PasswordEncryption {
    /**
     * Md5 apache string.
     *
     * @param password the password
     * @return the string
     */
    public static String md5Apache(String password){
        String md5Hex = DigestUtils.md5Hex(password);
        return md5Hex;
    }
}
