/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package by.epam.project.action;

import by.epam.project.controller.SessionRequestContent;
import by.epam.project.dao.query.Criteria;
import java.util.Collection;
import java.util.HashSet;

/**
 *
 * @author User
 */
public interface ActionCommand {
    static final String PARAM_NAME_LENG = "leng";
    static final String PARAM_NAME_LOCALE = "locale";
    static final String PARAM_NAME_PAGE = "page";
    
    static final String PARAM_NAME_COUNTRY_LIST = "countryList";
    static final String PARAM_NAME_COUNTRY_COUNT = "countryCount";
    static final String PARAM_NAME_CURRENT_COUNTRY = "currCountry";
    
    static final String PARAM_NAME_CITY_LIST = "cityList";
    static final String PARAM_NAME_CURR_CITY_LIST = "currCityList";
    static final String PARAM_NAME_CITY_COUNT = "cityCount";
    static final String PARAM_NAME_CURRENT_CITY = "currCity";
    
    static final String PARAM_NAME_HOTEL_LIST = "hotelList";
    static final String PARAM_NAME_HOTEL_COUNT = "hotelCount";
    static final String PARAM_NAME_CURRENT_HOTEL = "currHotel";
    
    static final String PARAM_NAME_SELECT_ID = "selectId";
    static final String PARAM_NAME_DIRECTION_LIST = "directionList";
    static final String PARAM_NAME_DIRECTION_COUNT = "directionCount";
    static final String PARAM_NAME_CURRENT_DIRECTION = "currDirection";
    static final String PARAM_NAME_TOUR_TYPE_LIST = "tourTypeList";
    static final String PARAM_NAME_TOUR_TYPE_COUNT = "tourTypeCount";
    static final String PARAM_NAME_TRANS_MODE_LIST = "transModeList";
    static final String PARAM_NAME_TRANS_MODE_COUNT = "transModeCount";
    
    
    static final String PARAM_NAME_CURR_COUNTRY_TAGS = "currCountryTag";
    static final String PARAM_NAME_CURR_CITY_TAGS = "currCityTag";
    static final String PARAM_NAME_CURR_HOTEL_TAGS = "currHotelTag";
    static final String PARAM_NAME_CURR_TOUR_TYPE = "currTourType";
    static final String PARAM_NAME_CURR_TRANS_MODE = "currTransMode";
    static final String PARAM_NAME_CURR_ID_COUNTRY = "currIdCountry";
    static final String PARAM_NAME_CURR_ID_CITY = "currIdCity";
    static final String PARAM_NAME_CURR_ID_HOTEL = "currIdHotel";
    static final String PARAM_NAME_COUNTRY_TAG_LIST = "countryTagList";
    static final String PARAM_NAME_CITY_TAG_LIST = "cityTagList";
    static final String PARAM_NAME_HOTEL_TAG_LIST = "hotelTagList";
    
    
    
    String execute(SessionRequestContent request) throws DaoLogicException;
    
    public static void checkParam(SessionRequestContent request, Criteria criteria, String name){
        
        String param = (String) request.getParameter(name);
        if (param != null && !param.isEmpty()) {
            Integer currParam = Integer.decode(param);
            if (currParam > 0) {
                criteria.addParam(name, currParam);
            }
        }
    }
    
    public static void checkParam(SessionRequestContent request, Criteria criteria, String name1, String name2){
        
        String param = (String) request.getParameter(name1);
        if (param != null && !param.isEmpty()) {
            Integer currParam = Integer.decode(param);
            if (currParam > 0) {
                criteria.addParam(name2, currParam);
            }
        }
    }
    
    public static void checkArrParam(SessionRequestContent request, Criteria criteria, String name1, String name2){
        String[] arr = (String[]) request.getAllParameters(name2);
        Collection<Integer> set = new HashSet();
        if (arr != null && arr.length > 0) {
            for (String param : arr) {
                if (param != null && !param.isEmpty()) {
                    Integer currParam = Integer.decode(param);
                    if (currParam > 0) {
                        set.add(currParam);
                    }
                }
            }
        }
        criteria.addParam(name1, set);
    }
}
