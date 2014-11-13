/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package by.epam.project.action.hotel;

import static by.epam.project.action.JspParamNames.*;
import by.epam.project.action.SessionRequestContent;
import static by.epam.project.dao.entquery.CityQuery.DAO_ID_CITY;
import static by.epam.project.dao.entquery.HotelQuery.DAO_HOTEL_STATUS;
import static by.epam.project.dao.entquery.RoleQuery.DAO_ROLE_NAME;
import static by.epam.project.dao.entquery.UserQuery.DAO_USER_LOGIN;
import by.epam.project.dao.query.Criteria;
import by.epam.project.entity.City;
import by.epam.project.entity.ClientType;
import by.epam.project.entity.Description;
import by.epam.project.entity.Hotel;
import by.epam.project.entity.User;
import by.epam.project.exception.ServletLogicException;
import by.epam.project.exception.TechnicalException;
import by.epam.project.logic.HotelLogic;
import by.epam.project.manager.ClientTypeManager;
import by.epam.project.manager.ParamManager;
import static by.epam.project.manager.ParamManager.getBoolParam;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;

/**
 *
 * @author User
 */
public class HotelCommand {
    
    public void formHotelList(SessionRequestContent request) throws ServletLogicException {
        Criteria criteria = new Criteria();
        criteria.addParam(DAO_ID_CITY, request.getAttribute(JSP_ID_CITY));
        User user = (User) request.getSessionAttribute(JSP_USER);
        if (user != null) {
            criteria.addParam(DAO_USER_LOGIN, user.getLogin());
            ClientType type = ClientTypeManager.clientTypeOf(user.getRole().getRoleName());
            criteria.addParam(DAO_ROLE_NAME, type);
        } else {
            criteria.addParam(DAO_ROLE_NAME, request.getSessionAttribute(JSP_ROLE_TYPE));
        }
        
        Integer hotelStatus = getHotelStatus(request);
        if (hotelStatus != null) {
            criteria.addParam(DAO_HOTEL_STATUS, hotelStatus);
        }
        
        try {
            List<Hotel> hotels = new HotelLogic().doGetEntity(criteria);
            request.setSessionAttribute(JSP_HOTEL_LIST, hotels);
        } catch (TechnicalException ex) {
            throw new ServletLogicException(ex.getMessage(), ex);
        }
    }
    
    public Integer getHotelStatus(SessionRequestContent request) {
        Boolean validStatus = getBoolParam(request, JSP_HOTEL_VALID_STATUS);
        Boolean invalidStatus = getBoolParam(request, JSP_HOTEL_INVALID_STATUS);
        if (validStatus == null && invalidStatus == null) {
            validStatus = (Boolean) request.getSessionAttribute(JSP_HOTEL_VALID_STATUS);
            invalidStatus = (Boolean) request.getSessionAttribute(JSP_HOTEL_INVALID_STATUS);
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
        request.setSessionAttribute(JSP_HOTEL_VALID_STATUS, validStatus);
        request.setSessionAttribute(JSP_HOTEL_INVALID_STATUS, invalidStatus);
        
        Integer status = null;
        if (validStatus && ! invalidStatus) {
            status = 1;
        } else if ( ! validStatus && invalidStatus) {
            status = 0;
        }
        return status;
    }
    
    public void resaveParamsSaveHotel(SessionRequestContent request) {
        
        String currCountry = request.getParameter(JSP_CURR_ID_COUNTRY);
        if (currCountry != null && !currCountry.isEmpty()) {
            request.setAttribute(JSP_CURR_ID_COUNTRY, currCountry);
        }
        
        String currCity = request.getParameter(JSP_CURR_ID_CITY);
        if (currCity != null && !currCity.isEmpty()) {
            request.setAttribute(JSP_CURR_ID_CITY, currCity);
        }
        createCurrHotel(request);
    }
    
    private void createCurrHotel(SessionRequestContent request) {
        Hotel currHotel = (Hotel) request.getSessionAttribute(JSP_CURRENT_HOTEL);
        if (currHotel == null) {
            currHotel = new Hotel();
            currHotel.setCity(new City());
            currHotel.setDescription(new Description());
        }
        Integer idCity = ParamManager.getIntParam(request, JSP_CURR_ID_CITY);
        currHotel.getCity().setIdCity(idCity);
        currHotel.setName(request.getParameter(JSP_HOTEL_NAME));
        currHotel.setPicture(request.getParameter(JSP_HOTEL_PICTURE));
        currHotel.getDescription().setText(request.getParameter(JSP_DESCRIPTION_TEXT));
        Integer stars = ParamManager.getIntParam(request, JSP_CURR_HOTEL_STARS);
        currHotel.setStars(stars);
        request.setSessionAttribute(JSP_CURRENT_HOTEL, currHotel);
    }
    
    public void showSelectedHotel(SessionRequestContent request) {
        String selected = request.getParameter(JSP_SELECT_ID);
        Hotel currHotel = null;
        if (selected != null) {
            Integer idHotel = Integer.decode(selected); 
            if (idHotel != null) {
                List<Hotel> list = (List<Hotel>) request.getSessionAttribute(JSP_HOTEL_LIST);
                Iterator<Hotel> it = list.iterator();
                while (it.hasNext() && currHotel == null) {
                    Hotel hotel = it.next();
                    if (Objects.equals(hotel.getIdHotel(), idHotel)) {
                        currHotel = hotel;
                        request.setAttribute(JSP_CURR_ID_HOTEL, idHotel);
                    }
                }
            }
        }
        request.setSessionAttribute(JSP_CURRENT_HOTEL, currHotel);
    }
}
