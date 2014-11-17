package by.epam.project.action.city;

import by.epam.project.action.ActionCommand;
import static by.epam.project.action.JspParamNames.*;
import by.epam.project.action.SessionRequestContent;
import by.epam.project.action.country.CountryCommand;
import by.epam.project.exception.ServletLogicException;
import by.epam.project.manager.ConfigurationManager;

/**
 * Class of command of displaying the page of city object creation
 * @author Helena.Grouk
 */
public class GoCreateNewCity extends CityCommand implements ActionCommand {
    @Override
    public String execute(SessionRequestContent request) throws ServletLogicException {
        String page = ConfigurationManager.getProperty("path.page.editcity");
        request.setSessionAttribute(JSP_PAGE, page);
        request.deleteSessionAttribute(JSP_CURRENT_CITY);
        new CountryCommand().formCountryList(request);
        return page;
    }
}
