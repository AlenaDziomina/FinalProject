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
    
    public static void checkParam(SessionRequestContent request, Criteria criteria, String reqName, String critName){
        
        String param = (String) request.getParameter(reqName);
        if (param != null && !param.isEmpty()) {
            Integer currParam = Integer.decode(param);
            if (currParam > 0) {
                criteria.addParam(critName, currParam);
            }
        }
    }
    
    public static void checkArrParam(SessionRequestContent request, Criteria criteria, String reqName, String critName){
        String[] arr = (String[]) request.getAllParameters(reqName);
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
        criteria.addParam(critName, set);
    }
}
