/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package by.epam.project.action;

import static by.epam.project.action.ActionCommand.*;
import static by.epam.project.controller.JspParamNames.JSP_CITY_NAME;
import static by.epam.project.controller.JspParamNames.JSP_CITY_PICTURE;
import static by.epam.project.controller.JspParamNames.JSP_DESCRIPTION_TEXT;
import static by.epam.project.controller.JspParamNames.JSP_ID_CITY;
import static by.epam.project.controller.JspParamNames.JSP_ID_COUNTRY;
import static by.epam.project.controller.JspParamNames.JSP_ID_DESCRIPTION;
import static by.epam.project.controller.JspParamNames.JSP_PAGE;
import static by.epam.project.controller.JspParamNames.JSP_ROLE_TYPE;
import static by.epam.project.controller.JspParamNames.JSP_USER_LOGIN;
import by.epam.project.controller.SessionRequestContent;
import static by.epam.project.dao.AbstractDao.*;
import static by.epam.project.dao.entquery.CityQuery.DAO_CITY_NAME;
import static by.epam.project.dao.entquery.CityQuery.DAO_CITY_PICTURE;
import static by.epam.project.dao.entquery.CityQuery.DAO_ID_CITY;
import static by.epam.project.dao.entquery.CountryQuery.DAO_ID_COUNTRY;
import static by.epam.project.dao.entquery.DescriptionQuery.DAO_DESCRIPTION_TEXT;
import static by.epam.project.dao.entquery.DescriptionQuery.DAO_ID_DESCRIPTION;
import static by.epam.project.dao.entquery.RoleQuery.DAO_ROLE_NAME;
import static by.epam.project.dao.entquery.UserQuery.DAO_USER_LOGIN;
import by.epam.project.dao.query.Criteria;
import by.epam.project.exception.DaoException;
import by.epam.project.logic.CityLogic;
import by.epam.project.manager.ConfigurationManager;
import by.epam.project.manager.MessageManager;

/**
 *
 * @author User
 */
public class SaveRedactCity implements ActionCommand {

    public SaveRedactCity() {
    }

    @Override
    public String execute(SessionRequestContent request) throws DaoLogicException {
        
        String page = null;
        Criteria criteria = new Criteria();
        
        checkParam(request, criteria, JSP_ID_COUNTRY, DAO_ID_COUNTRY);
        checkParam(request, criteria, JSP_ID_CITY, DAO_ID_CITY);
        checkParam(request, criteria, JSP_ID_DESCRIPTION, DAO_ID_DESCRIPTION);
        
        criteria.addParam(DAO_USER_LOGIN, request.getSessionAttribute(JSP_USER_LOGIN));
        criteria.addParam(DAO_ROLE_NAME, request.getSessionAttribute(JSP_ROLE_TYPE));
        criteria.addParam(DAO_CITY_NAME, request.getParameter(JSP_CITY_NAME));
        criteria.addParam(DAO_CITY_PICTURE, request.getParameter(JSP_CITY_PICTURE));
        criteria.addParam(DAO_DESCRIPTION_TEXT, request.getParameter(JSP_DESCRIPTION_TEXT));
        
        try {
            Integer resIdCity = CityLogic.redactCity(criteria);
            if (resIdCity == null) {
                page = ConfigurationManager.getProperty("path.page.editcity");
                request.setSessionAttribute(JSP_PAGE, page);
                request.setAttribute("errorSaveData", MessageManager.getProperty("message.errorsavedata"));
            } else {
                new GoShowCity().execute(request);
                request.setParameter(PARAM_NAME_SELECT_ID, resIdCity.toString());
                page = new ShowCity().execute(request);
                request.setSessionAttribute(JSP_PAGE, page);
            }
            return page;
        } catch (DaoException ex) {
            throw new DaoLogicException(MessageManager.getProperty("message.daoerror"));
        }
        
    }
    
}
