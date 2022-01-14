package by.epam.finalproject.util;

import java.util.Map;
import java.util.StringJoiner;

public class URLUtil {
    private static final String CONTROLLER = "/controller?";
    private static final String EQUAL_SIGN = "=";
    private static final String SIGN = "&";

    private URLUtil(){}

    public static String createURL(Map<String, String> parameters) {
        StringBuilder builder = new StringBuilder(CONTROLLER);
        for (String key: parameters.keySet()){
            String value = parameters.get(key);
            builder.append(key).append(EQUAL_SIGN)
                    .append(value).append(SIGN);
        }
        builder.deleteCharAt(builder.length() - 1);
        return builder.toString();
    }
}
