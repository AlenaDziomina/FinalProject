package by.epam.project.action.hotel;

import by.epam.project.action.ActionCommand;
import static by.epam.project.action.JspParamNames.*;
import by.epam.project.action.SessionRequestContent;
import by.epam.project.action.city.CityCommand;
import by.epam.project.action.country.CountryCommand;
import by.epam.project.entity.City;
import by.epam.project.entity.Hotel;
import by.epam.project.exception.ServletLogicException;
import by.epam.project.manager.ConfigurationManager;
import java.util.List;

/**
 * Class of command of displaying the page of hotel object editing
 * @author Helena.Grouk
 */
public class GoEditHotel extends HotelCommand implements ActionCommand {
    @Override
    public String execute(SessionRequestContent request) throws ServletLogicException {
        String page = ConfigurationManager.getProperty("path.page.edithotel");
        request.setSessionAttribute(JSP_PAGE, page);
        new CountryCommand().formCountryList(request);
        new CityCommand().formCityList(request);
        Hotel currHotel = (Hotel) request.getSessionAttribute(JSP_CURRENT_HOTEL);
        List<City> cityList = (List<City>) request.getSessionAttribute(JSP_CITY_LIST);
        
        if (currHotel != null) {
            Integer idCity = currHotel.getCity().getIdCity();
            request.setAttribute(JSP_CURR_ID_CITY, idCity);
            for (City city: cityList) {
                if (city.getIdCity().equals(idCity)) {
                    Integer idCountry = city.getCountry().getIdCountry();
                    request.setAttribute(JSP_CURR_ID_COUNTRY, idCountry);
                    request.setAttribute(JSP_ID_COUNTRY, idCountry);
                    new CityCommand().formCityList(request);
                    request.setSessionAttribute(JSP_CURR_CITY_LIST, request.getSessionAttribute(JSP_CITY_LIST));
                }
            }
        }
        return page;
    }
}