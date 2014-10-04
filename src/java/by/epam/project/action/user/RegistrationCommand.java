/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package by.epam.project.action.user;

import by.epam.project.action.ActionCommand;
import static by.epam.project.action.JspParamNames.JSP_LOCALE;
import static by.epam.project.action.JspParamNames.JSP_ROLE_TYPE;
import static by.epam.project.action.JspParamNames.JSP_USER_EMAIL;
import static by.epam.project.action.JspParamNames.JSP_USER_LOGIN;
import static by.epam.project.action.JspParamNames.JSP_USER_PASSWORD;
import static by.epam.project.action.JspParamNames.JSP_USER_PHONE;
import by.epam.project.controller.SessionRequestContent;
import static by.epam.project.dao.entquery.RoleQuery.DAO_ROLE_NAME;
import static by.epam.project.dao.entquery.UserQuery.DAO_USER_EMAIL;
import static by.epam.project.dao.entquery.UserQuery.DAO_USER_LANGUAGE;
import static by.epam.project.dao.entquery.UserQuery.DAO_USER_LOGIN;
import static by.epam.project.dao.entquery.UserQuery.DAO_USER_PASSWORD;
import static by.epam.project.dao.entquery.UserQuery.DAO_USER_PHONE;
import by.epam.project.dao.query.Criteria;
import by.epam.project.exception.DaoException;
import by.epam.project.exception.DaoUserLogicException;
import by.epam.project.logic.UserLogic;
import by.epam.project.manager.MessageManager;
import java.util.Locale;

/**
 *
 * @author User
 */
public class RegistrationCommand implements ActionCommand {

    @Override
    public String execute(SessionRequestContent request) throws DaoUserLogicException{
        String page = null;
        Criteria criteria = new Criteria();
        
        criteria.addParam(DAO_USER_LOGIN, request.getSessionAttribute(JSP_USER_LOGIN));
        criteria.addParam(DAO_ROLE_NAME, request.getSessionAttribute(JSP_ROLE_TYPE));
        criteria.addParam(DAO_USER_PASSWORD, request.getParameter(JSP_USER_PASSWORD).hashCode());
        criteria.addParam(DAO_USER_EMAIL, request.getParameter(JSP_USER_EMAIL));
        criteria.addParam(DAO_USER_PHONE, request.getParameter(JSP_USER_PHONE));
        Locale locale = (Locale)(request.getSessionAttribute(JSP_LOCALE));
        criteria.addParam(DAO_USER_LANGUAGE, locale.getLanguage());
        
        try {
            UserLogic.userRegistration(criteria);
        } catch (DaoException ex) {
            throw new DaoUserLogicException(MessageManager.getProperty("message.daoerror"));
        }
        return null;
    }
    
}
