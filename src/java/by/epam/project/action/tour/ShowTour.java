/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package by.epam.project.action.tour;

import by.epam.project.action.ActionCommand;
import static by.epam.project.action.JspParamNames.JSP_CURR_ID_TOUR;
import static by.epam.project.action.JspParamNames.JSP_PAGE;
import static by.epam.project.action.JspParamNames.JSP_SELECT_ID;
import static by.epam.project.action.JspParamNames.JSP_TOUR_LIST;
import static by.epam.project.action.tour.GoShowTour.formTourList;
import by.epam.project.controller.SessionRequestContent;
import by.epam.project.entity.Tour;
import by.epam.project.exception.DaoUserLogicException;
import by.epam.project.manager.ConfigurationManager;
import java.util.List;

/**
 *
 * @author User
 */
public class ShowTour implements ActionCommand {

    @Override
    public String execute(SessionRequestContent request) throws DaoUserLogicException {
        String page = ConfigurationManager.getProperty("path.page.tours");
        request.setSessionAttribute(JSP_PAGE, page);
        
        formTourList(request);
        List<Tour> list = (List<Tour>) request.getSessionAttribute(JSP_TOUR_LIST);
        Integer idTour = Integer.decode(request.getParameter(JSP_SELECT_ID));
        request.setAttribute(JSP_CURR_ID_TOUR, idTour);
//        for (Hotel hotel: list) {
//            if (Objects.equals(hotel.getIdHotel(), idHotel)) {
//                request.setSessionAttribute(JSP_CURRENT_HOTEL, hotel);
//                return page;
//            }
//        }
        return page;
    }
    
}
