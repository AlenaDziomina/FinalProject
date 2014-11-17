package by.epam.project.action.order;

import by.epam.project.action.ActionCommand;
import static by.epam.project.action.JspParamNames.*;
import by.epam.project.action.SessionRequestContent;
import by.epam.project.entity.Order;
import by.epam.project.entity.Tour;
import by.epam.project.entity.Tourist;
import by.epam.project.entity.User;
import by.epam.project.exception.ServletLogicException;
import by.epam.project.exception.TechnicalException;
import by.epam.project.manager.ConfigurationManager;
import by.epam.project.manager.ParamManager;
import by.epam.project.manager.PriceDiscountManager;
import by.epam.project.manager.Validator;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Class of command of displaying the page of order object confirmation.
 * @author Helena.Grouk
 */
public class GoCreateNewOrder extends OrderCommand implements ActionCommand {
    private static final String MSG_ERR_NULL_ENTITY = "message.errorNullEntity";
    private static final String MSG_ERR_TOURIST_ENTITY = "message.errorTableTourist";

    @Override
    public String execute(SessionRequestContent request) throws ServletLogicException {
        String page = ConfigurationManager.getProperty("path.page.createorder");
        try {
            createCurrOrder(request);
            Order currOrder = (Order) request.getSessionAttribute(JSP_CURRENT_ORDER);
            Validator.validateOrder(currOrder);
        } catch (TechnicalException ex) {
            page = ConfigurationManager.getProperty("path.page.editorder");
            request.setAttribute("errorReason", ex.getMessage());
            request.setAttribute("errorSaveData", "message.errorSaveData");
        }
        request.setSessionAttribute(JSP_PAGE, page);        
        return page;
    }
    
    /**
     * Create and store in session attributes current order object using 
     * current input parameters.
     * @param request parameters and attributes of the request and the session
     */
    private void createCurrOrder(SessionRequestContent request)throws TechnicalException{
        Order order = (Order) request.getSessionAttribute(JSP_CURRENT_ORDER);
        User user = (User) request.getSessionAttribute(JSP_USER);
        Tour tour = (Tour) request.getSessionAttribute(JSP_CURRENT_TOUR);
        if (order == null || user == null || tour == null) {
           throw new TechnicalException(MSG_ERR_NULL_ENTITY);
        }
        
        order.setUser(user);
        order.setCurrentUserDiscount(user.getDiscount());
        order.setTour(tour);
        order.setCurrentPrice(tour.getPrice());
        order.setCurrentDiscount(tour.getDiscount());
        order.setOrderDate(new Date());
        order.setSeats(ParamManager.getIntParam(request, JSP_CURR_ORDER_SEATS));
        
        String[] firstName = request.getAllParameters(JSP_TOURIST_FIRST_NAME);
        String[] middleName = request.getAllParameters(JSP_TOURIST_MIDDLE_NAME);
        String[] lastName = request.getAllParameters(JSP_TOURIST_LAST_NAME);
        String[] passport = request.getAllParameters(JSP_TOURIST_PASSPORT);
        
        if (firstName == null || middleName == null || lastName == null || passport == null
                || firstName.length != middleName.length || middleName.length != lastName.length) {
            throw new TechnicalException(MSG_ERR_TOURIST_ENTITY);
        }
        List<Tourist> touristList = new ArrayList<>();
        for (int i = 0; i < firstName.length; i++) {
            Tourist tourist = new Tourist();
            tourist.setFirstName(firstName[i]);
            tourist.setMiddleName(middleName[i]);
            tourist.setLastName(lastName[i]);
            tourist.setPassport(passport[i]);
            Validator.validateTourist(tourist);
            touristList.add(tourist);
        }
        order.setTouristCollection(touristList);
        order.setFinalPrice(PriceDiscountManager.getFinalPrice(order));
        request.setSessionAttribute(JSP_CURRENT_ORDER, order);
    }
}
