package by.epam.project.action.direction;

import by.epam.project.action.ActionCommand;
import static by.epam.project.action.JspParamNames.*;
import by.epam.project.action.SessionRequestContent;
import by.epam.project.exception.ServletLogicException;
import by.epam.project.manager.ConfigurationManager;

/**
 * Class of command of displaying the page of direction object list
 * @author Helena.Grouk
 */
public class GoShowDirections extends DirectionCommand implements ActionCommand {
    @Override
    public String execute(SessionRequestContent request) throws ServletLogicException {
        String page = ConfigurationManager.getProperty("path.page.directions");
        String prevPage = (String) request.getSessionAttribute(JSP_PAGE);
        resaveParamsShowDirections(request);
        formDirectionList(request);
        if (page == null ? prevPage != null : ! page.equals(prevPage)) {
            request.setSessionAttribute(JSP_PAGE, page);
            cleanSessionShowDirections(request);
        }
        return page;
    }
  
    /**
     * Resave common parameters of show direction page.
     * @param request parameters and attributes of the request and the session
     */
    private void resaveParamsShowDirections(SessionRequestContent request) {
        String validDirectionStatus = request.getParameter(JSP_DIRECTION_VALID_STATUS);
        if(validDirectionStatus != null) {
            request.setSessionAttribute(JSP_DIRECTION_VALID_STATUS, validDirectionStatus);
        }
        
        String invalidDirectionStatus = request.getParameter(JSP_DIRECTION_INVALID_STATUS);
        if(invalidDirectionStatus != null) {
            request.setSessionAttribute(JSP_DIRECTION_INVALID_STATUS, invalidDirectionStatus);
        }
        String validTourStatus = request.getParameter(JSP_TOUR_VALID_STATUS);
        if(validTourStatus != null) {
            request.setSessionAttribute(JSP_TOUR_VALID_STATUS, validTourStatus);
        }
        
        String invalidTourStatus = request.getParameter(JSP_TOUR_INVALID_STATUS);
        if(invalidTourStatus != null) {
            request.setSessionAttribute(JSP_TOUR_INVALID_STATUS, invalidTourStatus);
        }
        
        String validTourDate = request.getParameter(JSP_TOUR_VALID_DATE);
        if(validTourDate != null) {
            request.setSessionAttribute(JSP_TOUR_VALID_DATE, validTourDate);
        }
        
        String invalidTourDate = request.getParameter(JSP_TOUR_INVALID_DATE);
        if(invalidTourDate != null) {
            request.setSessionAttribute(JSP_TOUR_INVALID_DATE, invalidTourDate);
        }
        
        
    }
    
    /**
     * Clean session attributes of show direction page.
     * @param request parameters and attributes of the request and the session
     */
    private void cleanSessionShowDirections(SessionRequestContent request) {
        //city
        request.deleteSessionAttribute(JSP_CITY_LIST);
        request.deleteSessionAttribute(JSP_CURR_CITY_LIST);
        request.deleteSessionAttribute(JSP_CURRENT_CITY);
        request.deleteSessionAttribute(JSP_CITY_VALID_STATUS);
        request.deleteSessionAttribute(JSP_CITY_INVALID_STATUS);
        
        //country
        request.deleteSessionAttribute(JSP_COUNTRY_LIST);
        request.deleteSessionAttribute(JSP_CURRENT_COUNTRY);
        request.deleteSessionAttribute(JSP_COUNTRY_VALID_STATUS);
        request.deleteSessionAttribute(JSP_COUNTRY_INVALID_STATUS);
        
        //direction
        //request.deleteSessionAttribute(JSP_DIRECTION_LIST);
        //request.deleteSessionAttribute(JSP_PAGE_LIST);
        //request.deleteSessionAttribute(JSP_DIRECTION_VALID_STATUS);
        //request.deleteSessionAttribute(JSP_DIRECTION_INVALID_STATUS);
        //request.deleteSessionAttribute(JSP_TOUR_TYPE_LIST);
        //request.deleteSessionAttribute(JSP_TRANS_MODE_LIST);
        //request.deleteSessionAttribute(JSP_CURRENT_DIRECTION);
        
        //hotel
        request.deleteSessionAttribute(JSP_HOTEL_LIST);
        request.deleteSessionAttribute(JSP_CURRENT_HOTEL);
        request.deleteSessionAttribute(JSP_HOTEL_VALID_STATUS);
        request.deleteSessionAttribute(JSP_HOTEL_INVALID_STATUS);
        
        //order
        request.deleteSessionAttribute(JSP_CURRENT_ORDER);
        request.deleteSessionAttribute(JSP_ORDER_LIST);
        
        
        //tour
        request.deleteSessionAttribute(JSP_TOUR_VALID_STATUS);
        request.deleteSessionAttribute(JSP_TOUR_INVALID_STATUS);
        request.deleteSessionAttribute(JSP_TOUR_VALID_DATE);
        request.deleteSessionAttribute(JSP_TOUR_INVALID_DATE);
        request.deleteSessionAttribute(JSP_TOUR_LIST);
        request.deleteSessionAttribute(JSP_COUNTRY_TAG_LIST);
        request.deleteSessionAttribute(JSP_CITY_TAG_LIST);
        request.deleteSessionAttribute(JSP_PRICE_STEP);
        request.deleteSessionAttribute(JSP_DISCOUNT_STEP);
        request.deleteSessionAttribute(JSP_BOX_ALL_DEPART_DATE);
        request.deleteSessionAttribute(JSP_BOX_ALL_DAYS_COUNT);
        request.deleteSessionAttribute(JSP_BOX_ALL_PRICE);
        request.deleteSessionAttribute(JSP_BOX_ALL_COUNTRIES);
        request.deleteSessionAttribute(JSP_BOX_ALL_CITIES);
        request.deleteSessionAttribute(JSP_BOX_ALL_HOTELS);
        request.deleteSessionAttribute(JSP_IS_HIDDEN);
        request.deleteSessionAttribute(JSP_CURRENT_TOUR);
        request.deleteSessionAttribute(JSP_CURR_TOUR_TYPE);
        request.deleteSessionAttribute(JSP_CURR_TRANS_MODE);
        request.deleteSessionAttribute(JSP_CURR_ID_COUNTRY);
        request.deleteSessionAttribute(JSP_CURR_ID_CITY);
        request.deleteSessionAttribute(JSP_CURR_ID_HOTEL);
        request.deleteSessionAttribute(JSP_CURR_COUNTRY_TAGS);
        request.deleteSessionAttribute(JSP_CURR_CITY_TAGS);
        request.deleteSessionAttribute(JSP_CURR_PRICE_FROM);
        request.deleteSessionAttribute(JSP_CURR_PRICE_TO);
        request.deleteSessionAttribute(JSP_CURR_DEPART_DATE_FROM);
        request.deleteSessionAttribute(JSP_CURR_DEPART_DATE_TO);
        request.deleteSessionAttribute(JSP_CURR_DAYS_COUNT_FROM);
        request.deleteSessionAttribute(JSP_CURR_DAYS_COUNT_TO);
        request.deleteSessionAttribute(JSP_CURR_DISCOUNT_FROM);
        request.deleteSessionAttribute(JSP_CURR_HOTEL_STARS);    
        request.deleteSessionAttribute(JSP_HOTEL_TAG_LIST);
        
        //user
        request.deleteSessionAttribute(JSP_USER_LIST);
        request.deleteSessionAttribute(JSP_CURRENT_USER);
        
    }
}
