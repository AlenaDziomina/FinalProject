/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package by.epam.project.action.hotel;

import by.epam.project.action.ActionCommand;
import static by.epam.project.action.JspParamNames.JSP_CITY_LIST;
import static by.epam.project.action.JspParamNames.JSP_CURRENT_HOTEL;
import static by.epam.project.action.JspParamNames.JSP_CURR_CITY_LIST;
import static by.epam.project.action.JspParamNames.JSP_PAGE;
import by.epam.project.action.SessionRequestContent;
import by.epam.project.action.city.CityCommand;
import by.epam.project.action.country.CountryCommand;
import by.epam.project.exception.ServletLogicException;
import by.epam.project.manager.ConfigurationManager;

/**
 *
 * @author User
 */
public class GoCreateNewHotel extends HotelCommand implements ActionCommand {

    @Override
    public String execute(SessionRequestContent request) throws ServletLogicException {
        String page = ConfigurationManager.getProperty("path.page.edithotel");
        request.setSessionAttribute(JSP_PAGE, page);
        new CountryCommand().formCountryList(request);
        new CityCommand().formCityList(request);
        request.deleteSessionAttribute(JSP_CURRENT_HOTEL);
        request.setSessionAttribute(JSP_CURR_CITY_LIST, request.getSessionAttribute(JSP_CITY_LIST));
        return page;
    }
    
}
