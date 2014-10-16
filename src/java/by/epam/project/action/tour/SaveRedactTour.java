/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package by.epam.project.action.tour;

import by.epam.project.action.ActionCommand;
import static by.epam.project.action.JspParamNames.JSP_CURR_ARRIVAL_DATE;
import static by.epam.project.action.JspParamNames.JSP_CURR_DEPART_DATE;
import static by.epam.project.action.JspParamNames.JSP_CURR_ID_DIRECTION;
import static by.epam.project.action.JspParamNames.JSP_CURR_ID_TOUR;
import static by.epam.project.action.JspParamNames.JSP_FREE_SEATS;
import static by.epam.project.action.JspParamNames.JSP_PAGE;
import static by.epam.project.action.JspParamNames.JSP_ROLE_TYPE;
import static by.epam.project.action.JspParamNames.JSP_SELECT_ID;
import static by.epam.project.action.JspParamNames.JSP_TOTAL_SEATS;
import static by.epam.project.action.JspParamNames.JSP_TOUR_DISCOUNT;
import static by.epam.project.action.JspParamNames.JSP_TOUR_PRICE;
import static by.epam.project.action.JspParamNames.JSP_USER_LOGIN;
import static by.epam.project.action.ProcessSavedParameters.resaveParams;
import by.epam.project.action.SessionRequestContent;
import static by.epam.project.dao.entquery.DirectionQuery.DAO_ID_DIRECTION;
import static by.epam.project.dao.entquery.RoleQuery.DAO_ROLE_NAME;
import static by.epam.project.dao.entquery.TourQuery.DAO_ID_TOUR;
import static by.epam.project.dao.entquery.TourQuery.DAO_TOUR_DATE;
import static by.epam.project.dao.entquery.TourQuery.DAO_TOUR_DAYS;
import static by.epam.project.dao.entquery.TourQuery.DAO_TOUR_DISCOUNT;
import static by.epam.project.dao.entquery.TourQuery.DAO_TOUR_FREE_SEATS;
import static by.epam.project.dao.entquery.TourQuery.DAO_TOUR_PRICE;
import static by.epam.project.dao.entquery.TourQuery.DAO_TOUR_TOTAL_SEATS;
import static by.epam.project.dao.entquery.UserQuery.DAO_USER_LOGIN;
import by.epam.project.dao.query.Criteria;
import by.epam.project.exception.DaoUserLogicException;
import by.epam.project.exception.TechnicalException;
import by.epam.project.logic.TourLogic;
import by.epam.project.manager.ConfigurationManager;
import by.epam.project.manager.MessageManager;
import static by.epam.project.manager.ParamManager.checkFltParam;
import static by.epam.project.manager.ParamManager.checkIntParam;
import static by.epam.project.manager.ParamManager.getDateDiff;
import static by.epam.project.manager.ParamManager.getDateParam;
import java.util.Date;


/**
 *
 * @author User
 */
public class SaveRedactTour implements ActionCommand {

    @Override
    public String execute(SessionRequestContent request) throws DaoUserLogicException {
        String page = ConfigurationManager.getProperty("path.page.edittour");
        resaveParams(request);
        
        Criteria criteria = new Criteria();
        checkIntParam(request, criteria, JSP_CURR_ID_DIRECTION, DAO_ID_DIRECTION);
        checkIntParam(request, criteria, JSP_CURR_ID_TOUR, DAO_ID_TOUR);
        
        Date d1 = getDateParam(request, JSP_CURR_DEPART_DATE);
        Date d2 = getDateParam(request, JSP_CURR_ARRIVAL_DATE);
        criteria.addParam(DAO_TOUR_DATE, d1);
        criteria.addParam(DAO_TOUR_DAYS, getDateDiff(d1, d2));
        
        checkFltParam(request, criteria, JSP_TOUR_PRICE, DAO_TOUR_PRICE);
        checkIntParam(request, criteria, JSP_TOUR_DISCOUNT, DAO_TOUR_DISCOUNT);
        checkIntParam(request, criteria, JSP_TOTAL_SEATS, DAO_TOUR_TOTAL_SEATS);
        checkIntParam(request, criteria, JSP_FREE_SEATS, DAO_TOUR_FREE_SEATS);
        
        criteria.addParam(DAO_USER_LOGIN, request.getSessionAttribute(JSP_USER_LOGIN));
        criteria.addParam(DAO_ROLE_NAME, request.getSessionAttribute(JSP_ROLE_TYPE));
        
        try {
            Integer resIdTour = new TourLogic().doRedactEntity(criteria);
            request.setParameter(JSP_SELECT_ID, resIdTour.toString());
            return new ShowTour().execute(request);
        } catch (TechnicalException ex) {
            request.setAttribute("errorReason", ex.getMessage());
            request.setAttribute("errorAdminMsg", ex.getCause().getMessage());
            request.setAttribute("errorSaveData", MessageManager.getProperty("message.errorsavedata"));
            request.setSessionAttribute(JSP_PAGE, page);
            return page;
        }       
    }
    
    
}