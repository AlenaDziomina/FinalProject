/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package by.epam.project.action.tour;

import by.epam.project.action.ActionCommand;
import by.epam.project.action.JspParamNames;
import static by.epam.project.action.JspParamNames.JSP_BOX_ALL_CITIES;
import static by.epam.project.action.JspParamNames.JSP_BOX_ALL_COUNTRIES;
import static by.epam.project.action.JspParamNames.JSP_BOX_ALL_DEPART_DATE;
import static by.epam.project.action.JspParamNames.JSP_BOX_ALL_HOTELS;
import static by.epam.project.action.JspParamNames.JSP_BOX_ALL_PRICE;
import static by.epam.project.action.JspParamNames.JSP_CURR_CITY_TAGS;
import static by.epam.project.action.JspParamNames.JSP_CURR_COUNTRY_TAGS;
import static by.epam.project.action.JspParamNames.JSP_CURR_DAYS_COUNT_FROM;
import static by.epam.project.action.JspParamNames.JSP_CURR_DAYS_COUNT_TO;
import static by.epam.project.action.JspParamNames.JSP_CURR_DEPART_DATE_FROM;
import static by.epam.project.action.JspParamNames.JSP_CURR_DEPART_DATE_TO;
import static by.epam.project.action.JspParamNames.JSP_CURR_DISCOUNT_FROM;
import static by.epam.project.action.JspParamNames.JSP_CURR_HOTEL_STARS;
import static by.epam.project.action.JspParamNames.JSP_CURR_HOTEL_TAGS;
import static by.epam.project.action.JspParamNames.JSP_CURR_PRICE_FROM;
import static by.epam.project.action.JspParamNames.JSP_CURR_PRICE_TO;
import static by.epam.project.action.JspParamNames.JSP_CURR_TOUR_TYPE;
import static by.epam.project.action.JspParamNames.JSP_CURR_TRANS_MODE;
import static by.epam.project.action.JspParamNames.JSP_DESCRIPTION_TEXT;
import static by.epam.project.action.JspParamNames.JSP_DIRECTION_NAME;
import static by.epam.project.action.JspParamNames.JSP_DIRECTION_PICTURE;
import static by.epam.project.action.JspParamNames.JSP_DIRECTION_STATUS;
import static by.epam.project.action.JspParamNames.JSP_DIRECTION_TEXT;
import static by.epam.project.action.JspParamNames.JSP_ID_DESCRIPTION;
import static by.epam.project.action.JspParamNames.JSP_ID_DIRECTION;
import static by.epam.project.action.JspParamNames.JSP_IS_HIDDEN;
import static by.epam.project.action.JspParamNames.JSP_PAGE;
import static by.epam.project.action.JspParamNames.JSP_ROLE_TYPE;
import static by.epam.project.action.JspParamNames.JSP_SELECT_ID;
import static by.epam.project.action.JspParamNames.JSP_TOUR_LIST;
import static by.epam.project.action.JspParamNames.JSP_USER_LOGIN;
import static by.epam.project.action.ProcessSavedParameters.resaveParams;
import by.epam.project.action.direction.ShowDirection;
import by.epam.project.controller.SessionRequestContent;
import static by.epam.project.dao.entquery.CityQuery.DAO_ID_CITY;
import static by.epam.project.dao.entquery.CountryQuery.DAO_ID_COUNTRY;
import static by.epam.project.dao.entquery.DescriptionQuery.DAO_DESCRIPTION_TEXT;
import static by.epam.project.dao.entquery.DescriptionQuery.DAO_ID_DESCRIPTION;
import static by.epam.project.dao.entquery.DirectionQuery.DAO_DIRECTION_NAME;
import static by.epam.project.dao.entquery.DirectionQuery.DAO_DIRECTION_PICTURE;
import static by.epam.project.dao.entquery.DirectionQuery.DAO_DIRECTION_STATUS;
import static by.epam.project.dao.entquery.DirectionQuery.DAO_DIRECTION_TEXT;
import static by.epam.project.dao.entquery.DirectionQuery.DAO_ID_DIRECTION;
import static by.epam.project.dao.entquery.HotelQuery.DAO_HOTEL_STARS;
import static by.epam.project.dao.entquery.HotelQuery.DAO_ID_HOTEL;
import static by.epam.project.dao.entquery.RoleQuery.DAO_ROLE_NAME;
import by.epam.project.dao.entquery.SearchQuery;
import static by.epam.project.dao.entquery.SearchQuery.DAO_TOUR_DATE_FROM;
import static by.epam.project.dao.entquery.SearchQuery.DAO_TOUR_DATE_TO;
import static by.epam.project.dao.entquery.SearchQuery.DAO_TOUR_DAYS_FROM;
import static by.epam.project.dao.entquery.SearchQuery.DAO_TOUR_DAYS_TO;
import static by.epam.project.dao.entquery.SearchQuery.DAO_TOUR_DISCOUNT_FROM;
import static by.epam.project.dao.entquery.SearchQuery.DAO_TOUR_PRICE_FROM;
import static by.epam.project.dao.entquery.SearchQuery.DAO_TOUR_PRICE_TO;
import static by.epam.project.dao.entquery.TourTypeQuery.DAO_ID_TOURTYPE;
import static by.epam.project.dao.entquery.TransModeQuery.DAO_ID_TRANSMODE;
import static by.epam.project.dao.entquery.UserQuery.DAO_USER_LOGIN;
import by.epam.project.dao.query.Criteria;
import by.epam.project.entity.Tour;
import by.epam.project.exception.DaoAccessPermission;
import by.epam.project.exception.DaoConnectException;
import by.epam.project.exception.DaoException;
import by.epam.project.exception.DaoInitException;
import by.epam.project.exception.DaoQueryException;
import by.epam.project.exception.DaoUserLogicException;
import by.epam.project.logic.DirectionLogic;
import by.epam.project.logic.SearchLogic;
import by.epam.project.manager.ConfigurationManager;
import by.epam.project.manager.MessageManager;
import by.epam.project.manager.ParamManager;
import static by.epam.project.manager.ParamManager.checkArrParam;
import static by.epam.project.manager.ParamManager.checkIntParam;
import static by.epam.project.manager.ParamManager.getDateParam;
import static by.epam.project.manager.ParamManager.getFltParam;
import static by.epam.project.manager.ParamManager.getIntParam;
import java.util.Date;
import java.util.List;

