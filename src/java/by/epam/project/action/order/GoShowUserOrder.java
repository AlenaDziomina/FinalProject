/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package by.epam.project.action.order;

import by.epam.project.action.ActionCommand;
import static by.epam.project.action.JspParamNames.JSP_CITY_TAG_LIST;
import static by.epam.project.action.JspParamNames.JSP_COUNTRY_TAG_LIST;
import static by.epam.project.action.JspParamNames.JSP_CURRENT_CITY;
import static by.epam.project.action.JspParamNames.JSP_CURRENT_COUNTRY;
import static by.epam.project.action.JspParamNames.JSP_CURRENT_DIRECTION;
import static by.epam.project.action.JspParamNames.JSP_CURRENT_TOUR;
import static by.epam.project.action.JspParamNames.JSP_CURR_CITY_LIST;
import static by.epam.project.action.JspParamNames.JSP_DISCOUNT_STEP;
import static by.epam.project.action.JspParamNames.JSP_HOTEL_TAG_LIST;
import static by.epam.project.action.JspParamNames.JSP_ORDER_INVALID_STATUS;
import static by.epam.project.action.JspParamNames.JSP_ORDER_LIST;
import static by.epam.project.action.JspParamNames.JSP_ORDER_VALID_STATUS;
import static by.epam.project.action.JspParamNames.JSP_PAGE;
import static by.epam.project.action.JspParamNames.JSP_PAGE_LIST;
import static by.epam.project.action.JspParamNames.JSP_PRICE_STEP;
import static by.epam.project.action.JspParamNames.JSP_ROLE_TYPE;
import static by.epam.project.action.JspParamNames.JSP_TOUR_LIST;
import static by.epam.project.action.JspParamNames.JSP_TOUR_TYPE_LIST;
import static by.epam.project.action.JspParamNames.JSP_TRANS_MODE_LIST;
import static by.epam.project.action.JspParamNames.JSP_USER;
import by.epam.project.action.SessionRequestContent;
import static by.epam.project.dao.entquery.OrderQuery.DAO_ORDER_STATUS;
import static by.epam.project.dao.entquery.RoleQuery.DAO_ROLE_NAME;
import static by.epam.project.dao.entquery.UserQuery.DAO_ID_USER;
import static by.epam.project.dao.entquery.UserQuery.DAO_USER_LOGIN;
import by.epam.project.dao.query.Criteria;
import by.epam.project.entity.ClientType;
import by.epam.project.entity.Order;
import by.epam.project.entity.User;
import by.epam.project.exception.ServletLogicException;
import by.epam.project.exception.TechnicalException;
import by.epam.project.logic.OrderLogic;
import by.epam.project.manager.ClientTypeManager;
import by.epam.project.manager.ConfigurationManager;
import static by.epam.project.manager.ParamManager.getBoolParam;
import by.epam.project.tag.ObjList;
import java.util.List;

/**
 *
 * @author User
 */
public class GoShowUserOrder  implements ActionCommand {

    @Override
    public String execute(SessionRequestContent request) throws ServletLogicException {
        String page = ConfigurationManager.getProperty("path.page.userorder");
        String prevPage = (String) request.getSessionAttribute(JSP_PAGE);
        resaveParamsShowOrder(request);
        
        formOrderList(request);
        if (page == null ? prevPage != null : ! page.equals(prevPage)) {
            request.setSessionAttribute(JSP_PAGE, page);
            cleanSessionShowOrder(request);
        }
        return page;
    }

    public static void resaveParamsShowOrder(SessionRequestContent request) throws ServletLogicException {
        String validHotelStatus = request.getParameter(JSP_ORDER_VALID_STATUS);
        if(validHotelStatus != null) {
            request.setAttribute(JSP_ORDER_VALID_STATUS, validHotelStatus);
        }
        
        String invalidHotelStatus = request.getParameter(JSP_ORDER_INVALID_STATUS);
        if(invalidHotelStatus != null) {
            request.setAttribute(JSP_ORDER_INVALID_STATUS, invalidHotelStatus);
        }
    }

    private void formOrderList(SessionRequestContent request) throws ServletLogicException {
        Criteria criteria = new Criteria();
        User user = (User) request.getSessionAttribute(JSP_USER);
        if (user != null) {
            criteria.addParam(DAO_USER_LOGIN, user.getLogin());
            criteria.addParam(DAO_ID_USER, user.getIdUser());
            ClientType type = ClientTypeManager.clientTypeOf(user.getRole().getRoleName());
            criteria.addParam(DAO_ROLE_NAME, type);
        } else {
            criteria.addParam(DAO_ROLE_NAME, request.getSessionAttribute(JSP_ROLE_TYPE));
        }
        
        Integer orderStatus = getOrderStatus(request);
        if (orderStatus != null) {
            criteria.addParam(DAO_ORDER_STATUS, orderStatus);
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

    public static void cleanSessionShowOrder(SessionRequestContent request) {
        request.deleteSessionAttribute(JSP_CURRENT_CITY);
        request.deleteSessionAttribute(JSP_CURRENT_COUNTRY);
        request.deleteSessionAttribute(JSP_CURRENT_DIRECTION);
        request.deleteSessionAttribute(JSP_CURRENT_TOUR);
        
        request.deleteSessionAttribute(JSP_TOUR_TYPE_LIST);
        request.deleteSessionAttribute(JSP_TRANS_MODE_LIST);
        
        request.deleteSessionAttribute(JSP_COUNTRY_TAG_LIST);
        request.deleteSessionAttribute(JSP_CITY_TAG_LIST);
        request.deleteSessionAttribute(JSP_HOTEL_TAG_LIST);
        
        request.deleteSessionAttribute(JSP_TOUR_LIST);
        request.deleteSessionAttribute(JSP_PRICE_STEP);
        request.deleteSessionAttribute(JSP_DISCOUNT_STEP);
        
        request.setSessionAttribute(JSP_CURR_CITY_LIST, null);
    }

    public static Integer getOrderStatus(SessionRequestContent request) {
        Boolean validStatus = getBoolParam(request, JSP_ORDER_VALID_STATUS);
        Boolean invalidStatus = getBoolParam(request, JSP_ORDER_INVALID_STATUS);
        if (validStatus == null && invalidStatus == null) {
            validStatus = true;
            invalidStatus = false;
        } else {
            if (validStatus == null) {
                validStatus = false;
            }
            if (invalidStatus == null) {
                invalidStatus = false;
            }
        }
        request.setAttribute(JSP_ORDER_VALID_STATUS, validStatus);
        request.setAttribute(JSP_ORDER_INVALID_STATUS, invalidStatus);
        
        Integer status = null;
        if (validStatus && ! invalidStatus) {
            status = 1;
        } else if ( ! validStatus && invalidStatus) {
            status = 0;
        }
        return status;
    }
    
}
