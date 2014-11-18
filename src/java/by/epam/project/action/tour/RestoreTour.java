package by.epam.project.action.tour;

import by.epam.project.action.ActionCommand;
import static by.epam.project.action.JspParamNames.*;
import static by.epam.project.dao.DaoParamNames.*;
import by.epam.project.action.SessionRequestContent;
import by.epam.project.dao.query.Criteria;
import by.epam.project.dao.ClientType;
import by.epam.project.entity.Tour;
import by.epam.project.entity.User;
import by.epam.project.exception.ServletLogicException;
import by.epam.project.exception.TechnicalException;
import by.epam.project.logic.AbstractLogic;
import by.epam.project.logic.LogicFactory;
import by.epam.project.logic.LogicType;
import by.epam.project.manager.ClientTypeManager;
import by.epam.project.manager.ConfigurationManager;

/**
 * Class of command to restore tour object
 * @author Helena.Grouk
 */
public class RestoreTour extends TourCommand implements ActionCommand {
    @Override
    public String execute(SessionRequestContent request) throws ServletLogicException {
        String page = ConfigurationManager.getProperty("path.page.tour");
        try {
            Criteria criteria = new Criteria();
            Tour currTour = (Tour) request.getSessionAttribute(JSP_CURRENT_TOUR);
            if (currTour == null) {
                throw new TechnicalException("message.errorNullEntity");
            }
            criteria.addParam(DAO_ID_TOUR, currTour.getIdTour());
            User user = (User) request.getSessionAttribute(JSP_USER);
            if (user != null) {
                ClientType type = ClientTypeManager.clientTypeOf(user.getRole().getRoleName());
                criteria.addParam(DAO_ROLE_NAME, type);
            } else {
                criteria.addParam(DAO_ROLE_NAME, request.getSessionAttribute(JSP_ROLE_TYPE));
            }
            AbstractLogic tourLogic = LogicFactory.getInctance(LogicType.TOURLOGIC);
            tourLogic.doRestoreEntity(criteria);
            page = new ShowTour().execute(request);
        } catch (TechnicalException ex) {
            request.setAttribute("errorRestoreReason", ex.getMessage());
            request.setAttribute("errorRestore", "message.errorRestoreData");
            request.setSessionAttribute(JSP_PAGE, page);
        }
        return page;
    }
}
