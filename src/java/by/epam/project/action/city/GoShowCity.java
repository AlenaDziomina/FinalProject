/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package by.epam.project.action.city;

import by.epam.project.action.ActionCommand;
import static by.epam.project.action.JspParamNames.*;
import by.epam.project.action.SessionRequestContent;
import static by.epam.project.action.city.ShowCity.showSelectedCity;
import static by.epam.project.action.hotel.GoShowHotel.getHotelStatus;
import static by.epam.project.dao.entquery.CityQuery.DAO_CITY_STATUS;
import static by.epam.project.dao.entquery.HotelQuery.DAO_HOTEL_STATUS;
import static by.epam.project.dao.entquery.RoleQuery.DAO_ROLE_NAME;
import static by.epam.project.dao.entquery.UserQuery.DAO_USER_LOGIN;
import by.epam.project.dao.query.Criteria;
import by.epam.project.entity.City;
import by.epam.project.exception.ServletLogicException;
import by.epam.project.exception.TechnicalException;
import by.epam.project.logic.CityLogic;
import by.epam.project.manager.ConfigurationManager;
import static by.epam.project.manager.ParamManager.getBoolParam;
import java.util.List;

/**
 *
 * @author User
 */
public class GoShowCity implements ActionCommand {

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
    
    public static void formCityList(SessionRequestContent request) throws ServletLogicException {
        Criteria criteria = new Criteria();
        criteria.addParam(DAO_USER_LOGIN, request.getSessionAttribute(JSP_USER_LOGIN));
        criteria.addParam(DAO_ROLE_NAME, request.getSessionAttribute(JSP_ROLE_TYPE));
        
        Integer cityStatus = getCityStatus(request);
        if (cityStatus != null) {
            criteria.addParam(DAO_CITY_STATUS, cityStatus);
        }
        
        Integer hotelStatus = getHotelStatus(request);
        if (hotelStatus != null) {
            criteria.addParam(DAO_HOTEL_STATUS, hotelStatus);
        }
        
        try {
            List<City> cities = new CityLogic().doGetEntity(criteria);
            request.setSessionAttribute(JSP_CITY_LIST, cities);
        } catch (TechnicalException ex) {
            throw new ServletLogicException(ex.getMessage(), ex);
        }
    }
    
    public static Integer getCityStatus(SessionRequestContent request) {
        Boolean validStatus = getBoolParam(request, JSP_CITY_VALID_STATUS);
        Boolean invalidStatus = getBoolParam(request, JSP_CITY_INVALID_STATUS);
        if (validStatus == null && invalidStatus == null) {
            validStatus = true;
            invalidStatus = false;
        } else {
            if (validStatus == null) {
                validStatus = false;
            }
            if (invalidStatus == null) {
                invalidStatus = false;
            }
        }
        request.setAttribute(JSP_CITY_VALID_STATUS, validStatus);
        request.setAttribute(JSP_CITY_INVALID_STATUS, invalidStatus);
        
        Integer status = null;
        if (validStatus && ! invalidStatus) {
            status = 1;
        } else if ( ! validStatus && invalidStatus) {
            status = 0;
        }
        return status;
    }
    
    public static void resaveParamsShowCity(SessionRequestContent request) {
        String validCityStatus = request.getParameter(JSP_CITY_VALID_STATUS);
        if(validCityStatus != null) {
            request.setAttribute(JSP_CITY_VALID_STATUS, validCityStatus);
        }
        
        String invalidCityStatus = request.getParameter(JSP_CITY_INVALID_STATUS);
        if(invalidCityStatus != null) {
            request.setAttribute(JSP_CITY_INVALID_STATUS, invalidCityStatus);
        }
        
        String validHotelStatus = request.getParameter(JSP_HOTEL_VALID_STATUS);
        if(validHotelStatus != null) {
            request.setAttribute(JSP_HOTEL_VALID_STATUS, validHotelStatus);
        }
        
        String invalidHotelStatus = request.getParameter(JSP_HOTEL_INVALID_STATUS);
        if(invalidHotelStatus != null) {
            request.setAttribute(JSP_HOTEL_INVALID_STATUS, invalidHotelStatus);
        }
    }
    
    public static void cleanSessionShowCity(SessionRequestContent request) {
        request.deleteSessionAttribute(JSP_CURRENT_COUNTRY);
        request.deleteSessionAttribute(JSP_CURRENT_HOTEL);
        request.deleteSessionAttribute(JSP_CURRENT_DIRECTION);
        request.deleteSessionAttribute(JSP_CURRENT_TOUR);
        
        request.deleteSessionAttribute(JSP_TOUR_TYPE_LIST);
        request.deleteSessionAttribute(JSP_TRANS_MODE_LIST);
        
        request.deleteSessionAttribute(JSP_COUNTRY_TAG_LIST);
        request.deleteSessionAttribute(JSP_CITY_TAG_LIST);
        request.deleteSessionAttribute(JSP_HOTEL_TAG_LIST);
        
        request.deleteSessionAttribute(JSP_TOUR_LIST);
        request.deleteSessionAttribute(JSP_PRICE_STEP);
        request.deleteSessionAttribute(JSP_DISCOUNT_STEP);
        
        request.setSessionAttribute(JSP_CURR_CITY_LIST, null);
    }
    
}
