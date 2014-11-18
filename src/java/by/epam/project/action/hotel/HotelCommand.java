package by.epam.project.action.hotel;

import static by.epam.project.action.JspParamNames.*;
import static by.epam.project.dao.DaoParamNames.*;
import by.epam.project.action.SessionRequestContent;
import by.epam.project.dao.query.Criteria;
import by.epam.project.entity.City;
import by.epam.project.dao.ClientType;
import by.epam.project.entity.Description;
import by.epam.project.entity.Hotel;
import by.epam.project.entity.User;
import by.epam.project.exception.ServletLogicException;
import by.epam.project.exception.TechnicalException;
import by.epam.project.logic.HotelLogic;
import by.epam.project.manager.ClientTypeManager;
import by.epam.project.manager.ParamManager;
import static by.epam.project.manager.ParamManager.getBoolParam;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;

/**
 * Class {@code HotelCommand} is the parent class of all commands of actions
 * on the hotel objects.
 * Contains custom public methods of actions on the hotel objects.
 * @author Helena.Grouk
 */
public class HotelCommand {
    /**
     * Find the list of hotels and save it as the attribute of session.
     * Also determine and store in session attributes display options of hotel 
     * status.
     * @param request parameters and attributes of the request and the session
     * @throws ServletLogicException if this can not be done due to the 
     * exceptions of logic layer
     */
    public void formHotelList(SessionRequestContent request) throws ServletLogicException {
        Criteria criteria = new Criteria();
        criteria.addParam(DAO_ID_CITY, request.getAttribute(JSP_ID_CITY));
        User user = (User) request.getSessionAttribute(JSP_USER);
        if (user != null) {
            criteria.addParam(DAO_USER_LOGIN, user.getLogin());
            ClientType type = ClientTypeManager.clientTypeOf(user.getRole().getRoleName());
            criteria.addParam(DAO_ROLE_NAME, type);
        } else {
            criteria.addParam(DAO_ROLE_NAME, request.getSessionAttribute(JSP_ROLE_TYPE));
        }
        
        Short hotelStatus = getHotelStatus(request);
        if (hotelStatus != null) {
            criteria.addParam(DAO_HOTEL_STATUS, hotelStatus);
        }
        
        try {
            List<Hotel> hotels = new HotelLogic().doGetEntity(criteria);
            request.setSessionAttribute(JSP_HOTEL_LIST, hotels);
        } catch (TechnicalException ex) {
            throw new ServletLogicException(ex.getMessage(), ex);
        }
    }
    
    /**
     * Determine and store in session attributes display options of hotel 
     * status. Default value means 'only valid'.
     * @param request parameters and attributes of the request and the session
     * @return {@code ACTIVE} == 1 if 'only valid'; {@code DELETED} == 0 
     * if 'only invalid'.
     */
    public Short getHotelStatus(SessionRequestContent request) {
        Boolean validStatus = getBoolParam(request, JSP_HOTEL_VALID_STATUS);
        Boolean invalidStatus = getBoolParam(request, JSP_HOTEL_INVALID_STATUS);
        if (validStatus == null && invalidStatus == null) {
            validStatus = (Boolean) request.getSessionAttribute(JSP_HOTEL_VALID_STATUS);
            invalidStatus = (Boolean) request.getSessionAttribute(JSP_HOTEL_INVALID_STATUS);
            if (validStatus == null && invalidStatus == null) {
                validStatus = true;
                invalidStatus = false;
            }
        } else {
            if (validStatus == null) {
                validStatus = false;
            }
            if (invalidStatus == null) {
                invalidStatus = false;
            }
        }
        request.setSessionAttribute(JSP_HOTEL_VALID_STATUS, validStatus);
        request.setSessionAttribute(JSP_HOTEL_INVALID_STATUS, invalidStatus);
        
        Short status = null;
        if (validStatus && ! invalidStatus) {
            status = ACTIVE;
        } else if ( ! validStatus && invalidStatus) {
            status = DELETED;
        }
        return status;
    }
    
    /**
     * Resave common parameters of hotel page.
     * @param request parameters and attributes of the request and the session
     */
    public void resaveParamsSaveHotel(SessionRequestContent request) {
        String currCountry = request.getParameter(JSP_CURR_ID_COUNTRY);
        if (currCountry != null && !currCountry.isEmpty()) {
            request.setAttribute(JSP_CURR_ID_COUNTRY, currCountry);
        }
        String currCity = request.getParameter(JSP_CURR_ID_CITY);
        if (currCity != null && !currCity.isEmpty()) {
            request.setAttribute(JSP_CURR_ID_CITY, currCity);
        }
        createCurrHotel(request);
    }
    
    /**
     * Determine and store in session attributes current hotel and its id for 
     * displaying it. It needs list of hotels and selected id in request.
     * @param request parameters and attributes of the request and the session
     */
    protected void showSelectedHotel(SessionRequestContent request) {
        String selected = request.getParameter(JSP_SELECT_ID);
        Hotel currHotel = null;
        if (selected != null) {
            Integer idHotel = Integer.decode(selected); 
            if (idHotel != null) {
                List<Hotel> list = (List<Hotel>) request.getSessionAttribute(JSP_HOTEL_LIST);
                Iterator<Hotel> it = list.iterator();
                while (it.hasNext() && currHotel == null) {
                    Hotel hotel = it.next();
                    if (Objects.equals(hotel.getIdHotel(), idHotel)) {
                        currHotel = hotel;
                        request.setAttribute(JSP_CURR_ID_HOTEL, idHotel);
                    }
                }
            }
        }
        request.setSessionAttribute(JSP_CURRENT_HOTEL, currHotel);
    }
    
    /**
     * Create and store in session attributes current hotel object using current
     * input parameters.
     * @param request parameters and attributes of the request and the session
     */
    private void createCurrHotel(SessionRequestContent request) {
        Hotel currHotel = (Hotel) request.getSessionAttribute(JSP_CURRENT_HOTEL);
        if (currHotel == null) {
            currHotel = new Hotel();
            currHotel.setCity(new City());
            currHotel.setDescription(new Description());
        }
        Integer idCity = ParamManager.getIntParam(request, JSP_CURR_ID_CITY);
        currHotel.getCity().setIdCity(idCity);
        currHotel.setName(request.getParameter(JSP_HOTEL_NAME));
        currHotel.setPicture(request.getParameter(JSP_HOTEL_PICTURE));
        currHotel.getDescription().setText(request.getParameter(JSP_DESCRIPTION_TEXT));
        Integer stars = ParamManager.getIntParam(request, JSP_CURR_HOTEL_STARS);
        currHotel.setStars(stars);
        request.setSessionAttribute(JSP_CURRENT_HOTEL, currHotel);
    }
}
