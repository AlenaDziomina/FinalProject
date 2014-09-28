/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package by.epam.project.action;

import static by.epam.project.action.ActionCommand.*;
import by.epam.project.controller.SessionRequestContent;
import by.epam.project.entity.City;
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
    public String execute(SessionRequestContent request) throws DaoLogicException {
        
        String page = ConfigurationManager.getProperty("path.page.cities");
        request.setSessionAttribute(PARAM_NAME_PAGE, page);
        List<City> list = (List<City>) request.getSessionAttribute(PARAM_NAME_CITY_LIST);
        if (list == null || list.isEmpty()){
            new GoShowCity().execute(request);
            list = (List<City>) request.getSessionAttribute(PARAM_NAME_CITY_LIST);
        }
        Integer id = Integer.decode(request.getParameter(PARAM_NAME_SELECT_ID));
        for (City c: list) {
            if (Objects.equals(c.getIdCity(), id)) {
                request.setSessionAttribute(PARAM_NAME_CURRENT_CITY, c);
                return page;
            }
        }
        return page;
    
    }
    
}
