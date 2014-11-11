/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package by.epam.project.action.direction;

import by.epam.project.action.ActionCommand;
import static by.epam.project.action.JspParamNames.ACTIVE;
import static by.epam.project.action.JspParamNames.JSP_CURRENT_DIRECTION;
import static by.epam.project.action.JspParamNames.JSP_PAGE;
import static by.epam.project.action.JspParamNames.JSP_ROLE_TYPE;
import static by.epam.project.action.JspParamNames.JSP_USER;
import by.epam.project.action.SessionRequestContent;
import static by.epam.project.dao.entquery.DirectionQuery.DAO_ID_DIRECTION;
import static by.epam.project.dao.entquery.RoleQuery.DAO_ROLE_NAME;
import by.epam.project.dao.query.Criteria;
import by.epam.project.entity.ClientType;
import by.epam.project.entity.Direction;
import by.epam.project.entity.User;
import by.epam.project.exception.ServletLogicException;
import by.epam.project.exception.TechnicalException;
import by.epam.project.logic.DirectionLogic;
import by.epam.project.manager.ClientTypeManager;
import by.epam.project.manager.ConfigurationManager;
import by.epam.project.manager.MessageManager;

/**
 *
 * @author User
 */
public class RestoreDirection implements ActionCommand {

    @Override
    public String execute(SessionRequestContent request) throws ServletLogicException {
        String page = ConfigurationManager.getProperty("path.page.direction");
        
        Criteria criteria = new Criteria();
        Direction currDirection = (Direction) request.getSessionAttribute(JSP_CURRENT_DIRECTION);
        if (currDirection != null) {
            criteria.addParam(DAO_ID_DIRECTION, currDirection.getIdDirection());
        }
        
        User user = (User) request.getSessionAttribute(JSP_USER);
        if (user != null) {
            ClientType type = ClientTypeManager.clientTypeOf(user.getRole().getRoleName());
            criteria.addParam(DAO_ROLE_NAME, type);
        } else {
            criteria.addParam(DAO_ROLE_NAME, request.getSessionAttribute(JSP_ROLE_TYPE));
        }
        
        try {
            Integer resIdDirection = new DirectionLogic().doRestoreEntity(criteria);
            currDirection.setStatus(ACTIVE);
            return new ShowDirection().execute(request);
        } catch (TechnicalException ex) {
            request.setAttribute("errorReason", ex.getMessage());
            request.setAttribute("errorAdminMsg", ex.getCause().getMessage());
            request.setAttribute("errorSaveData", MessageManager.getProperty("message.errorsavedata"));
            request.setSessionAttribute(JSP_PAGE, page);
            return page;
        }
    }
    
}