/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package by.epam.project.action;

import static by.epam.project.controller.JspParamNames.JSP_ROLE_TYPE;
import static by.epam.project.controller.JspParamNames.JSP_USER_LOGIN;
import by.epam.project.controller.SessionRequestContent;
import static by.epam.project.dao.AbstractDao.PARAM_NAME_ID_HOTEL;
import static by.epam.project.dao.AbstractDao.PARAM_NAME_NAME_DIRECTION;
import static by.epam.project.dao.AbstractDao.PARAM_NAME_PICTURE_DIRECTION;
import static by.epam.project.dao.AbstractDao.PARAM_NAME_TEXT_DESCRIPTION;
import static by.epam.project.dao.AbstractDao.PARAM_NAME_TEXT_DIRECTION;
import by.epam.project.exception.DaoException;
import static by.epam.project.dao.entquery.RoleQuery.DAO_ROLE_NAME;
import static by.epam.project.dao.entquery.UserQuery.DAO_USER_LOGIN;
import by.epam.project.dao.query.Criteria;
import by.epam.project.entity.Description;
import by.epam.project.entity.Direction;
import by.epam.project.entity.Hotel;
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
    public String execute(SessionRequestContent request) throws DaoLogicException {
        
        String[] currCoutryTags = request.getAllParameters(PARAM_NAME_CURR_COUNTRY_TAGS);
        request.setAttribute(PARAM_NAME_CURR_COUNTRY_TAGS, currCoutryTags);
        
        String[] currCityTags = request.getAllParameters(PARAM_NAME_CURR_CITY_TAGS);
        request.setAttribute(PARAM_NAME_CURR_CITY_TAGS, currCityTags);
        
        String currTourType = request.getParameter(PARAM_NAME_CURR_TOUR_TYPE);
        request.setAttribute(PARAM_NAME_CURR_TOUR_TYPE, currTourType);
        
        String currTransMode = request.getParameter(PARAM_NAME_CURR_TRANS_MODE);
        request.setAttribute(PARAM_NAME_CURR_TRANS_MODE, currTransMode);
        
        String currCountry = request.getParameter(PARAM_NAME_CURR_ID_COUNTRY);
        request.setAttribute(PARAM_NAME_CURR_ID_COUNTRY, currCountry);
 
        String currCity = request.getParameter(PARAM_NAME_CURR_ID_CITY);
        request.setAttribute(PARAM_NAME_CURR_ID_CITY, currCity);
        
        String currHotel = request.getParameter(PARAM_NAME_CURR_ID_HOTEL);
        request.setAttribute(PARAM_NAME_CURR_ID_HOTEL, currHotel);
        
        createCurrHotelTag(request);
        
        createCurrDirect(request);
        
        
        return null;
    }

    private void createCurrHotelTag(SessionRequestContent request) throws DaoLogicException {
        String[] currHotelTags = request.getAllParameters(PARAM_NAME_CURR_HOTEL_TAGS);
        if (currHotelTags != null) {
            List<Hotel> hotelTagList = new ArrayList();
            for (String tag : currHotelTags) {
                Integer idHotel = Integer.decode(tag);
                if (idHotel > 0) {
                    Criteria criteria = new Criteria();
                    criteria.addParam(DAO_USER_LOGIN, request.getSessionAttribute(JSP_USER_LOGIN));
                    criteria.addParam(DAO_ROLE_NAME, request.getSessionAttribute(JSP_ROLE_TYPE));
                    criteria.addParam(PARAM_NAME_ID_HOTEL, idHotel);
                    try {
                        List<Hotel> hotels = HotelLogic.getHotels(criteria);
                        hotelTagList.addAll(hotels);
                    } catch (DaoException ex) {
                        throw new DaoLogicException(MessageManager.getProperty("message.daoerror"));
                    }
                }
            }
            request.setAttribute(PARAM_NAME_HOTEL_TAG_LIST, hotelTagList);
        }
    }

    private void createCurrDirect(SessionRequestContent request) {
        Direction currDir = (Direction) request.getSessionAttribute(PARAM_NAME_CURRENT_DIRECTION);
        if (currDir == null) {
            currDir = new Direction();
            currDir.setDescription(new Description());
        }
        
        currDir.setName(request.getParameter(PARAM_NAME_NAME_DIRECTION));
        currDir.setPicture(request.getParameter(PARAM_NAME_PICTURE_DIRECTION));
        currDir.setText(request.getParameter(PARAM_NAME_TEXT_DIRECTION));
        currDir.getDescription().setText(request.getParameter(PARAM_NAME_TEXT_DESCRIPTION));
        
        request.setSessionAttribute(PARAM_NAME_CURRENT_DIRECTION, currDir);
    }
    
    
    
}
