/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package by.epam.project.action.country;

import by.epam.project.action.ActionCommand;
import by.epam.project.action.city.GoShowCity;
import by.epam.project.action.hotel.GoShowHotel;
import by.epam.project.action.ProcessSavedParameters;
import static by.epam.project.action.JspParamNames.JSP_CITY_LIST;
import static by.epam.project.action.JspParamNames.JSP_CURR_ID_CITY;
import static by.epam.project.action.JspParamNames.JSP_CURR_ID_COUNTRY;
import static by.epam.project.action.JspParamNames.JSP_HOTEL_LIST;
import static by.epam.project.action.JspParamNames.JSP_ID_CITY;
import static by.epam.project.action.JspParamNames.JSP_ID_COUNTRY;
import static by.epam.project.action.JspParamNames.JSP_PAGE;
import by.epam.project.controller.SessionRequestContent;
import by.epam.project.entity.City;
import by.epam.project.entity.Hotel;
import by.epam.project.exception.DaoUserLogicException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author User
 */
public class IfCountrySelected implements ActionCommand {

    @Override
    public String execute(SessionRequestContent request) throws DaoUserLogicException {
        
        String page = (String) request.getSessionAttribute(JSP_PAGE);
        
        new ProcessSavedParameters().execute(request);
        request.setAttribute(JSP_CURR_ID_CITY, "0");

        String currCountry = request.getParameter(JSP_CURR_ID_COUNTRY);
        if (currCountry != null && !currCountry.isEmpty()){
            Integer idCountry = Integer.decode(currCountry);
            if (idCountry > 0){
                request.setAttribute(JSP_ID_COUNTRY, idCountry);
            }
            new GoShowCity().execute(request);
            List<City> cityList = (List<City>) request.getSessionAttribute(JSP_CITY_LIST);
            
            List<Hotel> commonHotelList = new ArrayList();
            for (City c : cityList) {
                request.setAttribute(JSP_ID_CITY, c.getIdCity());
                new GoShowHotel().execute(request);
                List<Hotel> hotelList = (List<Hotel>) request.getSessionAttribute(JSP_HOTEL_LIST);
                commonHotelList.addAll(hotelList);
            }
            request.setSessionAttribute(JSP_HOTEL_LIST, commonHotelList);
        }
        request.setSessionAttribute(JSP_PAGE, page);
        return page;
    }
    
}
