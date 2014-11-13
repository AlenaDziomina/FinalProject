/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package by.epam.project.action.city;

import static by.epam.project.action.JspParamNames.*;
import by.epam.project.action.SessionRequestContent;
import by.epam.project.action.hotel.HotelCommand;
import static by.epam.project.dao.entquery.CityQuery.DAO_CITY_STATUS;
import static by.epam.project.dao.entquery.CountryQuery.DAO_ID_COUNTRY;
import static by.epam.project.dao.entquery.HotelQuery.DAO_HOTEL_STATUS;
import static by.epam.project.dao.entquery.RoleQuery.DAO_ROLE_NAME;
import static by.epam.project.dao.entquery.UserQuery.DAO_USER_LOGIN;
import by.epam.project.dao.query.Criteria;
import by.epam.project.entity.City;
import by.epam.project.entity.ClientType;
import by.epam.project.entity.User;
import by.epam.project.exception.ServletLogicException;
import by.epam.project.exception.TechnicalException;
import by.epam.project.logic.CityLogic;
import by.epam.project.manager.ClientTypeManager;
import static by.epam.project.manager.ParamManager.getBoolParam;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;

/**
 *
 * @author User
 */
public class CityCommand {
    
    public void formCityList(SessionRequestContent request) throws ServletLogicException {
        Criteria criteria = new Criteria();
        criteria.addParam(DAO_ID_COUNTRY, request.getAttribute(JSP_ID_COUNTRY));
        User user = (User) request.getSessionAttribute(JSP_USER);
        if (user != null) {
            criteria.addParam(DAO_USER_LOGIN, user.getLogin());
            ClientType type = ClientTypeManager.clientTypeOf(user.getRole().getRoleName());
            criteria.addParam(DAO_ROLE_NAME, type);
        } else {
            criteria.addParam(DAO_ROLE_NAME, request.getSessionAttribute(JSP_ROLE_TYPE));
        }
        
        Integer cityStatus = getCityStatus(request);
        if (cityStatus != null) {
            criteria.addParam(DAO_CITY_STATUS, cityStatus);
        }
        
        Integer hotelStatus = new HotelCommand().getHotelStatus(request);
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
    
    public Integer getCityStatus(SessionRequestContent request) {
        Boolean validStatus = getBoolParam(request, JSP_CITY_VALID_STATUS);
        Boolean invalidStatus = getBoolParam(request, JSP_CITY_INVALID_STATUS);
        if (validStatus == null && invalidStatus == null) {
            validStatus = (Boolean) request.getSessionAttribute(JSP_CITY_VALID_STATUS);
            invalidStatus = (Boolean) request.getSessionAttribute(JSP_CITY_INVALID_STATUS);
            if (validStatus == null && invalidStatus == null) {
                validStatus = true;
                invalidStatus = false;
            }
        } else {
            if (validStatus == null) {
                validStatus = false;
            }
            if (invalidStatus == null) {
                invalidStatus = false;
            }
        }
        request.setSessionAttribute(JSP_CITY_VALID_STATUS, validStatus);
        request.setSessionAttribute(JSP_CITY_INVALID_STATUS, invalidStatus);
        
        Integer status = null;
        if (validStatus && ! invalidStatus) {
            status = 1;
        } else if ( ! validStatus && invalidStatus) {
            status = 0;
        }
        return status;
    }
    
    public void showSelectedCity(SessionRequestContent request) {
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
