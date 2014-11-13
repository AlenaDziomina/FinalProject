/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package by.epam.project.action.tour;

import by.epam.project.action.ActionCommand;
import static by.epam.project.action.JspParamNames.JSP_BOX_ALL_CITIES;
import static by.epam.project.action.JspParamNames.JSP_BOX_ALL_COUNTRIES;
import static by.epam.project.action.JspParamNames.JSP_BOX_ALL_DAYS_COUNT;
import static by.epam.project.action.JspParamNames.JSP_BOX_ALL_DEPART_DATE;
import static by.epam.project.action.JspParamNames.JSP_BOX_ALL_HOTELS;
import static by.epam.project.action.JspParamNames.JSP_BOX_ALL_PRICE;
import static by.epam.project.action.JspParamNames.JSP_CITY_INVALID_STATUS;
import static by.epam.project.action.JspParamNames.JSP_CITY_LIST;
import static by.epam.project.action.JspParamNames.JSP_CITY_TAG_LIST;
import static by.epam.project.action.JspParamNames.JSP_CITY_VALID_STATUS;
import static by.epam.project.action.JspParamNames.JSP_COUNTRY_INVALID_STATUS;
import static by.epam.project.action.JspParamNames.JSP_COUNTRY_LIST;
import static by.epam.project.action.JspParamNames.JSP_COUNTRY_TAG_LIST;
import static by.epam.project.action.JspParamNames.JSP_COUNTRY_VALID_STATUS;
import static by.epam.project.action.JspParamNames.JSP_CURRENT_CITY;
import static by.epam.project.action.JspParamNames.JSP_CURRENT_COUNTRY;
import static by.epam.project.action.JspParamNames.JSP_CURRENT_DIRECTION;
import static by.epam.project.action.JspParamNames.JSP_CURRENT_HOTEL;
import static by.epam.project.action.JspParamNames.JSP_CURRENT_ORDER;
import static by.epam.project.action.JspParamNames.JSP_CURRENT_TOUR;
import static by.epam.project.action.JspParamNames.JSP_CURRENT_USER;
import static by.epam.project.action.JspParamNames.JSP_CURR_ARRIVAL_DATE;
import static by.epam.project.action.JspParamNames.JSP_CURR_CITY_LIST;
import static by.epam.project.action.JspParamNames.JSP_CURR_CITY_TAGS;
import static by.epam.project.action.JspParamNames.JSP_CURR_COUNTRY_TAGS;
import static by.epam.project.action.JspParamNames.JSP_CURR_DAYS_COUNT_FROM;
import static by.epam.project.action.JspParamNames.JSP_CURR_DAYS_COUNT_TO;
import static by.epam.project.action.JspParamNames.JSP_CURR_DEPART_DATE;
import static by.epam.project.action.JspParamNames.JSP_CURR_DEPART_DATE_FROM;
import static by.epam.project.action.JspParamNames.JSP_CURR_DEPART_DATE_TO;
import static by.epam.project.action.JspParamNames.JSP_CURR_DISCOUNT_FROM;
import static by.epam.project.action.JspParamNames.JSP_CURR_HOTEL_STARS;
import static by.epam.project.action.JspParamNames.JSP_CURR_ID_CITY;
import static by.epam.project.action.JspParamNames.JSP_CURR_ID_COUNTRY;
import static by.epam.project.action.JspParamNames.JSP_CURR_ID_HOTEL;
import static by.epam.project.action.JspParamNames.JSP_CURR_PRICE_FROM;
import static by.epam.project.action.JspParamNames.JSP_CURR_PRICE_TO;
import static by.epam.project.action.JspParamNames.JSP_CURR_TOUR_TYPE;
import static by.epam.project.action.JspParamNames.JSP_CURR_TRANS_MODE;
import static by.epam.project.action.JspParamNames.JSP_DIRECTION_INVALID_STATUS;
import static by.epam.project.action.JspParamNames.JSP_DIRECTION_LIST;
import static by.epam.project.action.JspParamNames.JSP_DIRECTION_VALID_STATUS;
import static by.epam.project.action.JspParamNames.JSP_DISCOUNT_STEP;
import static by.epam.project.action.JspParamNames.JSP_HOTEL_INVALID_STATUS;
import static by.epam.project.action.JspParamNames.JSP_HOTEL_LIST;
import static by.epam.project.action.JspParamNames.JSP_HOTEL_TAG_LIST;
import static by.epam.project.action.JspParamNames.JSP_HOTEL_VALID_STATUS;
import static by.epam.project.action.JspParamNames.JSP_ID_DIRECTION;
import static by.epam.project.action.JspParamNames.JSP_IS_HIDDEN;
import static by.epam.project.action.JspParamNames.JSP_ORDER_LIST;
import static by.epam.project.action.JspParamNames.JSP_PAGE;
import static by.epam.project.action.JspParamNames.JSP_PAGE_LIST;
import static by.epam.project.action.JspParamNames.JSP_PRICE_STEP;
import static by.epam.project.action.JspParamNames.JSP_ROLE_TYPE;
import static by.epam.project.action.JspParamNames.JSP_SELECT_ID;
import static by.epam.project.action.JspParamNames.JSP_TOUR_INVALID_DATE;
import static by.epam.project.action.JspParamNames.JSP_TOUR_INVALID_STATUS;
import static by.epam.project.action.JspParamNames.JSP_TOUR_LIST;
import static by.epam.project.action.JspParamNames.JSP_TOUR_TYPE_LIST;
import static by.epam.project.action.JspParamNames.JSP_TOUR_VALID_DATE;
import static by.epam.project.action.JspParamNames.JSP_TOUR_VALID_STATUS;
import static by.epam.project.action.JspParamNames.JSP_TRANS_MODE_LIST;
import static by.epam.project.action.JspParamNames.JSP_USER;
import static by.epam.project.action.JspParamNames.JSP_USER_LIST;
import by.epam.project.action.SessionRequestContent;
import static by.epam.project.dao.entquery.DirectionQuery.DAO_ID_DIRECTION;
import static by.epam.project.dao.entquery.RoleQuery.DAO_ROLE_NAME;
import static by.epam.project.dao.entquery.SearchQuery.DAO_TOUR_DATE_FROM;
import static by.epam.project.dao.entquery.SearchQuery.DAO_TOUR_DATE_TO;
import static by.epam.project.dao.entquery.TourQuery.DAO_TOUR_STATUS;
import static by.epam.project.dao.entquery.UserQuery.DAO_USER_LOGIN;
import by.epam.project.dao.query.Criteria;
import by.epam.project.entity.ClientType;
import by.epam.project.entity.Direction;
import by.epam.project.entity.Tour;
import by.epam.project.entity.User;
import by.epam.project.exception.ServletLogicException;
import by.epam.project.exception.TechnicalException;
import by.epam.project.logic.TourLogic;
import by.epam.project.manager.ClientTypeManager;
import by.epam.project.manager.ConfigurationManager;
import static by.epam.project.manager.ParamManager.getBoolParam;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Objects;

