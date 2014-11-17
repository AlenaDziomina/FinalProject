/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package by.epam.project.action;

import static by.epam.project.action.JspParamNames.JSP_CURR_PAGE_NO;
import static by.epam.project.action.JspParamNames.JSP_PAGE;
import static by.epam.project.action.JspParamNames.JSP_PAGE_LIST;
import by.epam.project.entity.Direction;
import by.epam.project.entity.Order;
import by.epam.project.entity.Tour;
import by.epam.project.exception.ServletLogicException;
import by.epam.project.manager.ConfigurationManager;
import by.epam.project.tag.ObjList;

/**
 *
 * @author User
 */
public class ShowPage implements ActionCommand {

    @Override
    public String execute(SessionRequestContent request) throws ServletLogicException {
        String page = (String) request.getSessionAttribute(JSP_PAGE);
        String directionsPage = ConfigurationManager.getProperty("path.page.directions");
        String toursPage = ConfigurationManager.getProperty("path.page.tours");
        String ordersPage = ConfigurationManager.getProperty("path.page.orders");
        String userOrdersPage = ConfigurationManager.getProperty("path.page.userorder");
        if (page == null ? directionsPage == null : page.equals(directionsPage)) {
            showDirectionPage(request);
        } else if (page == null ? toursPage == null : page.equals(toursPage)) {
            showTourPage(request);
        } else if (page == null ? ordersPage == null : page.equals(ordersPage)) {
            showOrderPage(request);
        } else if (page == null ? userOrdersPage == null : page.equals(userOrdersPage)) {
            showUserOrderPage(request);
        }
        
        return page;
    }

    private void showDirectionPage(SessionRequestContent request) {
        ObjList<Direction> list = (ObjList<Direction>) request.getSessionAttribute(JSP_PAGE_LIST);
        Integer currPageNo = Integer.decode(request.getParameter(JSP_CURR_PAGE_NO));
        list.setCurrPageNo(currPageNo);
    }

    private void showTourPage(SessionRequestContent request) throws ServletLogicException {
        ObjList<Tour> list = (ObjList<Tour>) request.getSessionAttribute(JSP_PAGE_LIST);
        Integer currPageNo = Integer.decode(request.getParameter(JSP_CURR_PAGE_NO));
        list.setCurrPageNo(currPageNo);
    }

    private void showOrderPage(SessionRequestContent request) {
        ObjList<Order> list = (ObjList<Order>) request.getSessionAttribute(JSP_PAGE_LIST);
        Integer currPageNo = Integer.decode(request.getParameter(JSP_CURR_PAGE_NO));
        list.setCurrPageNo(currPageNo);
    }

    private void showUserOrderPage(SessionRequestContent request) {
        ObjList<Order> list = (ObjList<Order>) request.getSessionAttribute(JSP_PAGE_LIST);
        Integer currPageNo = Integer.decode(request.getParameter(JSP_CURR_PAGE_NO));
        list.setCurrPageNo(currPageNo);
    }
    
}
