/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package by.epam.project.action;

import static by.epam.project.action.JspParamNames.JSP_CURRENT_HOTEL;
import static by.epam.project.action.JspParamNames.JSP_HOTEL_LIST;
import static by.epam.project.action.JspParamNames.JSP_PAGE;
import static by.epam.project.action.JspParamNames.JSP_SELECT_ID;
import by.epam.project.controller.SessionRequestContent;
import by.epam.project.entity.Hotel;
import by.epam.project.exception.DaoUserLogicException;
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
    public String execute(SessionRequestContent request) throws DaoUserLogicException {
        
        String page = ConfigurationManager.getProperty("path.page.hotels");
        request.setSessionAttribute(JSP_PAGE, page);
        List<Hotel> list = (List<Hotel>) request.getSessionAttribute(JSP_HOTEL_LIST);
        if (list == null || list.isEmpty()){
            new GoShowHotel().execute(request);
            list = (List<Hotel>) request.getSessionAttribute(JSP_HOTEL_LIST);
        }
        Integer id = Integer.decode(request.getParameter(JSP_SELECT_ID));
        for (Hotel c: list) {
            if (Objects.equals(c.getIdHotel(), id)) {
                request.setSessionAttribute(JSP_CURRENT_HOTEL, c);
                return page;
            }
        }
        return page;
    
    }
    
}