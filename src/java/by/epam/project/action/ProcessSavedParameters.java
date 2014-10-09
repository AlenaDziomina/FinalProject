/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package by.epam.project.action;

import static by.epam.project.action.JspParamNames.JSP_CITY_NAME;
import static by.epam.project.action.JspParamNames.JSP_CITY_PICTURE;
import static by.epam.project.action.JspParamNames.JSP_COUNTRY_NAME;
import static by.epam.project.action.JspParamNames.JSP_COUNTRY_PICTURE;
import static by.epam.project.action.JspParamNames.JSP_CURRENT_CITY;
import static by.epam.project.action.JspParamNames.JSP_CURRENT_COUNTRY;
import static by.epam.project.action.JspParamNames.JSP_CURRENT_DIRECTION;
import static by.epam.project.action.JspParamNames.JSP_CURRENT_HOTEL;
import static by.epam.project.action.JspParamNames.JSP_CURRENT_TOUR;
import static by.epam.project.action.JspParamNames.JSP_CURR_ARRIVAL_DATE;
import static by.epam.project.action.JspParamNames.JSP_CURR_CITY_TAGS;
import static by.epam.project.action.JspParamNames.JSP_CURR_COUNTRY_TAGS;
import static by.epam.project.action.JspParamNames.JSP_CURR_DEPART_DATE;
import static by.epam.project.action.JspParamNames.JSP_CURR_HOTEL_STARS;
import static by.epam.project.action.JspParamNames.JSP_CURR_HOTEL_TAGS;
import static by.epam.project.action.JspParamNames.JSP_CURR_ID_CITY;
import static by.epam.project.action.JspParamNames.JSP_CURR_ID_COUNTRY;
import static by.epam.project.action.JspParamNames.JSP_CURR_ID_DIRECTION;
import static by.epam.project.action.JspParamNames.JSP_CURR_ID_HOTEL;
import static by.epam.project.action.JspParamNames.JSP_CURR_ID_TOUR;
import static by.epam.project.action.JspParamNames.JSP_CURR_TOUR_TYPE;
import static by.epam.project.action.JspParamNames.JSP_CURR_TRANS_MODE;
import static by.epam.project.action.JspParamNames.JSP_DESCRIPTION_TEXT;
import static by.epam.project.action.JspParamNames.JSP_DIRECTION_NAME;
import static by.epam.project.action.JspParamNames.JSP_DIRECTION_PICTURE;
import static by.epam.project.action.JspParamNames.JSP_DIRECTION_TEXT;
import static by.epam.project.action.JspParamNames.JSP_FREE_SEATS;
import static by.epam.project.action.JspParamNames.JSP_HOTEL_NAME;
import static by.epam.project.action.JspParamNames.JSP_HOTEL_PICTURE;
import static by.epam.project.action.JspParamNames.JSP_HOTEL_TAG_LIST;
import static by.epam.project.action.JspParamNames.JSP_ROLE_TYPE;
import static by.epam.project.action.JspParamNames.JSP_TOTAL_SEATS;
import static by.epam.project.action.JspParamNames.JSP_TOUR_DISCOUNT;
import static by.epam.project.action.JspParamNames.JSP_TOUR_PRICE;
import static by.epam.project.action.JspParamNames.JSP_USER_LOGIN;
import by.epam.project.controller.SessionRequestContent;
import static by.epam.project.dao.entquery.HotelQuery.DAO_ID_HOTEL;
import static by.epam.project.dao.entquery.RoleQuery.DAO_ROLE_NAME;
import static by.epam.project.dao.entquery.UserQuery.DAO_USER_LOGIN;
import by.epam.project.dao.query.Criteria;
import by.epam.project.entity.City;
import by.epam.project.entity.Country;
import by.epam.project.entity.Description;
import by.epam.project.entity.Direction;
import by.epam.project.entity.Hotel;
import by.epam.project.entity.Tour;
import by.epam.project.exception.DaoException;
import by.epam.project.exception.DaoUserLogicException;
import by.epam.project.logic.HotelLogic;
import by.epam.project.manager.MessageManager;
import static by.epam.project.manager.ParamManager.getFltParam;
import static by.epam.project.manager.ParamManager.getIntParam;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author User
 */
public class ProcessSavedParameters implements ActionCommand{

