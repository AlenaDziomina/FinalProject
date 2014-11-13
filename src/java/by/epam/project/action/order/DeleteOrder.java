/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package by.epam.project.action.order;

import by.epam.project.action.ActionCommand;
import static by.epam.project.action.JspParamNames.*;
import by.epam.project.action.SessionRequestContent;
import static by.epam.project.dao.entquery.OrderQuery.DAO_ID_ORDER;
import static by.epam.project.dao.entquery.OrderQuery.DAO_ORDER_TOURIST_LIST;
import static by.epam.project.dao.entquery.RoleQuery.DAO_ROLE_NAME;
import static by.epam.project.dao.entquery.UserQuery.DAO_ID_USER;
import static by.epam.project.dao.entquery.UserQuery.DAO_USER_LOGIN;
import by.epam.project.dao.query.Criteria;
import by.epam.project.entity.ClientType;
import by.epam.project.entity.Order;
import by.epam.project.entity.User;
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
public class DeleteOrder extends OrderCommand implements ActionCommand {

    @Override
    public String execute(SessionRequestContent request) throws ServletLogicException {
        String page = ConfigurationManager.getProperty("path.page.order");
        
        Criteria criteria = new Criteria();
        Order order = (Order) request.getSessionAttribute(JSP_CURRENT_ORDER);
        if (order != null) {
            Integer idOrder = order.getIdOrder();
            if (idOrder != null) {
                criteria.addParam(DAO_ID_ORDER, idOrder);
            }
            criteria.addParam(DAO_ID_USER, order.getUser().getIdUser());
            criteria.addParam(DAO_ORDER_TOURIST_LIST, order.getTouristCollection());
        }
        
        User user = (User) request.getSessionAttribute(JSP_USER);
        if (user != null) {
            criteria.addParam(DAO_USER_LOGIN, user.getLogin());
            ClientType type = ClientTypeManager.clientTypeOf(user.getRole().getRoleName());
            criteria.addParam(DAO_ROLE_NAME, type);
        } else {
            criteria.addParam(DAO_ROLE_NAME, request.getSessionAttribute(JSP_ROLE_TYPE));
        }
        
        try {
            Integer resIdOrder = new OrderLogic().doDeleteEntity(criteria);
            request.setParameter(JSP_SELECT_ID, resIdOrder.toString());
            order.setStatus(DELETED);
            return new ShowOrder().execute(request);
        } catch (TechnicalException ex) {
            request.setAttribute("errorDeleteReason", ex.getMessage());
            request.setAttribute("errorDelete", "message.errorDeleteData");
            request.setSessionAttribute(JSP_PAGE, page);
            return page;
        }
    }   
}
