package by.epam.project.action.city;

import by.epam.project.action.ActionCommand;
import static by.epam.project.action.JspParamNames.*;
import by.epam.project.action.SessionRequestContent;
import by.epam.project.action.direction.DirectionCommand;
import by.epam.project.action.hotel.HotelCommand;
import by.epam.project.action.tour.TourCommand;
import by.epam.project.exception.ServletLogicException;
import by.epam.project.manager.ConfigurationManager;

/**
 * Class of command of displaying the new hotels list corresponding
 * to the selected city
 * @author Helena.Grouk
 */
public class IfCitySelected extends CityCommand implements ActionCommand {
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
            new HotelCommand().formHotelList(request);
        }
        return page;
    }

    /**
     * Resave common parameters of page if city selected.
     * @param request parameters and attributes of the request and the session
     * @throws by.epam.project.exception.ServletLogicException
     */
    private void resaveParamsCitySelected(SessionRequestContent request) throws ServletLogicException {
        String page = (String) request.getSessionAttribute(JSP_PAGE);
        String editDirectionPage = ConfigurationManager.getProperty("path.page.editdirection");
        String searchingPage = ConfigurationManager.getProperty("path.page.tours");
        if (page != null) {
            if (page.equals(editDirectionPage)) {
                new DirectionCommand().resaveParamsSaveDirection(request);
            } else if (page.equals(searchingPage)) {
                new TourCommand().resaveParamsSearchTour(request);
            }
        }
    }    
}
