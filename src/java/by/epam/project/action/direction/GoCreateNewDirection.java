/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package by.epam.project.action.direction;

import by.epam.project.action.ActionCommand;
import static by.epam.project.action.JspParamNames.*;
import static by.epam.project.action.city.GoShowCity.formCityList;
import static by.epam.project.action.country.GoShowCountry.formCountryList;
import static by.epam.project.action.direction.GoShowDirections.formTourTypeList;
import static by.epam.project.action.direction.GoShowDirections.formTransModeList;
import static by.epam.project.action.hotel.GoShowHotel.formHotelList;
import by.epam.project.action.SessionRequestContent;
import by.epam.project.exception.ServletLogicException;
import by.epam.project.manager.ConfigurationManager;

/**
 *
 * @author User
 */
public class GoCreateNewDirection implements ActionCommand {

    @Override
    public String execute(SessionRequestContent request) throws ServletLogicException {
        String page = ConfigurationManager.getProperty("path.page.editdirection");
        request.setSessionAttribute(JSP_PAGE, page);
        request.deleteSessionAttribute(JSP_CURRENT_DIRECTION);
        
        formCountryList(request);
        formCityList(request);
        formHotelList(request);
        formTourTypeList(request);
        formTransModeList(request);
        
        request.setSessionAttribute(JSP_COUNTRY_TAG_LIST, request.getSessionAttribute(JSP_COUNTRY_LIST));
        request.setSessionAttribute(JSP_CITY_TAG_LIST, request.getSessionAttribute(JSP_CITY_LIST));
        
        return page;
    }
}
