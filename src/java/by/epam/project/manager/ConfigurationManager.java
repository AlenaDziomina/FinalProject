/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package by.epam.project.manager;

import java.util.ResourceBundle;

/**
 *
 * @author Helena.Grouk
 */
public class ConfigurationManager {
    private final static ResourceBundle RESOURCE_BUNDLE = ResourceBundle.getBundle("resource.config");
    // класс извлекает информацию из файла config.properties
    private ConfigurationManager() { }
    public static String getProperty(String key) {
        return RESOURCE_BUNDLE.getString(key);
    }
}