/**
 *
 * @author User
 */
public class ShowTour extends TourCommand implements ActionCommand {

    @Override
    public String execute(SessionRequestContent request) throws ServletLogicException {
        String page = ConfigurationManager.getProperty("path.page.tour");
        String prevPage = (String) request.getSessionAttribute(JSP_PAGE);
        resaveParamsShowTour(request);
        
        formTourList(request);
        distinguishTour(request);
        if(page == null ? prevPage == null : !page.equals(prevPage)){
            request.setSessionAttribute(JSP_PAGE, page);
            cleanSessionShowTour(request);
        }
        return page;
    }

    private void distinguishTour(SessionRequestContent request) {
        List<Tour> list = (List<Tour>) request.getSessionAttribute(JSP_TOUR_LIST);
        String selectId = request.getParameter(JSP_SELECT_ID);
        Integer idTour = null;
        if (selectId != null) {
            idTour = Integer.decode(selectId);
        } else {
            Tour tour = (Tour) request.getSessionAttribute(JSP_CURRENT_TOUR);
            if (tour != null) {
                idTour = tour.getIdTour();
            }
        }
        
        if (idTour != null) {
            request.setSessionAttribute(JSP_CURRENT_TOUR, null);
            for (Tour tour: list) {
                if (Objects.equals(tour.getIdTour(), idTour)) {
                    request.setSessionAttribute(JSP_CURRENT_TOUR, tour);

                    SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
                    Date d1 = tour.getDepartDate();
                    String ds1 = formatter.format(d1);  
                    request.setAttribute(JSP_CURR_DEPART_DATE, ds1);

                    Integer dn = tour.getDaysCount();
                    Calendar c = Calendar.getInstance(); 
                    c.setTime(d1); 
                    c.add(Calendar.DATE, dn);
                    Date d2 = c.getTime();
                    String ds2 = formatter.format(d2);
                    request.setAttribute(JSP_CURR_ARRIVAL_DATE, ds2);
                }
            }
        }
    }

