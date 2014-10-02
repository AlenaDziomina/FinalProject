/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package by.epam.project.action;

import static by.epam.project.controller.JspParamNames.JSP_LOCALE;
import static by.epam.project.controller.JspParamNames.JSP_PAGE;
import static by.epam.project.controller.JspParamNames.JSP_ROLE_TYPE;
import static by.epam.project.controller.JspParamNames.JSP_USER_BALANCE;
import static by.epam.project.controller.JspParamNames.JSP_USER_DISCOUNT;
import static by.epam.project.controller.JspParamNames.JSP_USER_LOGIN;
import static by.epam.project.controller.JspParamNames.JSP_USER_PASSWORD;
import by.epam.project.controller.SessionRequestContent;
import by.epam.project.dao.ClientType;
import static by.epam.project.dao.entquery.RoleQuery.DAO_ROLE_NAME;
import static by.epam.project.dao.entquery.UserQuery.DAO_USER_LOGIN;
import static by.epam.project.dao.entquery.UserQuery.DAO_USER_PASSWORD;
import by.epam.project.dao.query.Criteria;
import by.epam.project.entity.User;
import by.epam.project.exception.DaoException;
import by.epam.project.logic.UserLogic;
import by.epam.project.manager.ClientTypeManager;
import by.epam.project.manager.ConfigurationManager;
import static by.epam.project.manager.LocaleManager.getLocale;
import by.epam.project.manager.MessageManager;
import java.util.Locale;

/**
 *
 * @author User
 */
public class LoginCommand implements ActionCommand{

    @Override
    public String execute(SessionRequestContent request) throws DaoLogicException{
        String page = null;
        
        Criteria criteria = new Criteria();
        criteria.addParam(DAO_USER_LOGIN, request.getParameter(JSP_USER_LOGIN));
        criteria.addParam(DAO_USER_PASSWORD, request.getParameter(JSP_USER_PASSWORD).hashCode());
        criteria.addParam(DAO_ROLE_NAME, request.getSessionAttribute(JSP_ROLE_TYPE));
        
        try {
            User person = UserLogic.checkLogin(criteria);
            if (person != null) {
                request.setSessionAttribute(JSP_USER_LOGIN, person.getLogin());
                String role = person.getRole().getRoleName();
                ClientType type = ClientTypeManager.clientTypeOf(role);
                request.setSessionAttribute(JSP_ROLE_TYPE, type);
                Locale locale = getLocale(person.getLanguage());
                if (locale != null) {
                    request.setSessionAttribute(JSP_LOCALE, locale);
                }
                request.setSessionAttribute(JSP_USER_DISCOUNT, person.getDiscount());
                request.setSessionAttribute(JSP_USER_BALANCE, person.getBalance());
                // определение пути к main.jsp
                page = ConfigurationManager.getProperty("path.page.main");
            } else {
                request.setAttribute("errorLoginPassMessage", MessageManager.getProperty("message.loginerror"));
                page = ConfigurationManager.getProperty("path.page.login");
            }
            request.setSessionAttribute(JSP_PAGE, page);
            return page;
        } catch (DaoException ex) {
            throw new DaoLogicException(MessageManager.getProperty("message.daoerror"));
        }
        
        
    }
    
}
