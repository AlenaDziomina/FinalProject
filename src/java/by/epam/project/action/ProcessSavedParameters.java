/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package by.epam.project.action;

import static by.epam.project.action.JspParamNames.JSP_CURRENT_DIRECTION;
import static by.epam.project.action.JspParamNames.JSP_CURR_CITY_TAGS;
import static by.epam.project.action.JspParamNames.JSP_CURR_COUNTRY_TAGS;
import static by.epam.project.action.JspParamNames.JSP_CURR_HOTEL_TAGS;
import static by.epam.project.action.JspParamNames.JSP_CURR_ID_CITY;
import static by.epam.project.action.JspParamNames.JSP_CURR_ID_COUNTRY;
import static by.epam.project.action.JspParamNames.JSP_CURR_ID_HOTEL;
import static by.epam.project.action.JspParamNames.JSP_CURR_TOUR_TYPE;
import static by.epam.project.action.JspParamNames.JSP_CURR_TRANS_MODE;
import static by.epam.project.action.JspParamNames.JSP_DESCRIPTION_TEXT;
import static by.epam.project.action.JspParamNames.JSP_DIRECTION_NAME;
import static by.epam.project.action.JspParamNames.JSP_DIRECTION_PICTURE;
import static by.epam.project.action.JspParamNames.JSP_DIRECTION_TEXT;
import static by.epam.project.action.JspParamNames.JSP_HOTEL_TAG_LIST;
import static by.epam.project.action.JspParamNames.JSP_ROLE_TYPE;
import static by.epam.project.action.JspParamNames.JSP_USER_LOGIN;
import by.epam.project.controller.SessionRequestContent;
import static by.epam.project.dao.entquery.HotelQuery.DAO_ID_HOTEL;
import static by.epam.project.dao.entquery.RoleQuery.DAO_ROLE_NAME;
import static by.epam.project.dao.entquery.UserQuery.DAO_USER_LOGIN;
import by.epam.project.dao.query.Criteria;
import by.epam.project.entity.Description;
import by.epam.project.entity.Direction;
import by.epam.project.entity.Hotel;
import by.epam.project.exception.DaoException;
import by.epam.project.exception.DaoUserLogicException;
import by.epam.project.logic.HotelLogic;
import by.epam.project.manager.MessageManager;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author User
 */
public class ProcessSavedParameters implements ActionCommand{

    @Override
    public String execute(SessionRequestContent request) throws DaoUserLogicException {
        
        String[] currCoutryTags = request.getAllParameters(JSP_CURR_COUNTRY_TAGS);
        request.setAttribute(JSP_CURR_COUNTRY_TAGS, currCoutryTags);
        
        String[] currCityTags = request.getAllParameters(JSP_CURR_CITY_TAGS);
        request.setAttribute(JSP_CURR_CITY_TAGS, currCityTags);
        
        String currTourType = request.getParameter(JSP_CURR_TOUR_TYPE);
        request.setAttribute(JSP_CURR_TOUR_TYPE, currTourType);
        
        String currTransMode = request.getParameter(JSP_CURR_TRANS_MODE);
        request.setAttribute(JSP_CURR_TRANS_MODE, currTransMode);
        
        String currCountry = request.getParameter(JSP_CURR_ID_COUNTRY);
        request.setAttribute(JSP_CURR_ID_COUNTRY, currCountry);
 
        String currCity = request.getParameter(JSP_CURR_ID_CITY);
        request.setAttribute(JSP_CURR_ID_CITY, currCity);
        
        String currHotel = request.getParameter(JSP_CURR_ID_HOTEL);
        request.setAttribute(JSP_CURR_ID_HOTEL, currHotel);
        
        createCurrHotelTag(request);
        
        createCurrDirect(request);
        
        
        return null;
    }

    private void createCurrHotelTag(SessionRequestContent request) throws DaoUserLogicException {
        String[] currHotelTags = request.getAllParameters(JSP_CURR_HOTEL_TAGS);
        if (currHotelTags != null) {
            List<Hotel> hotelTagList = new ArrayList();
            for (String tag : currHotelTags) {
                Integer idHotel = Integer.decode(tag);
                if (idHotel > 0) {
                    Criteria criteria = new Criteria();
                    criteria.addParam(DAO_USER_LOGIN, request.getSessionAttribute(JSP_USER_LOGIN));
                    criteria.addParam(DAO_ROLE_NAME, request.getSessionAttribute(JSP_ROLE_TYPE));
                    criteria.addParam(DAO_ID_HOTEL, idHotel);
                    try {
                        List<Hotel> hotels = HotelLogic.getHotels(criteria);
                        hotelTagList.addAll(hotels);
                    } catch (DaoException ex) {
                        throw new DaoUserLogicException(MessageManager.getProperty("message.daoerror"));
                    }
                }
            }
            request.setAttribute(JSP_HOTEL_TAG_LIST, hotelTagList);
        }
    }

    private void createCurrDirect(SessionRequestContent request) {
        Direction currDir = (Direction) request.getSessionAttribute(JSP_CURRENT_DIRECTION);
        if (currDir == null) {
            currDir = new Direction();
            currDir.setDescription(new Description());
        }
        
        currDir.setName(request.getParameter(JSP_DIRECTION_NAME));
        currDir.setPicture(request.getParameter(JSP_DIRECTION_PICTURE));
        currDir.setText(request.getParameter(JSP_DIRECTION_TEXT));
        currDir.getDescription().setText(request.getParameter(JSP_DESCRIPTION_TEXT));
        
        request.setSessionAttribute(JSP_CURRENT_DIRECTION, currDir);
    }
    
    
    
}
