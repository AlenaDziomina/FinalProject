/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package by.epam.project.action;

import by.epam.project.controller.SessionRequestContent;
import by.epam.project.dao.query.Criteria;

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
    static final String PARAM_NAME_CITY_COUNT = "cityCount";
    static final String PARAM_NAME_CURRENT_CITY = "currCity";
    static final String PARAM_NAME_HOTEL_LIST = "hotelList";
    static final String PARAM_NAME_HOTEL_COUNT = "hotelCount";
    static final String PARAM_NAME_CURRENT_HOTEL = "currHotel";
    static final String PARAM_NAME_SELECT_ID = "selectId";
    
    
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
}
