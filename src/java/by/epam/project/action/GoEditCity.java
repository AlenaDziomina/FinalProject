/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package by.epam.project.action;

import static by.epam.project.action.ActionCommand.PARAM_NAME_PAGE;
import by.epam.project.controller.SessionRequestContent;
import by.epam.project.manager.ConfigurationManager;

/**
 *
 * @author User
 */
public class GoEditCity implements ActionCommand {

    public GoEditCity() {
    }

    @Override
    public String execute(SessionRequestContent request) throws DaoLogicException {
        
        request.deleteSessionAttribute(PARAM_NAME_CITY_LIST);
        request.deleteSessionAttribute(PARAM_NAME_CITY_COUNT);
        new GoShowCountry().execute(request);
        String page = ConfigurationManager.getProperty("path.page.editcity");
        request.setSessionAttribute(PARAM_NAME_PAGE, page);
        return page;
    }
    
}
