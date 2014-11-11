/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package by.epam.project.action.user;

import by.epam.project.action.ActionCommand;
import static by.epam.project.action.JspParamNames.JSP_CURRENT_USER;
import static by.epam.project.action.JspParamNames.JSP_PAGE;
import static by.epam.project.action.JspParamNames.JSP_ROLE_TYPE;
import static by.epam.project.action.JspParamNames.JSP_SELECT_ID;
import static by.epam.project.action.JspParamNames.JSP_USER;
import by.epam.project.action.SessionRequestContent;
import static by.epam.project.dao.entquery.RoleQuery.DAO_ROLE_NAME;
import static by.epam.project.dao.entquery.UserQuery.DAO_ID_USER;
import static by.epam.project.dao.entquery.UserQuery.DAO_USER_LOGIN;
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
public class ShowUser implements ActionCommand {
    

    @Override
    public String execute(SessionRequestContent request) throws ServletLogicException {
        String page = ConfigurationManager.getProperty("path.page.user");
        String prevPage = (String) request.getSessionAttribute(JSP_PAGE);
        findUser(request);
        if(page == null ? prevPage == null : !page.equals(prevPage)){
            request.setSessionAttribute(JSP_PAGE, page);
            cleanSessionShowUser(request);
        }
        return page;
    }

    private void findUser(SessionRequestContent request) throws ServletLogicException {
        Criteria criteria = new Criteria();
        criteria.addParam(DAO_ID_USER, request.getParameter(JSP_SELECT_ID));

        User user = (User) request.getSessionAttribute(JSP_USER);
        if (user != null) {
            ClientType type = ClientTypeManager.clientTypeOf(user.getRole().getRoleName());
            criteria.addParam(DAO_ROLE_NAME, type);
        } else {
            criteria.addParam(DAO_ROLE_NAME, request.getSessionAttribute(JSP_ROLE_TYPE));
        }
        try { 
            List<User> users = new UserLogic().doGetEntity(criteria);
            if (users != null && !users.isEmpty()) {
                request.setSessionAttribute(JSP_CURRENT_USER, users.get(0));
            }
        } catch (TechnicalException ex) {
            throw new ServletLogicException(ex.getMessage(), ex);
        }
    }

    private void cleanSessionShowUser(SessionRequestContent request) {
        //?????????????????????????????????
    }
}