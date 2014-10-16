/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package by.epam.project.action;

import by.epam.project.manager.ConfigurationManager;

/**
 *
 * @author User
 */
public class EmptyCommand implements ActionCommand{

    @Override
    public String execute(SessionRequestContent request) {
        /* в случае ошибки или прямого обращения к контроллеру
        * переадресация на страницу ввода логина */
        String page = ConfigurationManager.getProperty("path.page.login");
        return page;
    }
    
}
