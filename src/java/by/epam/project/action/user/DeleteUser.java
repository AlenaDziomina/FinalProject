/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package by.epam.project.action.user;

import by.epam.project.action.ActionCommand;
import static by.epam.project.action.JspParamNames.*;
import by.epam.project.action.SessionRequestContent;
import static by.epam.project.dao.entquery.RoleQuery.DAO_ROLE_NAME;
import static by.epam.project.dao.entquery.UserQuery.DAO_ID_USER;
import by.epam.project.dao.query.Criteria;
import by.epam.project.entity.ClientType;
import by.epam.project.entity.User;
import by.epam.project.exception.ServletLogicException;
import by.epam.project.exception.TechnicalException;
import by.epam.project.logic.UserLogic;
import by.epam.project.manager.ClientTypeManager;
import by.epam.project.manager.ConfigurationManager;
import by.epam.project.manager.MessageManager;

/**
 *
 * @author User
 */
public class DeleteUser implements ActionCommand {

    @Override
    public String execute(SessionRequestContent request) throws ServletLogicException {
        String page = ConfigurationManager.getProperty("path.page.user");
        
        Criteria criteria = new Criteria();
        User currUser = (User) request.getSessionAttribute(JSP_CURRENT_USER);
        if (currUser != null) {
            criteria.addParam(DAO_ID_USER, currUser.getIdUser());
        }
        
        User user = (User) request.getSessionAttribute(JSP_USER);
        if (user != null) {
            ClientType type = ClientTypeManager.clientTypeOf(user.getRole().getRoleName());
            criteria.addParam(DAO_ROLE_NAME, type);
        } else {
            criteria.addParam(DAO_ROLE_NAME, request.getSessionAttribute(JSP_ROLE_TYPE));
        }
        
        try {
            Integer resIdUser = new UserLogic().doDeleteEntity(criteria);
            request.setParameter(JSP_SELECT_ID, resIdUser.toString());
            currUser.setStatus(DELETED);
            return new ShowUser().execute(request);
        } catch (TechnicalException ex) {
            request.setAttribute("errorDeleteReason", ex.getMessage());
            request.setAttribute("errorDelete", "message.errorDeleteData");
            request.setSessionAttribute(JSP_PAGE, page);
            return page;
        }
    }
    
}
