package by.epam.project.action.city;

import by.epam.project.action.ActionCommand;
import static by.epam.project.action.JspParamNames.*;
import by.epam.project.action.SessionRequestContent;
import by.epam.project.action.country.CountryCommand;
import by.epam.project.entity.City;
import by.epam.project.exception.ServletLogicException;
import by.epam.project.manager.ConfigurationManager;

/**
 * Class of command of displaying the page of city object editing
 * @author Helena.Grouk
 */
public class GoEditCity extends CityCommand implements ActionCommand {
    @Override
    public String execute(SessionRequestContent request) throws ServletLogicException {
        new CountryCommand().formCountryList(request);
        City currCity = (City) request.getSessionAttribute(JSP_CURRENT_CITY);
        if (currCity != null) {
            request.setAttribute(JSP_CURR_ID_COUNTRY, currCity.getCountry().getIdCountry());
        }
        String page = ConfigurationManager.getProperty("path.page.editcity");
        request.setSessionAttribute(JSP_PAGE, page);
        return page;
    }
}
