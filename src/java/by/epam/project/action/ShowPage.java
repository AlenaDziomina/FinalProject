/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package by.epam.project.action;

import static by.epam.project.action.JspParamNames.JSP_PAGE;
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
        ObjList<Direction> list = (ObjList<Direction>) request.getSessionAttribute("rw");
        Integer currPageNo = Integer.decode(request.getParameter("currPageNo"));
        list.setCurrPageNo(currPageNo);
        return page;
    }
    
}
