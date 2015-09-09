package by.epam.project.action.tour;

import by.epam.project.action.ActionCommand;
import static by.epam.project.action.JspParamNames.*;
import by.epam.project.action.SessionRequestContent;
import by.epam.project.entity.Tour;
import by.epam.project.exception.ServletLogicException;
import by.epam.project.manager.ConfigurationManager;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Class of command of displaying the page of tour object editing.
 * @author Helena.Grouk
 */
public class GoEditTour extends TourCommand implements ActionCommand {
    @Override
    public String execute(SessionRequestContent request) throws ServletLogicException {
        String page = ConfigurationManager.getProperty("path.page.edittour");
        request.setSessionAttribute(JSP_PAGE, page);
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
}