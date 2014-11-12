/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package by.epam.project.action.order;

import by.epam.project.action.ActionCommand;
import static by.epam.project.action.JspParamNames.*;
import by.epam.project.action.SessionRequestContent;
import static by.epam.project.action.order.GoShowUserOrder.cleanSessionShowOrder;
import static by.epam.project.action.order.GoShowUserOrder.resaveParamsShowOrder;
import by.epam.project.entity.Order;
import by.epam.project.exception.ServletLogicException;
import by.epam.project.manager.ConfigurationManager;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;

/**
 *
 * @author User
 */
public class ShowOrder implements ActionCommand {

    @Override
    public String execute(SessionRequestContent request) throws ServletLogicException {
        String page = ConfigurationManager.getProperty("path.page.order");
        String prevPage = (String) request.getSessionAttribute(JSP_PAGE);
        resaveParamsShowOrder(request);
        showSelectedOrder(request);
        if(page == null ? prevPage == null : !page.equals(prevPage)){
            request.setSessionAttribute(JSP_PAGE, page);
            cleanSessionShowOrder(request);
        }
        return page;
    }
    
    private void showSelectedOrder(SessionRequestContent request) {
        String selected = request.getParameter(JSP_SELECT_ID);
        Order currOrder = null;
        if (selected != null) {
            Integer idOrder = Integer.decode(selected); 
            if (idOrder != null) {
                List<Order> list = (List<Order>) request.getSessionAttribute(JSP_ORDER_LIST);
                Iterator<Order> it = list.iterator();
                while (it.hasNext() && currOrder == null) {
                    Order order = it.next();
                    if (Objects.equals(order.getIdOrder(), idOrder)) {
                        currOrder = order;
                        request.setAttribute(JSP_CURR_ID_ORDER, idOrder);
                    }
                }
            }
        }
        request.setSessionAttribute(JSP_CURRENT_ORDER, currOrder);
    }
}