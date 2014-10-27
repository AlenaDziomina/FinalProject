/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package by.epam.project.action.direction;

import by.epam.project.action.ActionCommand;
import static by.epam.project.action.JspParamNames.JSP_CURR_PAGE_NO;
import static by.epam.project.action.JspParamNames.JSP_PAGE;
import static by.epam.project.action.JspParamNames.JSP_PAGE_LIST;
import by.epam.project.action.SessionRequestContent;
import static by.epam.project.action.direction.GoShowDirections.resaveParamsShowDirections;
import by.epam.project.entity.Direction;
import by.epam.project.exception.ServletLogicException;
import by.epam.project.tag.ObjList;

/**
 *
 * @author User
 */
public class ShowPage implements ActionCommand {

    @Override
    public String execute(SessionRequestContent request) throws ServletLogicException {
        String page = (String) request.getSessionAttribute(JSP_PAGE);
        ObjList<Direction> list = (ObjList<Direction>) request.getSessionAttribute(JSP_PAGE_LIST);
        Integer currPageNo = Integer.decode(request.getParameter(JSP_CURR_PAGE_NO));
        list.setCurrPageNo(currPageNo);
        resaveParamsShowDirections(request);
        return page;
    }
    
}
