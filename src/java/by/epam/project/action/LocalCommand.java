/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package by.epam.project.action;

import by.epam.project.controller.SessionRequestContent;
import by.epam.project.manager.ConfigurationManager;
import by.epam.project.manager.LocaleManager;
import java.util.Locale;

/**
 *
 * @author User
 */
public class LocalCommand implements ActionCommand {

    @Override
    public String execute(SessionRequestContent request) {
        String page = ConfigurationManager.getProperty("path.page.index");
        String leng = request.getParameter("leng");
        Locale locale = LocaleManager.getLocale(leng);
        if (locale != null) {
            request.setSessionAttribute("locale", locale);
        }
        
        return page;
    }
    
}
