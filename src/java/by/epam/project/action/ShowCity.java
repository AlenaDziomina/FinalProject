/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package by.epam.project.action;

import static by.epam.project.action.JspParamNames.JSP_CITY_LIST;
import static by.epam.project.action.JspParamNames.JSP_CURRENT_CITY;
import static by.epam.project.action.JspParamNames.JSP_PAGE;
import static by.epam.project.action.JspParamNames.JSP_SELECT_ID;
import by.epam.project.controller.SessionRequestContent;
import by.epam.project.entity.City;
import by.epam.project.exception.DaoUserLogicException;
import by.epam.project.manager.ConfigurationManager;
import java.util.List;
import java.util.Objects;

/**
 *
 * @author User
 */
public class ShowCity implements ActionCommand {
    
    public ShowCity(){}

    @Override
    public String execute(SessionRequestContent request) throws DaoUserLogicException {
        
        String page = ConfigurationManager.getProperty("path.page.cities");
        request.setSessionAttribute(JSP_PAGE, page);
        List<City> list = (List<City>) request.getSessionAttribute(JSP_CITY_LIST);
        if (list == null || list.isEmpty()){
            new GoShowCity().execute(request);
            list = (List<City>) request.getSessionAttribute(JSP_CITY_LIST);
        }
        Integer id = Integer.decode(request.getParameter(JSP_SELECT_ID));
        for (City c: list) {
            if (Objects.equals(c.getIdCity(), id)) {
                request.setSessionAttribute(JSP_CURRENT_CITY, c);
                return page;
            }
        }
        return page;
    
    }
    
}
