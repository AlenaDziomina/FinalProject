package by.epam.project.action.tour;

import static by.epam.project.action.JspParamNames.*;
import static by.epam.project.dao.DaoParamNames.*;
import by.epam.project.action.SessionRequestContent;
import by.epam.project.dao.query.Criteria;
import by.epam.project.entity.ClientType;
import by.epam.project.entity.Tour;
import by.epam.project.entity.User;
import by.epam.project.exception.ServletLogicException;
import by.epam.project.exception.TechnicalException;
import by.epam.project.logic.TourLogic;
import by.epam.project.manager.ClientTypeManager;
import static by.epam.project.manager.ParamManager.getBoolParam;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Class {@code TourCommand} is the parent class of all commands of actions
 * on the tour objects.
 * Contains custom public methods of actions on the tour objects.
 * @author Helena.Grouk
 */
public class TourCommand {
    /**
     * Find the list of tours and save it as the attribute of session.
     * Also determine and store in session attributes display options of tour 
     * status.
     * @param request parameters and attributes of the request and the session
     * @throws ServletLogicException if this can not be done due to the 
     * exceptions of logic layer
     */
    public void formTourList(SessionRequestContent request) throws ServletLogicException {
        Criteria criteria = new Criteria();
        criteria.addParam(DAO_ID_DIRECTION, request.getAttribute(JSP_ID_DIRECTION));
        
        User user = (User) request.getSessionAttribute(JSP_USER);
        if (user != null) {
            criteria.addParam(DAO_USER_LOGIN, user.getLogin());
            ClientType type = ClientTypeManager.clientTypeOf(user.getRole().getRoleName());
            criteria.addParam(DAO_ROLE_NAME, type);
        } else {
            criteria.addParam(DAO_ROLE_NAME, request.getSessionAttribute(JSP_ROLE_TYPE));
        }
        
        Short tourStatus = getTourStatus(request);
        if (tourStatus != null) {
            criteria.addParam(DAO_TOUR_STATUS, tourStatus);
        }
        Short tourDateStatus = getTourDateStatus(request);
        if (tourDateStatus != null) {
            Calendar calendar = Calendar.getInstance();
            Date date = calendar.getTime();
            if (tourDateStatus == 1) {
                criteria.addParam(DAO_TOUR_DATE_FROM, date);
            } else if (tourDateStatus == 0) {
                criteria.addParam(DAO_TOUR_DATE_TO, date);
            }
        }
        
        try { 
            List<Tour> tours = new TourLogic().doGetEntity(criteria);
            request.setSessionAttribute(JSP_TOUR_LIST, tours);
        } catch (TechnicalException ex) {
            throw new ServletLogicException(ex.getMessage(), ex);
        }
    }
    
