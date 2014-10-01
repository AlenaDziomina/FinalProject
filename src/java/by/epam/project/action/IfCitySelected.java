/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package by.epam.project.action;

import static by.epam.project.action.ActionCommand.PARAM_NAME_PAGE;
import by.epam.project.controller.SessionRequestContent;
import static by.epam.project.dao.AbstractDao.PARAM_NAME_ID_CITY;

/**
 *
 * @author User
 */
public class IfCitySelected implements ActionCommand {

    @Override
    public String execute(SessionRequestContent request) throws DaoLogicException {
        String page = (String) request.getSessionAttribute(PARAM_NAME_PAGE);
        
        new ProcessSavedParameters().execute(request);
        
        String currCity = request.getParameter(PARAM_NAME_CURR_ID_CITY);
        if (currCity != null && !currCity.isEmpty()){
            Integer idCity = Integer.decode(currCity);
            if (idCity > 0) {
                request.setAttribute(PARAM_NAME_ID_CITY, idCity);
            }
            new GoShowHotel().execute(request);
        }
        request.setSessionAttribute(PARAM_NAME_PAGE, page);
        return page;
    }
    
}
