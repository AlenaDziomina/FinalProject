/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package by.epam.project.action;

import by.epam.project.controller.SessionRequestContent;
import by.epam.project.manager.ConfigurationManager;

/**
 *
 * @author User
 */
public class GoCreateNewDirection implements ActionCommand {

    @Override
    public String execute(SessionRequestContent request) throws DaoLogicException {
        
        request.deleteSessionAttribute(PARAM_NAME_CURRENT_DIRECTION);
        request.deleteSessionAttribute(PARAM_NAME_CURR_COUNTRY_TAGS);
        request.deleteSessionAttribute(PARAM_NAME_CURR_CITY_TAGS);
        request.deleteSessionAttribute(PARAM_NAME_CURR_HOTEL_TAGS);
        request.deleteSessionAttribute(PARAM_NAME_DIRECTION_LIST);
        request.deleteSessionAttribute(PARAM_NAME_DIRECTION_COUNT);
        
        new GoShowCountry().execute(request);
        new GoShowCity().execute(request);
        new GoShowHotel().execute(request);
        new GoShowTourType().execute(request);
        new GoShowTransMode().execute(request);
        
        request.setSessionAttribute(PARAM_NAME_COUNTRY_TAG_LIST, request.getSessionAttribute(PARAM_NAME_COUNTRY_LIST));
        request.setSessionAttribute(PARAM_NAME_CITY_TAG_LIST, request.getSessionAttribute(PARAM_NAME_CITY_LIST));
        
        
        
        String page = ConfigurationManager.getProperty("path.page.editdirection");
        request.setSessionAttribute(PARAM_NAME_PAGE, page);
        return page;
        
    }
    
}
