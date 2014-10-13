/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package by.epam.project.action.tour;

import by.epam.project.action.ActionCommand;
import static by.epam.project.action.JspParamNames.JSP_CURR_CITY_TAGS;
import static by.epam.project.action.JspParamNames.JSP_CURR_COUNTRY_TAGS;
import static by.epam.project.action.JspParamNames.JSP_CURR_HOTEL_TAGS;
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
import static by.epam.project.dao.entquery.HotelQuery.DAO_ID_HOTEL;
import static by.epam.project.dao.entquery.RoleQuery.DAO_ROLE_NAME;
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
import static by.epam.project.manager.ParamManager.checkArrParam;
import static by.epam.project.manager.ParamManager.checkIntParam;
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
        checkIntParam(request, criteria, JSP_CURR_TOUR_TYPE, DAO_ID_TOURTYPE);
        checkIntParam(request, criteria, JSP_CURR_TRANS_MODE, DAO_ID_TRANSMODE);
        
        
        criteria.addParam(DAO_USER_LOGIN, request.getSessionAttribute(JSP_USER_LOGIN));
        criteria.addParam(DAO_ROLE_NAME, request.getSessionAttribute(JSP_ROLE_TYPE));
        
        checkArrParam(request, criteria, JSP_CURR_COUNTRY_TAGS, DAO_ID_COUNTRY);
        checkArrParam(request, criteria, JSP_CURR_CITY_TAGS, DAO_ID_CITY);
        checkArrParam(request, criteria, JSP_CURR_HOTEL_TAGS, DAO_ID_HOTEL);
        
        
        try {
            List<Tour> tours = SearchLogic.getTours(criteria);
            request.setSessionAttribute(JSP_TOUR_LIST, tours);
            request.setAttribute(JSP_IS_HIDDEN, true);
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
