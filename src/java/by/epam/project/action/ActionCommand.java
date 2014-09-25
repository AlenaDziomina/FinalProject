/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package by.epam.project.action;

import by.epam.project.controller.SessionRequestContent;

/**
 *
 * @author User
 */
public interface ActionCommand {
    static final String PARAM_NAME_LENG = "leng";
    static final String PARAM_NAME_LOCALE = "locale";
    static final String PARAM_NAME_PAGE = "page";
    static final String PARAM_NAME_COUNTRY_LIST = "countryList";
    static final String PARAM_NAME_COUNTRY_COUNT = "countryCount";
    static final String PARAM_NAME_SELECT_ID = "selectId";
    static final String PARAM_NAME_CURRENT_COUNTRY = "currCountry";
    
    String execute(SessionRequestContent request) throws DaoLogicException;
}
