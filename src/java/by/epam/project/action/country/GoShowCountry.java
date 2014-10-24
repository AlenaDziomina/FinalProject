/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package by.epam.project.action.country;


import by.epam.project.action.ActionCommand;
import static by.epam.project.action.JspParamNames.JSP_COUNTRY_INVALID_STATUS;
import static by.epam.project.action.JspParamNames.JSP_COUNTRY_LIST;
import static by.epam.project.action.JspParamNames.JSP_COUNTRY_VALID_STATUS;
import static by.epam.project.action.JspParamNames.JSP_PAGE;
import static by.epam.project.action.JspParamNames.JSP_ROLE_TYPE;
import static by.epam.project.action.JspParamNames.JSP_USER_LOGIN;
import static by.epam.project.action.ProcessSavedParameters.resaveParamsShowCountry;
import static by.epam.project.action.SessionGarbageCollector.cleanSession;
import by.epam.project.action.SessionRequestContent;
import static by.epam.project.action.city.GoShowCity.getCityStatus;
import static by.epam.project.action.country.ShowCountry.showSelectedCountry;
import static by.epam.project.dao.entquery.CityQuery.DAO_CITY_STATUS;
import static by.epam.project.dao.entquery.CountryQuery.DAO_COUNTRY_STATUS;
import static by.epam.project.dao.entquery.RoleQuery.DAO_ROLE_NAME;
import static by.epam.project.dao.entquery.UserQuery.DAO_USER_LOGIN;
import by.epam.project.dao.query.Criteria;
import by.epam.project.entity.Country;
import by.epam.project.exception.ServletLogicException;
import by.epam.project.exception.TechnicalException;
import by.epam.project.logic.CountryLogic;
import by.epam.project.manager.ConfigurationManager;
import static by.epam.project.manager.ParamManager.getBoolParam;
import java.util.List;

/**
 *
 * @author Grouk.Helena
 */
public class GoShowCountry implements ActionCommand {

    @Override
    public String execute(SessionRequestContent request) throws ServletLogicException {
        String page = ConfigurationManager.getProperty("path.page.countries");
        String prevPage = (String) request.getSessionAttribute(JSP_PAGE);
        resaveParamsShowCountry(request);
        
        formCountryList(request);
        if (page == null ? prevPage != null : page.equals(prevPage)) {
            showSelectedCountry(request);
        } else {
            request.setSessionAttribute(JSP_PAGE, page);
            cleanSession(request);
        }
        return page;
    }   
    
    public static void formCountryList(SessionRequestContent request)throws ServletLogicException {
        Criteria criteria = new Criteria();
        criteria.addParam(DAO_USER_LOGIN, request.getSessionAttribute(JSP_USER_LOGIN));
        criteria.addParam(DAO_ROLE_NAME, request.getSessionAttribute(JSP_ROLE_TYPE));
        
        Integer countryStatus = getCountryStatus(request);
        if (countryStatus != null) {
            criteria.addParam(DAO_COUNTRY_STATUS, countryStatus);
        }
        
        Integer cityStatus = getCityStatus(request);
        if (cityStatus != null) {
            criteria.addParam(DAO_CITY_STATUS, cityStatus);
        }
        
        try {
            List<Country> countrys = new CountryLogic().doGetEntity(criteria);
            request.setSessionAttribute(JSP_COUNTRY_LIST, countrys);
        } catch (TechnicalException ex) {
            throw new ServletLogicException(ex.getMessage(), ex);
        }
    }

    public static Integer getCountryStatus(SessionRequestContent request) {
        Boolean validStatus = getBoolParam(request, JSP_COUNTRY_VALID_STATUS);
        Boolean invalidStatus = getBoolParam(request, JSP_COUNTRY_INVALID_STATUS);
        if (validStatus == null && invalidStatus == null) {
            validStatus = true;
            invalidStatus = false;
        } else {
            if (validStatus == null) {
                validStatus = false;
            }
            if (invalidStatus == null) {
                invalidStatus = false;
            }
        }
        request.setAttribute(JSP_COUNTRY_VALID_STATUS, validStatus);
        request.setAttribute(JSP_COUNTRY_INVALID_STATUS, invalidStatus);
        
        Integer status = null;
        if (validStatus && ! invalidStatus) {
            status = 1;
        } else if ( ! validStatus && invalidStatus) {
            status = 0;
        }
        return status;
    }
}
