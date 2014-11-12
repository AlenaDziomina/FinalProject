/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package by.epam.project.action.direction;

import by.epam.project.action.ActionCommand;
import static by.epam.project.action.JspParamNames.*;
import by.epam.project.action.SessionRequestContent;
import static by.epam.project.action.tour.ShowTour.getTourDateStatus;
import static by.epam.project.action.tour.ShowTour.getTourStatus;
import static by.epam.project.dao.entquery.DirectionQuery.DAO_DIRECTION_STATUS;
import static by.epam.project.dao.entquery.DirectionQuery.DAO_ID_DIRECTION;
import static by.epam.project.dao.entquery.RoleQuery.DAO_ROLE_NAME;
import static by.epam.project.dao.entquery.SearchQuery.DAO_TOUR_DATE_FROM;
import static by.epam.project.dao.entquery.SearchQuery.DAO_TOUR_DATE_TO;
import static by.epam.project.dao.entquery.TourQuery.DAO_TOUR_STATUS;
import static by.epam.project.dao.entquery.UserQuery.DAO_USER_LOGIN;
import by.epam.project.dao.query.Criteria;
import by.epam.project.entity.ClientType;
import by.epam.project.entity.Direction;
import by.epam.project.entity.TourType;
import by.epam.project.entity.TransMode;
import by.epam.project.entity.User;
import by.epam.project.exception.ServletLogicException;
import by.epam.project.exception.TechnicalException;
import by.epam.project.logic.DirectionLogic;
import by.epam.project.logic.TourTypeLogic;
import by.epam.project.logic.TransModeLogic;
import by.epam.project.manager.ClientTypeManager;
import by.epam.project.manager.ConfigurationManager;
import static by.epam.project.manager.ParamManager.getBoolParam;
import by.epam.project.tag.ObjList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 *
 * @author User
 */
public class GoShowDirections implements ActionCommand {

    @Override
    public String execute(SessionRequestContent request) throws ServletLogicException {
        String page = ConfigurationManager.getProperty("path.page.directions");
        String prevPage = (String) request.getSessionAttribute(JSP_PAGE);
        resaveParamsShowDirections(request);
        
        formDirectionList(request);
        if (page == null ? prevPage != null : ! page.equals(prevPage)) {
            request.setSessionAttribute(JSP_PAGE, page);
            cleanSessionShowDirections(request);
        }
        return page;
    }
  
    public static void formDirectionList(SessionRequestContent request) throws ServletLogicException {

        Criteria criteria = new Criteria();
        User user = (User) request.getSessionAttribute(JSP_USER);
        if (user != null) {
            criteria.addParam(DAO_USER_LOGIN, user.getLogin());
            ClientType type = ClientTypeManager.clientTypeOf(user.getRole().getRoleName());
            criteria.addParam(DAO_ROLE_NAME, type);
        } else {
            criteria.addParam(DAO_ROLE_NAME, request.getSessionAttribute(JSP_ROLE_TYPE));
        }
        criteria.addParam(DAO_ID_DIRECTION, request.getAttribute(JSP_ID_DIRECTION));
        
        Integer directionStatus = getDirectionStatus(request);
        if (directionStatus != null) {
            criteria.addParam(DAO_DIRECTION_STATUS, directionStatus);
        }
        
        Integer tourStatus = getTourStatus(request);
        if (tourStatus != null) {
            criteria.addParam(DAO_TOUR_STATUS, tourStatus);
        }
        Integer tourDateStatus = getTourDateStatus(request);
        if (tourDateStatus != null) {
            Calendar calendar = Calendar.getInstance();
            Date date = calendar.getTime();
            if (tourDateStatus == 1) {
                criteria.addParam(DAO_TOUR_DATE_FROM, date);
            } else if (tourDateStatus == 0) {
                criteria.addParam(DAO_TOUR_DATE_TO, date);
            }
        }
        
        try {
            List<Direction> directions = new DirectionLogic().doGetEntity(criteria);
            request.setSessionAttribute(JSP_DIRECTION_LIST, directions);
            ObjList<Direction> list = new ObjList<>(directions);
            request.setSessionAttribute(JSP_PAGE_LIST, list);
        } catch (TechnicalException ex) {
            throw new ServletLogicException(ex.getMessage(), ex);
        }
    }
    
    public static void formTourTypeList(SessionRequestContent request) throws ServletLogicException {
        Criteria criteria = new Criteria();
        User user = (User) request.getSessionAttribute(JSP_USER);
        if (user != null) {
            criteria.addParam(DAO_USER_LOGIN, user.getLogin());
            ClientType type = ClientTypeManager.clientTypeOf(user.getRole().getRoleName());
            criteria.addParam(DAO_ROLE_NAME, type);
        } else {
            criteria.addParam(DAO_ROLE_NAME, request.getSessionAttribute(JSP_ROLE_TYPE));
        }
        
        try {
            List<TourType> types = new TourTypeLogic().doGetEntity(criteria);
            request.setSessionAttribute(JSP_TOUR_TYPE_LIST, types);
        } catch (TechnicalException ex) {
            throw new ServletLogicException(ex.getMessage(), ex);
        }
    }
    
    public static void formTransModeList(SessionRequestContent request) throws ServletLogicException {
        Criteria criteria = new Criteria();
        User user = (User) request.getSessionAttribute(JSP_USER);
        if (user != null) {
            criteria.addParam(DAO_USER_LOGIN, user.getLogin());
            ClientType type = ClientTypeManager.clientTypeOf(user.getRole().getRoleName());
            criteria.addParam(DAO_ROLE_NAME, type);
        } else {
            criteria.addParam(DAO_ROLE_NAME, request.getSessionAttribute(JSP_ROLE_TYPE));
        }
        
        try {
            List<TransMode> modes = new TransModeLogic().doGetEntity(criteria);
            request.setSessionAttribute(JSP_TRANS_MODE_LIST, modes);
        } catch (TechnicalException ex) {
            throw new ServletLogicException(ex.getMessage(), ex);
        }
    }
 
