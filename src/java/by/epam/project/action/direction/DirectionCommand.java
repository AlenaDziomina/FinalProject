package by.epam.project.action.direction;

import static by.epam.project.action.JspParamNames.*;
import static by.epam.project.dao.DaoParamNames.*;
import by.epam.project.action.SessionRequestContent;
import by.epam.project.action.tour.TourCommand;
import by.epam.project.dao.query.Criteria;
import by.epam.project.dao.ClientType;
import by.epam.project.entity.Description;
import by.epam.project.entity.Direction;
import by.epam.project.entity.Hotel;
import by.epam.project.entity.TourType;
import by.epam.project.entity.TransMode;
import by.epam.project.entity.User;
import by.epam.project.exception.ServletLogicException;
import by.epam.project.exception.TechnicalException;
import by.epam.project.logic.AbstractLogic;
import by.epam.project.logic.LogicFactory;
import by.epam.project.logic.LogicType;
import by.epam.project.manager.ClientTypeManager;
import static by.epam.project.manager.ParamManager.getBoolParam;
import by.epam.project.tag.ObjList;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Class {@code DirectionCommand} is the parent class of all commands of actions
 * on the direction objects.
 * Contains custom public methods of actions on the direction objects.
 * @author Helena.Grouk
 */
public class DirectionCommand {

    /**
     * Resave common parameters of direction page.
     * @param request parameters and attributes of the request and the session
     * @throws by.epam.project.exception.ServletLogicException
     */
    public void resaveParamsSaveDirection(SessionRequestContent request) throws ServletLogicException {
        String currCountry = request.getParameter(JSP_CURR_ID_COUNTRY);
        if (currCountry != null && !currCountry.isEmpty()) {
            request.setAttribute(JSP_CURR_ID_COUNTRY, currCountry);
        }

        String currCity = request.getParameter(JSP_CURR_ID_CITY);
        if (currCity != null && !currCity.isEmpty()) {
            request.setAttribute(JSP_CURR_ID_CITY, currCity);
        }

        String currHotel = request.getParameter(JSP_CURR_ID_HOTEL);
        if (currHotel != null && !currHotel.isEmpty()) {
            request.setAttribute(JSP_CURR_ID_HOTEL, currHotel);
        }

        String[] currCoutryTags = request.getAllParameters(JSP_CURR_COUNTRY_TAGS);
        if (currCoutryTags != null) {
            request.setAttribute(JSP_CURR_COUNTRY_TAGS, currCoutryTags);
        }

        String[] currCityTags = request.getAllParameters(JSP_CURR_CITY_TAGS);
        if (currCityTags != null) {
            request.setAttribute(JSP_CURR_CITY_TAGS, currCityTags);
        }

        String currTourType = request.getParameter(JSP_CURR_TOUR_TYPE);
        if (currTourType != null) {
            request.setAttribute(JSP_CURR_TOUR_TYPE, currTourType);
        }

        String currTransMode = request.getParameter(JSP_CURR_TRANS_MODE);
        if (currTransMode != null) {
            request.setAttribute(JSP_CURR_TRANS_MODE, currTransMode);
        }

        createCurrHotelTag(request);
        createCurrDirection(request);
    }

