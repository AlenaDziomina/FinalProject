/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package by.epam.project.action.tour;

import by.epam.project.action.ActionCommand;
import static by.epam.project.action.ActionCommand.checkParam;
import static by.epam.project.action.ActionCommand.checkParam;
import static by.epam.project.action.ActionCommand.checkParam;
import static by.epam.project.action.ActionCommand.checkParam;
import static by.epam.project.action.ActionCommand.checkParam;
import static by.epam.project.action.ActionCommand.checkParam;
import static by.epam.project.action.ActionCommand.checkParam;
import static by.epam.project.action.ActionCommand.checkParam;
import static by.epam.project.action.ActionCommand.checkParam;
import static by.epam.project.action.ActionCommand.checkParam;
import static by.epam.project.action.ActionCommand.checkParam;
import static by.epam.project.action.ActionCommand.checkParam;
import static by.epam.project.action.ActionCommand.checkParam;
import static by.epam.project.action.ActionCommand.checkParam;
import static by.epam.project.action.ActionCommand.checkParam;
import static by.epam.project.action.ActionCommand.checkParam;
import static by.epam.project.action.ActionCommand.checkParam;
import static by.epam.project.action.ActionCommand.checkParam;
import static by.epam.project.action.JspParamNames.JSP_CURR_HOTEL_STARS;
import static by.epam.project.action.JspParamNames.JSP_CURR_ID_CITY;
import static by.epam.project.action.JspParamNames.JSP_CURR_ID_COUNTRY;
import static by.epam.project.action.JspParamNames.JSP_CURR_ID_DIRECTION;
import static by.epam.project.action.JspParamNames.JSP_CURR_ID_TOUR;
import static by.epam.project.action.JspParamNames.JSP_DESCRIPTION_TEXT;
import static by.epam.project.action.JspParamNames.JSP_FREE_SEATS;
import static by.epam.project.action.JspParamNames.JSP_HOTEL_NAME;
import static by.epam.project.action.JspParamNames.JSP_HOTEL_PICTURE;
import static by.epam.project.action.JspParamNames.JSP_ID_DESCRIPTION;
import static by.epam.project.action.JspParamNames.JSP_ID_HOTEL;
import static by.epam.project.action.JspParamNames.JSP_PAGE;
import static by.epam.project.action.JspParamNames.JSP_ROLE_TYPE;
import static by.epam.project.action.JspParamNames.JSP_SELECT_ID;
import static by.epam.project.action.JspParamNames.JSP_TOTAL_SEATS;
import static by.epam.project.action.JspParamNames.JSP_TOUR_DATE;
import static by.epam.project.action.JspParamNames.JSP_TOUR_DAYS;
import static by.epam.project.action.JspParamNames.JSP_TOUR_DISCOUNT;
import static by.epam.project.action.JspParamNames.JSP_TOUR_PRICE;
import static by.epam.project.action.JspParamNames.JSP_USER_LOGIN;
import by.epam.project.action.ProcessSavedParameters;
import by.epam.project.action.hotel.ShowHotel;
import by.epam.project.controller.SessionRequestContent;
import static by.epam.project.dao.entquery.CityQuery.DAO_ID_CITY;
import static by.epam.project.dao.entquery.CountryQuery.DAO_ID_COUNTRY;
import static by.epam.project.dao.entquery.DescriptionQuery.DAO_DESCRIPTION_TEXT;
import static by.epam.project.dao.entquery.DescriptionQuery.DAO_ID_DESCRIPTION;
import static by.epam.project.dao.entquery.DirectionQuery.DAO_ID_DIRECTION;
import static by.epam.project.dao.entquery.HotelQuery.DAO_HOTEL_NAME;
import static by.epam.project.dao.entquery.HotelQuery.DAO_HOTEL_PICTURE;
import static by.epam.project.dao.entquery.HotelQuery.DAO_HOTEL_STARS;
import static by.epam.project.dao.entquery.HotelQuery.DAO_ID_HOTEL;
import static by.epam.project.dao.entquery.RoleQuery.DAO_ROLE_NAME;
import static by.epam.project.dao.entquery.TourQuery.DAO_ID_TOUR;
import static by.epam.project.dao.entquery.TourQuery.DAO_TOUR_DATE;
import static by.epam.project.dao.entquery.TourQuery.DAO_TOUR_DAYS;
import static by.epam.project.dao.entquery.TourQuery.DAO_TOUR_DISCOUNT;
import static by.epam.project.dao.entquery.TourQuery.DAO_TOUR_FREE_SEATS;
import static by.epam.project.dao.entquery.TourQuery.DAO_TOUR_PRICE;
import static by.epam.project.dao.entquery.TourQuery.DAO_TOUR_TOTAL_SEATS;
import static by.epam.project.dao.entquery.UserQuery.DAO_USER_LOGIN;
import by.epam.project.dao.query.Criteria;
import by.epam.project.exception.DaoAccessPermission;
import by.epam.project.exception.DaoConnectException;
import by.epam.project.exception.DaoException;
import by.epam.project.exception.DaoInitException;
import by.epam.project.exception.DaoQueryException;
import by.epam.project.exception.DaoUserLogicException;
import by.epam.project.logic.HotelLogic;
import by.epam.project.logic.TourLogic;
import by.epam.project.manager.ConfigurationManager;
import by.epam.project.manager.MessageManager;

/**
 *
 * @author User
 */
public class SaveRedactTour implements ActionCommand {

    @Override
    public String execute(SessionRequestContent request) throws DaoUserLogicException {
        String page = ConfigurationManager.getProperty("path.page.edittour");
        new ProcessSavedParameters().execute(request);
        
        Criteria criteria = new Criteria();
        checkParam(request, criteria, JSP_CURR_ID_DIRECTION, DAO_ID_DIRECTION);
        checkParam(request, criteria, JSP_CURR_ID_TOUR, DAO_ID_TOUR);
        checkParam(request, criteria, JSP_TOUR_DATE, DAO_TOUR_DATE);
        checkParam(request, criteria, JSP_TOUR_DAYS, DAO_TOUR_DAYS);
        checkParam(request, criteria, JSP_TOUR_PRICE, DAO_TOUR_PRICE);
        checkParam(request, criteria, JSP_TOUR_DISCOUNT, DAO_TOUR_DISCOUNT);
        checkParam(request, criteria, JSP_TOTAL_SEATS, DAO_TOUR_TOTAL_SEATS);
        checkParam(request, criteria, JSP_FREE_SEATS, DAO_TOUR_FREE_SEATS);
        
        criteria.addParam(DAO_USER_LOGIN, request.getSessionAttribute(JSP_USER_LOGIN));
        criteria.addParam(DAO_ROLE_NAME, request.getSessionAttribute(JSP_ROLE_TYPE));
        
        try {
            Integer resIdTour = TourLogic.redactTour(criteria);
            request.setParameter(JSP_SELECT_ID, resIdTour.toString());
            return new ShowTour().execute(request);
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
        return page;
    }
}