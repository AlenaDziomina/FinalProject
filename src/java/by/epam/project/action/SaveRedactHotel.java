/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package by.epam.project.action;

import static by.epam.project.action.ActionCommand.*;
import by.epam.project.controller.SessionRequestContent;
import static by.epam.project.dao.AbstractDao.*;
import by.epam.project.dao.DaoException;
import by.epam.project.dao.query.Criteria;
import by.epam.project.logic.HotelLogic;
import by.epam.project.manager.ConfigurationManager;
import by.epam.project.manager.MessageManager;

/**
 *
 * @author User
 */
public class SaveRedactHotel implements ActionCommand {

    public SaveRedactHotel() {
    }

    @Override
    public String execute(SessionRequestContent request) throws DaoLogicException {
        
        String page = null;
        Criteria criteria = new Criteria();
        
        checkParam(request, criteria, PARAM_NAME_ID_COUNTRY);
        checkParam(request, criteria, PARAM_NAME_ID_CITY);
        checkParam(request, criteria, PARAM_NAME_ID_HOTEL);
        checkParam(request, criteria, PARAM_NAME_STARS_HOTEL);
        checkParam(request, criteria, PARAM_NAME_ID_DESCRIPTION);
        
        criteria.addParam(PARAM_NAME_ROLE, request.getSessionAttribute(PARAM_NAME_ROLE));
        criteria.addParam(PARAM_NAME_LOGIN, request.getSessionAttribute(PARAM_NAME_ROLE));
        criteria.addParam(PARAM_NAME_NAME_HOTEL, request.getParameter(PARAM_NAME_NAME_HOTEL));
        criteria.addParam(PARAM_NAME_PICTURE_HOTEL, request.getParameter(PARAM_NAME_PICTURE_HOTEL));
        criteria.addParam(PARAM_NAME_TEXT_DESCRIPTION, request.getParameter(PARAM_NAME_TEXT_DESCRIPTION));
        
        try {
            Integer resIdHotel = HotelLogic.redactHotel(criteria);
            if (resIdHotel == null) {
                page = ConfigurationManager.getProperty("path.page.edithotel");
                request.setSessionAttribute(PARAM_NAME_PAGE, page);
                request.setAttribute("errorSaveData", MessageManager.getProperty("message.errorsavedata"));
            } else {
                new GoShowHotel().execute(request);
                request.setParameter(PARAM_NAME_SELECT_ID, resIdHotel.toString());
                page = new ShowHotel().execute(request);
                request.setSessionAttribute(PARAM_NAME_PAGE, page);
            }
            return page;
        } catch (DaoException ex) {
            throw new DaoLogicException(MessageManager.getProperty("message.daoerror"));
        }
        
    }
    
}