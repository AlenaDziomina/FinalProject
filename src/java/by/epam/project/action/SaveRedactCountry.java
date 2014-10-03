/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package by.epam.project.action;

import static by.epam.project.action.ActionCommand.checkParam;
import static by.epam.project.action.ActionCommand.checkParam;
import static by.epam.project.action.JspParamNames.JSP_COUNTRY_NAME;
import static by.epam.project.action.JspParamNames.JSP_COUNTRY_PICTURE;
import static by.epam.project.action.JspParamNames.JSP_DESCRIPTION_TEXT;
import static by.epam.project.action.JspParamNames.JSP_ID_COUNTRY;
import static by.epam.project.action.JspParamNames.JSP_ID_DESCRIPTION;
import static by.epam.project.action.JspParamNames.JSP_PAGE;
import static by.epam.project.action.JspParamNames.JSP_ROLE_TYPE;
import static by.epam.project.action.JspParamNames.JSP_SELECT_ID;
import static by.epam.project.action.JspParamNames.JSP_USER_LOGIN;
import by.epam.project.controller.SessionRequestContent;
import static by.epam.project.dao.entquery.CountryQuery.DAO_COUNTRY_NAME;
import static by.epam.project.dao.entquery.CountryQuery.DAO_COUNTRY_PICTURE;
import static by.epam.project.dao.entquery.CountryQuery.DAO_ID_COUNTRY;
import static by.epam.project.dao.entquery.DescriptionQuery.DAO_DESCRIPTION_TEXT;
import static by.epam.project.dao.entquery.DescriptionQuery.DAO_ID_DESCRIPTION;
import static by.epam.project.dao.entquery.RoleQuery.DAO_ROLE_NAME;
import static by.epam.project.dao.entquery.UserQuery.DAO_USER_LOGIN;
import by.epam.project.dao.query.Criteria;
import by.epam.project.exception.DaoException;
import by.epam.project.exception.DaoUserLogicException;
import by.epam.project.logic.CountryLogic;
import by.epam.project.manager.ConfigurationManager;
import by.epam.project.manager.MessageManager;

/**
 *
 * @author User
 */
class SaveRedactCountry implements ActionCommand {

    public SaveRedactCountry() {
    }

    @Override
    public String execute(SessionRequestContent request) throws DaoUserLogicException {
        
        String page = null;
        Criteria criteria = new Criteria();
        
        checkParam(request, criteria, JSP_ID_COUNTRY, DAO_ID_COUNTRY);
        checkParam(request, criteria, JSP_ID_DESCRIPTION, DAO_ID_DESCRIPTION);
        
        criteria.addParam(DAO_USER_LOGIN, request.getSessionAttribute(JSP_USER_LOGIN));
        criteria.addParam(DAO_ROLE_NAME, request.getSessionAttribute(JSP_ROLE_TYPE));
        criteria.addParam(DAO_COUNTRY_NAME, request.getParameter(JSP_COUNTRY_NAME));
        criteria.addParam(DAO_COUNTRY_PICTURE, request.getParameter(JSP_COUNTRY_PICTURE));
        criteria.addParam(DAO_DESCRIPTION_TEXT, request.getParameter(JSP_DESCRIPTION_TEXT));
        
        try {
            Integer resIdCountry = CountryLogic.redactCountry(criteria);
            if (resIdCountry == null) {
                page = ConfigurationManager.getProperty("path.page.editcountry");
                request.setSessionAttribute(JSP_PAGE, page);
                request.setAttribute("errorSaveData", MessageManager.getProperty("message.errorsavedata"));
            } else {
                new GoShowCountry().execute(request);
                request.setParameter(JSP_SELECT_ID, resIdCountry.toString());
                page = new ShowCountry().execute(request);
                request.setSessionAttribute(JSP_PAGE, page);
            }
            return page;
        } catch (DaoException ex) {
            throw new DaoUserLogicException(MessageManager.getProperty("message.daoerror"));
        }
        
    }
    
}
