/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package by.epam.project.action;

import static by.epam.project.controller.JspParamNames.JSP_PAGE;
import by.epam.project.controller.SessionRequestContent;
import static by.epam.project.dao.AbstractDao.PARAM_NAME_ID_CITY;
import static by.epam.project.dao.AbstractDao.PARAM_NAME_ID_COUNTRY;
import by.epam.project.entity.City;
import by.epam.project.entity.Hotel;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author User
 */
public class IfCountrySelected implements ActionCommand {

    @Override
    public String execute(SessionRequestContent request) throws DaoLogicException {
        
        String page = (String) request.getSessionAttribute(JSP_PAGE);
        
        new ProcessSavedParameters().execute(request);
        request.setAttribute(PARAM_NAME_CURR_ID_CITY, "0");

        String currCountry = request.getParameter(PARAM_NAME_CURR_ID_COUNTRY);
        if (currCountry != null && !currCountry.isEmpty()){
            Integer idCountry = Integer.decode(currCountry);
            if (idCountry > 0){
                request.setAttribute(PARAM_NAME_ID_COUNTRY, idCountry);
            }
            new GoShowCity().execute(request);
            List<City> cityList = (List<City>) request.getSessionAttribute(PARAM_NAME_CITY_LIST);
            
            List<Hotel> commonHotelList = new ArrayList();
            for (City c : cityList) {
                request.setAttribute(PARAM_NAME_ID_CITY, c.getIdCity());
                new GoShowHotel().execute(request);
                List<Hotel> hotelList = (List<Hotel>) request.getSessionAttribute(PARAM_NAME_HOTEL_LIST);
                commonHotelList.addAll(hotelList);
            }
            request.setSessionAttribute(PARAM_NAME_HOTEL_LIST, commonHotelList);
        }
        request.setSessionAttribute(JSP_PAGE, page);
        return page;
    }
    
}
