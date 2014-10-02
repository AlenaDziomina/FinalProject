/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package by.epam.project.action;

import static by.epam.project.action.ActionCommand.checkArrParam;
import static by.epam.project.action.ActionCommand.checkParam;
import static by.epam.project.controller.JspParamNames.JSP_PAGE;
import static by.epam.project.controller.JspParamNames.JSP_ROLE_TYPE;
import static by.epam.project.controller.JspParamNames.JSP_USER_LOGIN;
import by.epam.project.controller.SessionRequestContent;
import static by.epam.project.dao.AbstractDao.PARAM_NAME_ID_CITY;
import static by.epam.project.dao.AbstractDao.PARAM_NAME_ID_COUNTRY;
import static by.epam.project.dao.AbstractDao.PARAM_NAME_ID_DESCRIPTION;
import static by.epam.project.dao.AbstractDao.PARAM_NAME_ID_DIRECTION;
import static by.epam.project.dao.AbstractDao.PARAM_NAME_ID_HOTEL;
import static by.epam.project.dao.AbstractDao.PARAM_NAME_ID_MODE;
import static by.epam.project.dao.AbstractDao.PARAM_NAME_ID_TOUR_TYPE;
import static by.epam.project.dao.AbstractDao.PARAM_NAME_NAME_DIRECTION;
import static by.epam.project.dao.AbstractDao.PARAM_NAME_PICTURE_DIRECTION;
import static by.epam.project.dao.AbstractDao.PARAM_NAME_STATUS_DIRECTION;
import static by.epam.project.dao.AbstractDao.PARAM_NAME_TEXT_DESCRIPTION;
import static by.epam.project.dao.AbstractDao.PARAM_NAME_TEXT_DIRECTION;
import by.epam.project.exception.DaoException;
import static by.epam.project.dao.entquery.RoleQuery.DAO_ROLE_NAME;
import static by.epam.project.dao.entquery.UserQuery.DAO_USER_LOGIN;
import by.epam.project.dao.query.Criteria;
import by.epam.project.logic.DirectionLogic;
import by.epam.project.manager.ConfigurationManager;
import by.epam.project.manager.MessageManager;

/**
 *
 * @author User
 */
public class SaveRedactDirection implements ActionCommand {

    @Override
    public String execute(SessionRequestContent request) throws DaoLogicException {
        
        String page = null;
        new ProcessSavedParameters().execute(request);
        
        Criteria criteria = new Criteria();
        checkParam(request, criteria, PARAM_NAME_ID_DIRECTION);
        checkParam(request, criteria, PARAM_NAME_ID_DESCRIPTION);
        checkParam(request, criteria, PARAM_NAME_CURR_TOUR_TYPE, PARAM_NAME_ID_TOUR_TYPE);
        checkParam(request, criteria, PARAM_NAME_CURR_TRANS_MODE, PARAM_NAME_ID_MODE);
        
        criteria.addParam(DAO_USER_LOGIN, request.getSessionAttribute(JSP_USER_LOGIN));
        criteria.addParam(DAO_ROLE_NAME, request.getSessionAttribute(JSP_ROLE_TYPE));
        criteria.addParam(PARAM_NAME_NAME_DIRECTION, request.getParameter(PARAM_NAME_NAME_DIRECTION));
        criteria.addParam(PARAM_NAME_PICTURE_DIRECTION, request.getParameter(PARAM_NAME_PICTURE_DIRECTION));
        criteria.addParam(PARAM_NAME_TEXT_DIRECTION, request.getParameter(PARAM_NAME_TEXT_DIRECTION));
        criteria.addParam(PARAM_NAME_STATUS_DIRECTION, request.getParameter(PARAM_NAME_STATUS_DIRECTION));
        criteria.addParam(PARAM_NAME_TEXT_DESCRIPTION, request.getParameter(PARAM_NAME_TEXT_DESCRIPTION));
        
        checkArrParam(request, criteria, PARAM_NAME_ID_COUNTRY, PARAM_NAME_CURR_COUNTRY_TAGS);
        checkArrParam(request, criteria, PARAM_NAME_ID_CITY, PARAM_NAME_CURR_CITY_TAGS);
        checkArrParam(request, criteria, PARAM_NAME_ID_HOTEL, PARAM_NAME_CURR_HOTEL_TAGS);
        
        
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
            throw new DaoLogicException(MessageManager.getProperty("message.daoerror"));
        }
        
    }
    
}
