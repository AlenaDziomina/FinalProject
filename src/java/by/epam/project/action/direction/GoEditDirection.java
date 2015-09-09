package by.epam.project.action.direction;

import by.epam.project.action.ActionCommand;
import static by.epam.project.action.JspParamNames.*;
import by.epam.project.action.SessionRequestContent;
import by.epam.project.action.city.CityCommand;
import by.epam.project.action.country.CountryCommand;
import by.epam.project.action.hotel.HotelCommand;
import by.epam.project.entity.Direction;
import by.epam.project.entity.Hotel;
import by.epam.project.exception.ServletLogicException;
import by.epam.project.manager.ConfigurationManager;
import java.util.ArrayList;
import java.util.List;

/**
 * Class of command of displaying the page of direction object editing
 * @author Helena.Grouk
 */
public class GoEditDirection extends DirectionCommand implements ActionCommand {
    @Override
    public String execute(SessionRequestContent request) throws ServletLogicException {
        String page = ConfigurationManager.getProperty("path.page.editdirection");
        request.setSessionAttribute(JSP_PAGE, page);

        new CountryCommand().formCountryList(request);
        new CityCommand().formCityList(request);
        new HotelCommand().formHotelList(request);
        formTourTypeList(request);
        formTransModeList(request);

        request.setSessionAttribute(JSP_COUNTRY_TAG_LIST, request.getSessionAttribute(JSP_COUNTRY_LIST));
        request.setSessionAttribute(JSP_CITY_TAG_LIST, request.getSessionAttribute(JSP_CITY_LIST));

        Direction dir = (Direction) request.getSessionAttribute(JSP_CURRENT_DIRECTION);
        request.setAttribute(JSP_CURR_ID_COUNTRY, 0);
        request.setAttribute(JSP_CURR_ID_CITY, 0);
        List<Integer> countryTags = new ArrayList<>();
        dir.getCountryCollection().stream().forEach((c) -> {
            countryTags.add(c.getIdCountry());
        });
        request.setAttribute(JSP_CURR_COUNTRY_TAGS, countryTags);
        List<Integer> cityTags = new ArrayList<>();
        dir.getCityCollection().stream().forEach((c) -> {
            cityTags.add(c.getIdCity());
        });
        request.setAttribute(JSP_CURR_CITY_TAGS, cityTags);
        List<Hotel> stays = new ArrayList<>();
        dir.getStayCollection().stream().forEach((s) -> {
            stays.add(s.getHotel());
        });
        request.setAttribute(JSP_HOTEL_TAG_LIST, stays);

        return page;
    }
}
