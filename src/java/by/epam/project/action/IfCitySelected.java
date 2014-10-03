/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package by.epam.project.action;

import static by.epam.project.controller.JspParamNames.JSP_ID_CITY;
import static by.epam.project.controller.JspParamNames.JSP_PAGE;
import by.epam.project.controller.SessionRequestContent;

/**
 *
 * @author User
 */
public class IfCitySelected implements ActionCommand {

    @Override
    public String execute(SessionRequestContent request) throws DaoLogicException {
        String page = (String) request.getSessionAttribute(JSP_PAGE);
        
        new ProcessSavedParameters().execute(request);
        
        String currCity = request.getParameter(PARAM_NAME_CURR_ID_CITY);
        if (currCity != null && !currCity.isEmpty()){
            Integer idCity = Integer.decode(currCity);
            if (idCity > 0) {
                request.setAttribute(JSP_ID_CITY, idCity);
            }
            new GoShowHotel().execute(request);
        }
        request.setSessionAttribute(JSP_PAGE, page);
        return page;
    }
    
}
