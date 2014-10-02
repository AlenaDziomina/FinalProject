/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package by.epam.project.action;

import static by.epam.project.action.ActionCommand.*;
import static by.epam.project.controller.JspParamNames.JSP_PAGE;
import by.epam.project.controller.SessionRequestContent;
import by.epam.project.entity.Hotel;
import by.epam.project.manager.ConfigurationManager;
import java.util.List;
import java.util.Objects;

/**
 *
 * @author User
 */
public class ShowHotel implements ActionCommand {
    
    public ShowHotel(){}

    @Override
    public String execute(SessionRequestContent request) throws DaoLogicException {
        
        String page = ConfigurationManager.getProperty("path.page.hotels");
        request.setSessionAttribute(JSP_PAGE, page);
        List<Hotel> list = (List<Hotel>) request.getSessionAttribute(PARAM_NAME_HOTEL_LIST);
        if (list == null || list.isEmpty()){
            new GoShowHotel().execute(request);
            list = (List<Hotel>) request.getSessionAttribute(PARAM_NAME_HOTEL_LIST);
        }
        Integer id = Integer.decode(request.getParameter(PARAM_NAME_SELECT_ID));
        for (Hotel c: list) {
            if (Objects.equals(c.getIdHotel(), id)) {
                request.setSessionAttribute(PARAM_NAME_CURRENT_HOTEL, c);
                return page;
            }
        }
        return page;
    
    }
    
}