    private void cleanSessionShowTour(SessionRequestContent request) {
        //city
        request.deleteSessionAttribute(JSP_CITY_LIST);
        request.deleteSessionAttribute(JSP_CURR_CITY_LIST);
        request.deleteSessionAttribute(JSP_CURRENT_CITY);
        request.deleteSessionAttribute(JSP_CITY_VALID_STATUS);
        request.deleteSessionAttribute(JSP_CITY_INVALID_STATUS);
        
        //country
        request.deleteSessionAttribute(JSP_COUNTRY_LIST);
        request.deleteSessionAttribute(JSP_CURRENT_COUNTRY);
        request.deleteSessionAttribute(JSP_COUNTRY_VALID_STATUS);
        request.deleteSessionAttribute(JSP_COUNTRY_INVALID_STATUS);
        
        //direction
        request.deleteSessionAttribute(JSP_COUNTRY_TAG_LIST);
        request.deleteSessionAttribute(JSP_CITY_TAG_LIST);
        request.deleteSessionAttribute(JSP_DIRECTION_LIST);
        request.deleteSessionAttribute(JSP_PAGE_LIST);
        request.deleteSessionAttribute(JSP_DIRECTION_VALID_STATUS);
        request.deleteSessionAttribute(JSP_DIRECTION_INVALID_STATUS);
        request.deleteSessionAttribute(JSP_TOUR_TYPE_LIST);
        request.deleteSessionAttribute(JSP_TRANS_MODE_LIST);
        
        //request.deleteSessionAttribute(JSP_CURRENT_DIRECTION);
        
        //hotel
        request.deleteSessionAttribute(JSP_HOTEL_LIST);
        request.deleteSessionAttribute(JSP_CURRENT_HOTEL);
        request.deleteSessionAttribute(JSP_HOTEL_VALID_STATUS);
        request.deleteSessionAttribute(JSP_HOTEL_INVALID_STATUS);
        
        //order
        request.deleteSessionAttribute(JSP_CURRENT_ORDER);
        request.deleteSessionAttribute(JSP_ORDER_LIST);
        
        //tour
        //request.deleteSessionAttribute(JSP_TOUR_VALID_STATUS);
        //request.deleteSessionAttribute(JSP_TOUR_INVALID_STATUS);
        //request.deleteSessionAttribute(JSP_TOUR_VALID_DATE);
        //request.deleteSessionAttribute(JSP_TOUR_INVALID_DATE);
        //request.deleteSessionAttribute(JSP_TOUR_LIST);
        //request.deleteSessionAttribute(JSP_CURRENT_TOUR);
        
        request.deleteSessionAttribute(JSP_PRICE_STEP);
        request.deleteSessionAttribute(JSP_DISCOUNT_STEP);
        request.deleteSessionAttribute(JSP_BOX_ALL_DEPART_DATE);
        request.deleteSessionAttribute(JSP_BOX_ALL_DAYS_COUNT);
        request.deleteSessionAttribute(JSP_BOX_ALL_PRICE);
        request.deleteSessionAttribute(JSP_BOX_ALL_COUNTRIES);
        request.deleteSessionAttribute(JSP_BOX_ALL_CITIES);
        request.deleteSessionAttribute(JSP_BOX_ALL_HOTELS);
        request.deleteSessionAttribute(JSP_IS_HIDDEN);
        request.deleteSessionAttribute(JSP_CURR_TOUR_TYPE);
        request.deleteSessionAttribute(JSP_CURR_TRANS_MODE);
        request.deleteSessionAttribute(JSP_CURR_ID_COUNTRY);
        request.deleteSessionAttribute(JSP_CURR_ID_CITY);
        request.deleteSessionAttribute(JSP_CURR_ID_HOTEL);
        request.deleteSessionAttribute(JSP_CURR_COUNTRY_TAGS);
        request.deleteSessionAttribute(JSP_CURR_CITY_TAGS);
        request.deleteSessionAttribute(JSP_CURR_PRICE_FROM);
        request.deleteSessionAttribute(JSP_CURR_PRICE_TO);
        request.deleteSessionAttribute(JSP_CURR_DEPART_DATE_FROM);
        request.deleteSessionAttribute(JSP_CURR_DEPART_DATE_TO);
        request.deleteSessionAttribute(JSP_CURR_DAYS_COUNT_FROM);
        request.deleteSessionAttribute(JSP_CURR_DAYS_COUNT_TO);
        request.deleteSessionAttribute(JSP_CURR_DISCOUNT_FROM);
        request.deleteSessionAttribute(JSP_CURR_HOTEL_STARS);    
        request.deleteSessionAttribute(JSP_HOTEL_TAG_LIST);
        
        //user
        request.deleteSessionAttribute(JSP_USER_LIST);
        request.deleteSessionAttribute(JSP_CURRENT_USER);
        
    }
}
