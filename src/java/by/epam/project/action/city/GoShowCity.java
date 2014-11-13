/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package by.epam.project.action.city;

import by.epam.project.action.ActionCommand;
import static by.epam.project.action.JspParamNames.*;
import by.epam.project.action.SessionRequestContent;
import by.epam.project.exception.ServletLogicException;
import by.epam.project.manager.ConfigurationManager;

/**
 *
 * @author User
 */
public class GoShowCity extends CityCommand implements ActionCommand {

    @Override
    public String execute(SessionRequestContent request) throws ServletLogicException {
        String page = ConfigurationManager.getProperty("path.page.cities");
        String prevPage = (String) request.getSessionAttribute(JSP_PAGE);
        resaveParamsShowCity(request);
        formCityList(request);
        showSelectedCity(request);
        if (page == null ? prevPage != null : ! page.equals(prevPage)) {
            request.setSessionAttribute(JSP_PAGE, page);
            cleanSessionShowCity(request);
        }
        return page;
    }
    
    private void resaveParamsShowCity(SessionRequestContent request) {
        String validCityStatus = request.getParameter(JSP_CITY_VALID_STATUS);
        if(validCityStatus != null) {
            request.setSessionAttribute(JSP_CITY_VALID_STATUS, validCityStatus);
        }
        
        String invalidCityStatus = request.getParameter(JSP_CITY_INVALID_STATUS);
        if(invalidCityStatus != null) {
            request.setSessionAttribute(JSP_CITY_INVALID_STATUS, invalidCityStatus);
        }
        
        String validHotelStatus = request.getParameter(JSP_HOTEL_VALID_STATUS);
        if(validHotelStatus != null) {
            request.setSessionAttribute(JSP_HOTEL_VALID_STATUS, validHotelStatus);
        }
        
        String invalidHotelStatus = request.getParameter(JSP_HOTEL_INVALID_STATUS);
        if(invalidHotelStatus != null) {
            request.setSessionAttribute(JSP_HOTEL_INVALID_STATUS, invalidHotelStatus);
        }
    }
    
    private void cleanSessionShowCity(SessionRequestContent request) {
        //city
        //request.deleteSessionAttribute(JSP_CITY_LIST);
        //request.deleteSessionAttribute(JSP_CURR_CITY_LIST);
        //request.deleteSessionAttribute(JSP_CURRENT_CITY);
        
        
        //country
        request.deleteSessionAttribute(JSP_COUNTRY_LIST);
        request.deleteSessionAttribute(JSP_CURRENT_COUNTRY);
        
        //direction
        request.deleteSessionAttribute(JSP_COUNTRY_TAG_LIST);
        request.deleteSessionAttribute(JSP_CITY_TAG_LIST);
        request.deleteSessionAttribute(JSP_DIRECTION_LIST);
        request.deleteSessionAttribute(JSP_PAGE_LIST);
        request.deleteSessionAttribute(JSP_DIRECTION_VALID_STATUS);
        request.deleteSessionAttribute(JSP_DIRECTION_INVALID_STATUS);
        request.deleteSessionAttribute(JSP_TOUR_TYPE_LIST);
        request.deleteSessionAttribute(JSP_TRANS_MODE_LIST);
        request.deleteSessionAttribute(JSP_CURRENT_DIRECTION);
        
        //hotel
        request.deleteSessionAttribute(JSP_HOTEL_LIST);
        request.deleteSessionAttribute(JSP_CURRENT_HOTEL);
        
        //order
        request.deleteSessionAttribute(JSP_CURRENT_TOUR);
        request.deleteSessionAttribute(JSP_CURRENT_ORDER);
        request.deleteSessionAttribute(JSP_ORDER_LIST);
        
        //tour
        request.deleteSessionAttribute(JSP_TOUR_VALID_STATUS);
        request.deleteSessionAttribute(JSP_TOUR_INVALID_STATUS);
        request.deleteSessionAttribute(JSP_TOUR_VALID_DATE);
        request.deleteSessionAttribute(JSP_TOUR_INVALID_DATE);
        request.deleteSessionAttribute(JSP_TOUR_LIST);
        request.deleteSessionAttribute(JSP_PRICE_STEP);
        request.deleteSessionAttribute(JSP_DISCOUNT_STEP);
        request.deleteSessionAttribute(JSP_BOX_ALL_DEPART_DATE);
        request.deleteSessionAttribute(JSP_BOX_ALL_DAYS_COUNT);
        request.deleteSessionAttribute(JSP_BOX_ALL_PRICE);
        request.deleteSessionAttribute(JSP_BOX_ALL_COUNTRIES);
        request.deleteSessionAttribute(JSP_BOX_ALL_CITIES);
        request.deleteSessionAttribute(JSP_BOX_ALL_HOTELS);
        request.deleteSessionAttribute(JSP_IS_HIDDEN);
        request.deleteSessionAttribute(JSP_CURR_TOUR_TYPE);
        request.deleteSessionAttribute(JSP_CURR_TRANS_MODE);
        request.deleteSessionAttribute(JSP_CURR_ID_COUNTRY);
        request.deleteSessionAttribute(JSP_CURR_ID_CITY);
        request.deleteSessionAttribute(JSP_CURR_ID_HOTEL);
        request.deleteSessionAttribute(JSP_CURR_COUNTRY_TAGS);
        request.deleteSessionAttribute(JSP_CURR_CITY_TAGS);
        request.deleteSessionAttribute(JSP_CURR_PRICE_FROM);
        request.deleteSessionAttribute(JSP_CURR_PRICE_TO);
        request.deleteSessionAttribute(JSP_CURR_DEPART_DATE_FROM);
        request.deleteSessionAttribute(JSP_CURR_DEPART_DATE_TO);
        request.deleteSessionAttribute(JSP_CURR_DAYS_COUNT_FROM);
        request.deleteSessionAttribute(JSP_CURR_DAYS_COUNT_TO);
        request.deleteSessionAttribute(JSP_CURR_DISCOUNT_FROM);
        request.deleteSessionAttribute(JSP_CURR_HOTEL_STARS);    
        request.deleteSessionAttribute(JSP_HOTEL_TAG_LIST);
        
        //user
        request.deleteSessionAttribute(JSP_USER_LIST);
        request.deleteSessionAttribute(JSP_CURRENT_USER);
    }
    
}
