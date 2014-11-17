/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package by.epam.project.action.order;

import by.epam.project.action.ActionCommand;
import static by.epam.project.action.JspParamNames.JSP_CURRENT_TOUR;
import static by.epam.project.action.JspParamNames.JSP_ORDER_LIST;
import static by.epam.project.action.JspParamNames.JSP_PAGE;
import static by.epam.project.action.JspParamNames.JSP_TOURIST_LIST;
import by.epam.project.action.SessionRequestContent;
import by.epam.project.entity.Order;
import by.epam.project.entity.Tourist;
import by.epam.project.exception.ServletLogicException;
import by.epam.project.manager.ConfigurationManager;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author User
 */
public class ShowTourTourists extends OrderCommand implements ActionCommand {

    @Override
    public String execute(SessionRequestContent request) throws ServletLogicException {
        String page = ConfigurationManager.getProperty("path.page.tourists");
        request.setSessionAttribute(JSP_PAGE, page);
        resaveParamsShowOrder(request);
        formOrderList(request);
        formTouristsList(request);
        return page;
    }

    private void formTouristsList(SessionRequestContent request) {
        List<Order> orders = (List<Order>) request.getSessionAttribute(JSP_ORDER_LIST);
        List<Tourist> tourists = new ArrayList();
        if (orders != null && !orders.isEmpty()) {
            for (Order order: orders) {
                tourists.addAll(order.getTouristCollection());
            }
            request.setSessionAttribute(JSP_CURRENT_TOUR, orders.get(0).getTour());
        }
        request.setSessionAttribute(JSP_TOURIST_LIST, tourists);
    }
    
    
}
