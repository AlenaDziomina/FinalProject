/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package by.epam.project.action.direction;

import by.epam.project.action.ActionCommand;
import static by.epam.project.action.JspParamNames.*;
import by.epam.project.action.SessionRequestContent;
import static by.epam.project.dao.entquery.DirectionQuery.DAO_DIRECTION_STATUS;
import static by.epam.project.dao.entquery.DirectionQuery.DAO_ID_DIRECTION;
import static by.epam.project.dao.entquery.RoleQuery.DAO_ROLE_NAME;
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
        
        try {
            List<Direction> directions = new DirectionLogic().doGetEntity(criteria);
            request.setSessionAttribute(JSP_DIRECTION_LIST, directions);
            ObjList<Direction> list = new ObjList<>(directions);
            request.setSessionAttribute(JSP_PAGE_LIST, list);
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
    
    protected static void resaveParamsShowDirections(SessionRequestContent request) {
        String validDirectionStatus = request.getParameter(JSP_DIRECTION_VALID_STATUS);
        if(validDirectionStatus != null) {
            request.setAttribute(JSP_DIRECTION_VALID_STATUS, validDirectionStatus);
        }
        
        String invalidDirectionStatus = request.getParameter(JSP_DIRECTION_INVALID_STATUS);
        if(invalidDirectionStatus != null) {
            request.setAttribute(JSP_DIRECTION_INVALID_STATUS, invalidDirectionStatus);
        }
    }
    
    private void cleanSessionShowDirections(SessionRequestContent request) {
        request.deleteSessionAttribute(JSP_CURRENT_CITY);
        request.deleteSessionAttribute(JSP_CURRENT_COUNTRY);
        request.deleteSessionAttribute(JSP_CURRENT_HOTEL);
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
 
}
