package by.epam.project.action.direction;

import by.epam.project.action.ActionCommand;
import static by.epam.project.action.JspParamNames.*;
import by.epam.project.action.SessionRequestContent;
import by.epam.project.action.city.CityCommand;
import by.epam.project.action.country.CountryCommand;
import by.epam.project.action.hotel.HotelCommand;
import by.epam.project.exception.ServletLogicException;
import by.epam.project.manager.ConfigurationManager;

/**
 * Class of command of displaying the page of direction object creation.
 * @author Helena.Grouk
 */
public class GoCreateNewDirection extends DirectionCommand implements ActionCommand {
    @Override
    public String execute(SessionRequestContent request) throws ServletLogicException {
        String page = ConfigurationManager.getProperty("path.page.editdirection");
        request.setSessionAttribute(JSP_PAGE, page);
        request.deleteSessionAttribute(JSP_CURRENT_DIRECTION);
        
        new CountryCommand().formCountryList(request);
        new CityCommand().formCityList(request);
        new HotelCommand().formHotelList(request);
        formTourTypeList(request);
        formTransModeList(request);
        request.setSessionAttribute(JSP_COUNTRY_TAG_LIST, request.getSessionAttribute(JSP_COUNTRY_LIST));
        request.setSessionAttribute(JSP_CITY_TAG_LIST, request.getSessionAttribute(JSP_CITY_LIST));
        return page;
    }
}
