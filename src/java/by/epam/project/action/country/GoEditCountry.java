/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package by.epam.project.action.country;


import by.epam.project.action.ActionCommand;
import static by.epam.project.action.JspParamNames.JSP_COUNTRY_COUNT;
import static by.epam.project.action.JspParamNames.JSP_COUNTRY_LIST;
import static by.epam.project.action.JspParamNames.JSP_PAGE;
import by.epam.project.controller.SessionRequestContent;
import by.epam.project.exception.DaoUserLogicException;
import by.epam.project.manager.ConfigurationManager;

/**
 *
 * @author User
 */
public class GoEditCountry implements ActionCommand {

    @Override
    public String execute(SessionRequestContent request) throws DaoUserLogicException {
        String page = ConfigurationManager.getProperty("path.page.editcountry");
        request.setSessionAttribute(JSP_PAGE, page);
        request.deleteSessionAttribute(JSP_COUNTRY_LIST);
        request.deleteSessionAttribute(JSP_COUNTRY_COUNT);
        return page;
    }
    
}
