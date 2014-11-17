package by.epam.project.action.tour;

import by.epam.project.action.ActionCommand;
import static by.epam.project.action.JspParamNames.*;
import by.epam.project.action.SessionRequestContent;
import by.epam.project.entity.Direction;
import by.epam.project.entity.Tour;
import by.epam.project.exception.ServletLogicException;
import by.epam.project.manager.ConfigurationManager;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Objects;

/**
 * Class of command of displaying the tour selected in tour list
 * @author Helena.Grouk
 */
public class ShowTour extends TourCommand implements ActionCommand {
    @Override
    public String execute(SessionRequestContent request) throws ServletLogicException {
        String page = ConfigurationManager.getProperty("path.page.tour");
        String prevPage = (String) request.getSessionAttribute(JSP_PAGE);
        resaveParamsShowTour(request);
        formTourList(request);
        distinguishTour(request);
        if(page == null ? prevPage == null : !page.equals(prevPage)){
            request.setSessionAttribute(JSP_PAGE, page);
            cleanSessionShowTour(request);
        }
        return page;
    }

    /**
     * Determine and store in session attributes current tour for 
     * displaying it. It needs selected id or currrent tour in request.
     * @param request parameters and attributes of the request and the session
     */
    private void distinguishTour(SessionRequestContent request) {
        List<Tour> list = (List<Tour>) request.getSessionAttribute(JSP_TOUR_LIST);
        String selectId = request.getParameter(JSP_SELECT_ID);
        Integer idTour = null;
        if (selectId != null) {
            idTour = Integer.decode(selectId);
        } else {
            Tour tour = (Tour) request.getSessionAttribute(JSP_CURRENT_TOUR);
            if (tour != null) {
                idTour = tour.getIdTour();
            }
        }
        if (idTour != null) {
            request.setSessionAttribute(JSP_CURRENT_TOUR, null);
            for (Tour tour: list) {
                if (Objects.equals(tour.getIdTour(), idTour)) {
                    request.setSessionAttribute(JSP_CURRENT_TOUR, tour);

                    SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
                    Date d1 = tour.getDepartDate();
                    String ds1 = formatter.format(d1);  
                    request.setAttribute(JSP_CURR_DEPART_DATE, ds1);

                    Integer dn = tour.getDaysCount();
                    Calendar c = Calendar.getInstance(); 
                    c.setTime(d1); 
                    c.add(Calendar.DATE, dn);
                    Date d2 = c.getTime();
                    String ds2 = formatter.format(d2);
                    request.setAttribute(JSP_CURR_ARRIVAL_DATE, ds2);
                }
            }
        }
    }

    /**
     * Clean session attributes of show tour page.
     * @param request parameters and attributes of the request and the session
     */
    private void cleanSessionShowTour(SessionRequestContent request) {
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
        request.deleteSessionAttribute(JSP_COUNTRY_TAG_LIST);
        request.deleteSessionAttribute(JSP_CITY_TAG_LIST);
        request.deleteSessionAttribute(JSP_DIRECTION_LIST);
        request.deleteSessionAttribute(JSP_PAGE_LIST);
        request.deleteSessionAttribute(JSP_DIRECTION_VALID_STATUS);
        request.deleteSessionAttribute(JSP_DIRECTION_INVALID_STATUS);
        request.deleteSessionAttribute(JSP_TOUR_TYPE_LIST);
        request.deleteSessionAttribute(JSP_TRANS_MODE_LIST);
        
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
        //request.deleteSessionAttribute(JSP_TOUR_VALID_STATUS);
        //request.deleteSessionAttribute(JSP_TOUR_INVALID_STATUS);
        //request.deleteSessionAttribute(JSP_TOUR_VALID_DATE);
        //request.deleteSessionAttribute(JSP_TOUR_INVALID_DATE);
        //request.deleteSessionAttribute(JSP_TOUR_LIST);
        //request.deleteSessionAttribute(JSP_CURRENT_TOUR);
        
        request.deleteSessionAttribute(JSP_PRICE_STEP);
        request.deleteSessionAttribute(JSP_DISCOUNT_STEP);
        request.deleteSessionAttribute(JSP_BOX_ALL_DEPART_DATE);
        request.deleteSessionAttribute(JSP_BOX_ALL_DAYS_COUNT);
        request.deleteSessionAttribute(JSP_BOX_ALL_PRICE);
        request.deleteSessionAttribute(JSP_BOX_ALL_COUNTRIES);
        request.deleteSessionAttribute(JSP_BOX_ALL_CITIES);
        request.deleteSessionAttribute(JSP_BOX_ALL_HOTELS);
        request.deleteSessionAttribute(JSP_IS_HIDDEN);
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
    
    /**
     * Resave common parameters of tour page.
     * @param request parameters and attributes of the request and the session
     */
    private void resaveParamsShowTour(SessionRequestContent request) {
        Direction direction = (Direction) request.getSessionAttribute(JSP_CURRENT_DIRECTION);
        if (direction != null) {
            Integer idDirection = direction.getIdDirection();
            if (idDirection != null) {
                request.setAttribute(JSP_ID_DIRECTION, idDirection);
            }
        }
    }
}