/**
 *
 * @author User
 */
public class SearchTour implements ActionCommand {

    @Override
    public String execute(SessionRequestContent request) throws DaoUserLogicException {
        String page = ConfigurationManager.getProperty("path.page.tours");
        resaveParams(request);
        
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
            criteria.addParam(DAO_TOUR_PRICE_FROM, priceFrom * priceStep);
            Float priceTo = getFltParam(request, JSP_CURR_PRICE_TO);
            criteria.addParam(DAO_TOUR_PRICE_TO, priceTo * priceStep);
        }
        
        Integer discountStep = Integer.decode(ConfigurationManager.getProperty("discount.step"));
        Integer discountFrom = getIntParam(request, JSP_CURR_DISCOUNT_FROM);
        if (discountFrom > 0) {
            criteria.addParam(DAO_TOUR_DISCOUNT_FROM, discountFrom * discountStep);
        }
        
        checkIntParam(request, criteria, JSP_CURR_HOTEL_STARS, DAO_HOTEL_STARS);
        checkIntParam(request, criteria, JSP_CURR_TOUR_TYPE, DAO_ID_TOURTYPE);
        checkIntParam(request, criteria, JSP_CURR_TRANS_MODE, DAO_ID_TRANSMODE);
        
        
        criteria.addParam(DAO_USER_LOGIN, request.getSessionAttribute(JSP_USER_LOGIN));
        criteria.addParam(DAO_ROLE_NAME, request.getSessionAttribute(JSP_ROLE_TYPE));
        
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
            List<Tour> tours = SearchLogic.getTours(criteria);
            if (tours.isEmpty()) {
                request.deleteSessionAttribute(JSP_TOUR_LIST);
                request.setAttribute("emptysearch", MessageManager.getProperty("message.emptysearch"));
                request.setAttribute(JSP_IS_HIDDEN, false);
            } else {
                request.setSessionAttribute(JSP_TOUR_LIST, tours);
                request.setAttribute(JSP_IS_HIDDEN, true);
            }
            return page;
        } catch (DaoAccessPermission ex) {
            request.setAttribute("errorReason", MessageManager.getProperty("message.errordaoaccess"));
            request.setAttribute("errorAdminMsg", ex.getMessage());
        } catch (DaoConnectException ex) {
            request.setAttribute("errorReason", MessageManager.getProperty("message.errordaoconnect"));
            request.setAttribute("errorAdminMsg", ex.getMessage());
        } catch (DaoQueryException ex) {
            request.setAttribute("errorReason", MessageManager.getProperty("message.errordaoquery"));
            request.setAttribute("errorAdminMsg", ex.getMessage());
        } catch (DaoInitException ex) {
            throw new DaoUserLogicException(MessageManager.getProperty("message.daoerror" + ex.getMessage()));
        } catch (DaoException ex){
            throw new DaoUserLogicException(MessageManager.getProperty("message.daoerror" + ex.getMessage()));
        }
        request.setAttribute("errorSaveData", MessageManager.getProperty("message.errorsavedata"));
        request.setSessionAttribute(JSP_PAGE, page);
        request.setAttribute(JSP_IS_HIDDEN, false);
        return page;
    }
    
}
