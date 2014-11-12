/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package by.epam.project.action.country;

import by.epam.project.action.ActionCommand;
import static by.epam.project.action.JspParamNames.*;
import by.epam.project.action.SessionRequestContent;
import static by.epam.project.action.city.GoShowCity.formCityList;
import static by.epam.project.action.direction.SaveRedactDirection.resaveParamsSaveDirection;
import static by.epam.project.action.hotel.GoShowHotel.formHotelList;
import static by.epam.project.action.hotel.SaveRedactHotel.resaveParamsSaveHotel;
import static by.epam.project.action.tour.SearchTour.resaveParamsSearchTour;
import by.epam.project.entity.City;
import by.epam.project.entity.Hotel;
import by.epam.project.exception.ServletLogicException;
import by.epam.project.manager.ConfigurationManager;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author User
 */
public class IfCountrySelected implements ActionCommand {

    @Override
    public String execute(SessionRequestContent request) throws ServletLogicException {
        String page = (String) request.getSessionAttribute(JSP_PAGE);
        resaveParamsCountrySelected(request);
        request.setAttribute(JSP_CURR_ID_CITY, "0");

        String currCountry = request.getParameter(JSP_CURR_ID_COUNTRY);
        if (currCountry != null && !currCountry.isEmpty()){
            Integer idCountry = Integer.decode(currCountry);
            if (idCountry != 0) {
                request.setAttribute(JSP_ID_COUNTRY, idCountry);
            }
            formCityList(request);
            List<City> cityList = (List<City>) request.getSessionAttribute(JSP_CITY_LIST);
            request.setSessionAttribute(JSP_CURR_CITY_LIST, cityList);
            
            List<Hotel> commonHotelList = new ArrayList();
            for (City c : cityList) {
                request.setAttribute(JSP_ID_CITY, c.getIdCity());
                formHotelList(request);
                List<Hotel> hotelList = (List<Hotel>) request.getSessionAttribute(JSP_HOTEL_LIST);
                commonHotelList.addAll(hotelList);
            }
            request.setSessionAttribute(JSP_HOTEL_LIST, commonHotelList);
        }
        request.setSessionAttribute(JSP_PAGE, page);
        return page;
    }

    private void resaveParamsCountrySelected(SessionRequestContent request) throws ServletLogicException {
        String page = (String) request.getSessionAttribute(JSP_PAGE);
        String editHotelPage = ConfigurationManager.getProperty("path.page.edithotel");
        String editDirectionPage = ConfigurationManager.getProperty("path.page.editdirection");
        String searchingPage = ConfigurationManager.getProperty("path.page.tours");
        if (page == null ? editHotelPage == null : page.equals(editHotelPage)) {
            resaveParamsSaveHotel(request);
        } else if (page == null ? editDirectionPage == null : page.equals(editDirectionPage)) {
            resaveParamsSaveDirection(request);
        } else if (page == null ? searchingPage == null : page.equals(searchingPage)) {
            resaveParamsSearchTour(request);
        }
    }
    
}
