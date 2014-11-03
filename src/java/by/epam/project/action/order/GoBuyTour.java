/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package by.epam.project.action.order;

import by.epam.project.action.ActionCommand;
import static by.epam.project.action.JspParamNames.JSP_PAGE;
import static by.epam.project.action.JspParamNames.JSP_USER;
import static by.epam.project.action.SessionGarbageCollector.cleanSession;
import by.epam.project.action.SessionRequestContent;
import static by.epam.project.dao.entquery.UserQuery.DAO_USER_LOGIN;
import by.epam.project.entity.ClientType;
import static by.epam.project.entity.ClientType.GUEST;
import by.epam.project.entity.User;
import by.epam.project.exception.ServletLogicException;
import by.epam.project.manager.ClientTypeManager;
import static by.epam.project.manager.ClientTypeManager.clientTypeOf;
import by.epam.project.manager.ConfigurationManager;

/**
 *
 * @author User
 */
public class GoBuyTour implements ActionCommand {

    @Override
    public String execute(SessionRequestContent request) throws ServletLogicException {
        String page;
        User user = (User) request.getSessionAttribute(JSP_USER);
        if (user != null && clientTypeOf(user.getRole().getRoleName()) != GUEST) {
            page = ConfigurationManager.getProperty("path.page.editorder");
        } else {
            page = ConfigurationManager.getProperty("path.page.login");
        }
        request.setSessionAttribute(JSP_PAGE, page);
        cleanSession(request);
        return page;
    }
    
}
