/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package by.epam.project.action.tour;

import by.epam.project.action.ActionCommand;
import static by.epam.project.action.JspParamNames.JSP_CURRENT_TOUR;
import static by.epam.project.action.JspParamNames.JSP_CURR_ARRIVAL_DATE;
import static by.epam.project.action.JspParamNames.JSP_CURR_DEPART_DATE;
import static by.epam.project.action.JspParamNames.JSP_CURR_ID_DIRECTION;
import static by.epam.project.action.JspParamNames.JSP_CURR_ID_TOUR;
import static by.epam.project.action.JspParamNames.JSP_PAGE;
import static by.epam.project.action.JspParamNames.JSP_SELECT_ID;
import static by.epam.project.action.JspParamNames.JSP_TOUR_LIST;
import static by.epam.project.action.tour.GoShowTour.formTourList;
import by.epam.project.action.SessionRequestContent;
import by.epam.project.entity.Tour;
import by.epam.project.exception.DaoUserLogicException;
import by.epam.project.manager.ConfigurationManager;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Objects;

/**
 *
 * @author User
 */
public class ShowTour implements ActionCommand {

    @Override
    public String execute(SessionRequestContent request) throws DaoUserLogicException {
        String page = ConfigurationManager.getProperty("path.page.tour");
        request.setSessionAttribute(JSP_PAGE, page);
        
        formTourList(request);
        List<Tour> list = (List<Tour>) request.getSessionAttribute(JSP_TOUR_LIST);
        Integer idTour = Integer.decode(request.getParameter(JSP_SELECT_ID));
        request.setAttribute(JSP_CURR_ID_TOUR, idTour);
        
        for (Tour tour: list) {
            if (Objects.equals(tour.getIdTour(), idTour)) {
                request.setSessionAttribute(JSP_CURRENT_TOUR, tour);
                request.setAttribute(JSP_CURR_ID_DIRECTION, tour.getDirection().getIdDirection());
                
                SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
                Date d1 = tour.getDepartDate();
                String ds1 = formatter.format(d1);  
                request.setAttribute(JSP_CURR_DEPART_DATE, ds1);

                Integer dn = tour.getDaysCount();
                Calendar c = Calendar.getInstance(); 
                c.setTime(d1); 
                c.add(Calendar.DATE, dn);
                Date d2 = c.getTime();
                String ds2 = formatter.format(d2);
                request.setAttribute(JSP_CURR_ARRIVAL_DATE, ds2);
                
                return page;
            }
        }
        return page;
    }
    
}
