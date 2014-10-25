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
import by.epam.project.action.SessionRequestContent;
import static by.epam.project.action.country.GoShowCountry.resaveParamsShowCountry;
import by.epam.project.entity.Country;
import by.epam.project.exception.ServletLogicException;
import by.epam.project.manager.ConfigurationManager;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;

/**
 *
 * @author User
 */
public class ShowCountry implements ActionCommand {

    @Override
    public String execute(SessionRequestContent request) throws ServletLogicException {
        String page = ConfigurationManager.getProperty("path.page.countries");
        request.setSessionAttribute(JSP_PAGE, page);
        resaveParamsShowCountry(request);
        showSelectedCountry(request);
        return page;
    }
    
    public static void showSelectedCountry(SessionRequestContent request) {
        String selected = request.getParameter(JSP_SELECT_ID);
        Country currCountry = null;
        if (selected != null) {
            Integer idCountry = Integer.decode(selected); 
            if (idCountry != null) {
                List<Country> list = (List<Country>) request.getSessionAttribute(JSP_COUNTRY_LIST);
                Iterator<Country> it = list.iterator();
                while (it.hasNext() && currCountry == null) {
                    Country country = it.next();
                    if (Objects.equals(country.getIdCountry(), idCountry)) {
                        currCountry = country;
                        request.setAttribute(JSP_CURR_ID_COUNTRY, idCountry);
                    }
                }
            }
        }
        request.setSessionAttribute(JSP_CURRENT_COUNTRY, currCountry);
    }
}
