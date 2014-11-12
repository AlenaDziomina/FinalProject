/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package by.epam.project.action.hotel;

import by.epam.project.action.ActionCommand;
import static by.epam.project.action.JspParamNames.*;
import by.epam.project.action.SessionRequestContent;
import by.epam.project.entity.Hotel;
import by.epam.project.exception.ServletLogicException;
import by.epam.project.manager.ConfigurationManager;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;

/**
 *
 * @author User
 */
public class ShowHotel implements ActionCommand {
    
    @Override
    public String execute(SessionRequestContent request) throws ServletLogicException {
        String page = ConfigurationManager.getProperty("path.page.hotels");
        request.setSessionAttribute(JSP_PAGE, page);
        resaveParamsShowHotel(request);
        showSelectedHotel(request);
        return page;
    }
    
    public static void showSelectedHotel(SessionRequestContent request) {
        String selected = request.getParameter(JSP_SELECT_ID);
        Hotel currHotel = null;
        if (selected != null) {
            Integer idHotel = Integer.decode(selected); 
            if (idHotel != null) {
                List<Hotel> list = (List<Hotel>) request.getSessionAttribute(JSP_HOTEL_LIST);
                Iterator<Hotel> it = list.iterator();
                while (it.hasNext() && currHotel == null) {
                    Hotel hotel = it.next();
                    if (Objects.equals(hotel.getIdHotel(), idHotel)) {
                        currHotel = hotel;
                        request.setAttribute(JSP_CURR_ID_HOTEL, idHotel);
                    }
                }
            }
        }
        request.setSessionAttribute(JSP_CURRENT_HOTEL, currHotel);
    }
    
    private void resaveParamsShowHotel(SessionRequestContent request) {
        String validHotelStatus = request.getParameter(JSP_HOTEL_VALID_STATUS);
        if(validHotelStatus != null) {
            request.setAttribute(JSP_HOTEL_VALID_STATUS, validHotelStatus);
        }
        
        String invalidHotelStatus = request.getParameter(JSP_HOTEL_INVALID_STATUS);
        if(invalidHotelStatus != null) {
            request.setAttribute(JSP_HOTEL_INVALID_STATUS, invalidHotelStatus);
        }
    }
}