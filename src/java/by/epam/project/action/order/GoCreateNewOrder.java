/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package by.epam.project.action.order;

import by.epam.project.action.ActionCommand;
import static by.epam.project.action.JspParamNames.*;
import by.epam.project.action.SessionRequestContent;
import by.epam.project.entity.Order;
import by.epam.project.entity.Tour;
import by.epam.project.entity.Tourist;
import by.epam.project.entity.User;
import by.epam.project.exception.LogicException;
import by.epam.project.exception.ServletLogicException;
import by.epam.project.manager.ConfigurationManager;
import by.epam.project.manager.MessageManager;
import by.epam.project.manager.ParamManager;
import by.epam.project.manager.PriceDiscountManager;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *
 * @author User
 */
public class GoCreateNewOrder implements ActionCommand {

    @Override
    public String execute(SessionRequestContent request) throws ServletLogicException {
        String page = ConfigurationManager.getProperty("path.page.createorder");
        try {
            createCurrOrder(request);
        } catch (LogicException ex) {
            page = ConfigurationManager.getProperty("path.page.editorder");
            request.setAttribute("errorReason", ex.getMessage());
            request.setAttribute("errorSaveData", MessageManager.getProperty("message.errorsavedata"));
        }
        request.setSessionAttribute(JSP_PAGE, page);        
        return page;
    }
    
    private void createCurrOrder(SessionRequestContent request) throws LogicException{
        Order order = (Order) request.getSessionAttribute(JSP_CURRENT_ORDER);
        if (order == null) {
            order = new Order();
        }
        User user = (User) request.getSessionAttribute(JSP_USER);
        if (user == null) {
            throw new LogicException("Error in User data.");
        }
        order.setUser(user);
        order.setCurrentUserDiscount(user.getDiscount());
        
        Tour tour = (Tour) request.getSessionAttribute(JSP_CURRENT_TOUR);
        if (tour == null) {
            throw new LogicException("Error in tour data.");
        }
        
        order.setTour(tour);
        order.setCurrentPrice(tour.getPrice());
        order.setCurrentDiscount(tour.getDiscount());
        
        order.setOrderDate(new Date());
        Integer seats = ParamManager.getIntParam(request, JSP_CURR_ORDER_SEATS);
        if (seats == null) {
            throw new LogicException("Error in seats count.");
        }
        order.setSeats(seats);
        
        String[] firstName = request.getAllParameters(JSP_TOURIST_FIRST_NAME);
        if (firstName == null || firstName.length < seats) {
            throw new LogicException("Error in first name data.");
        }
        String[] middleName = request.getAllParameters(JSP_TOURIST_MIDDLE_NAME);
        if (middleName == null || middleName.length < seats) {
            throw new LogicException("Error in middle name data.");
        }
        String[] lastName = request.getAllParameters(JSP_TOURIST_LAST_NAME);
        if (lastName == null || lastName.length < seats) {
            throw new LogicException("Error in last name data.");
        }
        String[] passport = request.getAllParameters(JSP_TOURIST_PASSPORT);
        if (passport == null || passport.length < seats) {
            throw new LogicException("Error in passport data.");
        }
        
        List<Tourist> touristList = new ArrayList<>();
        for (int i = 0; i < seats; i++) {
            Tourist tourist = new Tourist();
            tourist.setFirstName(firstName[i]);
            tourist.setMiddleName(middleName[i]);
            tourist.setLastName(lastName[i]);
            tourist.setPassport(passport[i]);
            touristList.add(tourist);
        }
        order.setTouristCollection(touristList);
        order.setFinalPrice(PriceDiscountManager.getFinalPrice(order));
        request.setSessionAttribute(JSP_CURRENT_ORDER, order);
    }

}
