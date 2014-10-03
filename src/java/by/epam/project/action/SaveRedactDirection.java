/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package by.epam.project.action;

import static by.epam.project.action.ActionCommand.checkArrParam;
import static by.epam.project.action.ActionCommand.checkParam;
import static by.epam.project.action.ActionCommand.checkParam;
import static by.epam.project.action.JspParamNames.JSP_CURR_CITY_TAGS;
import static by.epam.project.action.JspParamNames.JSP_CURR_COUNTRY_TAGS;
import static by.epam.project.action.JspParamNames.JSP_CURR_HOTEL_TAGS;
import static by.epam.project.action.JspParamNames.JSP_CURR_TOUR_TYPE;
import static by.epam.project.action.JspParamNames.JSP_CURR_TRANS_MODE;
import static by.epam.project.action.JspParamNames.JSP_DESCRIPTION_TEXT;
import static by.epam.project.action.JspParamNames.JSP_DIRECTION_NAME;
import static by.epam.project.action.JspParamNames.JSP_DIRECTION_PICTURE;
import static by.epam.project.action.JspParamNames.JSP_DIRECTION_STATUS;
import static by.epam.project.action.JspParamNames.JSP_DIRECTION_TEXT;
import static by.epam.project.action.JspParamNames.JSP_ID_DESCRIPTION;
import static by.epam.project.action.JspParamNames.JSP_ID_DIRECTION;
import static by.epam.project.action.JspParamNames.JSP_PAGE;
import static by.epam.project.action.JspParamNames.JSP_ROLE_TYPE;
import static by.epam.project.action.JspParamNames.JSP_USER_LOGIN;
import by.epam.project.controller.SessionRequestContent;
import static by.epam.project.dao.entquery.CityQuery.DAO_ID_CITY;
import static by.epam.project.dao.entquery.CountryQuery.DAO_ID_COUNTRY;
import static by.epam.project.dao.entquery.DescriptionQuery.DAO_DESCRIPTION_TEXT;
import static by.epam.project.dao.entquery.DescriptionQuery.DAO_ID_DESCRIPTION;
import static by.epam.project.dao.entquery.DirectionQuery.DAO_DIRECTION_NAME;
import static by.epam.project.dao.entquery.DirectionQuery.DAO_DIRECTION_PICTURE;
import static by.epam.project.dao.entquery.DirectionQuery.DAO_DIRECTION_STATUS;
import static by.epam.project.dao.entquery.DirectionQuery.DAO_DIRECTION_TEXT;
import static by.epam.project.dao.entquery.DirectionQuery.DAO_ID_DIRECTION;
import static by.epam.project.dao.entquery.HotelQuery.DAO_ID_HOTEL;
import static by.epam.project.dao.entquery.RoleQuery.DAO_ROLE_NAME;
import static by.epam.project.dao.entquery.TourTypeQuery.DAO_ID_TOURTYPE;
import static by.epam.project.dao.entquery.TransModeQuery.DAO_ID_TRANSMODE;
import static by.epam.project.dao.entquery.UserQuery.DAO_USER_LOGIN;
import by.epam.project.dao.query.Criteria;
import by.epam.project.exception.DaoException;
import by.epam.project.exception.DaoUserLogicException;
import by.epam.project.logic.DirectionLogic;
import by.epam.project.manager.ConfigurationManager;
import by.epam.project.manager.MessageManager;

/**
 *
 * @author User
 */
public class SaveRedactDirection implements ActionCommand {

    @Override
    public String execute(SessionRequestContent request) throws DaoUserLogicException {
        
        String page = null;
        new ProcessSavedParameters().execute(request);
        
        Criteria criteria = new Criteria();
        checkParam(request, criteria, JSP_ID_DIRECTION, DAO_ID_DIRECTION);
        checkParam(request, criteria, JSP_ID_DESCRIPTION, DAO_ID_DESCRIPTION);
        checkParam(request, criteria, JSP_CURR_TOUR_TYPE, DAO_ID_TOURTYPE);
        checkParam(request, criteria, JSP_CURR_TRANS_MODE, DAO_ID_TRANSMODE);
        
        criteria.addParam(DAO_USER_LOGIN, request.getSessionAttribute(JSP_USER_LOGIN));
        criteria.addParam(DAO_ROLE_NAME, request.getSessionAttribute(JSP_ROLE_TYPE));
        criteria.addParam(DAO_DIRECTION_NAME, request.getParameter(JSP_DIRECTION_NAME));
        criteria.addParam(DAO_DIRECTION_PICTURE, request.getParameter(JSP_DIRECTION_PICTURE));
        criteria.addParam(DAO_DIRECTION_TEXT, request.getParameter(JSP_DIRECTION_TEXT));
        criteria.addParam(DAO_DIRECTION_STATUS, request.getParameter(JSP_DIRECTION_STATUS));
        criteria.addParam(DAO_DESCRIPTION_TEXT, request.getParameter(JSP_DESCRIPTION_TEXT));
        
        checkArrParam(request, criteria, JSP_CURR_COUNTRY_TAGS, DAO_ID_COUNTRY);
        checkArrParam(request, criteria, JSP_CURR_CITY_TAGS, DAO_ID_CITY);
        checkArrParam(request, criteria, JSP_CURR_HOTEL_TAGS, DAO_ID_HOTEL);
        
        
        try {
            Integer resIdCountry = DirectionLogic.redactDirection(criteria);
            if (resIdCountry == null) {
                page = ConfigurationManager.getProperty("path.page.editdirection");
                request.setSessionAttribute(JSP_PAGE, page);
                request.setAttribute("errorSaveData", MessageManager.getProperty("message.errorsavedata"));
            } else {
                page = new GoShowDirections().execute(request);
//                request.setParameter(PARAM_NAME_SELECT_ID, resIdCountry.toString());
//                page = new ShowCountry().execute(request);
                request.setSessionAttribute(JSP_PAGE, page);
            }
            return page;
        } catch (DaoException ex) {
            throw new DaoUserLogicException(MessageManager.getProperty("message.daoerror"));
        }
        
    }
    
}
