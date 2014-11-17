/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package by.epam.project.action.user;

import by.epam.project.action.ActionCommand;
import static by.epam.project.action.JspParamNames.*;
import static by.epam.project.dao.DaoParamNames.*;
import by.epam.project.action.SessionRequestContent;
import by.epam.project.dao.query.Criteria;
import by.epam.project.entity.ClientType;
import by.epam.project.entity.User;
import by.epam.project.exception.ServletLogicException;
import by.epam.project.exception.TechnicalException;
import by.epam.project.logic.UserLogic;
import by.epam.project.manager.ClientTypeManager;
import by.epam.project.manager.ConfigurationManager;
import static by.epam.project.manager.LocaleManager.getLocale;
import by.epam.project.manager.Validator;
import java.util.List;
import java.util.Locale;

/**
 *
 * @author User
 */
public class LoginCommand implements ActionCommand{

    @Override
    public String execute(SessionRequestContent request) throws ServletLogicException{
        String page;
        try {
            Criteria criteria = new Criteria();
            String login = request.getParameter(JSP_USER_LOGIN);
            Validator.validateLogin(login);
            criteria.addParam(DAO_USER_LOGIN, login);
            String password = request.getParameter(JSP_USER_PASSWORD);
            Validator.validatePassword(password);
            criteria.addParam(DAO_USER_PASSWORD, password.hashCode());
            criteria.addParam(DAO_ROLE_NAME, request.getSessionAttribute(JSP_ROLE_TYPE));
            criteria.addParam(DAO_USER_STATUS, ACTIVE);
        
            List<User> users = new UserLogic().doGetEntity(criteria);
            if (users != null && ! users.isEmpty()) {
                User user = users.get(0);
                String role = user.getRole().getRoleName();
                request.setSessionAttribute(JSP_USER, user);
                ClientType type = ClientTypeManager.clientTypeOf(role);
                request.setSessionAttribute(JSP_ROLE_TYPE, type);
                Locale locale = getLocale(user.getLanguage());
                if (locale != null) {
                    request.setSessionAttribute(JSP_LOCALE, locale);
                }
                page = ConfigurationManager.getProperty("path.page.main");
            } else {
                request.setAttribute("errorLoginPassMessage", "message.passLoginError");
                page = ConfigurationManager.getProperty("path.page.login");
            }
            request.setSessionAttribute(JSP_PAGE, page);
            return page;
        } catch (TechnicalException ex) {
            throw new ServletLogicException(ex.getMessage(), ex);
        }
    }
    
}
