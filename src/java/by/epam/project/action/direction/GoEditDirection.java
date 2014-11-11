/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package by.epam.project.action.direction;

import by.epam.project.action.ActionCommand;
import static by.epam.project.action.JspParamNames.JSP_CITY_LIST;
import static by.epam.project.action.JspParamNames.JSP_CITY_TAG_LIST;
import static by.epam.project.action.JspParamNames.JSP_COUNTRY_LIST;
import static by.epam.project.action.JspParamNames.JSP_COUNTRY_TAG_LIST;
import static by.epam.project.action.JspParamNames.JSP_CURRENT_DIRECTION;
import static by.epam.project.action.JspParamNames.JSP_CURR_CITY_TAGS;
import static by.epam.project.action.JspParamNames.JSP_CURR_COUNTRY_TAGS;
import static by.epam.project.action.JspParamNames.JSP_CURR_ID_CITY;
import static by.epam.project.action.JspParamNames.JSP_CURR_ID_COUNTRY;
import static by.epam.project.action.JspParamNames.JSP_HOTEL_TAG_LIST;
import static by.epam.project.action.JspParamNames.JSP_PAGE;
import static by.epam.project.action.city.GoShowCity.formCityList;
import static by.epam.project.action.country.GoShowCountry.formCountryList;
import static by.epam.project.action.direction.GoShowDirections.formTourTypeList;
import static by.epam.project.action.direction.GoShowDirections.formTransModeList;
import static by.epam.project.action.hotel.GoShowHotel.formHotelList;
import by.epam.project.action.SessionRequestContent;
import by.epam.project.entity.Direction;
import by.epam.project.entity.Hotel;
import by.epam.project.exception.ServletLogicException;
import by.epam.project.manager.ConfigurationManager;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author User
 */
public class GoEditDirection implements ActionCommand {

    @Override
    public String execute(SessionRequestContent request) throws ServletLogicException {
        String page = ConfigurationManager.getProperty("path.page.editdirection");
        request.setSessionAttribute(JSP_PAGE, page);
        
        formCountryList(request);
        formCityList(request);
        formHotelList(request);
        formTourTypeList(request);
        formTransModeList(request);
        
        request.setSessionAttribute(JSP_COUNTRY_TAG_LIST, request.getSessionAttribute(JSP_COUNTRY_LIST));
        request.setSessionAttribute(JSP_CITY_TAG_LIST, request.getSessionAttribute(JSP_CITY_LIST));
        
        Direction dir = (Direction) request.getSessionAttribute(JSP_CURRENT_DIRECTION);
        request.setAttribute(JSP_CURR_ID_COUNTRY, 0);
        request.setAttribute(JSP_CURR_ID_CITY, 0);
        List<Integer> countryTags = new ArrayList<>();
        dir.getCountryCollection().stream().forEach((c) -> {
            countryTags.add(c.getIdCountry());
        });
        request.setAttribute(JSP_CURR_COUNTRY_TAGS, countryTags);
        List<Integer> cityTags = new ArrayList<>();
        dir.getCityCollection().stream().forEach((c) -> {
            cityTags.add(c.getIdCity());
        });
        request.setAttribute(JSP_CURR_CITY_TAGS, cityTags);
        List<Hotel> stays = new ArrayList<>();
        dir.getStayCollection().stream().forEach((s) -> {
            stays.add(s.getHotel());
        });
        request.setAttribute(JSP_HOTEL_TAG_LIST, stays);
        
        return page;
    }
    
}
