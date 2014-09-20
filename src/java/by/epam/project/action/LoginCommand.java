/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package by.epam.project.action;

import by.epam.project.controller.SessionRequestContent;
import by.epam.project.dao.DaoException;
import by.epam.project.entity.User;
import by.epam.project.logic.LoginLogic;
import by.epam.project.manager.ConfigurationManager;
import static by.epam.project.manager.LocaleManager.getLocale;
import by.epam.project.manager.MessageManager;
import java.util.Locale;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author User
 */
public class LoginCommand implements ActionCommand{

    private static final String PARAM_NAME_LOGIN = "login";
    private static final String PARAM_NAME_PASSWORD = "password";
    private static final String PARAM_NAME_ROLE = "role";
    private static final String PARAM_NAME_EMAIL = "email";
    private static final String PARAM_NAME_LOCALE = "locale";
    private static final String PARAM_NAME_DISCOUNT = "discount";
    private static final String PARAM_NAME_BALANCE = "balance";

    @Override
    public String execute(SessionRequestContent request) throws DaoLogicException{
        String page = null;
        // извлечение из запроса логина и пароля
        String login = request.getParameter(PARAM_NAME_LOGIN);
        String pass = request.getParameter(PARAM_NAME_PASSWORD);
        String role = request.getParameter(PARAM_NAME_ROLE);
        // проверка логина и пароля
        User person;
        try {
            person = LoginLogic.checkLogin(login, pass, role);
            if (person != null) {
                request.setSessionAttribute(PARAM_NAME_LOGIN, person.getLogin().toUpperCase());
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
            return page;
        } catch (DaoException ex) {
            throw new DaoLogicException(MessageManager.getProperty("message.daoerror"));
        }
        
        
    }
    
}
