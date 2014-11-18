package by.epam.project.action.city;

import static by.epam.project.action.JspParamNames.*;
import static by.epam.project.dao.DaoParamNames.*;
import by.epam.project.action.SessionRequestContent;
import by.epam.project.action.hotel.HotelCommand;
import by.epam.project.dao.query.Criteria;
import by.epam.project.entity.City;
import by.epam.project.dao.ClientType;
import by.epam.project.entity.User;
import by.epam.project.exception.ServletLogicException;
import by.epam.project.exception.TechnicalException;
import by.epam.project.logic.CityLogic;
import by.epam.project.manager.ClientTypeManager;
import static by.epam.project.manager.ParamManager.getBoolParam;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;

/**
 * Class {@code CityCommand} is the parent class of all commands of actions
 * on the city objects.
 * Contains custom public methods of actions on the city objects.
 * @author Helena.Grouk
 */
public class CityCommand {
    /**
     * Find the list of cities and save it as the attribute of session.
     * Also determine and store in session attributes display options of city 
     * and hotel status.
     * @param request parameters and attributes of the request and the session
     * @throws ServletLogicException if this can not be done due to the 
     * exceptions of logic layer
     */
    public void formCityList(SessionRequestContent request) throws ServletLogicException {
        Criteria criteria = new Criteria();
        criteria.addParam(DAO_ID_COUNTRY, request.getAttribute(JSP_ID_COUNTRY));
        User user = (User) request.getSessionAttribute(JSP_USER);
        if (user != null) {
            criteria.addParam(DAO_USER_LOGIN, user.getLogin());
            ClientType type = ClientTypeManager.clientTypeOf(user.getRole().getRoleName());
            criteria.addParam(DAO_ROLE_NAME, type);
        } else {
            criteria.addParam(DAO_ROLE_NAME, request.getSessionAttribute(JSP_ROLE_TYPE));
        }
        
        Short cityStatus = getCityStatus(request);
        if (cityStatus != null) {
            criteria.addParam(DAO_CITY_STATUS, cityStatus);
        }
        
        Short hotelStatus = new HotelCommand().getHotelStatus(request);
        if (hotelStatus != null) {
            criteria.addParam(DAO_HOTEL_STATUS, hotelStatus);
        }
        
        try {
            List<City> cities = new CityLogic().doGetEntity(criteria);
            request.setSessionAttribute(JSP_CITY_LIST, cities);
        } catch (TechnicalException ex) {
            throw new ServletLogicException(ex.getMessage(), ex);
        }
    }
    
    /**
     * Determine and store in session attributes display options of city 
     * status. Default value means 'only valid'.
     * @param request parameters and attributes of the request and the session
     * @return {@code ACTIVE} == 1 if 'only valid'; {@code DELETED} == 0 
     * if 'only invalid'.
     */
    public Short getCityStatus(SessionRequestContent request) {
        Boolean validStatus = getBoolParam(request, JSP_CITY_VALID_STATUS);
        Boolean invalidStatus = getBoolParam(request, JSP_CITY_INVALID_STATUS);
        if (validStatus == null && invalidStatus == null) {
            validStatus = (Boolean) request.getSessionAttribute(JSP_CITY_VALID_STATUS);
            invalidStatus = (Boolean) request.getSessionAttribute(JSP_CITY_INVALID_STATUS);
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
        request.setSessionAttribute(JSP_CITY_VALID_STATUS, validStatus);
        request.setSessionAttribute(JSP_CITY_INVALID_STATUS, invalidStatus);
        
        Short status = null;
        if (validStatus && ! invalidStatus) {
            status = ACTIVE;
        } else if ( ! validStatus && invalidStatus) {
            status = DELETED;
        }
        return status;
    }
    
    /**
     * Determine and store in session attributes current city and its id for 
     * displaying it. It needs list of cities and selected id in request.
     * @param request parameters and attributes of the request and the session
     */
    protected void showSelectedCity(SessionRequestContent request) {
        String selected = request.getParameter(JSP_SELECT_ID);
        City currCity = null;
        if (selected != null) {
            Integer idCity = Integer.decode(selected); 
            if (idCity != null) {
                List<City> list = (List<City>) request.getSessionAttribute(JSP_CITY_LIST);
                Iterator<City> it = list.iterator();
                while (it.hasNext() && currCity == null) {
                    City city = it.next();
                    if (Objects.equals(city.getIdCity(), idCity)) {
                        currCity = city;
                        request.setAttribute(JSP_CURR_ID_CITY, idCity);
                    }
                }
            }
        }
        request.setSessionAttribute(JSP_CURRENT_CITY, currCity);
    }
}