    /**
     * Resave common parameters of tour searching page.
     * @param request parameters and attributes of the request and the session
     * @throws by.epam.project.exception.ServletLogicException
     */
    public void resaveParamsSearchTour(SessionRequestContent request) throws ServletLogicException {
        String currTourType = request.getParameter(JSP_CURR_TOUR_TYPE);
        if (currTourType != null) {
            request.setSessionAttribute(JSP_CURR_TOUR_TYPE, currTourType);
        }
        String currTransMode = request.getParameter(JSP_CURR_TRANS_MODE);
        if (currTransMode != null) {
            request.setSessionAttribute(JSP_CURR_TRANS_MODE, currTransMode);
        }
        String currCountry = request.getParameter(JSP_CURR_ID_COUNTRY);
        if (currCountry != null && !currCountry.isEmpty()) {
            request.setSessionAttribute(JSP_CURR_ID_COUNTRY, currCountry);
        }
        String currCity = request.getParameter(JSP_CURR_ID_CITY);
        if (currCity != null && !currCity.isEmpty()) {
            request.setSessionAttribute(JSP_CURR_ID_CITY, currCity);
        }
        String currHotel = request.getParameter(JSP_CURR_ID_HOTEL);
        if (currHotel != null && !currHotel.isEmpty()) {
            request.setSessionAttribute(JSP_CURR_ID_HOTEL, currHotel);
        }
        String[] currCoutryTags = request.getAllParameters(JSP_CURR_COUNTRY_TAGS);
        if (currCoutryTags != null) {
            request.setSessionAttribute(JSP_CURR_COUNTRY_TAGS, currCoutryTags);
        }
        String[] currCityTags = request.getAllParameters(JSP_CURR_CITY_TAGS);
        if (currCityTags != null) {
            request.setSessionAttribute(JSP_CURR_CITY_TAGS, currCityTags);
        }
        
        createCurrHotelTag(request);
        
        String currPriceFrom = request.getParameter(JSP_CURR_PRICE_FROM);
        if (currPriceFrom != null) {
            request.setSessionAttribute(JSP_CURR_PRICE_FROM, currPriceFrom);
        }
        String currPriceTo = request.getParameter(JSP_CURR_PRICE_TO);
        if (currPriceTo != null) {
            request.setSessionAttribute(JSP_CURR_PRICE_TO, currPriceTo);
        }
        String currDepartDateFrom = request.getParameter(JSP_CURR_DEPART_DATE_FROM);
        if (currDepartDateFrom != null) {
            request.setSessionAttribute(JSP_CURR_DEPART_DATE_FROM, currDepartDateFrom);
        }
        String currDepartDateTo = request.getParameter(JSP_CURR_DEPART_DATE_TO);
        if (currDepartDateTo != null) {
            request.setSessionAttribute(JSP_CURR_DEPART_DATE_TO, currDepartDateTo);
        }
        String currDaysCountFrom = request.getParameter(JSP_CURR_DAYS_COUNT_FROM);
        if (currDaysCountFrom != null) {
            request.setSessionAttribute(JSP_CURR_DAYS_COUNT_FROM, currDaysCountFrom);
        }
        String currDaysCountTo = request.getParameter(JSP_CURR_DAYS_COUNT_TO);
        if (currDaysCountTo != null) {
            request.setSessionAttribute(JSP_CURR_DAYS_COUNT_TO, currDaysCountTo);
        }
        String currDiscountFrom = request.getParameter(JSP_CURR_DISCOUNT_FROM);
        if (currDiscountFrom != null) {
            request.setSessionAttribute(JSP_CURR_DISCOUNT_FROM, currDiscountFrom);
        }
        String currStars = request.getParameter(JSP_CURR_HOTEL_STARS);
        if (currStars != null) {
           request.setSessionAttribute(JSP_CURR_HOTEL_STARS, currStars);
        }
        String allDepartDate = request.getParameter(JSP_BOX_ALL_DEPART_DATE);
        if (allDepartDate != null) {
            request.setSessionAttribute(JSP_BOX_ALL_DEPART_DATE, allDepartDate);
        }
        String allDaysCount = request.getParameter(JSP_BOX_ALL_DAYS_COUNT);
        if (allDaysCount != null) {
            request.setSessionAttribute(JSP_BOX_ALL_DAYS_COUNT, allDaysCount);
        }
        String allPrice = request.getParameter(JSP_BOX_ALL_PRICE);
        if (allPrice != null) {
            request.setSessionAttribute(JSP_BOX_ALL_PRICE, allPrice);
        }
        String allCountries = request.getParameter(JSP_BOX_ALL_COUNTRIES);
        if (allCountries != null) {
            request.setSessionAttribute(JSP_BOX_ALL_COUNTRIES, allCountries);
        }
        String allCities = request.getParameter(JSP_BOX_ALL_CITIES);
        if (allCities != null) {
            request.setSessionAttribute(JSP_BOX_ALL_CITIES, allCities);
        }
        String allHotels = request.getParameter(JSP_BOX_ALL_HOTELS);
        if (allHotels != null) {
            request.setSessionAttribute(JSP_BOX_ALL_HOTELS, allHotels);
        }
        String isHidden = request.getParameter(JSP_IS_HIDDEN);
        if(isHidden != null) {
            request.setSessionAttribute(JSP_IS_HIDDEN, false);
        }
    }

    /**
     * Determine and store in session attributes display options of tour 
     * status. Default value means 'only valid'.
     * @param request parameters and attributes of the request and the session
     * @return {@code ACTIVE} == 1 if 'only valid'; {@code DELETED} == 0 
     * if 'only invalid'.
     */
    public Short getTourStatus(SessionRequestContent request) {
        Boolean validStatus = getBoolParam(request, JSP_TOUR_VALID_STATUS);
        Boolean invalidStatus = getBoolParam(request, JSP_TOUR_INVALID_STATUS);
        if (validStatus == null && invalidStatus == null) {
            validStatus = (Boolean) request.getSessionAttribute(JSP_TOUR_VALID_STATUS);
            invalidStatus = (Boolean) request.getSessionAttribute(JSP_TOUR_INVALID_STATUS);
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
        request.setSessionAttribute(JSP_TOUR_VALID_STATUS, validStatus);
        request.setSessionAttribute(JSP_TOUR_INVALID_STATUS, invalidStatus);
        
        Short status = null;
        if (validStatus && ! invalidStatus) {
            status = ACTIVE;
        } else if ( ! validStatus && invalidStatus) {
            status = DELETED;
        }
        return status;
    }

    /**
     * Determine and store in session attributes display options of tour 
     * date status. Default value means 'only valid date'.
     * @param request parameters and attributes of the request and the session
     * @return {@code ACTIVE} == 1 if 'only valid date'; {@code DELETED} == 0 
     * if 'only invalid date'.
     */
    public Short getTourDateStatus(SessionRequestContent request) {
        Boolean validStatus = getBoolParam(request, JSP_TOUR_VALID_DATE);
        Boolean invalidStatus = getBoolParam(request, JSP_TOUR_INVALID_DATE);
        if (validStatus == null && invalidStatus == null) {
            validStatus = (Boolean) request.getSessionAttribute(JSP_TOUR_VALID_DATE);
            invalidStatus = (Boolean) request.getSessionAttribute(JSP_TOUR_INVALID_DATE);
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
        request.setSessionAttribute(JSP_TOUR_VALID_DATE, validStatus);
        request.setSessionAttribute(JSP_TOUR_INVALID_DATE, invalidStatus);
        
        Short status = null;
        if (validStatus && ! invalidStatus) {
            status = ACTIVE;
        } else if ( ! validStatus && invalidStatus) {
            status = DELETED;
        }
        return status;
    }
}
