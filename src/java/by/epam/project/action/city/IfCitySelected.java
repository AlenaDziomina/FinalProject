/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package by.epam.project.action.city;

import by.epam.project.action.ActionCommand;
import static by.epam.project.action.JspParamNames.JSP_CURR_ID_CITY;
import static by.epam.project.action.JspParamNames.JSP_ID_CITY;
import static by.epam.project.action.JspParamNames.JSP_PAGE;
import by.epam.project.action.ProcessSavedParameters;
import by.epam.project.action.hotel.GoShowHotel;
import static by.epam.project.action.hotel.GoShowHotel.formHotelList;
import by.epam.project.controller.SessionRequestContent;
import by.epam.project.exception.DaoUserLogicException;

/**
 *
 * @author User
 */
public class IfCitySelected implements ActionCommand {

    @Override
    public String execute(SessionRequestContent request) throws DaoUserLogicException {
        String page = (String) request.getSessionAttribute(JSP_PAGE);
        
        new ProcessSavedParameters().execute(request);
        
        String currCity = request.getParameter(JSP_CURR_ID_CITY);
        if (currCity != null && !currCity.isEmpty()){
            Integer idCity = Integer.decode(currCity);
            if (idCity > 0) {
                request.setAttribute(JSP_ID_CITY, idCity);
            }
            formHotelList(request);
        }
        request.setSessionAttribute(JSP_PAGE, page);
        return page;
    }
    
}
