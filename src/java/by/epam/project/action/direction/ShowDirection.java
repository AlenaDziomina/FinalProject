/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package by.epam.project.action.direction;

import by.epam.project.action.ActionCommand;
import static by.epam.project.action.JspParamNames.JSP_CURRENT_DIRECTION;
import static by.epam.project.action.JspParamNames.JSP_DIRECTION_INVALID_STATUS;
import static by.epam.project.action.JspParamNames.JSP_DIRECTION_LIST;
import static by.epam.project.action.JspParamNames.JSP_DIRECTION_VALID_STATUS;
import static by.epam.project.action.JspParamNames.JSP_ID_DIRECTION;
import static by.epam.project.action.JspParamNames.JSP_PAGE;
import static by.epam.project.action.JspParamNames.JSP_SELECT_ID;
import by.epam.project.action.SessionRequestContent;
import static by.epam.project.action.direction.GoShowDirections.formDirectionList;
import static by.epam.project.action.direction.GoShowDirections.resaveParamsShowDirections;
import by.epam.project.entity.Direction;
import by.epam.project.exception.ServletLogicException;
import by.epam.project.manager.ConfigurationManager;
import java.util.List;

/**
 *
 * @author User
 */
public class ShowDirection implements ActionCommand {

    @Override
    public String execute(SessionRequestContent request) throws ServletLogicException {
        String page = ConfigurationManager.getProperty("path.page.direction");
        request.setSessionAttribute(JSP_PAGE, page);
        resaveParamsShowDirections(request);
        showSelectedDirection(request);
        
        return page;
    }

    private static void showSelectedDirection(SessionRequestContent request) throws ServletLogicException {
        Integer idDirection = Integer.decode(request.getParameter(JSP_SELECT_ID));
        request.setAttribute(JSP_ID_DIRECTION, idDirection);
        formDirectionList(request);
        List<Direction> list = (List<Direction>) request.getSessionAttribute(JSP_DIRECTION_LIST);
        if (!list.isEmpty()){
            request.setSessionAttribute(JSP_CURRENT_DIRECTION, list.get(0));
        }
    }
    
}
