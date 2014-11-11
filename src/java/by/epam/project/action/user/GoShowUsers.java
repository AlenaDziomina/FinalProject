/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package by.epam.project.action.user;

import by.epam.project.action.ActionCommand;
import static by.epam.project.action.JspParamNames.JSP_PAGE;
import static by.epam.project.action.JspParamNames.JSP_ROLE_TYPE;
import static by.epam.project.action.JspParamNames.JSP_USER;
import static by.epam.project.action.JspParamNames.JSP_USER_LIST;
import by.epam.project.action.SessionRequestContent;
import static by.epam.project.dao.entquery.RoleQuery.DAO_ROLE_NAME;
import by.epam.project.dao.query.Criteria;
import by.epam.project.entity.ClientType;
import by.epam.project.entity.User;
import by.epam.project.exception.ServletLogicException;
import by.epam.project.exception.TechnicalException;
import by.epam.project.logic.UserLogic;
import by.epam.project.manager.ClientTypeManager;
import by.epam.project.manager.ConfigurationManager;
import java.util.List;

/**
 *
 * @author User
 */
public class GoShowUsers implements ActionCommand {
    

    @Override
    public String execute(SessionRequestContent request) throws ServletLogicException {
        String page = ConfigurationManager.getProperty("path.page.users");
        String prevPage = (String) request.getSessionAttribute(JSP_PAGE);
        formUserList(request);
        if(page == null ? prevPage == null : !page.equals(prevPage)){
            request.setSessionAttribute(JSP_PAGE, page);
            cleanSessionShowUsers(request);
        }
        return page;
    }

    private void formUserList(SessionRequestContent request) throws ServletLogicException {
        Criteria criteria = new Criteria();
        User user = (User) request.getSessionAttribute(JSP_USER);
        if (user != null) {
            ClientType type = ClientTypeManager.clientTypeOf(user.getRole().getRoleName());
            criteria.addParam(DAO_ROLE_NAME, type);
        } else {
            criteria.addParam(DAO_ROLE_NAME, request.getSessionAttribute(JSP_ROLE_TYPE));
        }
        
        try {
            List<User> users = new UserLogic().doGetEntity(criteria);
            request.setSessionAttribute(JSP_USER_LIST, users);
        } catch (TechnicalException ex) {
            throw new ServletLogicException(ex.getMessage(), ex);
        }
    }

    private void cleanSessionShowUsers(SessionRequestContent request) {
        //??????????????????????????????????
    }
}
