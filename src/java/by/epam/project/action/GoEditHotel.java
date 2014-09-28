/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package by.epam.project.action;

import static by.epam.project.action.ActionCommand.PARAM_NAME_CITY_LIST;
import static by.epam.project.action.ActionCommand.PARAM_NAME_COUNTRY_LIST;
import static by.epam.project.action.ActionCommand.PARAM_NAME_PAGE;
import by.epam.project.controller.SessionRequestContent;
import by.epam.project.entity.City;
import by.epam.project.entity.Country;
import by.epam.project.entity.Hotel;
import by.epam.project.manager.ConfigurationManager;
import java.util.List;

/**
 *
 * @author User
 */
public class GoEditHotel implements ActionCommand {

    public GoEditHotel() {
    }

    @Override
    public String execute(SessionRequestContent request) throws DaoLogicException {
        String page = ConfigurationManager.getProperty("path.page.edithotel");
        request.setSessionAttribute(PARAM_NAME_PAGE, page);
        
        List<Country> countryList = (List<Country>) request.getSessionAttribute(PARAM_NAME_COUNTRY_LIST);
        if (countryList == null || countryList.isEmpty()){
            new GoShowCountry().execute(request);
            countryList = (List<Country>) request.getSessionAttribute(PARAM_NAME_COUNTRY_LIST);
        }
        
        List<City> cityList = (List<City>) request.getSessionAttribute(PARAM_NAME_CITY_LIST);
        if (cityList == null || cityList.isEmpty()){
            new GoShowCity().execute(request);
            cityList = (List<City>) request.getSessionAttribute(PARAM_NAME_CITY_LIST);
        }
        
        Hotel hotel = (Hotel) request.getSessionAttribute(PARAM_NAME_CURRENT_HOTEL);
        Integer idCity = hotel.getIdCity();
        for (City c: cityList) {
            if (c.getIdCity().equals(idCity)) {
                Integer idCountry = c.getIdCountry();
                for (Country cntr: countryList) {
                    if (cntr.getIdCountry().equals(idCountry)){
                         request.setAttribute(PARAM_NAME_CURRENT_COUNTRY, cntr);
                    }
                }
            }
        }
        
        request.setAttribute(PARAM_NAME_CURR_CITY_LIST, cityList);
        
        request.deleteSessionAttribute(PARAM_NAME_HOTEL_LIST);
        request.deleteSessionAttribute(PARAM_NAME_HOTEL_COUNT);
        return page;
    }
    
}