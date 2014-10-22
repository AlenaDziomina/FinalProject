/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package by.epam.project.action.hotel;

import by.epam.project.action.ActionCommand;
import static by.epam.project.action.JspParamNames.JSP_CURRENT_HOTEL;
import static by.epam.project.action.JspParamNames.JSP_CURR_ID_HOTEL;
import static by.epam.project.action.JspParamNames.JSP_HOTEL_LIST;
import static by.epam.project.action.JspParamNames.JSP_PAGE;
import static by.epam.project.action.JspParamNames.JSP_SELECT_ID;
import static by.epam.project.action.hotel.GoShowHotel.formHotelList;
import by.epam.project.action.SessionRequestContent;
import by.epam.project.entity.Hotel;
import by.epam.project.exception.ServletLogicException;
import by.epam.project.manager.ConfigurationManager;
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
        
        formHotelList(request);
        List<Hotel> list = (List<Hotel>) request.getSessionAttribute(JSP_HOTEL_LIST);
        Integer idHotel = Integer.decode(request.getParameter(JSP_SELECT_ID));
        request.setAttribute(JSP_CURR_ID_HOTEL, idHotel);
        for (Hotel hotel: list) {
            if (Objects.equals(hotel.getIdHotel(), idHotel)) {
                request.setSessionAttribute(JSP_CURRENT_HOTEL, hotel);
                return page;
            }
        }
        return page;
    
    }
    
}