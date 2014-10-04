/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package by.epam.project.action.city;

import by.epam.project.action.ActionCommand;
import static by.epam.project.action.JspParamNames.JSP_CITY_COUNT;
import static by.epam.project.action.JspParamNames.JSP_CITY_LIST;
import static by.epam.project.action.JspParamNames.JSP_ID_COUNTRY;
import static by.epam.project.action.JspParamNames.JSP_PAGE;
import static by.epam.project.action.JspParamNames.JSP_ROLE_TYPE;
import static by.epam.project.action.JspParamNames.JSP_USER_LOGIN;
import by.epam.project.controller.SessionRequestContent;
import static by.epam.project.dao.entquery.CountryQuery.DAO_ID_COUNTRY;
import static by.epam.project.dao.entquery.RoleQuery.DAO_ROLE_NAME;
import static by.epam.project.dao.entquery.UserQuery.DAO_USER_LOGIN;
import by.epam.project.dao.query.Criteria;
import by.epam.project.entity.City;
import by.epam.project.exception.DaoException;
import by.epam.project.exception.DaoUserLogicException;
import by.epam.project.logic.CityLogic;
import by.epam.project.manager.ConfigurationManager;
import by.epam.project.manager.MessageManager;
import java.util.List;

/**
 *
 * @author User
 */
public class GoShowCity implements ActionCommand {

    @Override
    public String execute(SessionRequestContent request) throws DaoUserLogicException {
        String page = ConfigurationManager.getProperty("path.page.cities");
        request.setSessionAttribute(JSP_PAGE, page);
        
        Criteria criteria = new Criteria();
        criteria.addParam(DAO_USER_LOGIN, request.getSessionAttribute(JSP_USER_LOGIN));
        criteria.addParam(DAO_ROLE_NAME, request.getSessionAttribute(JSP_ROLE_TYPE));
        criteria.addParam(DAO_ID_COUNTRY, request.getAttribute(JSP_ID_COUNTRY));
        
        try {
            List<City> cities = CityLogic.getCities(criteria);
            if (cities != null || !cities.isEmpty()) {
                request.setSessionAttribute(JSP_CITY_LIST, cities);
                request.setSessionAttribute(JSP_CITY_COUNT, cities.size());
            } else {
                request.setAttribute("errorGetListMessage", MessageManager.getProperty("message.listerror"));
            }
            request.setSessionAttribute(JSP_PAGE, page);
            return page;
        } catch (DaoException ex) {
            throw new DaoUserLogicException(MessageManager.getProperty("message.daoerror"));
        }
    }
    
}
