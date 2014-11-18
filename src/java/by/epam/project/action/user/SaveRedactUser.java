package by.epam.project.action.user;

import by.epam.project.action.ActionCommand;
import static by.epam.project.action.JspParamNames.*;
import static by.epam.project.dao.DaoParamNames.*;
import by.epam.project.action.SessionRequestContent;
import by.epam.project.dao.query.Criteria;
import by.epam.project.dao.ClientType;
import by.epam.project.entity.User;
import by.epam.project.exception.LogicException;
import by.epam.project.exception.ServletLogicException;
import by.epam.project.exception.TechnicalException;
import by.epam.project.logic.UserLogic;
import by.epam.project.manager.ClientTypeManager;
import by.epam.project.manager.ConfigurationManager;
import by.epam.project.manager.Validator;

/**
 * Class of command to save redacted user object.
 * @author Helena.Grouk
 */
public class SaveRedactUser implements ActionCommand {
    @Override
    public String execute(SessionRequestContent request) throws ServletLogicException{
        String page = ConfigurationManager.getProperty("path.page.edituser");
        resaveParamsSaveUser(request);
        try {
            Criteria criteria = new Criteria();
            User currUser = (User) request.getSessionAttribute(JSP_CURRENT_USER);
            Validator.validateUser(currUser);
            criteria.addParam(DAO_ID_USER, currUser.getIdUser());
            criteria.addParam(DAO_USER_EMAIL, currUser.getEmail());
            criteria.addParam(DAO_USER_PHONE, currUser.getPhone());
            String password = request.getParameter(JSP_USER_PASSWORD);
            Validator.validatePassword(password);
            criteria.addParam(DAO_USER_PASSWORD, password.hashCode());
        
            User user = (User) request.getSessionAttribute(JSP_USER);
            if (user != null) {
                ClientType type = ClientTypeManager.clientTypeOf(user.getRole().getRoleName());
                criteria.addParam(DAO_ROLE_NAME, type);
            } else {
                criteria.addParam(DAO_ROLE_NAME, request.getSessionAttribute(JSP_ROLE_TYPE));
            }
        
            Integer resUser = new UserLogic().doRedactEntity(criteria);
            request.setParameter(JSP_SELECT_ID, resUser.toString());
            page = new ShowUser().execute(request);
        } catch (TechnicalException | LogicException ex) {
            request.setAttribute("errorSaveReason", ex.getMessage());
            request.setAttribute("errorSave", "message.errorSaveData");
            request.setSessionAttribute(JSP_PAGE, page);
        }   
        return page;
    }

    /**
     * Resave common parameters of edit user page.
     * @param request parameters and attributes of the request and the session
     */
    private void resaveParamsSaveUser(SessionRequestContent request) {
        User currUser = (User) request.getSessionAttribute(JSP_CURRENT_USER);
        currUser.setPhone(request.getParameter(JSP_USER_PHONE));
        currUser.setEmail(request.getParameter(JSP_USER_EMAIL));
    }
}
