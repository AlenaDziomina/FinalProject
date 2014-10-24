/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package by.epam.project.action.city;

import by.epam.project.action.ActionCommand;
import static by.epam.project.action.JspParamNames.JSP_CITY_NAME;
import static by.epam.project.action.JspParamNames.JSP_CITY_PICTURE;
import static by.epam.project.action.JspParamNames.JSP_CURR_ID_COUNTRY;
import static by.epam.project.action.JspParamNames.JSP_DESCRIPTION_TEXT;
import static by.epam.project.action.JspParamNames.JSP_ID_CITY;
import static by.epam.project.action.JspParamNames.JSP_ID_DESCRIPTION;
import static by.epam.project.action.JspParamNames.JSP_PAGE;
import static by.epam.project.action.JspParamNames.JSP_ROLE_TYPE;
import static by.epam.project.action.JspParamNames.JSP_SELECT_ID;
import static by.epam.project.action.JspParamNames.JSP_USER_LOGIN;
import static by.epam.project.action.ProcessSavedParameters.resaveParams;
import by.epam.project.action.SessionRequestContent;
import static by.epam.project.dao.entquery.CityQuery.DAO_CITY_NAME;
import static by.epam.project.dao.entquery.CityQuery.DAO_CITY_PICTURE;
import static by.epam.project.dao.entquery.CityQuery.DAO_ID_CITY;
import static by.epam.project.dao.entquery.CountryQuery.DAO_ID_COUNTRY;
import static by.epam.project.dao.entquery.DescriptionQuery.DAO_DESCRIPTION_TEXT;
import static by.epam.project.dao.entquery.DescriptionQuery.DAO_ID_DESCRIPTION;
import static by.epam.project.dao.entquery.RoleQuery.DAO_ROLE_NAME;
import static by.epam.project.dao.entquery.UserQuery.DAO_USER_LOGIN;
import by.epam.project.dao.query.Criteria;
import by.epam.project.exception.ServletLogicException;
import by.epam.project.exception.TechnicalException;
import by.epam.project.logic.CityLogic;
import by.epam.project.manager.ConfigurationManager;
import by.epam.project.manager.MessageManager;
import static by.epam.project.manager.ParamManager.checkIntParam;


/**
 *
 * @author User
 */
public class SaveRedactCity implements ActionCommand {

    @Override
    public String execute(SessionRequestContent request) throws ServletLogicException {
        String page = ConfigurationManager.getProperty("path.page.editcity");
        resaveParams(request);
        
        Criteria criteria = new Criteria();
        checkIntParam(request, criteria, JSP_CURR_ID_COUNTRY, DAO_ID_COUNTRY);
        checkIntParam(request, criteria, JSP_ID_CITY, DAO_ID_CITY);
        checkIntParam(request, criteria, JSP_ID_DESCRIPTION, DAO_ID_DESCRIPTION);
        
        criteria.addParam(DAO_USER_LOGIN, request.getSessionAttribute(JSP_USER_LOGIN));
        criteria.addParam(DAO_ROLE_NAME, request.getSessionAttribute(JSP_ROLE_TYPE));
        criteria.addParam(DAO_CITY_NAME, request.getParameter(JSP_CITY_NAME));
        criteria.addParam(DAO_CITY_PICTURE, request.getParameter(JSP_CITY_PICTURE));
        criteria.addParam(DAO_DESCRIPTION_TEXT, request.getParameter(JSP_DESCRIPTION_TEXT));
        try {
            Integer resIdCity = new CityLogic().doRedactEntity(criteria);
            request.setParameter(JSP_SELECT_ID, resIdCity.toString());
            return new ShowCity().execute(request); 
        } catch (TechnicalException ex) {
            request.setAttribute("errorReason", ex.getMessage());
            request.setAttribute("errorAdminMsg", ex.getCause().getMessage());
            request.setAttribute("errorSaveData", MessageManager.getProperty("message.errorsavedata"));
            request.setSessionAttribute(JSP_PAGE, page);
            return page;
        }         
    }
}