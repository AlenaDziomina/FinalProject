/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package by.epam.project.action.tour;

import by.epam.project.action.ActionCommand;
import static by.epam.project.action.JspParamNames.JSP_CITY_TAG_LIST;
import static by.epam.project.action.JspParamNames.JSP_COUNTRY_TAG_LIST;
import static by.epam.project.action.JspParamNames.JSP_CURRENT_CITY;
import static by.epam.project.action.JspParamNames.JSP_CURRENT_COUNTRY;
import static by.epam.project.action.JspParamNames.JSP_CURRENT_DIRECTION;
import static by.epam.project.action.JspParamNames.JSP_CURRENT_HOTEL;
import static by.epam.project.action.JspParamNames.JSP_CURRENT_TOUR;
import static by.epam.project.action.JspParamNames.JSP_CURR_ARRIVAL_DATE;
import static by.epam.project.action.JspParamNames.JSP_CURR_CITY_LIST;
import static by.epam.project.action.JspParamNames.JSP_CURR_DEPART_DATE;
import static by.epam.project.action.JspParamNames.JSP_DISCOUNT_STEP;
import static by.epam.project.action.JspParamNames.JSP_HOTEL_TAG_LIST;
import static by.epam.project.action.JspParamNames.JSP_ID_DIRECTION;
import static by.epam.project.action.JspParamNames.JSP_PAGE;
import static by.epam.project.action.JspParamNames.JSP_PRICE_STEP;
import static by.epam.project.action.JspParamNames.JSP_ROLE_TYPE;
import static by.epam.project.action.JspParamNames.JSP_SELECT_ID;
import static by.epam.project.action.JspParamNames.JSP_TOUR_INVALID_DATE;
import static by.epam.project.action.JspParamNames.JSP_TOUR_INVALID_STATUS;
import static by.epam.project.action.JspParamNames.JSP_TOUR_LIST;
import static by.epam.project.action.JspParamNames.JSP_TOUR_TYPE_LIST;
import static by.epam.project.action.JspParamNames.JSP_TOUR_VALID_DATE;
import static by.epam.project.action.JspParamNames.JSP_TOUR_VALID_STATUS;
import static by.epam.project.action.JspParamNames.JSP_TRANS_MODE_LIST;
import static by.epam.project.action.JspParamNames.JSP_USER;
import by.epam.project.action.SessionRequestContent;
import static by.epam.project.dao.entquery.DirectionQuery.DAO_ID_DIRECTION;
import static by.epam.project.dao.entquery.RoleQuery.DAO_ROLE_NAME;
import static by.epam.project.dao.entquery.SearchQuery.DAO_TOUR_DATE_FROM;
import static by.epam.project.dao.entquery.SearchQuery.DAO_TOUR_DATE_TO;
import static by.epam.project.dao.entquery.TourQuery.DAO_TOUR_STATUS;
import static by.epam.project.dao.entquery.UserQuery.DAO_USER_LOGIN;
import by.epam.project.dao.query.Criteria;
import by.epam.project.entity.ClientType;
import by.epam.project.entity.Direction;
import by.epam.project.entity.Tour;
import by.epam.project.entity.User;
import by.epam.project.exception.ServletLogicException;
import by.epam.project.exception.TechnicalException;
import by.epam.project.logic.TourLogic;
import by.epam.project.manager.ClientTypeManager;
import by.epam.project.manager.ConfigurationManager;
import static by.epam.project.manager.ParamManager.getBoolParam;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Objects;

/**
 *
 * @author User
 */
public class ShowTour implements ActionCommand {

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
    
    public static void formTourList(SessionRequestContent request) throws ServletLogicException {
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
        
        Integer tourStatus = getTourStatus(request);
        if (tourStatus != null) {
            criteria.addParam(DAO_TOUR_STATUS, tourStatus);
        }
        Integer tourDateStatus = getTourDateStatus(request);
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

    private void distinguishTour(SessionRequestContent request) {
        List<Tour> list = (List<Tour>) request.getSessionAttribute(JSP_TOUR_LIST);
        String selectId = request.getParameter(JSP_SELECT_ID);
        Integer idTour;
        if (selectId != null) {
            idTour = Integer.decode(selectId);
        } else {
            Tour tour = (Tour) request.getSessionAttribute(JSP_CURRENT_TOUR);
            idTour = tour.getIdTour();
        }
        
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
                return;
            }
        }
    }

    public static void resaveParamsShowTour(SessionRequestContent request) {
        Direction direction = (Direction) request.getSessionAttribute(JSP_CURRENT_DIRECTION);
        if (direction != null) {
            Integer idDirection = direction.getIdDirection();
            if (idDirection != null) {
                request.setAttribute(JSP_ID_DIRECTION, idDirection);
            }
        }
    }

    private void cleanSessionShowTour(SessionRequestContent request) {
        request.deleteSessionAttribute(JSP_CURRENT_CITY);
        request.deleteSessionAttribute(JSP_CURRENT_COUNTRY);
        request.deleteSessionAttribute(JSP_CURRENT_HOTEL);
        
        
        request.deleteSessionAttribute(JSP_TOUR_TYPE_LIST);
        request.deleteSessionAttribute(JSP_TRANS_MODE_LIST);
        
        request.deleteSessionAttribute(JSP_COUNTRY_TAG_LIST);
        request.deleteSessionAttribute(JSP_CITY_TAG_LIST);
        request.deleteSessionAttribute(JSP_HOTEL_TAG_LIST);
        
        
        request.deleteSessionAttribute(JSP_PRICE_STEP);
        request.deleteSessionAttribute(JSP_DISCOUNT_STEP);
        
        request.setSessionAttribute(JSP_CURR_CITY_LIST, null);
    }

    public static Integer getTourStatus(SessionRequestContent request) {
        Boolean validStatus = getBoolParam(request, JSP_TOUR_VALID_STATUS);
        Boolean invalidStatus = getBoolParam(request, JSP_TOUR_INVALID_STATUS);
        if (validStatus == null && invalidStatus == null) {
            validStatus = true;
            invalidStatus = false;
        } else {
            if (validStatus == null) {
                validStatus = false;
            }
            if (invalidStatus == null) {
                invalidStatus = false;
            }
        }
        request.setAttribute(JSP_TOUR_VALID_STATUS, validStatus);
        request.setAttribute(JSP_TOUR_INVALID_STATUS, invalidStatus);
        
        Integer status = null;
        if (validStatus && ! invalidStatus) {
            status = 1;
        } else if ( ! validStatus && invalidStatus) {
            status = 0;
        }
        return status;
    }

    public static Integer getTourDateStatus(SessionRequestContent request) {
        Boolean validStatus = getBoolParam(request, JSP_TOUR_VALID_DATE);
        Boolean invalidStatus = getBoolParam(request, JSP_TOUR_INVALID_DATE);
        if (validStatus == null && invalidStatus == null) {
            validStatus = true;
            invalidStatus = false;
        } else {
            if (validStatus == null) {
                validStatus = false;
            }
            if (invalidStatus == null) {
                invalidStatus = false;
            }
        }
        request.setAttribute(JSP_TOUR_VALID_DATE, validStatus);
        request.setAttribute(JSP_TOUR_INVALID_DATE, invalidStatus);
        
        Integer status = null;
        if (validStatus && ! invalidStatus) {
            status = 1;
        } else if ( ! validStatus && invalidStatus) {
            status = 0;
        }
        return status;
    }
    
}
