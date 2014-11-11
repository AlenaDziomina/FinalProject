/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package by.epam.project.action.order;

import by.epam.project.action.ActionCommand;
import static by.epam.project.action.JspParamNames.DELETED;
import static by.epam.project.action.JspParamNames.JSP_CURRENT_ORDER;
import static by.epam.project.action.JspParamNames.JSP_PAGE;
import static by.epam.project.action.JspParamNames.JSP_ROLE_TYPE;
import static by.epam.project.action.JspParamNames.JSP_SELECT_ID;
import static by.epam.project.action.JspParamNames.JSP_TOURIST_FIRST_NAME;
import static by.epam.project.action.JspParamNames.JSP_TOURIST_LAST_NAME;
import static by.epam.project.action.JspParamNames.JSP_TOURIST_MIDDLE_NAME;
import static by.epam.project.action.JspParamNames.JSP_TOURIST_PASSPORT;
import static by.epam.project.action.JspParamNames.JSP_USER;
import by.epam.project.action.SessionRequestContent;
import by.epam.project.dao.entquery.OrderQuery;
import static by.epam.project.dao.entquery.OrderQuery.DAO_ID_ORDER;
import static by.epam.project.dao.entquery.OrderQuery.DAO_ORDER_STATUS;
import static by.epam.project.dao.entquery.OrderQuery.DAO_ORDER_TOURIST_LIST;
import static by.epam.project.dao.entquery.RoleQuery.DAO_ROLE_NAME;
import static by.epam.project.dao.entquery.UserQuery.DAO_USER_LOGIN;
import by.epam.project.dao.query.Criteria;
import by.epam.project.entity.ClientType;
import by.epam.project.entity.Order;
import by.epam.project.entity.Tourist;
import by.epam.project.entity.User;
import by.epam.project.exception.LogicException;
import by.epam.project.exception.ServletLogicException;
import by.epam.project.exception.TechnicalException;
import by.epam.project.logic.OrderLogic;
import by.epam.project.manager.ClientTypeManager;
import by.epam.project.manager.ConfigurationManager;
import by.epam.project.manager.MessageManager;
import by.epam.project.manager.PriceDiscountManager;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author User
 */
public class SaveRedactOrder implements ActionCommand {

    @Override
    public String execute(SessionRequestContent request) throws ServletLogicException {
        String page = ConfigurationManager.getProperty("path.page.edittourist");
        try {
            resaveParamsSaveOrder(request);
        
            Criteria criteria = new Criteria();
            Order order = (Order) request.getSessionAttribute(JSP_CURRENT_ORDER);
            if (order != null) {
                Integer idOrder = order.getIdOrder();
                if (idOrder != null) {
                    criteria.addParam(DAO_ID_ORDER, idOrder);
                }
                
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
        
            Integer resIdOrder = new OrderLogic().doRedactEntity(criteria);
            request.setParameter(JSP_SELECT_ID, resIdOrder.toString());
            return new ShowOrder().execute(request);
        } catch (TechnicalException | LogicException ex) {
            request.setAttribute("errorReason", ex.getMessage());
            request.setAttribute("errorAdminMsg", ex.getCause().getMessage());
            request.setAttribute("errorSaveData", MessageManager.getProperty("message.errorsavedata"));
            request.setSessionAttribute(JSP_PAGE, page);
            return page;
        }
    }

    private void resaveParamsSaveOrder(SessionRequestContent request) throws TechnicalException, LogicException {
        
        Order order = (Order) request.getSessionAttribute(JSP_CURRENT_ORDER);
        if (order == null) {
            throw new TechnicalException("Redacted order is null.");
        }
        List<Tourist> touristList = (List<Tourist>) order.getTouristCollection();
        Integer seats = order.getSeats();
        if (touristList.size() != seats) {
            throw new TechnicalException("Count of tourists not equals to count of tourists.");
        }
        String[] firstName = request.getAllParameters(JSP_TOURIST_FIRST_NAME);
        if (firstName == null || firstName.length < seats) {
            throw new TechnicalException("Error in first name data.");
        }
        String[] middleName = request.getAllParameters(JSP_TOURIST_MIDDLE_NAME);
        if (middleName == null || middleName.length < seats) {
            throw new TechnicalException("Error in middle name data.");
        }
        String[] lastName = request.getAllParameters(JSP_TOURIST_LAST_NAME);
        if (lastName == null || lastName.length < seats) {
            throw new TechnicalException("Error in last name data.");
        }
        String[] passport = request.getAllParameters(JSP_TOURIST_PASSPORT);
        if (passport == null || passport.length < seats) {
            throw new TechnicalException("Error in passport data.");
        }
                
        for (int i = 0; i < seats; i++) {
            Tourist tourist = touristList.get(i);
            tourist.setFirstName(firstName[i]);
            tourist.setMiddleName(middleName[i]);
            tourist.setLastName(lastName[i]);
            tourist.setPassport(passport[i]);
        }
        request.setSessionAttribute(JSP_CURRENT_ORDER, order);
    }
    
}
