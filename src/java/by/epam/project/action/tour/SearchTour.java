package by.epam.project.action.tour;

import by.epam.project.action.ActionCommand;
import by.epam.project.action.JspParamNames;
import static by.epam.project.action.JspParamNames.*;
import static by.epam.project.dao.DaoParamNames.*;
import by.epam.project.action.SessionRequestContent;
import by.epam.project.dao.query.Criteria;
import by.epam.project.dao.ClientType;
import by.epam.project.entity.Tour;
import by.epam.project.entity.User;
import by.epam.project.exception.ServletLogicException;
import by.epam.project.exception.TechnicalException;
import by.epam.project.logic.SearchLogic;
import by.epam.project.manager.ClientTypeManager;
import by.epam.project.manager.ConfigurationManager;
import by.epam.project.manager.ParamManager;
import static by.epam.project.manager.ParamManager.checkArrParam;
import static by.epam.project.manager.ParamManager.checkIntParam;
import static by.epam.project.manager.ParamManager.getFltParam;
import static by.epam.project.manager.ParamManager.getIntParam;
import by.epam.project.tag.ObjList;
import java.util.List;

/**
 * Class of command of displaying the result of tour searching and its parameters
 * @author Helena.Grouk
 */
public class SearchTour extends TourCommand implements ActionCommand {
    @Override
    public String execute(SessionRequestContent request) throws ServletLogicException {
        String page = ConfigurationManager.getProperty("path.page.tours");
        resaveParamsSearchTour(request);
        
        Criteria criteria = new Criteria();
        if ( ! ParamManager.getBoolParam(request, JSP_BOX_ALL_DEPART_DATE)) {
            ParamManager.checkDatParam(request, criteria, JSP_CURR_DEPART_DATE_FROM, DAO_TOUR_DATE_FROM);
            ParamManager.checkDatParam(request, criteria, JSP_CURR_DEPART_DATE_TO, DAO_TOUR_DATE_TO);
        }
        if ( ! ParamManager.getBoolParam(request, JspParamNames.JSP_BOX_ALL_DAYS_COUNT)) {
            ParamManager.checkIntParam(request, criteria, JSP_CURR_DAYS_COUNT_FROM, DAO_TOUR_DAYS_FROM);
            ParamManager.checkIntParam(request, criteria, JSP_CURR_DAYS_COUNT_TO, DAO_TOUR_DAYS_TO);
        }
        if ( ! ParamManager.getBoolParam(request, JSP_BOX_ALL_PRICE)) {
            Integer priceStep = Integer.decode(ConfigurationManager.getProperty("price.step"));
            Float priceFrom = getFltParam(request, JSP_CURR_PRICE_FROM);
            if (priceFrom > 0) {
                criteria.addParam(DAO_TOUR_PRICE_FROM, priceFrom * priceStep);
            }
            Float priceTo = getFltParam(request, JSP_CURR_PRICE_TO);
            if (priceTo > 0) {
                criteria.addParam(DAO_TOUR_PRICE_TO, priceTo * priceStep);
            }
        }
        Integer discountStep = Integer.decode(ConfigurationManager.getProperty("discount.step"));
        Integer discountFrom = getIntParam(request, JSP_CURR_DISCOUNT_FROM);
        if (discountFrom != null && discountFrom > 0) {
            criteria.addParam(DAO_TOUR_DISCOUNT_FROM, discountFrom * discountStep);
        }
        checkIntParam(request, criteria, JSP_CURR_HOTEL_STARS, DAO_HOTEL_STARS);
        checkIntParam(request, criteria, JSP_CURR_TOUR_TYPE, DAO_ID_TOURTYPE);
        checkIntParam(request, criteria, JSP_CURR_TRANS_MODE, DAO_ID_TRANSMODE);
        
        User user = (User) request.getSessionAttribute(JSP_USER);
        if (user != null) {
            criteria.addParam(DAO_USER_LOGIN, user.getLogin());
            ClientType type = ClientTypeManager.clientTypeOf(user.getRole().getRoleName());
            criteria.addParam(DAO_ROLE_NAME, type);
        } else {
            criteria.addParam(DAO_ROLE_NAME, request.getSessionAttribute(JSP_ROLE_TYPE));
        }
        
        if ( ! ParamManager.getBoolParam(request, JSP_BOX_ALL_COUNTRIES)) {
            checkArrParam(request, criteria, JSP_CURR_COUNTRY_TAGS, DAO_ID_COUNTRY);
        }
        if ( ! ParamManager.getBoolParam(request, JSP_BOX_ALL_CITIES)) {
            checkArrParam(request, criteria, JSP_CURR_CITY_TAGS, DAO_ID_CITY);
        }
        if ( ! ParamManager.getBoolParam(request, JSP_BOX_ALL_HOTELS)) {
            checkArrParam(request, criteria, JSP_CURR_HOTEL_TAGS, DAO_ID_HOTEL);
        }
        try {
            List<Tour> tours = new SearchLogic().doGetEntity(criteria);
            if (tours.isEmpty()) {
                request.deleteSessionAttribute(JSP_TOUR_LIST);
                request.setAttribute("emptysearch", "message.emptySearch");
                request.setSessionAttribute(JSP_IS_HIDDEN, false);
                request.deleteSessionAttribute(JSP_PAGE_LIST);
            } else {
                request.setSessionAttribute(JSP_TOUR_LIST, tours);
                request.setSessionAttribute(JSP_IS_HIDDEN, true);
                ObjList<Tour> list = new ObjList<>(tours);
                request.setSessionAttribute(JSP_PAGE_LIST, list);
            }
        } catch (TechnicalException ex) {
            throw new ServletLogicException(ex.getMessage(), ex);
        }
        return page;
    }   
}
