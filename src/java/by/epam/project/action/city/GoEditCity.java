/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package by.epam.project.action.city;

import by.epam.project.action.ActionCommand;
import static by.epam.project.action.JspParamNames.JSP_CITY_COUNT;
import static by.epam.project.action.JspParamNames.JSP_CITY_LIST;
import static by.epam.project.action.JspParamNames.JSP_COUNTRY_LIST;
import static by.epam.project.action.JspParamNames.JSP_CURRENT_CITY;
import static by.epam.project.action.JspParamNames.JSP_CURR_ID_COUNTRY;
import static by.epam.project.action.JspParamNames.JSP_PAGE;
import by.epam.project.action.country.GoShowCountry;
import static by.epam.project.action.country.GoShowCountry.formCountryList;
import by.epam.project.controller.SessionRequestContent;
import by.epam.project.entity.City;
import by.epam.project.exception.DaoUserLogicException;
import by.epam.project.manager.ConfigurationManager;

/**
 *
 * @author Grouk.Helena
 */
public class GoEditCity implements ActionCommand {

    @Override
    public String execute(SessionRequestContent request) throws DaoUserLogicException {
        
        request.deleteSessionAttribute(JSP_CITY_LIST);
        request.deleteSessionAttribute(JSP_CITY_COUNT);
        
        formCountryList(request);
        City currCity = (City) request.getSessionAttribute(JSP_CURRENT_CITY);
        request.setAttribute(JSP_CURR_ID_COUNTRY, currCity.getCountry().getIdCountry());

        String page = ConfigurationManager.getProperty("path.page.editcity");
        request.setSessionAttribute(JSP_PAGE, page);
        return page;
    }
    
}
