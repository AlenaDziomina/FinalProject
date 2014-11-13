/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package by.epam.project.action.direction;

import by.epam.project.action.ActionCommand;
import static by.epam.project.action.JspParamNames.*;
import by.epam.project.action.SessionRequestContent;
import by.epam.project.action.tour.TourCommand;
import by.epam.project.entity.Direction;
import by.epam.project.entity.Order;
import by.epam.project.entity.Tour;
import by.epam.project.exception.ServletLogicException;
import by.epam.project.manager.ConfigurationManager;
import java.util.List;

/**
 *
 * @author User
 */
public class ShowDirection extends DirectionCommand implements ActionCommand {

    @Override
    public String execute(SessionRequestContent request) throws ServletLogicException {
        String page = ConfigurationManager.getProperty("path.page.direction");
        request.setSessionAttribute(JSP_PAGE, page);
        resaveParamsShowDirection(request);
        showSelectedDirection(request);
        return page;
    }

    private void showSelectedDirection(SessionRequestContent request) throws ServletLogicException {
        String selectId = request.getParameter(JSP_SELECT_ID);
        if (selectId != null) {
            Integer idDirection = Integer.decode(selectId);
            request.setAttribute(JSP_ID_DIRECTION, idDirection);
            formDirectionList(request);
            List<Direction> list = (List<Direction>) request.getSessionAttribute(JSP_DIRECTION_LIST);
            if (!list.isEmpty()){
                request.setSessionAttribute(JSP_CURRENT_DIRECTION, list.get(0));
            } else {
                Order currOrder = (Order) request.getSessionAttribute(JSP_CURRENT_ORDER);
                if (currOrder != null) {
                    request.setSessionAttribute(JSP_CURRENT_DIRECTION, currOrder.getTour().getDirection());
                }
            }
        } else {
            Direction direction = (Direction) request.getSessionAttribute(JSP_CURRENT_DIRECTION);
            request.setAttribute(JSP_ID_DIRECTION, direction.getIdDirection());
            new TourCommand().formTourList(request);
            List<Tour> list = (List<Tour>) request.getSessionAttribute(JSP_TOUR_LIST);
            direction.setTourCollection(list);
        }
    }

    private void resaveParamsShowDirection(SessionRequestContent request) {
        String validTourStatus = request.getParameter(JSP_TOUR_VALID_STATUS);
        if(validTourStatus != null) {
            request.setAttribute(JSP_TOUR_VALID_STATUS, validTourStatus);
        }
        
        String invalidTourStatus = request.getParameter(JSP_TOUR_INVALID_STATUS);
        if(invalidTourStatus != null) {
            request.setAttribute(JSP_TOUR_INVALID_STATUS, invalidTourStatus);
        }
        
        String validTourDate = request.getParameter(JSP_TOUR_VALID_DATE);
        if(validTourDate != null) {
            request.setAttribute(JSP_TOUR_VALID_DATE, validTourDate);
        }
        
        String invalidTourDate = request.getParameter(JSP_TOUR_INVALID_DATE);
        if(invalidTourDate != null) {
            request.setAttribute(JSP_TOUR_INVALID_DATE, invalidTourDate);
        }
    }
    
}
