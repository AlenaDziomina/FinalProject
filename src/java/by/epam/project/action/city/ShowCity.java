/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package by.epam.project.action.city;

import by.epam.project.action.ActionCommand;
import static by.epam.project.action.JspParamNames.JSP_CITY_LIST;
import static by.epam.project.action.JspParamNames.JSP_CURRENT_CITY;
import static by.epam.project.action.JspParamNames.JSP_CURR_ID_CITY;
import static by.epam.project.action.JspParamNames.JSP_PAGE;
import static by.epam.project.action.JspParamNames.JSP_SELECT_ID;
import by.epam.project.action.SessionRequestContent;
import static by.epam.project.action.city.GoShowCity.resaveParamsShowCity;
import by.epam.project.entity.City;
import by.epam.project.exception.ServletLogicException;
import by.epam.project.manager.ConfigurationManager;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;

/**
 *
 * @author User
 */
public class ShowCity implements ActionCommand {
    
    @Override
    public String execute(SessionRequestContent request) throws ServletLogicException {
        String page = ConfigurationManager.getProperty("path.page.cities");
        request.setSessionAttribute(JSP_PAGE, page);
        resaveParamsShowCity(request);
        showSelectedCity(request);
        return page;
    
    }
    
    public static void showSelectedCity(SessionRequestContent request) {
        String selected = request.getParameter(JSP_SELECT_ID);
        City currCity = null;
        if (selected != null) {
            Integer idCity = Integer.decode(selected); 
            if (idCity != null) {
                List<City> list = (List<City>) request.getSessionAttribute(JSP_CITY_LIST);
                Iterator<City> it = list.iterator();
                while (it.hasNext() && currCity == null) {
                    City city = it.next();
                    if (Objects.equals(city.getIdCity(), idCity)) {
                        currCity = city;
                        request.setAttribute(JSP_CURR_ID_CITY, idCity);
                    }
                }
            }
        }
        request.setSessionAttribute(JSP_CURRENT_CITY, currCity);
    }
    
}
