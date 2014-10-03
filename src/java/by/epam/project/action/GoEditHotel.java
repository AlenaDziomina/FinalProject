/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package by.epam.project.action;

import static by.epam.project.controller.JspParamNames.JSP_CITY_LIST;
import static by.epam.project.controller.JspParamNames.JSP_COUNTRY_LIST;
import static by.epam.project.controller.JspParamNames.JSP_CURRENT_COUNTRY;
import static by.epam.project.controller.JspParamNames.JSP_CURRENT_HOTEL;
import static by.epam.project.controller.JspParamNames.JSP_CURR_CITY_LIST;
import static by.epam.project.controller.JspParamNames.JSP_HOTEL_COUNT;
import static by.epam.project.controller.JspParamNames.JSP_HOTEL_LIST;
import static by.epam.project.controller.JspParamNames.JSP_PAGE;
import by.epam.project.controller.SessionRequestContent;
import by.epam.project.entity.City;
import by.epam.project.entity.Country;
import by.epam.project.entity.Hotel;
import by.epam.project.manager.ConfigurationManager;
import java.util.List;

/**
 *
 * @author User
 */
public class GoEditHotel implements ActionCommand {

    public GoEditHotel() {
    }

    @Override
    public String execute(SessionRequestContent request) throws DaoLogicException {
        String page = ConfigurationManager.getProperty("path.page.edithotel");
        request.setSessionAttribute(JSP_PAGE, page);
        
        List<Country> countryList = (List<Country>) request.getSessionAttribute(JSP_COUNTRY_LIST);
        if (countryList == null || countryList.isEmpty()){
            new GoShowCountry().execute(request);
            countryList = (List<Country>) request.getSessionAttribute(JSP_COUNTRY_LIST);
        }
        
        List<City> cityList = (List<City>) request.getSessionAttribute(JSP_CITY_LIST);
        if (cityList == null || cityList.isEmpty()){
            new GoShowCity().execute(request);
            cityList = (List<City>) request.getSessionAttribute(JSP_CITY_LIST);
        }
        
        Hotel hotel = (Hotel) request.getSessionAttribute(JSP_CURRENT_HOTEL);
        Integer idCity = hotel.getCity().getIdCity();
        for (City c: cityList) {
            if (c.getIdCity().equals(idCity)) {
                Integer idCountry = c.getCountry().getIdCountry();
                for (Country cntr: countryList) {
                    if (cntr.getIdCountry().equals(idCountry)){
                         request.setAttribute(JSP_CURRENT_COUNTRY, cntr);
                    }
                }
            }
        }
        
        request.setAttribute(JSP_CURR_CITY_LIST, cityList);
        
        request.deleteSessionAttribute(JSP_HOTEL_LIST);
        request.deleteSessionAttribute(JSP_HOTEL_COUNT);
        return page;
    }
    
}