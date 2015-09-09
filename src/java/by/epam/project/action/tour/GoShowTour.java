package by.epam.project.action.tour;

import by.epam.project.action.ActionCommand;
import static by.epam.project.action.JspParamNames.*;
import static by.epam.project.dao.DaoParamNames.*;
import by.epam.project.action.SessionRequestContent;
import by.epam.project.action.city.CityCommand;
import by.epam.project.action.country.CountryCommand;
import by.epam.project.action.direction.DirectionCommand;
import by.epam.project.action.hotel.HotelCommand;
import by.epam.project.dao.query.Criteria;
import by.epam.project.dao.ClientType;
import by.epam.project.entity.Tour;
import by.epam.project.entity.User;
import by.epam.project.exception.ServletLogicException;
import by.epam.project.exception.TechnicalException;
import by.epam.project.logic.AbstractLogic;
import by.epam.project.logic.LogicFactory;
import by.epam.project.logic.LogicType;
import by.epam.project.manager.ClientTypeManager;
import by.epam.project.manager.ConfigurationManager;
import by.epam.project.tag.ObjList;
import java.util.List;

/**
 * Class of command of displaying the page of tour searching and start tour list
 * @author Helena.Grouk
 */
public class GoShowTour extends TourCommand implements ActionCommand {
    @Override
    public String execute(SessionRequestContent request) throws ServletLogicException {
        String page = ConfigurationManager.getProperty("path.page.tours");
        request.setSessionAttribute(JSP_PAGE, page);
        formTourSearchList(request);
        new CountryCommand().formCountryList(request);
        new CityCommand().formCityList(request);
        new HotelCommand().formHotelList(request);
        new DirectionCommand().formTourTypeList(request);
        new DirectionCommand().formTransModeList(request);
        cleanSessionShowTour(request);
        setDefaultParameters(request);
        return page;
    }

    /**
     * Find the full list of tours and save it as the attribute of session.
     * Also determine and store in session attributes display options of tour
     * status.
     * @param request parameters and attributes of the request and the session
     * @throws ServletLogicException if this can not be done due to the
     * exceptions of logic layer
     */
    private void formTourSearchList(SessionRequestContent request) throws ServletLogicException {
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

        try {
            AbstractLogic searchLogic = LogicFactory.getInctance(LogicType.SEARCHLOGIC);
            List<Tour> tours = searchLogic.doGetEntity(criteria);
            request.setSessionAttribute(JSP_TOUR_LIST, tours);
            ObjList<Tour> list = new ObjList<>(tours);
            request.setSessionAttribute(JSP_PAGE_LIST, list);
        } catch (TechnicalException ex) {
            throw new ServletLogicException(ex.getMessage(), ex);
        }
    }

    /**
     * Save default session attributes of tour searching.
     * @param request parameters and attributes of the request and the session
     */
    private void setDefaultParameters(SessionRequestContent request) {
        request.setSessionAttribute(JSP_COUNTRY_TAG_LIST, request.getSessionAttribute(JSP_COUNTRY_LIST));
        request.setSessionAttribute(JSP_CITY_TAG_LIST, request.getSessionAttribute(JSP_CITY_LIST));
        request.setSessionAttribute(JSP_PRICE_STEP, ConfigurationManager.getProperty("price.step"));
        request.setSessionAttribute(JSP_DISCOUNT_STEP, ConfigurationManager.getProperty("discount.step"));
        request.setSessionAttribute(JSP_BOX_ALL_DEPART_DATE, true);
        request.setSessionAttribute(JSP_BOX_ALL_DAYS_COUNT, true);
        request.setSessionAttribute(JSP_BOX_ALL_PRICE, true);
        request.setSessionAttribute(JSP_BOX_ALL_COUNTRIES, true);
        request.setSessionAttribute(JSP_BOX_ALL_CITIES, true);
        request.setSessionAttribute(JSP_BOX_ALL_HOTELS, true);
        request.setSessionAttribute(JSP_IS_HIDDEN, true);
    }

    /**
     * Clean session attributes of show tour page.
     * @param request parameters and attributes of the request and the session
     */
    private void cleanSessionShowTour(SessionRequestContent request) {
        //city
        request.deleteSessionAttribute(JSP_CURR_CITY_LIST);
        request.deleteSessionAttribute(JSP_CURRENT_CITY);
        request.deleteSessionAttribute(JSP_CITY_VALID_STATUS);
        request.deleteSessionAttribute(JSP_CITY_INVALID_STATUS);

        //country
        request.deleteSessionAttribute(JSP_CURRENT_COUNTRY);
        request.deleteSessionAttribute(JSP_COUNTRY_VALID_STATUS);
        request.deleteSessionAttribute(JSP_COUNTRY_INVALID_STATUS);

        //direction
        request.deleteSessionAttribute(JSP_DIRECTION_LIST);
        request.deleteSessionAttribute(JSP_DIRECTION_VALID_STATUS);
        request.deleteSessionAttribute(JSP_DIRECTION_INVALID_STATUS);
        request.deleteSessionAttribute(JSP_CURRENT_DIRECTION);

        //hotel
        request.deleteSessionAttribute(JSP_CURRENT_HOTEL);
        request.deleteSessionAttribute(JSP_HOTEL_VALID_STATUS);
        request.deleteSessionAttribute(JSP_HOTEL_INVALID_STATUS);

        //order
        request.deleteSessionAttribute(JSP_CURRENT_TOUR);
        request.deleteSessionAttribute(JSP_CURRENT_ORDER);
        request.deleteSessionAttribute(JSP_ORDER_LIST);

        //tour
        //request.deleteSessionAttribute(JSP_TOUR_VALID_STATUS);
        //request.deleteSessionAttribute(JSP_TOUR_INVALID_STATUS);
        //request.deleteSessionAttribute(JSP_TOUR_VALID_DATE);
        //request.deleteSessionAttribute(JSP_TOUR_INVALID_DATE);
        //request.deleteSessionAttribute(JSP_TOUR_LIST);
        //request.deleteSessionAttribute(JSP_PAGE_LIST);
        //request.deleteSessionAttribute(JSP_COUNTRY_TAG_LIST);
        //request.deleteSessionAttribute(JSP_CITY_TAG_LIST);
        //request.deleteSessionAttribute(JSP_PRICE_STEP);
        //request.deleteSessionAttribute(JSP_DISCOUNT_STEP);
        //request.deleteSessionAttribute(JSP_BOX_ALL_DEPART_DATE);
        //request.deleteSessionAttribute(JSP_BOX_ALL_DAYS_COUNT);
        //request.deleteSessionAttribute(JSP_BOX_ALL_PRICE);
        //request.deleteSessionAttribute(JSP_BOX_ALL_COUNTRIES);
        //request.deleteSessionAttribute(JSP_BOX_ALL_CITIES);
        //request.deleteSessionAttribute(JSP_BOX_ALL_HOTELS);
        //request.deleteSessionAttribute(JSP_IS_HIDDEN);

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