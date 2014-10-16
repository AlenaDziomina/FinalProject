/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package by.epam.project.action.direction;

import by.epam.project.action.ActionCommand;
import static by.epam.project.action.JspParamNames.JSP_CURRENT_DIRECTION;
import static by.epam.project.action.JspParamNames.JSP_DIRECTION_LIST;
import static by.epam.project.action.JspParamNames.JSP_ID_DIRECTION;
import static by.epam.project.action.JspParamNames.JSP_PAGE;
import static by.epam.project.action.JspParamNames.JSP_SELECT_ID;
import static by.epam.project.action.direction.GoShowDirections.formDirectionList;
import by.epam.project.action.SessionRequestContent;
import by.epam.project.entity.Direction;
import by.epam.project.exception.DaoUserLogicException;
import by.epam.project.manager.ConfigurationManager;
import java.util.List;

/**
 *
 * @author User
 */
public class ShowDirection implements ActionCommand {

    @Override
    public String execute(SessionRequestContent request) throws DaoUserLogicException {
        String page = ConfigurationManager.getProperty("path.page.direction");
        request.setSessionAttribute(JSP_PAGE, page);
        
        Integer idDirection = Integer.decode(request.getParameter(JSP_SELECT_ID));
        request.setAttribute(JSP_ID_DIRECTION, idDirection);
        formDirectionList(request);
        List<Direction> list = (List<Direction>) request.getSessionAttribute(JSP_DIRECTION_LIST);
        
        request.setSessionAttribute(JSP_CURRENT_DIRECTION, list.get(0));
        return page;
    }
    
}
