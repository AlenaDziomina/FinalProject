/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package by.epam.project.action.direction;

import by.epam.project.action.ActionCommand;
import static by.epam.project.action.JspParamNames.JSP_CURR_PAGE_NO;
import static by.epam.project.action.JspParamNames.JSP_PAGE;
import static by.epam.project.action.JspParamNames.JSP_PAGE_LIST;
import by.epam.project.action.SessionRequestContent;
import static by.epam.project.action.direction.GoShowDirections.resaveParamsShowDirections;
import static by.epam.project.action.tour.SearchTour.resaveParamsSearchTour;
import by.epam.project.entity.Direction;
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
        if (page == null ? directionsPage == null : page.equals(directionsPage)) {
            showDirectionPage(request);
        } else if (page == null ? toursPage == null : page.equals(toursPage)) {
            showTourPage(request);
        }
        return page;
    }

    private void showDirectionPage(SessionRequestContent request) {
        ObjList<Direction> list = (ObjList<Direction>) request.getSessionAttribute(JSP_PAGE_LIST);
        Integer currPageNo = Integer.decode(request.getParameter(JSP_CURR_PAGE_NO));
        list.setCurrPageNo(currPageNo);
        resaveParamsShowDirections(request);
    }

    private void showTourPage(SessionRequestContent request) throws ServletLogicException {
        ObjList<Tour> list = (ObjList<Tour>) request.getSessionAttribute(JSP_PAGE_LIST);
        Integer currPageNo = Integer.decode(request.getParameter(JSP_CURR_PAGE_NO));
        list.setCurrPageNo(currPageNo);
    }
    
}
