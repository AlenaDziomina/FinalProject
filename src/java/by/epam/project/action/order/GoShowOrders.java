/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package by.epam.project.action.order;

import by.epam.project.action.ActionCommand;
import static by.epam.project.action.JspParamNames.*;
import by.epam.project.action.SessionRequestContent;
import by.epam.project.exception.ServletLogicException;
import by.epam.project.manager.ConfigurationManager;

/**
 *
 * @author User
 */
public class GoShowOrders extends OrderCommand implements ActionCommand {

    @Override
    public String execute(SessionRequestContent request) throws ServletLogicException {
        String page = ConfigurationManager.getProperty("path.page.orders");
        String prevPage = (String) request.getSessionAttribute(JSP_PAGE);
        resaveParamsShowOrder(request);
        
        formOrderList(request);
        if (page == null ? prevPage != null : ! page.equals(prevPage)) {
            request.setSessionAttribute(JSP_PAGE, page);
            cleanSessionShowOrder(request);
        }
        return page;
    }
    
}
