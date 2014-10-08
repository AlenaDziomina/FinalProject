/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package by.epam.project.action.tour;

import by.epam.project.action.ActionCommand;
import static by.epam.project.action.JspParamNames.JSP_ID_DIRECTION;
import static by.epam.project.action.JspParamNames.JSP_PAGE;
import static by.epam.project.action.JspParamNames.JSP_ROLE_TYPE;
import static by.epam.project.action.JspParamNames.JSP_TOUR_LIST;
import static by.epam.project.action.JspParamNames.JSP_USER_LOGIN;
import static by.epam.project.action.SessionGarbageCollector.cleanSession;
import by.epam.project.controller.SessionRequestContent;
import static by.epam.project.dao.entquery.DirectionQuery.DAO_ID_DIRECTION;
import static by.epam.project.dao.entquery.RoleQuery.DAO_ROLE_NAME;
import static by.epam.project.dao.entquery.UserQuery.DAO_USER_LOGIN;
import by.epam.project.dao.query.Criteria;
import by.epam.project.entity.Tour;
import by.epam.project.exception.DaoException;
import by.epam.project.exception.DaoUserLogicException;
import by.epam.project.logic.TourLogic;
import by.epam.project.manager.ConfigurationManager;
import by.epam.project.manager.MessageManager;
import java.util.List;

/**
 *
 * @author User
 */
public class GoShowTour implements ActionCommand {

    @Override
    public String execute(SessionRequestContent request) throws DaoUserLogicException {
        String page = ConfigurationManager.getProperty("path.page.tours");
        request.setSessionAttribute(JSP_PAGE, page);
        formTourList(request);
        cleanSession(request);
        return page;
    }
    
    public static void formTourList(SessionRequestContent request) throws DaoUserLogicException {
        Criteria criteria = new Criteria();
        criteria.addParam(DAO_USER_LOGIN, request.getSessionAttribute(JSP_USER_LOGIN));
        criteria.addParam(DAO_ROLE_NAME, request.getSessionAttribute(JSP_ROLE_TYPE));
        criteria.addParam(DAO_ID_DIRECTION, request.getAttribute(JSP_ID_DIRECTION));
        
        try {
            List<Tour> tours = TourLogic.getTours(criteria);
            request.setSessionAttribute(JSP_TOUR_LIST, tours);
        } catch (DaoException ex) {
            throw new DaoUserLogicException(MessageManager.getProperty("message.daoerror"));
        }
    }
            
}