/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package by.epam.project.action.hotel;

import by.epam.project.action.ActionCommand;
import static by.epam.project.action.JspParamNames.JSP_HOTEL_LIST;
import static by.epam.project.action.JspParamNames.JSP_ID_CITY;
import static by.epam.project.action.JspParamNames.JSP_PAGE;
import static by.epam.project.action.JspParamNames.JSP_ROLE_TYPE;
import static by.epam.project.action.JspParamNames.JSP_USER_LOGIN;
import static by.epam.project.action.SessionGarbageCollector.cleanSession;
import by.epam.project.action.SessionRequestContent;
import static by.epam.project.dao.entquery.CityQuery.DAO_ID_CITY;
import static by.epam.project.dao.entquery.RoleQuery.DAO_ROLE_NAME;
import static by.epam.project.dao.entquery.UserQuery.DAO_USER_LOGIN;
import by.epam.project.dao.query.Criteria;
import by.epam.project.entity.Hotel;
import by.epam.project.exception.ServletLogicException;
import by.epam.project.exception.TechnicalException;
import by.epam.project.logic.HotelLogic;
import by.epam.project.manager.ConfigurationManager;
import java.util.List;

/**
 *
 * @author User
 */
public class GoShowHotel implements ActionCommand {

    @Override
    public String execute(SessionRequestContent request) throws ServletLogicException {
        String page = ConfigurationManager.getProperty("path.page.hotels");
        request.setSessionAttribute(JSP_PAGE, page);
        formHotelList(request);
        cleanSession(request);
        return page;
    }
    
    public static void formHotelList(SessionRequestContent request) throws ServletLogicException {
        Criteria criteria = new Criteria();
        criteria.addParam(DAO_USER_LOGIN, request.getSessionAttribute(JSP_USER_LOGIN));
        criteria.addParam(DAO_ROLE_NAME, request.getSessionAttribute(JSP_ROLE_TYPE));
        criteria.addParam(DAO_ID_CITY, request.getAttribute(JSP_ID_CITY));
        
        try {
            List<Hotel> hotels = new HotelLogic().doGetEntity(criteria);
            request.setSessionAttribute(JSP_HOTEL_LIST, hotels);
        } catch (TechnicalException ex) {
            throw new ServletLogicException(ex.getMessage(), ex);
        }
    }
            
}
