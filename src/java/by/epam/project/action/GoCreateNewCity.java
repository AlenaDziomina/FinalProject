/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package by.epam.project.action;

import static by.epam.project.controller.JspParamNames.JSP_CITY_COUNT;
import static by.epam.project.controller.JspParamNames.JSP_CITY_LIST;
import static by.epam.project.controller.JspParamNames.JSP_COUNTRY_LIST;
import static by.epam.project.controller.JspParamNames.JSP_CURRENT_CITY;
import static by.epam.project.controller.JspParamNames.JSP_PAGE;
import by.epam.project.controller.SessionRequestContent;
import by.epam.project.entity.Country;
import by.epam.project.manager.ConfigurationManager;
import java.util.List;

/**
 *
 * @author User
 */
public class GoCreateNewCity implements ActionCommand {

    public GoCreateNewCity() {
    }

    @Override
    public String execute(SessionRequestContent request) throws DaoLogicException {
        
        request.deleteSessionAttribute(JSP_CURRENT_CITY);
        request.deleteSessionAttribute(JSP_CITY_LIST);
        request.deleteSessionAttribute(JSP_CITY_COUNT);
        
        List<Country> countryList = (List<Country>) request.getSessionAttribute(JSP_COUNTRY_LIST);
        if (countryList == null || countryList.isEmpty()){
            new GoShowCountry().execute(request);
            countryList = (List<Country>) request.getSessionAttribute(JSP_COUNTRY_LIST);
        }
        
        String page = ConfigurationManager.getProperty("path.page.editcity");
        request.setSessionAttribute(JSP_PAGE, page);
        return page;
    }
    
}
