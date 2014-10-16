/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package by.epam.project.action.country;

import by.epam.project.action.ActionCommand;
import static by.epam.project.action.JspParamNames.JSP_COUNTRY_LIST;
import static by.epam.project.action.JspParamNames.JSP_CURRENT_COUNTRY;
import static by.epam.project.action.JspParamNames.JSP_CURR_ID_COUNTRY;
import static by.epam.project.action.JspParamNames.JSP_PAGE;
import static by.epam.project.action.JspParamNames.JSP_SELECT_ID;
import static by.epam.project.action.country.GoShowCountry.formCountryList;
import by.epam.project.action.SessionRequestContent;
import by.epam.project.entity.Country;
import by.epam.project.exception.DaoUserLogicException;
import by.epam.project.manager.ConfigurationManager;
import java.util.List;
import java.util.Objects;

/**
 *
 * @author User
 */
public class ShowCountry implements ActionCommand {

    @Override
    public String execute(SessionRequestContent request) throws DaoUserLogicException {
        String page = ConfigurationManager.getProperty("path.page.countries");
        request.setSessionAttribute(JSP_PAGE, page);
        
        formCountryList(request);
        List<Country> list = (List<Country>) request.getSessionAttribute(JSP_COUNTRY_LIST);
        Integer idCountry = Integer.decode(request.getParameter(JSP_SELECT_ID));
        request.setAttribute(JSP_CURR_ID_COUNTRY, idCountry);
        for (Country c: list) {
            if (Objects.equals(c.getIdCountry(), idCountry)) {
                request.setSessionAttribute(JSP_CURRENT_COUNTRY, c);
                return page;
            }
        }
        return page;
    }
    
}