    /**
     * Create and store in request attributes current list of hotel tags using
     * current input parameters.
     * @param request parameters and attributes of the request and the session
     * @throws by.epam.project.exception.ServletLogicException
     */
    public void createCurrHotelTag(SessionRequestContent request) throws ServletLogicException {
        String[] currHotelTags = request.getAllParameters(JSP_CURR_HOTEL_TAGS);
        if (currHotelTags != null) {
            List<Hotel> hotelTagList = new ArrayList();
            for (String tag : currHotelTags) {
                Integer idHotel = Integer.decode(tag);
                if (idHotel > 0) {
                    Criteria criteria = new Criteria();
                    User user = (User) request.getSessionAttribute(JSP_USER);
                    if (user != null) {
                        criteria.addParam(DAO_USER_LOGIN, user.getLogin());
                        ClientType type = ClientTypeManager.clientTypeOf(user.getRole().getRoleName());
                        criteria.addParam(DAO_ROLE_NAME, type);
                    } else {
                        criteria.addParam(DAO_ROLE_NAME, request.getSessionAttribute(JSP_ROLE_TYPE));
                    }
                    criteria.addParam(DAO_ID_HOTEL, idHotel);
                    try {
                        AbstractLogic hotelLogic = LogicFactory.getInctance(LogicType.HOTELLOGIC);
                        List<Hotel> hotels = hotelLogic.doGetEntity(criteria);
                        hotelTagList.addAll(hotels);
                    } catch (TechnicalException ex) {
                        throw new ServletLogicException(ex.getMessage(), ex);
                    }
                }
            }
            request.setAttribute(JSP_HOTEL_TAG_LIST, hotelTagList);
        }
    }

    /**
     * Find the list of tour types and save it as the attribute of session.
     * @param request parameters and attributes of the request and the session
     * @throws ServletLogicException if this can not be done due to the
     * exceptions of logic layer
     */
    public void formTourTypeList(SessionRequestContent request) throws ServletLogicException {
        Criteria criteria = new Criteria();
        User user = (User) request.getSessionAttribute(JSP_USER);
        if (user != null) {
            criteria.addParam(DAO_USER_LOGIN, user.getLogin());
            ClientType type = ClientTypeManager.clientTypeOf(user.getRole().getRoleName());
            criteria.addParam(DAO_ROLE_NAME, type);
        } else {
            criteria.addParam(DAO_ROLE_NAME, request.getSessionAttribute(JSP_ROLE_TYPE));
        }

        try {
            AbstractLogic tourTypeLogic = LogicFactory.getInctance(LogicType.TOURTYPELOGIC);
            List<TourType> types = tourTypeLogic.doGetEntity(criteria);
            request.setSessionAttribute(JSP_TOUR_TYPE_LIST, types);
        } catch (TechnicalException ex) {
            throw new ServletLogicException(ex.getMessage(), ex);
        }
    }

    /**
     * Find the list of transport modes and save it as the attribute of session.
     * @param request parameters and attributes of the request and the session
     * @throws ServletLogicException if this can not be done due to the
     * exceptions of logic layer
     */
    public void formTransModeList(SessionRequestContent request) throws ServletLogicException {
        Criteria criteria = new Criteria();
        User user = (User) request.getSessionAttribute(JSP_USER);
        if (user != null) {
            criteria.addParam(DAO_USER_LOGIN, user.getLogin());
            ClientType type = ClientTypeManager.clientTypeOf(user.getRole().getRoleName());
            criteria.addParam(DAO_ROLE_NAME, type);
        } else {
            criteria.addParam(DAO_ROLE_NAME, request.getSessionAttribute(JSP_ROLE_TYPE));
        }

        try {
            AbstractLogic transModeLogic = LogicFactory.getInctance(LogicType.TRANSMODELOGIC);
            List<TransMode> modes = transModeLogic.doGetEntity(criteria);
            request.setSessionAttribute(JSP_TRANS_MODE_LIST, modes);
        } catch (TechnicalException ex) {
            throw new ServletLogicException(ex.getMessage(), ex);
        }
    }

