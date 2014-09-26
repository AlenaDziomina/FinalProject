/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package by.epam.project.action;

import static by.epam.project.action.ActionCommand.PARAM_NAME_PAGE;
import by.epam.project.controller.SessionRequestContent;
import static by.epam.project.dao.AbstractDao.*;
import by.epam.project.dao.DaoException;
import by.epam.project.dao.query.Criteria;
import by.epam.project.logic.CountryLogic;
import by.epam.project.manager.ConfigurationManager;
import by.epam.project.manager.MessageManager;

/**
 *
 * @author User
 */
class SaveRedactCountry implements ActionCommand {

    public SaveRedactCountry() {
    }

    @Override
    public String execute(SessionRequestContent request) throws DaoLogicException {
        
        String page = null;
        Criteria criteria = new Criteria();
        
        String idCountry = (String) request.getParameter(PARAM_NAME_ID_COUNTRY);
        criteria.remuveParam(idCountry);
        if (idCountry != null && !idCountry.isEmpty()) {
            criteria.addParam(PARAM_NAME_ID_COUNTRY, Integer.decode(idCountry));
        }
        
        String idDescription = (String) request.getParameter(PARAM_NAME_ID_DESCRIPTION);
        criteria.remuveParam(idDescription);
        if (idDescription != null && !idDescription.isEmpty()) {
            criteria.addParam(PARAM_NAME_ID_DESCRIPTION, Integer.decode(idDescription));
        }
        
        criteria.addParam(PARAM_NAME_ROLE, request.getSessionAttribute(PARAM_NAME_ROLE));
        criteria.addParam(PARAM_NAME_LOGIN, request.getSessionAttribute(PARAM_NAME_ROLE));
        criteria.addParam(PARAM_NAME_NAME_COUNTRY, request.getParameter(PARAM_NAME_NAME_COUNTRY));
        criteria.addParam(PARAM_NAME_PICTURE_COUNTRY, request.getParameter(PARAM_NAME_PICTURE_COUNTRY));
        criteria.addParam(PARAM_NAME_TEXT_DESCRIPTION, request.getParameter(PARAM_NAME_TEXT_DESCRIPTION));
        
        try {
            Integer resIdCountry = CountryLogic.redactCountry(criteria);
            if (resIdCountry == null) {
                page = ConfigurationManager.getProperty("path.page.editcountry");
                request.setSessionAttribute(PARAM_NAME_PAGE, page);
                request.setAttribute("errorSaveData", MessageManager.getProperty("message.errorsavedata"));
            } else {
                new GoShowCountry().execute(request);
                request.setParameter(PARAM_NAME_SELECT_ID, resIdCountry.toString());
                page = new ShowCountry().execute(request);
                request.setSessionAttribute(PARAM_NAME_PAGE, page);
            }
            return page;
        } catch (DaoException ex) {
            throw new DaoLogicException(MessageManager.getProperty("message.daoerror"));
        }
        
    }
    
}
