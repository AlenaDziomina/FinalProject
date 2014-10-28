/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package by.epam.project.action.country;

import by.epam.project.action.ActionCommand;
import static by.epam.project.action.JspParamNames.*;
import by.epam.project.action.SessionRequestContent;
import static by.epam.project.dao.entquery.CountryQuery.DAO_COUNTRY_NAME;
import static by.epam.project.dao.entquery.CountryQuery.DAO_COUNTRY_PICTURE;
import static by.epam.project.dao.entquery.CountryQuery.DAO_ID_COUNTRY;
import static by.epam.project.dao.entquery.DescriptionQuery.DAO_DESCRIPTION_TEXT;
import static by.epam.project.dao.entquery.DescriptionQuery.DAO_ID_DESCRIPTION;
import static by.epam.project.dao.entquery.RoleQuery.DAO_ROLE_NAME;
import static by.epam.project.dao.entquery.UserQuery.DAO_USER_LOGIN;
import by.epam.project.dao.query.Criteria;
import by.epam.project.entity.ClientType;
import by.epam.project.entity.Country;
import by.epam.project.entity.Description;
import by.epam.project.entity.User;
import by.epam.project.exception.ServletLogicException;
import by.epam.project.exception.TechnicalException;
import by.epam.project.logic.CountryLogic;
import by.epam.project.manager.ClientTypeManager;
import by.epam.project.manager.ConfigurationManager;
import by.epam.project.manager.MessageManager;

/**
 *
 * @author User
 */
public class SaveRedactCountry implements ActionCommand {

    @Override
    public String execute(SessionRequestContent request) throws ServletLogicException {
        
        String page = ConfigurationManager.getProperty("path.page.editcountry");
        resaveParamsSaveCountry(request);
        
        Criteria criteria = new Criteria();
        Country country = (Country) request.getSessionAttribute(JSP_CURRENT_COUNTRY);
        if (country != null) {
            Integer idCountry = country.getIdCountry();
            if (idCountry != null) {
                criteria.addParam(DAO_ID_COUNTRY, idCountry);
            }
            Integer idDescription = country.getDescription().getIdDescription();
            if (idDescription != null) {
                criteria.addParam(DAO_ID_DESCRIPTION, idDescription);
            }
            criteria.addParam(DAO_COUNTRY_NAME, country.getName());
            criteria.addParam(DAO_COUNTRY_PICTURE, country.getPicture());
            criteria.addParam(DAO_DESCRIPTION_TEXT, country.getDescription().getText());
        }
        
        User user = (User) request.getSessionAttribute(JSP_USER);
        if (user != null) {
            criteria.addParam(DAO_USER_LOGIN, user.getLogin());
            ClientType type = ClientTypeManager.clientTypeOf(user.getRole().getRoleName());
            criteria.addParam(DAO_ROLE_NAME, type);
        } else {
            criteria.addParam(DAO_ROLE_NAME, request.getSessionAttribute(JSP_ROLE_TYPE));
        }
                
        try {
            Integer resIdCountry = new CountryLogic().doRedactEntity(criteria);
            request.setParameter(JSP_SELECT_ID, resIdCountry.toString());
            return new GoShowCountry().execute(request);     
        } catch (TechnicalException ex) {
            request.setAttribute("errorReason", ex.getMessage());
            request.setAttribute("errorAdminMsg", ex.getCause().getMessage());
            request.setAttribute("errorSaveData", MessageManager.getProperty("message.errorsavedata"));
            request.setSessionAttribute(JSP_PAGE, page);
            return page;
        }       
    }

    private static void resaveParamsSaveCountry(SessionRequestContent request) {
        createCurrCountry(request);
    }
    
    private static void createCurrCountry(SessionRequestContent request) {
        Country currCountry = (Country) request.getSessionAttribute(JSP_CURRENT_COUNTRY);
        if (currCountry == null) {
            currCountry = new Country();
            currCountry.setDescription(new Description());
        }
        currCountry.setName(request.getParameter(JSP_COUNTRY_NAME));
        currCountry.setPicture(request.getParameter(JSP_COUNTRY_PICTURE));
        currCountry.getDescription().setText(request.getParameter(JSP_DESCRIPTION_TEXT));
        request.setSessionAttribute(JSP_CURRENT_COUNTRY, currCountry);
    }
}


