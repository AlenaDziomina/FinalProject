/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package by.epam.project.action.tour;

import by.epam.project.action.ActionCommand;
import static by.epam.project.action.JspParamNames.JSP_CURRENT_DIRECTION;
import static by.epam.project.action.JspParamNames.JSP_CURRENT_TOUR;
import static by.epam.project.action.JspParamNames.JSP_CURR_ARRIVAL_DATE;
import static by.epam.project.action.JspParamNames.JSP_CURR_DEPART_DATE;
import static by.epam.project.action.JspParamNames.JSP_CURR_ID_DIRECTION;
import static by.epam.project.action.JspParamNames.JSP_CURR_ID_TOUR;
import static by.epam.project.action.JspParamNames.JSP_ID_DIRECTION;
import static by.epam.project.action.JspParamNames.JSP_PAGE;
import static by.epam.project.action.JspParamNames.JSP_TOUR_INVALID_DATE;
import static by.epam.project.action.JspParamNames.JSP_TOUR_INVALID_STATUS;
import static by.epam.project.action.JspParamNames.JSP_TOUR_VALID_DATE;
import static by.epam.project.action.JspParamNames.JSP_TOUR_VALID_STATUS;
import by.epam.project.action.SessionRequestContent;
import static by.epam.project.action.tour.ShowTour.resaveParamsShowTour;
import by.epam.project.entity.Direction;
import by.epam.project.entity.Tour;
import by.epam.project.exception.ServletLogicException;
import by.epam.project.manager.ConfigurationManager;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 *
 * @author User
 */
public class GoEditTour implements ActionCommand {

    @Override
    public String execute(SessionRequestContent request) throws ServletLogicException {
        String page = ConfigurationManager.getProperty("path.page.edittour");
        request.setSessionAttribute(JSP_PAGE, page);
        resaveParamsEditTour(request);
        Tour currTour = (Tour) request.getSessionAttribute(JSP_CURRENT_TOUR);
        
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        Date d1 = currTour.getDepartDate();
        String ds1 = formatter.format(d1);  
        request.setAttribute(JSP_CURR_DEPART_DATE, ds1);
        
        Integer dn = currTour.getDaysCount();
        Calendar c = Calendar.getInstance(); 
        c.setTime(d1); 
        c.add(Calendar.DATE, dn);
        Date d2 = c.getTime();
        String ds2 = formatter.format(d2);
        request.setAttribute(JSP_CURR_ARRIVAL_DATE, ds2);
        
        return page;
    }

    private void resaveParamsEditTour(SessionRequestContent request) {
        
        String validTourStatus = request.getParameter(JSP_TOUR_VALID_STATUS);
        if(validTourStatus != null) {
            request.setSessionAttribute(JSP_TOUR_VALID_STATUS, validTourStatus);
        }
        
        String invalidTourStatus = request.getParameter(JSP_TOUR_INVALID_STATUS);
        if(invalidTourStatus != null) {
            request.setSessionAttribute(JSP_TOUR_INVALID_STATUS, invalidTourStatus);
        }
        
        String validTourDate = request.getParameter(JSP_TOUR_VALID_DATE);
        if(validTourDate != null) {
            request.setSessionAttribute(JSP_TOUR_VALID_DATE, validTourDate);
        }
        
        
    }
    
}