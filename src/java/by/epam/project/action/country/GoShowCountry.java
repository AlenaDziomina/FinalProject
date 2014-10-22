/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package by.epam.project.action.country;


import by.epam.project.action.ActionCommand;
import static by.epam.project.action.JspParamNames.JSP_COUNTRY_LIST;
import static by.epam.project.action.JspParamNames.JSP_PAGE;
import static by.epam.project.action.JspParamNames.JSP_ROLE_TYPE;
import static by.epam.project.action.JspParamNames.JSP_USER_LOGIN;
import static by.epam.project.action.SessionGarbageCollector.cleanSession;
import by.epam.project.action.SessionRequestContent;
import static by.epam.project.dao.entquery.RoleQuery.DAO_ROLE_NAME;
import static by.epam.project.dao.entquery.UserQuery.DAO_USER_LOGIN;
import by.epam.project.dao.query.Criteria;
import by.epam.project.entity.Country;
import by.epam.project.exception.ServletLogicException;
import by.epam.project.exception.TechnicalException;
import by.epam.project.logic.CountryLogic;
import by.epam.project.manager.ConfigurationManager;
import java.util.List;

/**
 *
 * @author Grouk.Helena
 */
public class GoShowCountry implements ActionCommand {

    @Override
    public String execute(SessionRequestContent request) throws ServletLogicException {
        String page = ConfigurationManager.getProperty("path.page.countries");
        request.setSessionAttribute(JSP_PAGE, page);
        formCountryList(request);
        cleanSession(request);
        return page;
    }   
    
    public static void formCountryList(SessionRequestContent request)throws ServletLogicException {
        Criteria criteria = new Criteria();
        criteria.addParam(DAO_USER_LOGIN, request.getSessionAttribute(JSP_USER_LOGIN));
        criteria.addParam(DAO_ROLE_NAME, request.getSessionAttribute(JSP_ROLE_TYPE));
        try {
            List<Country> countrys = new CountryLogic().doGetEntity(criteria);
            request.setSessionAttribute(JSP_COUNTRY_LIST, countrys);
        } catch (TechnicalException ex) {
            throw new ServletLogicException(ex.getMessage(), ex);
        }
    }
}
