/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package by.epam.project.action;

import static by.epam.project.action.ActionCommand.PARAM_NAME_PAGE;
import by.epam.project.controller.SessionRequestContent;
import by.epam.project.entity.Country;
import by.epam.project.manager.ConfigurationManager;
import java.util.List;
import java.util.Objects;

/**
 *
 * @author User
 */
class ShowCountry implements ActionCommand {

    public ShowCountry() {
    }

    @Override
    public String execute(SessionRequestContent request) throws DaoLogicException {
        String page = ConfigurationManager.getProperty("path.page.countries");
        request.setSessionAttribute(PARAM_NAME_PAGE, page);
        List<Country> list = (List<Country>) request.getSessionAttribute(PARAM_NAME_COUNTRY_LIST);
        Integer id = Integer.decode(request.getParameter(PARAM_NAME_SELECT_ID));
        for (Country c: list) {
            if (Objects.equals(c.getIdCountry(), id)) {
                request.setSessionAttribute(PARAM_NAME_CURRENT_COUNTRY, c);
                return page;
            }
        }
        return page;
    }
    
}
