package by.epam.project.action.country;

import by.epam.project.action.ActionCommand;
import static by.epam.project.action.JspParamNames.*;
import by.epam.project.action.SessionRequestContent;
import by.epam.project.action.city.CityCommand;
import by.epam.project.action.direction.DirectionCommand;
import by.epam.project.action.hotel.HotelCommand;
import by.epam.project.action.tour.TourCommand;
import by.epam.project.entity.City;
import by.epam.project.entity.Hotel;
import by.epam.project.exception.ServletLogicException;
import by.epam.project.manager.ConfigurationManager;
import java.util.ArrayList;
import java.util.List;

/**
 * Class of command of displaying the new city list and new hotel list
 * corresponding to the selected country
 * @author Helena.Grouk
 */
public class IfCountrySelected extends CountryCommand implements ActionCommand {

    @Override
    public String execute(SessionRequestContent request) throws ServletLogicException {
        String page = (String) request.getSessionAttribute(JSP_PAGE);
        resaveParamsCountrySelected(request);
        request.setAttribute(JSP_CURR_ID_CITY, "0");

        String currCountry = request.getParameter(JSP_CURR_ID_COUNTRY);
        if (currCountry != null && !currCountry.isEmpty()){
            Integer idCountry = Integer.decode(currCountry);
            if (idCountry != 0) {
                request.setAttribute(JSP_ID_COUNTRY, idCountry);
            }
            new CityCommand().formCityList(request);
            List<City> cityList = (List<City>) request.getSessionAttribute(JSP_CITY_LIST);
            request.setSessionAttribute(JSP_CURR_CITY_LIST, cityList);

            List<Hotel> commonHotelList = new ArrayList();
            for (City c : cityList) {
                request.setAttribute(JSP_ID_CITY, c.getIdCity());
                new HotelCommand().formHotelList(request);
                List<Hotel> hotelList = (List<Hotel>) request.getSessionAttribute(JSP_HOTEL_LIST);
                commonHotelList.addAll(hotelList);
            }
            request.setSessionAttribute(JSP_HOTEL_LIST, commonHotelList);
        }
        request.setSessionAttribute(JSP_PAGE, page);
        return page;
    }

    /**
     * Resave common parameters of page if country selected.
     * @param request parameters and attributes of the request and the session
     * @throws by.epam.project.exception.ServletLogicException
     */
    private void resaveParamsCountrySelected(SessionRequestContent request) throws ServletLogicException {
        String page = (String) request.getSessionAttribute(JSP_PAGE);
        String editHotelPage = ConfigurationManager.getProperty("path.page.edithotel");
        String editDirectionPage = ConfigurationManager.getProperty("path.page.editdirection");
        String searchingPage = ConfigurationManager.getProperty("path.page.tours");
        if (page != null) {
            if (page.equals(editHotelPage)) {
                new HotelCommand().resaveParamsSaveHotel(request);
            } else if (page.equals(editDirectionPage)) {
                new DirectionCommand().resaveParamsSaveDirection(request);
            } else if (page.equals(searchingPage)) {
                new TourCommand().resaveParamsSearchTour(request);
            }
        }
    }
}
