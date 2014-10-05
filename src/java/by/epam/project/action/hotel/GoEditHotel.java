/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package by.epam.project.action.hotel;

import by.epam.project.action.ActionCommand;
import static by.epam.project.action.JspParamNames.JSP_CITY_LIST;
import static by.epam.project.action.JspParamNames.JSP_COUNTRY_LIST;
import static by.epam.project.action.JspParamNames.JSP_CURRENT_CITY;
import static by.epam.project.action.JspParamNames.JSP_CURRENT_COUNTRY;
import static by.epam.project.action.JspParamNames.JSP_CURRENT_HOTEL;
import static by.epam.project.action.JspParamNames.JSP_CURR_CITY_LIST;
import static by.epam.project.action.JspParamNames.JSP_CURR_ID_CITY;
import static by.epam.project.action.JspParamNames.JSP_CURR_ID_COUNTRY;
import static by.epam.project.action.JspParamNames.JSP_HOTEL_COUNT;
import static by.epam.project.action.JspParamNames.JSP_HOTEL_LIST;
import static by.epam.project.action.JspParamNames.JSP_ID_COUNTRY;
import static by.epam.project.action.JspParamNames.JSP_PAGE;
import by.epam.project.action.city.GoShowCity;
import static by.epam.project.action.city.GoShowCity.formCityList;
import by.epam.project.action.country.GoShowCountry;
import static by.epam.project.action.country.GoShowCountry.formCountryList;
import by.epam.project.controller.SessionRequestContent;
import by.epam.project.entity.City;
import by.epam.project.entity.Country;
import by.epam.project.entity.Hotel;
import by.epam.project.exception.DaoUserLogicException;
import by.epam.project.manager.ConfigurationManager;
import java.util.List;

/**
 *
 * @author User
 */
public class GoEditHotel implements ActionCommand {

    @Override
    public String execute(SessionRequestContent request) throws DaoUserLogicException {
        String page = ConfigurationManager.getProperty("path.page.edithotel");
        request.setSessionAttribute(JSP_PAGE, page);
        
        formCountryList(request);
        formCityList(request);
        Hotel currHotel = (Hotel) request.getSessionAttribute(JSP_CURRENT_HOTEL);
        Integer idCity = currHotel.getCity().getIdCity();
        request.setAttribute(JSP_CURR_ID_CITY, idCity);
        
        List<City> cityList = (List<City>) request.getSessionAttribute(JSP_CITY_LIST);
        for (City city: cityList) {
            if (city.getIdCity().equals(idCity)) {
                Integer idCountry = city.getCountry().getIdCountry();
                request.setAttribute(JSP_CURR_ID_COUNTRY, idCountry);
                request.setAttribute(JSP_ID_COUNTRY, idCountry);
                formCityList(request);
                request.setSessionAttribute(JSP_CURR_CITY_LIST, request.getSessionAttribute(JSP_CITY_LIST));
            }
        }
        return page;
    }
    
}