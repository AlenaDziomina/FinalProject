/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package by.epam.project.action;


import static by.epam.project.controller.JspParamNames.JSP_COUNTRY_COUNT;
import static by.epam.project.controller.JspParamNames.JSP_COUNTRY_LIST;
import static by.epam.project.controller.JspParamNames.JSP_PAGE;
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
        request.setSessionAttribute(JSP_PAGE, page);
        request.deleteSessionAttribute(JSP_COUNTRY_LIST);
        request.deleteSessionAttribute(JSP_COUNTRY_COUNT);
        return page;
    }
    
}
