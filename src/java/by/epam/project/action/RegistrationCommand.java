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
import by.epam.project.logic.RegistrationLogic;
import by.epam.project.manager.MessageManager;
import java.util.Locale;

/**
 *
 * @author User
 */
public class RegistrationCommand implements ActionCommand {

    

    @Override
    public String execute(SessionRequestContent request) throws DaoLogicException{
        String page = null;
        Criteria criteria = new Criteria();
        
        criteria.addParam(PARAM_NAME_LOGIN, request.getParameter(PARAM_NAME_LOGIN));
        criteria.addParam(PARAM_NAME_PASSWORD, request.getParameter(PARAM_NAME_PASSWORD).hashCode());
        criteria.addParam(PARAM_NAME_EMAIL, request.getParameter(PARAM_NAME_EMAIL));
        criteria.addParam(PARAM_NAME_PHONE, request.getParameter(PARAM_NAME_PHONE));
        Locale locale = (Locale)(request.getSessionAttribute(PARAM_NAME_LOCALE));
        criteria.addParam(PARAM_NAME_LANGUAGE, locale.getLanguage());
        criteria.addParam(PARAM_NAME_ROLE, request.getSessionAttribute(PARAM_NAME_ROLE));
               
        // проверка логина и пароля
        
        try {
            RegistrationLogic.getRegistration(criteria);
        } catch (DaoException ex) {
            throw new DaoLogicException(MessageManager.getProperty("message.daoerror"));
        }
        return null;
    }
    
}
