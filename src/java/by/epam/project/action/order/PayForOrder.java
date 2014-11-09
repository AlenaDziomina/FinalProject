/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package by.epam.project.action.order;

import by.epam.project.action.ActionCommand;
import static by.epam.project.action.JspParamNames.JSP_CURRENT_ORDER;
import static by.epam.project.action.JspParamNames.JSP_PAGE;
import static by.epam.project.action.JspParamNames.JSP_ROLE_TYPE;
import static by.epam.project.action.JspParamNames.JSP_SELECT_ID;
import static by.epam.project.action.JspParamNames.JSP_USER;
import by.epam.project.action.SessionRequestContent;
import static by.epam.project.dao.entquery.OrderQuery.*;
import static by.epam.project.dao.entquery.RoleQuery.DAO_ROLE_NAME;
import static by.epam.project.dao.entquery.TourQuery.DAO_ID_TOUR;
import static by.epam.project.dao.entquery.UserQuery.DAO_ID_USER;
import static by.epam.project.dao.entquery.UserQuery.DAO_USER_LOGIN;
import by.epam.project.dao.query.Criteria;
import by.epam.project.entity.ClientType;
import by.epam.project.entity.Order;
import by.epam.project.entity.User;
import by.epam.project.exception.LogicException;
import by.epam.project.exception.ServletLogicException;
import by.epam.project.exception.TechnicalException;
import by.epam.project.logic.OrderLogic;
import by.epam.project.manager.ClientTypeManager;
import by.epam.project.manager.ConfigurationManager;
import by.epam.project.manager.MessageManager;

/**
 *
 * @author User
 */
public class PayForOrder implements ActionCommand {

    @Override
    public String execute(SessionRequestContent request) throws ServletLogicException {
        String page = ConfigurationManager.getProperty("path.page.createorder");
        
        Criteria criteria = new Criteria();
        Order order = (Order) request.getSessionAttribute(JSP_CURRENT_ORDER);
        putOrderParams(criteria, order);
        User user = (User) request.getSessionAttribute(JSP_USER);
        if (user != null) {
            criteria.addParam(DAO_USER_LOGIN, user.getLogin());
            ClientType type = ClientTypeManager.clientTypeOf(user.getRole().getRoleName());
            criteria.addParam(DAO_ROLE_NAME, type);
        } else {
            criteria.addParam(DAO_ROLE_NAME, request.getSessionAttribute(JSP_ROLE_TYPE));
        }
        try {
            Integer resIdOrder = new OrderLogic().doRedactEntity(criteria);
            request.setParameter(JSP_SELECT_ID, resIdOrder.toString());
            return new GoShowUserOrder().execute(request);
        } catch (TechnicalException | LogicException ex) {
            request.setAttribute("errorReason", ex.getMessage());
            if (ex.getCause() != null) {
                request.setAttribute("errorAdminMsg", ex.getCause().getMessage());
            }
            request.setAttribute("errorSaveData", MessageManager.getProperty("message.errorsavedata"));
            request.setSessionAttribute(JSP_PAGE, page);
            return page;
        }       
        
    }
    
    private void putOrderParams(Criteria criteria, Order order) {
        criteria.addParam(DAO_ORDER_CURR_DISCOUNT, order.getCurrentDiscount());
        criteria.addParam(DAO_ORDER_CURR_PRICE, order.getCurrentPrice());
        criteria.addParam(DAO_ORDER_USER_DISCOUNT, order.getCurrentUserDiscount());
        criteria.addParam(DAO_ORDER_FINAL_PRICE, order.getFinalPrice());
        criteria.addParam(DAO_ORDER_SEATS, order.getSeats());
        criteria.addParam(DAO_ORDER_DATE, order.getOrderDate());
        criteria.addParam(DAO_ORDER_TOURIST_LIST, order.getTouristCollection());
        criteria.addParam(DAO_ID_TOUR, order.getTour().getIdTour());
        criteria.addParam(DAO_ID_USER, order.getUser().getIdUser());
    }
    
}
