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
public class ShowDirection implements ActionCommand {

    @Override
    public String execute(SessionRequestContent request) throws DaoLogicException {
        String page = ConfigurationManager.getProperty("path.page.direction");
        request.setSessionAttribute(PARAM_NAME_PAGE, page);
        String str = request.getParameter(PARAM_NAME_SELECT_ID);
        Integer id = Integer.decode(str);
        
        return page;
    }
    
}
