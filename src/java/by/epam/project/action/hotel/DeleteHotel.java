/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package by.epam.project.action.hotel;

import by.epam.project.action.ActionCommand;
import static by.epam.project.action.JspParamNames.JSP_CURRENT_CITY;
import static by.epam.project.action.JspParamNames.JSP_CURRENT_HOTEL;
import static by.epam.project.action.JspParamNames.JSP_PAGE;
import static by.epam.project.action.JspParamNames.JSP_ROLE_TYPE;
import static by.epam.project.action.JspParamNames.JSP_SELECT_ID;
import static by.epam.project.action.JspParamNames.JSP_USER;
import by.epam.project.action.SessionRequestContent;
import by.epam.project.action.city.GoShowCity;
import by.epam.project.action.city.ShowCity;
import static by.epam.project.dao.entquery.CityQuery.DAO_ID_CITY;
import static by.epam.project.dao.entquery.HotelQuery.DAO_ID_HOTEL;
import static by.epam.project.dao.entquery.RoleQuery.DAO_ROLE_NAME;
import by.epam.project.dao.query.Criteria;
import by.epam.project.entity.City;
import by.epam.project.entity.ClientType;
import by.epam.project.entity.Hotel;
import by.epam.project.entity.User;
import by.epam.project.exception.ServletLogicException;
import by.epam.project.exception.TechnicalException;
import by.epam.project.logic.CityLogic;
import by.epam.project.logic.HotelLogic;
import by.epam.project.manager.ClientTypeManager;
import by.epam.project.manager.ConfigurationManager;
import by.epam.project.manager.MessageManager;

/**
 *
 * @author User
 */
public class DeleteHotel implements ActionCommand {

    @Override
    public String execute(SessionRequestContent request) throws ServletLogicException {
        String page = ConfigurationManager.getProperty("path.page.hotels");
        
        Criteria criteria = new Criteria();
        Hotel currHotel = (Hotel) request.getSessionAttribute(JSP_CURRENT_HOTEL);
        if (currHotel != null) {
            criteria.addParam(DAO_ID_HOTEL, currHotel.getIdHotel());
        }
        
        User user = (User) request.getSessionAttribute(JSP_USER);
        if (user != null) {
            ClientType type = ClientTypeManager.clientTypeOf(user.getRole().getRoleName());
            criteria.addParam(DAO_ROLE_NAME, type);
        } else {
            criteria.addParam(DAO_ROLE_NAME, request.getSessionAttribute(JSP_ROLE_TYPE));
        }
        
        try {
            Integer resIdHotel = new HotelLogic().doDeleteEntity(criteria);
            new GoShowHotel().execute(request);
            return new ShowHotel().execute(request);
        } catch (TechnicalException ex) {
            request.setAttribute("errorReason", ex.getMessage());
            request.setAttribute("errorAdminMsg", ex.getCause().getMessage());
            request.setAttribute("errorSaveData", MessageManager.getProperty("message.errorsavedata"));
            request.setSessionAttribute(JSP_PAGE, page);
            return page;
        }
    }
    
}
