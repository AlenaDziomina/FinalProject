/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package by.epam.project.action;

import static by.epam.project.action.ActionCommand.PARAM_NAME_COUNTRY_LIST;
import static by.epam.project.action.ActionCommand.PARAM_NAME_PAGE;
import by.epam.project.controller.SessionRequestContent;
import by.epam.project.entity.City;
import by.epam.project.entity.Country;
import by.epam.project.manager.ConfigurationManager;
import java.util.List;

/**
 *
 * @author User
 */
public class GoEditCity implements ActionCommand {

    public GoEditCity() {
    }

    @Override
    public String execute(SessionRequestContent request) throws DaoLogicException {
        
        request.deleteSessionAttribute(PARAM_NAME_CITY_LIST);
        request.deleteSessionAttribute(PARAM_NAME_CITY_COUNT);
        City currCity = (City) request.getSessionAttribute(PARAM_NAME_CURRENT_CITY);
        
        List<Country> countryList = (List<Country>) request.getSessionAttribute(PARAM_NAME_COUNTRY_LIST);
        if (countryList == null || countryList.isEmpty()){
            new GoShowCountry().execute(request);
            countryList = (List<Country>) request.getSessionAttribute(PARAM_NAME_COUNTRY_LIST);
        }
        
        String page = ConfigurationManager.getProperty("path.page.editcity");
        request.setSessionAttribute(PARAM_NAME_PAGE, page);
        return page;
    }
    
}
