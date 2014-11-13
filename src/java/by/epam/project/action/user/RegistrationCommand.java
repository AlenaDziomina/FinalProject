/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package by.epam.project.action.user;

import by.epam.project.action.ActionCommand;
import static by.epam.project.action.JspParamNames.*;
import by.epam.project.action.SessionRequestContent;
import static by.epam.project.dao.entquery.RoleQuery.DAO_ROLE_NAME;
import static by.epam.project.dao.entquery.UserQuery.DAO_USER_EMAIL;
import static by.epam.project.dao.entquery.UserQuery.DAO_USER_LANGUAGE;
import static by.epam.project.dao.entquery.UserQuery.DAO_USER_LOGIN;
import static by.epam.project.dao.entquery.UserQuery.DAO_USER_PASSWORD;
import static by.epam.project.dao.entquery.UserQuery.DAO_USER_PHONE;
import by.epam.project.dao.query.Criteria;
import by.epam.project.entity.User;
import by.epam.project.exception.LogicException;
import by.epam.project.exception.ServletLogicException;
import by.epam.project.exception.TechnicalException;
import by.epam.project.logic.UserLogic;
import by.epam.project.manager.ConfigurationManager;
import by.epam.project.manager.MessageManager;
import by.epam.project.manager.Validator;
import java.util.Locale;

/**
 *
 * @author User
 */
public class RegistrationCommand implements ActionCommand {

    @Override
    public String execute(SessionRequestContent request) throws ServletLogicException{
        String page = ConfigurationManager.getProperty("path.page.registration");
        resaveParamsRegistrUser(request);
        try {
            Criteria criteria = new Criteria();
            criteria.addParam(DAO_ROLE_NAME, request.getSessionAttribute(JSP_ROLE_TYPE));
            User currUser = (User) request.getSessionAttribute(JSP_CURRENT_USER);
            Validator.validateUser(currUser);
            criteria.addParam(DAO_USER_EMAIL, currUser.getEmail());
            criteria.addParam(DAO_USER_PHONE, currUser.getPhone());
            criteria.addParam(DAO_USER_LANGUAGE, currUser.getLanguage());
            String login = request.getParameter(JSP_USER_LOGIN);
            Validator.validateLogin(login);
            criteria.addParam(DAO_USER_LOGIN, login);
            String password = request.getParameter(JSP_USER_PASSWORD);
            Validator.validatePassword(password);
            criteria.addParam(DAO_USER_PASSWORD, password.hashCode());
        
            Integer res = new UserLogic().doRedactEntity(criteria);
            if (res == null) {
                request.setAttribute("errorLoginPassMessage", "passLoginError");
            } else {
                return null;
            }
        } catch (TechnicalException | LogicException ex) {
            request.setAttribute("errorSaveReason", ex.getMessage());
            request.setAttribute("errorSave", "errorSaveData");
            request.setSessionAttribute(JSP_PAGE, page);
        }   
        return page;
    }
    
    private void resaveParamsRegistrUser(SessionRequestContent request) {
        User currUser = (User) request.getSessionAttribute(JSP_CURRENT_USER);
        if (currUser == null) {
            currUser = new User();
        }
        currUser.setLogin(request.getParameter(JSP_USER_LOGIN));
        currUser.setPhone(request.getParameter(JSP_USER_PHONE));
        currUser.setEmail(request.getParameter(JSP_USER_EMAIL));
        Locale locale = (Locale)(request.getSessionAttribute(JSP_LOCALE));
        currUser.setLanguage(locale.getLanguage());
        request.setSessionAttribute(JSP_CURRENT_USER, currUser);
    }
}