    /**
     * Find the list of directions and save it as the attribute of session.
     * Also determine and store in session attributes display options of
     * direction and tour status.
     * @param request parameters and attributes of the request and the session
     * @throws ServletLogicException if this can not be done due to the
     * exceptions of logic layer
     */
    protected void formDirectionList(SessionRequestContent request) throws ServletLogicException {
        Criteria criteria = new Criteria();
        User user = (User) request.getSessionAttribute(JSP_USER);
        if (user != null) {
            criteria.addParam(DAO_USER_LOGIN, user.getLogin());
            ClientType type = ClientTypeManager.clientTypeOf(user.getRole().getRoleName());
            criteria.addParam(DAO_ROLE_NAME, type);
        } else {
            criteria.addParam(DAO_ROLE_NAME, request.getSessionAttribute(JSP_ROLE_TYPE));
        }
        criteria.addParam(DAO_ID_DIRECTION, request.getAttribute(JSP_ID_DIRECTION));

        Short directionStatus = getDirectionStatus(request);
        if (directionStatus != null) {
            criteria.addParam(DAO_DIRECTION_STATUS, directionStatus);
        }

        Short tourStatus = new TourCommand().getTourStatus(request);
        if (tourStatus != null) {
            criteria.addParam(DAO_TOUR_STATUS, tourStatus);
        }
        Short tourDateStatus = new TourCommand().getTourDateStatus(request);
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
            AbstractLogic directionLogic = LogicFactory.getInctance(LogicType.DIRECTIONLOGIC);
            List<Direction> directions = directionLogic.doGetEntity(criteria);
            request.setSessionAttribute(JSP_DIRECTION_LIST, directions);
            ObjList<Direction> list = new ObjList<>(directions);
            request.setSessionAttribute(JSP_PAGE_LIST, list);
        } catch (TechnicalException ex) {
            throw new ServletLogicException(ex.getMessage(), ex);
        }
    }

    /**
     * Determine and store in session attributes display options of direction
     * status. Default value means 'only valid'.
     * @param request parameters and attributes of the request and the session
     * @return {@code ACTIVE} == 1 if 'only valid'; {@code DELETED} == 0
     * if 'only invalid'.
     */
    private Short getDirectionStatus(SessionRequestContent request) {
        Boolean validStatus = getBoolParam(request, JSP_DIRECTION_VALID_STATUS);
        Boolean invalidStatus = getBoolParam(request, JSP_DIRECTION_INVALID_STATUS);
        if (validStatus == null && invalidStatus == null) {
            validStatus = (Boolean) request.getSessionAttribute(JSP_DIRECTION_VALID_STATUS);
            invalidStatus = (Boolean) request.getSessionAttribute(JSP_DIRECTION_INVALID_STATUS);
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
        request.setSessionAttribute(JSP_DIRECTION_VALID_STATUS, validStatus);
        request.setSessionAttribute(JSP_DIRECTION_INVALID_STATUS, invalidStatus);

        Short status = null;
        if (validStatus && ! invalidStatus) {
            status = ACTIVE;
        } else if ( ! validStatus && invalidStatus) {
            status = DELETED;
        }
        return status;
    }

    /**
     * Create and store in session attributes current direction object using
     * current input parameters.
     * @param request parameters and attributes of the request and the session
     */
    private void createCurrDirection(SessionRequestContent request) {
        Direction currDir = (Direction) request.getSessionAttribute(JSP_CURRENT_DIRECTION);
        if (currDir == null) {
            currDir = new Direction();
            currDir.setDescription(new Description());
            currDir.setTourType(new TourType());
            currDir.setTransMode(new TransMode());
        }
        currDir.setName(request.getParameter(JSP_DIRECTION_NAME));
        currDir.setPicture(request.getParameter(JSP_DIRECTION_PICTURE));
        currDir.setText(request.getParameter(JSP_DIRECTION_TEXT));
        currDir.getDescription().setText(request.getParameter(JSP_DESCRIPTION_TEXT));
        String tourType = request.getParameter(JSP_CURR_TOUR_TYPE);
        if (tourType != null && !tourType.isEmpty()) {
            currDir.getTourType().setIdTourType(Integer.decode(tourType));
        }
        String transMode = request.getParameter(JSP_CURR_TRANS_MODE);
        if (transMode != null && !transMode.isEmpty()) {
            currDir.getTransMode().setIdMode(Integer.decode(transMode));
        }
        request.setSessionAttribute(JSP_CURRENT_DIRECTION, currDir);
    }
}
