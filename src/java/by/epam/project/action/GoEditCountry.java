/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package by.epam.project.action;

import static by.epam.project.action.ActionCommand.PARAM_NAME_CURRENT_COUNTRY;
import static by.epam.project.action.ActionCommand.PARAM_NAME_PAGE;
import by.epam.project.controller.SessionRequestContent;
import by.epam.project.manager.ConfigurationManager;

/**
 *
 * @author User
 */
class GoEditCountry implements ActionCommand {

    public GoEditCountry() {
    }

    @Override
    public String execute(SessionRequestContent request) throws DaoLogicException {
        String page = ConfigurationManager.getProperty("path.page.editcountry");
        request.setSessionAttribute(PARAM_NAME_PAGE, page);
        request.deleteSessionAttribute(PARAM_NAME_CURRENT_COUNTRY);
        request.deleteSessionAttribute(PARAM_NAME_COUNTRY_LIST);
        request.deleteSessionAttribute(PARAM_NAME_COUNTRY_COUNT);
        return page;
    }
    
}
