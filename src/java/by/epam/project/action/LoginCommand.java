/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package by.epam.project.action;

import by.epam.project.controller.SessionRequestContent;
import static by.epam.project.dao.AbstractDao.*;
import by.epam.project.dao.DaoException;
import by.epam.project.dao.query.Criteria;
import by.epam.project.entity.User;
import by.epam.project.logic.LoginLogic;
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
        criteria.addParam(PARAM_NAME_LOGIN, request.getParameter(PARAM_NAME_LOGIN));
        criteria.addParam(PARAM_NAME_PASSWORD, request.getParameter(PARAM_NAME_PASSWORD).hashCode());
        criteria.addParam(PARAM_NAME_ROLE, request.getSessionAttribute(PARAM_NAME_ROLE));
        
        try {
            User person = LoginLogic.checkLogin(criteria);
            if (person != null) {
                request.setSessionAttribute(PARAM_NAME_LOGIN, person.getLogin());
                request.setSessionAttribute(PARAM_NAME_ROLE, person.getRole());
                Locale locale = getLocale(person.getLanguage());
                if (locale != null) {
                    request.setSessionAttribute(PARAM_NAME_LOCALE, locale);
                }
                request.setSessionAttribute(PARAM_NAME_DISCOUNT, person.getDiscount());
                request.setSessionAttribute(PARAM_NAME_BALANCE, person.getBalance());
                // определение пути к main.jsp
                page = ConfigurationManager.getProperty("path.page.main");
            } else {
                request.setAttribute("errorLoginPassMessage", MessageManager.getProperty("message.loginerror"));
                page = ConfigurationManager.getProperty("path.page.login");
            }
            request.setAttribute(PARAM_NAME_PAGE, page);
            return page;
        } catch (DaoException ex) {
            throw new DaoLogicException(MessageManager.getProperty("message.daoerror"));
        }
        
        
    }
    
}
