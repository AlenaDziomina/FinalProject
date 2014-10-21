/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package by.epam.project.action.direction;

import by.epam.project.action.ActionCommand;
import static by.epam.project.action.JspParamNames.JSP_DIRECTION_LIST;
import static by.epam.project.action.JspParamNames.JSP_ID_DIRECTION;
import static by.epam.project.action.JspParamNames.JSP_ID_TOURTYPE;
import static by.epam.project.action.JspParamNames.JSP_ID_TRANSMODE;
import static by.epam.project.action.JspParamNames.JSP_PAGE;
import static by.epam.project.action.JspParamNames.JSP_ROLE_TYPE;
import static by.epam.project.action.JspParamNames.JSP_TOUR_TYPE_LIST;
import static by.epam.project.action.JspParamNames.JSP_TRANS_MODE_LIST;
import static by.epam.project.action.JspParamNames.JSP_USER_LOGIN;
import static by.epam.project.action.SessionGarbageCollector.cleanSession;
import by.epam.project.action.SessionRequestContent;
import static by.epam.project.dao.entquery.DirectionQuery.DAO_ID_DIRECTION;
import static by.epam.project.dao.entquery.RoleQuery.DAO_ROLE_NAME;
import static by.epam.project.dao.entquery.TourTypeQuery.DAO_ID_TOURTYPE;
import static by.epam.project.dao.entquery.TransModeQuery.DAO_ID_TRANSMODE;
import static by.epam.project.dao.entquery.UserQuery.DAO_USER_LOGIN;
import by.epam.project.dao.query.Criteria;
import by.epam.project.entity.Direction;
import by.epam.project.entity.TourType;
import by.epam.project.entity.TransMode;
import by.epam.project.exception.DaoUserLogicException;
import by.epam.project.exception.TechnicalException;
import by.epam.project.logic.DirectionLogic;
import by.epam.project.logic.TourTypeLogic;
import by.epam.project.logic.TransModeLogic;
import by.epam.project.manager.ConfigurationManager;
import by.epam.project.tag.ObjList;
import java.util.List;

/**
 *
 * @author User
 */
public class GoShowDirections implements ActionCommand {
    
    @Override
    public String execute(SessionRequestContent request) throws DaoUserLogicException {
        String page = ConfigurationManager.getProperty("path.page.directions");
        request.setSessionAttribute(JSP_PAGE, page);
        formDirectionList(request);
        cleanSession(request);
        return page;
    }
        
    public static void formDirectionList(SessionRequestContent request) throws DaoUserLogicException {

        Criteria criteria = new Criteria();
        criteria.addParam(DAO_USER_LOGIN, request.getSessionAttribute(JSP_USER_LOGIN));
        criteria.addParam(DAO_ROLE_NAME, request.getSessionAttribute(JSP_ROLE_TYPE));
        criteria.addParam(DAO_ID_DIRECTION, request.getAttribute(JSP_ID_DIRECTION));
        try {
            List<Direction> directions = new DirectionLogic().doGetEntity(criteria);
            request.setSessionAttribute(JSP_DIRECTION_LIST, directions);
            ObjList<Direction> list = new ObjList<>(directions);
            request.setSessionAttribute("rw", list);
        } catch (TechnicalException ex) {
            throw new DaoUserLogicException(ex.getMessage(), ex);
        }
    }
    
    public static void formTourTypeList(SessionRequestContent request) throws DaoUserLogicException {
        Criteria criteria = new Criteria();
        criteria.addParam(DAO_USER_LOGIN, request.getSessionAttribute(JSP_USER_LOGIN));
        criteria.addParam(DAO_ROLE_NAME, request.getSessionAttribute(JSP_ROLE_TYPE));
        criteria.addParam(DAO_ID_TOURTYPE, request.getAttribute(JSP_ID_TOURTYPE));
        try {
            List<TourType> types = new TourTypeLogic().doGetEntity(criteria);
            request.setSessionAttribute(JSP_TOUR_TYPE_LIST, types);
        } catch (TechnicalException ex) {
            throw new DaoUserLogicException(ex.getMessage(), ex);
        }
    }
    
    public static void formTransModeList(SessionRequestContent request) throws DaoUserLogicException {
        Criteria criteria = new Criteria();
        criteria.addParam(DAO_USER_LOGIN, request.getSessionAttribute(JSP_USER_LOGIN));
        criteria.addParam(DAO_ROLE_NAME, request.getSessionAttribute(JSP_ROLE_TYPE));
        criteria.addParam(DAO_ID_TRANSMODE, request.getAttribute(JSP_ID_TRANSMODE));
        try {
            List<TransMode> modes = new TransModeLogic().doGetEntity(criteria);
            request.setSessionAttribute(JSP_TRANS_MODE_LIST, modes);
        } catch (TechnicalException ex) {
            throw new DaoUserLogicException(ex.getMessage(), ex);
        }
    }
    
    
}
