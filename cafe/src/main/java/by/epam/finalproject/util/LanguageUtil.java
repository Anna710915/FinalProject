package by.epam.finalproject.util;

/**
 * The type Language util.
 */
public final class LanguageUtil {
    private static final String ENGLISH = "en_US";
    private static final String RUSSIAN = "ru_RU";
    private LanguageUtil(){}

    /**
     * Is correct language boolean.
     *
     * @param language the language
     * @return the boolean
     */
    public static boolean isCorrectLanguage(String language){
        return language!=null && (language.equals(ENGLISH) || language.equals(RUSSIAN));
    }
}
