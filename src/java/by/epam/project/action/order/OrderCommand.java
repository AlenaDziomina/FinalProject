package by.epam.project.action.order;

import static by.epam.project.action.JspParamNames.*;
import static by.epam.project.dao.DaoParamNames.*;
import by.epam.project.action.SessionRequestContent;
import by.epam.project.dao.query.Criteria;
import by.epam.project.entity.ClientType;
import by.epam.project.entity.Order;
import by.epam.project.entity.User;
import by.epam.project.exception.ServletLogicException;
import by.epam.project.exception.TechnicalException;
import by.epam.project.logic.OrderLogic;
import by.epam.project.manager.ClientTypeManager;
import static by.epam.project.manager.ParamManager.getBoolParam;
import by.epam.project.tag.ObjList;
import java.util.List;

/**
 * Class {@code OrderCommand} is the parent class of all commands of actions
 * on the order objects.
 * Contains custom public methods of actions on the order objects.
 * @author Helena.Grouk
 */
public class OrderCommand {
    /**
     * Resave common parameters of order page.
     * @param request parameters and attributes of the request and the session
     */
    protected void resaveParamsShowOrder(SessionRequestContent request) {
        String validHotelStatus = request.getParameter(JSP_ORDER_VALID_STATUS);
        if(validHotelStatus != null) {
            request.setSessionAttribute(JSP_ORDER_VALID_STATUS, validHotelStatus);
        }
        String invalidHotelStatus = request.getParameter(JSP_ORDER_INVALID_STATUS);
        if(invalidHotelStatus != null) {
            request.setSessionAttribute(JSP_ORDER_INVALID_STATUS, invalidHotelStatus);
        }
    }
    
    /**
     * Find the list of orders and save it as the attribute of session.
     * Also determine and store in session attributes display options of hotel 
     * status.
     * @param request parameters and attributes of the request and the session
     * @throws ServletLogicException if this can not be done due to the 
     * exceptions of logic layer
     */
    protected void formOrderList(SessionRequestContent request) throws ServletLogicException {
        Criteria criteria = new Criteria();
        User user = (User) request.getSessionAttribute(JSP_USER);
        if (user != null) {
            criteria.addParam(DAO_USER_LOGIN, user.getLogin());
            ClientType type = ClientTypeManager.clientTypeOf(user.getRole().getRoleName());
            criteria.addParam(DAO_ROLE_NAME, type);
        } else {
            criteria.addParam(DAO_ROLE_NAME, request.getSessionAttribute(JSP_ROLE_TYPE));
        }
        
        Short orderStatus = getOrderStatus(request);
        if (orderStatus != null) {
            criteria.addParam(DAO_ORDER_STATUS, orderStatus);
        }
        String selectId = request.getParameter(JSP_CURR_ID_TOUR);
        if (selectId != null) {
            Integer idTour = Integer.decode(selectId);
            criteria.addParam(DAO_ID_TOUR, idTour);
            request.setAttribute(JSP_CURR_ID_TOUR, idTour);
        }
        
        try {
            List<Order> orders = new OrderLogic().doGetEntity(criteria);
            request.setSessionAttribute(JSP_ORDER_LIST, orders);
            ObjList<Order> list = new ObjList<>(orders);
            request.setSessionAttribute(JSP_PAGE_LIST, list);
        } catch (TechnicalException ex) {
            throw new ServletLogicException(ex.getMessage(), ex);
        }
    }
    
    /**
     * Determine and store in session attributes display options of order 
     * status. Default value means 'only valid'.
     * @param request parameters and attributes of the request and the session
     * @return {@code ACTIVE} == 1 if 'only valid'; {@code DELETED} == 0 
     * if 'only invalid'.
     */
    protected Short getOrderStatus(SessionRequestContent request) {
        Boolean validStatus = getBoolParam(request, JSP_ORDER_VALID_STATUS);
        Boolean invalidStatus = getBoolParam(request, JSP_ORDER_INVALID_STATUS);
        if (validStatus == null && invalidStatus == null) {
            validStatus = (Boolean) request.getSessionAttribute(JSP_ORDER_VALID_STATUS);
            invalidStatus = (Boolean) request.getSessionAttribute(JSP_ORDER_INVALID_STATUS);
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
        request.setSessionAttribute(JSP_ORDER_VALID_STATUS, validStatus);
        request.setSessionAttribute(JSP_ORDER_INVALID_STATUS, invalidStatus);
        
        Short status = null;
        if (validStatus && ! invalidStatus) {
            status = ACTIVE;
        } else if ( ! validStatus && invalidStatus) {
            status = DELETED;
        }
        return status;
    }
    
    /**
     * Clean session attributes of show order page.
     * @param request parameters and attributes of the request and the session
     */
    protected void cleanSessionShowOrder(SessionRequestContent request) {
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
        request.deleteSessionAttribute(JSP_DIRECTION_LIST);
        request.deleteSessionAttribute(JSP_DIRECTION_VALID_STATUS);
        request.deleteSessionAttribute(JSP_DIRECTION_INVALID_STATUS);
        request.deleteSessionAttribute(JSP_TOUR_TYPE_LIST);
        request.deleteSessionAttribute(JSP_TRANS_MODE_LIST);
        request.deleteSessionAttribute(JSP_CURRENT_DIRECTION);
        
        //hotel
        request.deleteSessionAttribute(JSP_HOTEL_LIST);
        request.deleteSessionAttribute(JSP_CURRENT_HOTEL);
        request.deleteSessionAttribute(JSP_HOTEL_VALID_STATUS);
        request.deleteSessionAttribute(JSP_HOTEL_INVALID_STATUS);
        
        //order
        request.deleteSessionAttribute(JSP_CURRENT_TOUR);
        //request.deleteSessionAttribute(JSP_CURRENT_ORDER);
        //request.deleteSessionAttribute(JSP_ORDER_LIST);
        //request.deleteSessionAttribute(JSP_PAGE_LIST);
        
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
