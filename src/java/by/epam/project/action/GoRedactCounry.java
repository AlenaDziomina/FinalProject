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
import by.epam.project.entity.Country;
import by.epam.project.logic.CountryLogic;
import by.epam.project.manager.ConfigurationManager;
import by.epam.project.manager.MessageManager;
import java.util.List;

/**
 *
 * @author User
 */
class GoRedactCounry implements ActionCommand {

    public GoRedactCounry() {
    }

    @Override
    public String execute(SessionRequestContent request) throws DaoLogicException {
        String page = ConfigurationManager.getProperty("path.page.countries");
        request.setSessionAttribute(PARAM_NAME_PAGE, page);
        
        Criteria criteria = new Criteria();
        criteria.addParam(PARAM_NAME_LOGIN, request.getSessionAttribute(PARAM_NAME_LOGIN));
        criteria.addParam(PARAM_NAME_ROLE, request.getSessionAttribute(PARAM_NAME_ROLE));
        
        try {
            List<Country> countrys = CountryLogic.getCountries(criteria);
            if (countrys != null || !countrys.isEmpty()) {
                request.setSessionAttribute(PARAM_NAME_COUNTRY_LIST, countrys);
                request.setSessionAttribute(PARAM_NAME_COUNTRY_COUNT, countrys.size());
            } else {
                request.setAttribute("errorGetListMessage", MessageManager.getProperty("message.listerror"));
            }
            request.setSessionAttribute(PARAM_NAME_PAGE, page);
            return page;
        } catch (DaoException ex) {
            throw new DaoLogicException(MessageManager.getProperty("message.daoerror"));
        }
        
        
    }
    
}
