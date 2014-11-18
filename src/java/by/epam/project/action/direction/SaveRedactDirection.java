package by.epam.project.action.direction;

import by.epam.project.action.ActionCommand;
import static by.epam.project.action.JspParamNames.*;
import static by.epam.project.dao.DaoParamNames.*;
import by.epam.project.action.SessionRequestContent;
import by.epam.project.dao.query.Criteria;
import by.epam.project.dao.ClientType;
import by.epam.project.entity.Direction;
import by.epam.project.entity.User;
import by.epam.project.exception.LogicException;
import by.epam.project.exception.ServletLogicException;
import by.epam.project.exception.TechnicalException;
import by.epam.project.logic.AbstractLogic;
import by.epam.project.logic.LogicFactory;
import by.epam.project.logic.LogicType;
import by.epam.project.manager.ClientTypeManager;
import by.epam.project.manager.ConfigurationManager;
import static by.epam.project.manager.ParamManager.checkArrParam;
import by.epam.project.manager.Validator;

/**
 * Class of command to save redacted or created direction object.
 * @author Helena.Grouk
 */
public class SaveRedactDirection extends DirectionCommand implements ActionCommand {
    @Override
    public String execute(SessionRequestContent request) throws ServletLogicException {
        String page = ConfigurationManager.getProperty("path.page.editdirection");
        resaveParamsSaveDirection(request);
        try {
            Criteria criteria = new Criteria();
            Direction direction = (Direction) request.getSessionAttribute(JSP_CURRENT_DIRECTION);
            Validator.validateDirection(direction);
            Integer idDirection = direction.getIdDirection();
            if (idDirection != null) {
                criteria.addParam(DAO_ID_DIRECTION, idDirection);
            }
            Integer idDescription = direction.getDescription().getIdDescription();
            if (idDescription != null) {
                criteria.addParam(DAO_ID_DESCRIPTION, idDescription);
            }
            Integer idTourType = direction.getTourType().getIdTourType();
            if (idTourType != null) {
                criteria.addParam(DAO_ID_TOURTYPE, idTourType);
            }
            Integer idTransMode = direction.getTransMode().getIdMode();
            if (idTransMode != null) {
                criteria.addParam(DAO_ID_TRANSMODE, idTransMode);
            }
            criteria.addParam(DAO_DIRECTION_NAME, direction.getName());
            criteria.addParam(DAO_DIRECTION_PICTURE, direction.getPicture());
            criteria.addParam(DAO_DIRECTION_TEXT, direction.getText());
            criteria.addParam(DAO_DESCRIPTION_TEXT, direction.getDescription().getText());

            User user = (User) request.getSessionAttribute(JSP_USER);
            if (user != null) {
                criteria.addParam(DAO_USER_LOGIN, user.getLogin());
                ClientType type = ClientTypeManager.clientTypeOf(user.getRole().getRoleName());
                criteria.addParam(DAO_ROLE_NAME, type);
            } else {
                criteria.addParam(DAO_ROLE_NAME, request.getSessionAttribute(JSP_ROLE_TYPE));
            }
            checkArrParam(request, criteria, JSP_CURR_COUNTRY_TAGS, DAO_ID_COUNTRY);
            checkArrParam(request, criteria, JSP_CURR_CITY_TAGS, DAO_ID_CITY);
            checkArrParam(request, criteria, JSP_CURR_HOTEL_TAGS, DAO_ID_HOTEL);

            AbstractLogic directionLogic = LogicFactory.getInctance(LogicType.DIRECTIONLOGIC);
            Integer resIdDirection = directionLogic.doRedactEntity(criteria);
            request.setParameter(JSP_SELECT_ID, resIdDirection.toString());
            page = new ShowDirection().execute(request);
        } catch (TechnicalException | LogicException ex) {
            request.setAttribute("errorSaveReason", ex.getMessage());
            request.setAttribute("errorSave", "message.errorSaveData");
            request.setSessionAttribute(JSP_PAGE, page);
        }
        return page;
    }
}

