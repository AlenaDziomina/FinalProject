/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package by.epam.project.action;

import static by.epam.project.action.ActionCommand.PARAM_NAME_COUNTRY_COUNT;
import static by.epam.project.action.ActionCommand.PARAM_NAME_COUNTRY_LIST;
import by.epam.project.controller.SessionRequestContent;
import static by.epam.project.dao.AbstractDao.PARAM_NAME_ID_COUNTRY;
import static by.epam.project.dao.AbstractDao.PARAM_NAME_LOGIN;
import static by.epam.project.dao.AbstractDao.PARAM_NAME_ROLE;
import by.epam.project.dao.DaoException;
import by.epam.project.dao.query.Criteria;
import by.epam.project.entity.Country;
import by.epam.project.logic.CountryLogic;
import by.epam.project.manager.MessageManager;
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
        
        
        
        return null;
    }
    
    
    
}
