/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package by.epam.project.manager;

import java.util.ResourceBundle;

/**
 *
 * @author User
 */
public class MessageManager {
    private final static ResourceBundle resourceBundle = ResourceBundle.getBundle("by.epam.project.resource.messages");
    // класс извлекает информацию из файла messages.properties
    private MessageManager() { }
    public static String getProperty(String key) {
        return resourceBundle.getString(key);
    }
}