/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package by.epam.project.action;

import by.epam.project.controller.SessionRequestContent;
import by.epam.project.entity.Country;
import by.epam.project.entity.TourType;
import by.epam.project.entity.TransportationMode;
import by.epam.project.manager.ConfigurationManager;
import java.util.List;

/**
 *
 * @author User
 */
public class GoCreateNewDirection implements ActionCommand {

    @Override
    public String execute(SessionRequestContent request) throws DaoLogicException {
        
        request.deleteSessionAttribute(PARAM_NAME_CURRENT_DIRECTION);
        request.deleteSessionAttribute(PARAM_NAME_DIRECTION_LIST);
        request.deleteSessionAttribute(PARAM_NAME_DIRECTION_COUNT);
        
        List<Country> countryList = (List<Country>) request.getSessionAttribute(PARAM_NAME_COUNTRY_LIST);
        if (countryList == null || countryList.isEmpty()){
            new GoShowCountry().execute(request);
            countryList = (List<Country>) request.getSessionAttribute(PARAM_NAME_COUNTRY_LIST);
        }
        
//        List<City> cityList = (List<City>) request.getSessionAttribute(PARAM_NAME_CITY_LIST);
//        if (cityList == null || cityList.isEmpty()){
//            new GoShowCity().execute(request);
//            cityList = (List<City>) request.getSessionAttribute(PARAM_NAME_CITY_LIST);
//        }
//        
//        List<Hotel> hotelList = (List<Hotel>) request.getSessionAttribute(PARAM_NAME_HOTEL_LIST);
//        if (cityList == null || cityList.isEmpty()){
//            new GoShowHotel().execute(request);
//            hotelList = (List<Hotel>) request.getSessionAttribute(PARAM_NAME_CITY_LIST);
//        }
        
        List<TourType> tourTypeList = (List<TourType>) request.getSessionAttribute(PARAM_NAME_TOUR_TYPE_LIST);
        if (tourTypeList == null || tourTypeList.isEmpty()){
            new GoShowTourType().execute(request);
            tourTypeList = (List<TourType>) request.getSessionAttribute(PARAM_NAME_TOUR_TYPE_LIST);
        }
        
        List<TransportationMode> transModeList = (List<TransportationMode>) request.getSessionAttribute(PARAM_NAME_TRANS_MODE_LIST);
        if (transModeList == null || transModeList.isEmpty()){
            new GoShowTransMode().execute(request);
            transModeList = (List<TransportationMode>) request.getSessionAttribute(PARAM_NAME_TRANS_MODE_LIST);
        }
        
        String page = ConfigurationManager.getProperty("path.page.editdirection");
        request.setSessionAttribute(PARAM_NAME_PAGE, page);
        return page;
        
    }
    
}
