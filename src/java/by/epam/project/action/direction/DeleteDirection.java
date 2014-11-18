package by.epam.project.action.direction;

import by.epam.project.action.ActionCommand;
import static by.epam.project.action.JspParamNames.*;
import static by.epam.project.dao.DaoParamNames.*;
import by.epam.project.action.SessionRequestContent;
import by.epam.project.dao.query.Criteria;
import by.epam.project.dao.ClientType;
import by.epam.project.entity.Direction;
import by.epam.project.entity.User;
import by.epam.project.exception.ServletLogicException;
import by.epam.project.exception.TechnicalException;
import by.epam.project.logic.AbstractLogic;
import by.epam.project.logic.LogicFactory;
import by.epam.project.logic.LogicType;
import by.epam.project.manager.ClientTypeManager;
import by.epam.project.manager.ConfigurationManager;

/**
 * Class of command to delete direction object
 * @author Helena.Grouk
 */
public class DeleteDirection extends DirectionCommand implements ActionCommand {
    @Override
    public String execute(SessionRequestContent request) throws ServletLogicException {
        String page = ConfigurationManager.getProperty("path.page.direction");
        try {
            Criteria criteria = new Criteria();
            Direction currDirection = (Direction) request.getSessionAttribute(JSP_CURRENT_DIRECTION);
            if (currDirection == null) {
                throw new TechnicalException("message.errorNullEntity");
            }
            criteria.addParam(DAO_ID_DIRECTION, currDirection.getIdDirection());
            User user = (User) request.getSessionAttribute(JSP_USER);
            if (user != null) {
                ClientType type = ClientTypeManager.clientTypeOf(user.getRole().getRoleName());
                criteria.addParam(DAO_ROLE_NAME, type);
            } else {
                criteria.addParam(DAO_ROLE_NAME, request.getSessionAttribute(JSP_ROLE_TYPE));
            }
            AbstractLogic directionLogic = LogicFactory.getInctance(LogicType.DIRECTIONLOGIC);
            directionLogic.doDeleteEntity(criteria);
            currDirection.setStatus(DELETED);
            page = new ShowDirection().execute(request);
        } catch (TechnicalException ex) {
            request.setAttribute("errorDeleteReason", ex.getMessage());
            request.setAttribute("errorDelete", "message.errorDeleteData");
            request.setSessionAttribute(JSP_PAGE, page);
        }
        return page;
    }
}
