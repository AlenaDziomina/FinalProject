package by.epam.project.manager;

import java.util.ResourceBundle;

/**
 *
 * @author Helena.Grouk
 */
public class MessageManager {
    private final static ResourceBundle RESOURCE_BUNDLE = ResourceBundle.getBundle("resource.messages");
    // класс извлекает информацию из файла messages.properties
    private MessageManager() { }
    public static String getProperty(String key) {
        return RESOURCE_BUNDLE.getString(key);
    }
}
