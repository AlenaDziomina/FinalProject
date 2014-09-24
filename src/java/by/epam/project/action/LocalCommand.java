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
import by.epam.project.logic.LocalLogic;
import by.epam.project.manager.ConfigurationManager;
import by.epam.project.manager.LocaleManager;
import by.epam.project.manager.MessageManager;
import java.util.Locale;

/**
 *
 * @author User
 */
public class LocalCommand implements ActionCommand {
    
    

    @Override
    public String execute(SessionRequestContent request) throws DaoLogicException {
        
        String leng = request.getParameter(PARAM_NAME_LENG);
        Locale locale = LocaleManager.getLocale(leng);
        if (locale != null) {
            request.setSessionAttribute(PARAM_NAME_LOCALE, locale);
        }
        String page = request.getParameter(PARAM_NAME_PAGE);
        if (page == null) {
            page = ConfigurationManager.getProperty("path.page.index");
            request.setAttribute(PARAM_NAME_PAGE, page);
        }
        
        Criteria bean = new Criteria();
        bean.addParam(PARAM_NAME_LOGIN, request.getSessionAttribute(PARAM_NAME_LOGIN));
        bean.addParam(PARAM_NAME_ID_USER, request.getSessionAttribute(PARAM_NAME_ID_USER));
        bean.addParam(PARAM_NAME_ROLE, request.getSessionAttribute(PARAM_NAME_ROLE));
        
        Criteria criteria = new Criteria();
        Locale currLocale = (Locale)(request.getSessionAttribute(PARAM_NAME_LOCALE));
        criteria.addParam(PARAM_NAME_LANGUAGE, currLocale.getLanguage());
        
        
        try {
            User updUser = LocalLogic.setUserLocal(bean, criteria);           
        } catch (DaoException ex) {
            throw new DaoLogicException(MessageManager.getProperty("message.daoerror"));
        }
        
        
        
        
        return page;
    }
    
}