    @Override
    public String execute(SessionRequestContent request) throws DaoUserLogicException {
        
        String currCountry = request.getParameter(JSP_CURR_ID_COUNTRY);
        if (currCountry != null) {
            request.setAttribute(JSP_CURR_ID_COUNTRY, currCountry);
        }
 
        String currCity = request.getParameter(JSP_CURR_ID_CITY);
        if (currCity != null) {
            request.setAttribute(JSP_CURR_ID_CITY, currCity);
        }
        
        String currHotel = request.getParameter(JSP_CURR_ID_HOTEL);
        if (currHotel != null) {
            request.setAttribute(JSP_CURR_ID_HOTEL, currHotel);
        }
        
        String currDirection = request.getParameter(JSP_CURR_ID_DIRECTION);
        if (currDirection != null) {
            request.setAttribute(JSP_CURR_ID_DIRECTION, currDirection);
        }
        
        String currTour = request.getParameter(JSP_CURR_ID_TOUR);
        if (currTour != null) {
            request.setAttribute(JSP_CURR_ID_TOUR, currTour);
        }
        
        String currDepartDate = request.getParameter("departDate");
        if (currDepartDate != null) {
            request.setAttribute(JSP_CURR_DEPART_DATE, currDepartDate);
        }
        
        String currArrivalDate = request.getParameter("arrivalDate");
        if (currDepartDate != null) {
            request.setAttribute(JSP_CURR_ARRIVAL_DATE, currArrivalDate);
        }
        
        String[] currCoutryTags = request.getAllParameters(JSP_CURR_COUNTRY_TAGS);
        if (currCoutryTags != null) {
            request.setAttribute(JSP_CURR_COUNTRY_TAGS, currCoutryTags);
        }
        
        String[] currCityTags = request.getAllParameters(JSP_CURR_CITY_TAGS);
        if (currCityTags != null) {
            request.setAttribute(JSP_CURR_CITY_TAGS, currCityTags);
        }
        
        String currTourType = request.getParameter(JSP_CURR_TOUR_TYPE);
        if (currTourType != null) {
            request.setAttribute(JSP_CURR_TOUR_TYPE, currTourType);
        }
        
        String currTransMode = request.getParameter(JSP_CURR_TRANS_MODE);
        if (currTransMode != null) {
            request.setAttribute(JSP_CURR_TRANS_MODE, currTransMode);
        }

        createCurrHotelTag(request);
        
        createCurrDirect(request);
        
        createCurrTour(request);
        
        createCurrCity(request);
        
        createCurrCountry(request);
        
        createCurrHotel(request);
        
        return null;
    }
    
    private void createCurrTour(SessionRequestContent request) {
        Tour currTour = (Tour) request.getSessionAttribute(JSP_CURRENT_TOUR);
        if(currTour == null) {
            currTour = new Tour();
        }
        
        currTour.setPrice(getFltParam(request, JSP_TOUR_PRICE));
        currTour.setDiscount(getIntParam(request, JSP_TOUR_DISCOUNT));
        currTour.setTotalSeats(getIntParam(request, JSP_TOTAL_SEATS));
        currTour.setFreeSeats(getIntParam(request, JSP_FREE_SEATS));
        
        request.setSessionAttribute(JSP_CURRENT_TOUR, currTour);
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
    
    private void createCurrCity(SessionRequestContent request) {
        City currCity = (City) request.getSessionAttribute(JSP_CURRENT_CITY);
        if (currCity == null) {
            currCity = new City();
            currCity.setDescription(new Description());
        }
        currCity.setName(request.getParameter(JSP_CITY_NAME));
        currCity.setPicture(request.getParameter(JSP_CITY_PICTURE));
        currCity.getDescription().setText(request.getParameter(JSP_DESCRIPTION_TEXT));
        
        request.setSessionAttribute(JSP_CURRENT_CITY, currCity);
    }
    
    private void createCurrCountry(SessionRequestContent request) {
        Country currCountry = (Country) request.getSessionAttribute(JSP_CURRENT_COUNTRY);
        if (currCountry == null) {
            currCountry = new Country();
            currCountry.setDescription(new Description());
        }
        currCountry.setName(request.getParameter(JSP_COUNTRY_NAME));
        currCountry.setPicture(request.getParameter(JSP_COUNTRY_PICTURE));
        currCountry.getDescription().setText(request.getParameter(JSP_DESCRIPTION_TEXT));
        
        request.setSessionAttribute(JSP_CURRENT_COUNTRY, currCountry);
    }
    
    private void createCurrHotel(SessionRequestContent request) {
        Hotel currHotel = (Hotel) request.getSessionAttribute(JSP_CURRENT_HOTEL);
        if (currHotel == null) {
            currHotel = new Hotel();
            currHotel.setDescription(new Description());
        }
        currHotel.setName(request.getParameter(JSP_HOTEL_NAME));
        currHotel.setPicture(request.getParameter(JSP_HOTEL_PICTURE));
        currHotel.getDescription().setText(request.getParameter(JSP_DESCRIPTION_TEXT));
        String stars = request.getParameter(JSP_CURR_HOTEL_STARS);
        if (stars != null) {
            currHotel.setStars(Integer.decode(stars));
        }
        
        request.setSessionAttribute(JSP_CURRENT_HOTEL, currHotel);
    }
}
