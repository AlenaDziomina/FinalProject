/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package by.epam.project.action;

import by.epam.project.controller.SessionRequestContent;
import by.epam.project.manager.ConfigurationManager;

/**
 *
 * @author User
 */
public class GoLoginCommand implements ActionCommand {

    public GoLoginCommand() {
    }

    @Override
    public String execute(SessionRequestContent request){
        String page = ConfigurationManager.getProperty("path.page.login");
        request.setSessionAttribute(PARAM_NAME_PAGE, page);
        return page;
    }
    
}
