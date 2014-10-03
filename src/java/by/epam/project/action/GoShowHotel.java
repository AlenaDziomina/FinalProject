/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package by.epam.project.action;

import static by.epam.project.controller.JspParamNames.JSP_HOTEL_COUNT;
import static by.epam.project.controller.JspParamNames.JSP_HOTEL_LIST;
import static by.epam.project.controller.JspParamNames.JSP_ID_CITY;
import static by.epam.project.controller.JspParamNames.JSP_PAGE;
import static by.epam.project.controller.JspParamNames.JSP_ROLE_TYPE;
import static by.epam.project.controller.JspParamNames.JSP_USER_LOGIN;
import by.epam.project.controller.SessionRequestContent;
import static by.epam.project.dao.entquery.CityQuery.DAO_ID_CITY;
import static by.epam.project.dao.entquery.RoleQuery.DAO_ROLE_NAME;
import static by.epam.project.dao.entquery.UserQuery.DAO_USER_LOGIN;
import by.epam.project.dao.query.Criteria;
import by.epam.project.entity.Hotel;
import by.epam.project.exception.DaoException;
import by.epam.project.logic.HotelLogic;
import by.epam.project.manager.ConfigurationManager;
import by.epam.project.manager.MessageManager;
import java.util.List;

/**
 *
 * @author User
 */
class GoShowHotel implements ActionCommand {

    public GoShowHotel() {
    }

    @Override
    public String execute(SessionRequestContent request) throws DaoLogicException {
        String page = ConfigurationManager.getProperty("path.page.hotels");
        request.setSessionAttribute(JSP_PAGE, page);
        
        Criteria criteria = new Criteria();
        criteria.addParam(DAO_USER_LOGIN, request.getSessionAttribute(JSP_USER_LOGIN));
        criteria.addParam(DAO_ROLE_NAME, request.getSessionAttribute(JSP_ROLE_TYPE));
        criteria.addParam(DAO_ID_CITY, request.getAttribute(JSP_ID_CITY));
        
        try {
            List<Hotel> hotels = HotelLogic.getHotels(criteria);
            if (hotels != null || !hotels.isEmpty()) {
                request.setSessionAttribute(JSP_HOTEL_LIST, hotels);
                request.setSessionAttribute(JSP_HOTEL_COUNT, hotels.size());
            } else {
                request.setAttribute("errorGetListMessage", MessageManager.getProperty("message.listerror"));
            }
            request.setSessionAttribute(JSP_PAGE, page);
            return page;
        } catch (DaoException ex) {
            throw new DaoLogicException(MessageManager.getProperty("message.daoerror"));
        }
    }
    
}
