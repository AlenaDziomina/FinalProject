/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package by.epam.project.action.order;

import by.epam.project.action.ActionCommand;
import static by.epam.project.action.JspParamNames.JSP_CURRENT_ORDER;
import static by.epam.project.action.JspParamNames.JSP_CURRENT_TOUR;
import static by.epam.project.action.JspParamNames.JSP_CURR_ID_TOUR;
import static by.epam.project.action.JspParamNames.JSP_PAGE;
import static by.epam.project.action.JspParamNames.JSP_ROLE_TYPE;
import static by.epam.project.action.JspParamNames.JSP_USER;
import by.epam.project.action.SessionRequestContent;
import static by.epam.project.dao.entquery.RoleQuery.DAO_ROLE_NAME;
import static by.epam.project.dao.entquery.TourQuery.DAO_ID_TOUR;
import static by.epam.project.dao.entquery.UserQuery.DAO_USER_LOGIN;
import by.epam.project.dao.query.Criteria;
import by.epam.project.entity.ClientType;
import static by.epam.project.entity.ClientType.GUEST;
import by.epam.project.entity.Order;
import by.epam.project.entity.Tour;
import by.epam.project.entity.Tourist;
import by.epam.project.entity.User;
import by.epam.project.exception.ServletLogicException;
import by.epam.project.exception.TechnicalException;
import by.epam.project.logic.TourLogic;
import by.epam.project.manager.ClientTypeManager;
import static by.epam.project.manager.ClientTypeManager.clientTypeOf;
import by.epam.project.manager.ConfigurationManager;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *
 * @author User
 */
public class GoBuyTour implements ActionCommand {

    @Override
    public String execute(SessionRequestContent request) throws ServletLogicException {
        String page;
        User user = (User) request.getSessionAttribute(JSP_USER);
        if (user != null && clientTypeOf(user.getRole().getRoleName()) != GUEST) {
            page = ConfigurationManager.getProperty("path.page.editorder");
            findTour(request);
            createCurrOrder(request);
        } else {
            page = ConfigurationManager.getProperty("path.page.login");
        }
        request.setSessionAttribute(JSP_PAGE, page);
        //cleanSession(request);
        return page;
    }
    
    private static void findTour(SessionRequestContent request) throws ServletLogicException {
        Criteria criteria = new Criteria();
        criteria.addParam(DAO_ID_TOUR, request.getParameter(JSP_CURR_ID_TOUR));

        User user = (User) request.getSessionAttribute(JSP_USER);
        if (user != null) {
            criteria.addParam(DAO_USER_LOGIN, user.getLogin());
            ClientType type = ClientTypeManager.clientTypeOf(user.getRole().getRoleName());
            criteria.addParam(DAO_ROLE_NAME, type);
        } else {
            criteria.addParam(DAO_ROLE_NAME, request.getSessionAttribute(JSP_ROLE_TYPE));
        }
        try { 
            List<Tour> tours = new TourLogic().doGetEntity(criteria);
            if (tours != null && !tours.isEmpty()) {
                request.setSessionAttribute(JSP_CURRENT_TOUR, tours.get(0));
            }
        } catch (TechnicalException ex) {
            throw new ServletLogicException(ex.getMessage(), ex);
        }
    }
    
    private static void createCurrOrder(SessionRequestContent request){
        Order order = (Order) request.getSessionAttribute(JSP_CURRENT_ORDER);
        if (order == null) {
            order = new Order();
            order.setOrderDate(new Date());
            order.setSeats(1);
            List tourists = new ArrayList();
            tourists.add(new Tourist());
            order.setTouristCollection(tourists);
        }
        request.setSessionAttribute(JSP_CURRENT_ORDER, order);
    }
    
}
