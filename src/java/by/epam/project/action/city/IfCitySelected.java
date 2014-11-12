/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package by.epam.project.action.city;

import by.epam.project.action.ActionCommand;
import static by.epam.project.action.JspParamNames.*;
import by.epam.project.action.SessionRequestContent;
import static by.epam.project.action.direction.SaveRedactDirection.resaveParamsSaveDirection;
import static by.epam.project.action.hotel.GoShowHotel.formHotelList;
import static by.epam.project.action.tour.SearchTour.resaveParamsSearchTour;
import by.epam.project.exception.ServletLogicException;
import by.epam.project.manager.ConfigurationManager;

/**
 *
 * @author User
 */
public class IfCitySelected implements ActionCommand {

    @Override
    public String execute(SessionRequestContent request) throws ServletLogicException {
        String page = (String) request.getSessionAttribute(JSP_PAGE);
        resaveParamsCitySelected(request);
        request.setAttribute(JSP_CURR_ID_HOTEL, "0");
        
        String currCity = request.getParameter(JSP_CURR_ID_CITY);
        if (currCity != null && !currCity.isEmpty()){
            Integer idCity = Integer.decode(currCity);
            if (idCity > 0) {
                request.setAttribute(JSP_ID_CITY, idCity);
            }
            formHotelList(request);
        }
        return page;
    }

    private void resaveParamsCitySelected(SessionRequestContent request) throws ServletLogicException {
        String page = (String) request.getSessionAttribute(JSP_PAGE);
        String editDirectionPage = ConfigurationManager.getProperty("path.page.editdirection");
        String searchingPage = ConfigurationManager.getProperty("path.page.tours");
        if (page == null ? editDirectionPage == null : page.equals(editDirectionPage)) {
            resaveParamsSaveDirection(request);
        } else if (page == null ? searchingPage == null : page.equals(searchingPage)) {
            resaveParamsSearchTour(request);
        }
    }    
}