    private static Integer getDirectionStatus(SessionRequestContent request) {
        Boolean validStatus = getBoolParam(request, JSP_DIRECTION_VALID_STATUS);
        Boolean invalidStatus = getBoolParam(request, JSP_DIRECTION_INVALID_STATUS);
        if (validStatus == null && invalidStatus == null) {
            validStatus = (Boolean) request.getSessionAttribute(JSP_DIRECTION_VALID_STATUS);
            invalidStatus = (Boolean) request.getSessionAttribute(JSP_DIRECTION_INVALID_STATUS);
            if (validStatus == null && invalidStatus == null) {
                validStatus = true;
                invalidStatus = false;
            }
        } else {
            if (validStatus == null) {
                validStatus = false;
            }
            if (invalidStatus == null) {
                invalidStatus = false;
            }
        }
        request.setSessionAttribute(JSP_DIRECTION_VALID_STATUS, validStatus);
        request.setSessionAttribute(JSP_DIRECTION_INVALID_STATUS, invalidStatus);
        
        Integer status = null;
        if (validStatus && ! invalidStatus) {
            status = 1;
        } else if ( ! validStatus && invalidStatus) {
            status = 0;
        }
        return status;
    }
    
    private void resaveParamsShowDirections(SessionRequestContent request) {
        String validDirectionStatus = request.getParameter(JSP_DIRECTION_VALID_STATUS);
        if(validDirectionStatus != null) {
            request.setAttribute(JSP_DIRECTION_VALID_STATUS, validDirectionStatus);
        }
        
        String invalidDirectionStatus = request.getParameter(JSP_DIRECTION_INVALID_STATUS);
        if(invalidDirectionStatus != null) {
            request.setAttribute(JSP_DIRECTION_INVALID_STATUS, invalidDirectionStatus);
        }
        String validTourStatus = request.getParameter(JSP_TOUR_VALID_STATUS);
        if(validTourStatus != null) {
            request.setAttribute(JSP_TOUR_VALID_STATUS, validTourStatus);
        }
        
        String invalidTourStatus = request.getParameter(JSP_TOUR_INVALID_STATUS);
        if(invalidTourStatus != null) {
            request.setAttribute(JSP_TOUR_INVALID_STATUS, invalidTourStatus);
        }
        
        String validTourDate = request.getParameter(JSP_TOUR_VALID_DATE);
        if(validTourDate != null) {
            request.setAttribute(JSP_TOUR_VALID_DATE, validTourDate);
        }
        
        String invalidTourDate = request.getParameter(JSP_TOUR_INVALID_DATE);
        if(invalidTourDate != null) {
            request.setAttribute(JSP_TOUR_INVALID_DATE, invalidTourDate);
        }
        
        
    }

    private void cleanSessionShowDirections(SessionRequestContent request) {
        //city
        request.deleteSessionAttribute(JSP_CITY_LIST);
        request.deleteSessionAttribute(JSP_CURR_CITY_LIST);
        request.deleteSessionAttribute(JSP_CURRENT_CITY);
        
        //country
        request.deleteSessionAttribute(JSP_COUNTRY_LIST);
        request.deleteSessionAttribute(JSP_CURRENT_COUNTRY);
        
        //direction
        //request.deleteSessionAttribute(JSP_DIRECTION_LIST);
        //request.deleteSessionAttribute(JSP_PAGE_LIST);
        //request.deleteSessionAttribute(JSP_DIRECTION_VALID_STATUS);
        //request.deleteSessionAttribute(JSP_DIRECTION_INVALID_STATUS);
        //request.deleteSessionAttribute(JSP_TOUR_TYPE_LIST);
        //request.deleteSessionAttribute(JSP_TRANS_MODE_LIST);
        //request.deleteSessionAttribute(JSP_CURRENT_DIRECTION);
        
        //hotel
        request.deleteSessionAttribute(JSP_HOTEL_LIST);
        request.deleteSessionAttribute(JSP_CURRENT_HOTEL);
        
        //order
        request.deleteSessionAttribute(JSP_CURRENT_ORDER);
        request.deleteSessionAttribute(JSP_ORDER_LIST);
        
        //tour
        request.deleteSessionAttribute(JSP_TOUR_VALID_STATUS);
        request.deleteSessionAttribute(JSP_TOUR_INVALID_STATUS);
        request.deleteSessionAttribute(JSP_TOUR_VALID_DATE);
        request.deleteSessionAttribute(JSP_TOUR_INVALID_DATE);
        request.deleteSessionAttribute(JSP_TOUR_LIST);
        request.deleteSessionAttribute(JSP_COUNTRY_TAG_LIST);
        request.deleteSessionAttribute(JSP_CITY_TAG_LIST);
        request.deleteSessionAttribute(JSP_PRICE_STEP);
        request.deleteSessionAttribute(JSP_DISCOUNT_STEP);
        request.deleteSessionAttribute(JSP_BOX_ALL_DEPART_DATE);
        request.deleteSessionAttribute(JSP_BOX_ALL_DAYS_COUNT);
        request.deleteSessionAttribute(JSP_BOX_ALL_PRICE);
        request.deleteSessionAttribute(JSP_BOX_ALL_COUNTRIES);
        request.deleteSessionAttribute(JSP_BOX_ALL_CITIES);
        request.deleteSessionAttribute(JSP_BOX_ALL_HOTELS);
        request.deleteSessionAttribute(JSP_IS_HIDDEN);
        request.deleteSessionAttribute(JSP_CURRENT_TOUR);
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
