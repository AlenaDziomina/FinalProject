/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package by.epam.project.action.tour;

import by.epam.project.action.ActionCommand;
import static by.epam.project.action.JspParamNames.*;
import by.epam.project.action.SessionRequestContent;
import static by.epam.project.dao.entquery.DirectionQuery.DAO_ID_DIRECTION;
import static by.epam.project.dao.entquery.RoleQuery.DAO_ROLE_NAME;
import static by.epam.project.dao.entquery.TourQuery.DAO_ID_TOUR;
import static by.epam.project.dao.entquery.TourQuery.DAO_TOUR_DATE;
import static by.epam.project.dao.entquery.TourQuery.DAO_TOUR_DAYS;
import static by.epam.project.dao.entquery.TourQuery.DAO_TOUR_DISCOUNT;
import static by.epam.project.dao.entquery.TourQuery.DAO_TOUR_FREE_SEATS;
import static by.epam.project.dao.entquery.TourQuery.DAO_TOUR_PRICE;
import static by.epam.project.dao.entquery.TourQuery.DAO_TOUR_TOTAL_SEATS;
import static by.epam.project.dao.entquery.UserQuery.DAO_USER_LOGIN;
import by.epam.project.dao.query.Criteria;
import by.epam.project.entity.ClientType;
import by.epam.project.entity.Direction;
import by.epam.project.entity.Tour;
import by.epam.project.entity.User;
import by.epam.project.exception.LogicException;
import by.epam.project.exception.ServletLogicException;
import by.epam.project.exception.TechnicalException;
import by.epam.project.logic.TourLogic;
import by.epam.project.manager.ClientTypeManager;
import by.epam.project.manager.ConfigurationManager;
import by.epam.project.manager.MessageManager;
import static by.epam.project.manager.ParamManager.getDateDiff;
import static by.epam.project.manager.ParamManager.getDateParam;
import static by.epam.project.manager.ParamManager.getFltParam;
import static by.epam.project.manager.ParamManager.getIntParam;
import by.epam.project.manager.Validator;
import java.util.Date;


/**
 *
 * @author User
 */
public class SaveRedactTour extends TourCommand implements ActionCommand {

    @Override
    public String execute(SessionRequestContent request) throws ServletLogicException {
        String page = ConfigurationManager.getProperty("path.page.edittour");
        resaveParamsSaveTour(request);
        try {
            Criteria criteria = new Criteria();
            Tour tour = (Tour) request.getSessionAttribute(JSP_CURRENT_TOUR);
            Validator.validateTour(tour);
            if (tour != null) {
                Integer idTour = tour.getIdTour();
                if (idTour != null) {
                    criteria.addParam(DAO_ID_TOUR, idTour);
                }
                criteria.addParam(DAO_TOUR_DATE, tour.getDepartDate());
                criteria.addParam(DAO_TOUR_DAYS, tour.getDaysCount());
                criteria.addParam(DAO_TOUR_PRICE, tour.getPrice());
                criteria.addParam(DAO_TOUR_DISCOUNT, tour.getDiscount());
                criteria.addParam(DAO_TOUR_TOTAL_SEATS, tour.getTotalSeats());
                criteria.addParam(DAO_TOUR_FREE_SEATS, tour.getFreeSeats());
                criteria.addParam(DAO_ID_DIRECTION, tour.getDirection().getIdDirection());
            }
            User user = (User) request.getSessionAttribute(JSP_USER);
            if (user != null) {
                criteria.addParam(DAO_USER_LOGIN, user.getLogin());
                ClientType type = ClientTypeManager.clientTypeOf(user.getRole().getRoleName());
                criteria.addParam(DAO_ROLE_NAME, type);
            } else {
                criteria.addParam(DAO_ROLE_NAME, request.getSessionAttribute(JSP_ROLE_TYPE));
            }
            Integer resIdTour = new TourLogic().doRedactEntity(criteria);
            request.setParameter(JSP_SELECT_ID, resIdTour.toString());
            return new ShowTour().execute(request);
        } catch (TechnicalException | LogicException ex) {
            request.setAttribute("errorSaveReason", ex.getMessage());
            request.setAttribute("errorSave", "errorSaveData");
            request.setSessionAttribute(JSP_PAGE, page);
            return page;
        }       
    }

    private void resaveParamsSaveTour(SessionRequestContent request) {
        String invalidTourDate = request.getParameter(JSP_TOUR_INVALID_DATE);
        if(invalidTourDate != null) {
            request.setSessionAttribute(JSP_TOUR_INVALID_DATE, invalidTourDate);
        }
        String currDepartDate = request.getParameter("departDate");
        if (currDepartDate != null) {
            request.setAttribute(JSP_CURR_DEPART_DATE, currDepartDate);
        }
        
        String currArrivalDate = request.getParameter("arrivalDate");
        if (currDepartDate != null) {
            request.setAttribute(JSP_CURR_ARRIVAL_DATE, currArrivalDate);
        }
        createCurrTour(request);
    }
    
    private void createCurrTour(SessionRequestContent request) {
        Tour currTour = (Tour) request.getSessionAttribute(JSP_CURRENT_TOUR);
        if(currTour == null) {
            currTour = new Tour();
            Direction currDir = (Direction) request.getSessionAttribute(JSP_CURRENT_DIRECTION);
            if (currDir != null) {
                Integer idDirection = currDir.getIdDirection();
                currTour.setDirection(new Direction(idDirection));
            }
        }
        Date departDate = getDateParam(request, JSP_CURR_DEPART_DATE);
        Date arrivalDate = getDateParam(request, JSP_CURR_ARRIVAL_DATE);
        currTour.setDepartDate(departDate);
        currTour.setDaysCount(getDateDiff(departDate, arrivalDate));
        currTour.setPrice(getFltParam(request, JSP_TOUR_PRICE));
        currTour.setDiscount(getIntParam(request, JSP_TOUR_DISCOUNT));
        currTour.setTotalSeats(getIntParam(request, JSP_TOTAL_SEATS));
        currTour.setFreeSeats(getIntParam(request, JSP_FREE_SEATS));
        request.setSessionAttribute(JSP_CURRENT_TOUR, currTour);
    }
    
}