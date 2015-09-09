package by.epam.project.manager;

import java.util.Locale;

/**
 *
 * @author Helena.Grouk
 */
public abstract class LocaleManager {

    public static Locale getLocale(String language) {
        if (language == null || language.isEmpty()) {
            return null;
        }
        return getLocale(language, language);
    }

    public static Locale getLocale(String language, String country) {
        if (null != country && !country.isEmpty() && null != language && !language.isEmpty()) {
            return new Locale(language, country);
        } else {
            return null;
        }
    }


}
