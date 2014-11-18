package by.epam.project.action.country;

import by.epam.project.action.ActionCommand;
import static by.epam.project.action.JspParamNames.*;
import static by.epam.project.dao.DaoParamNames.*;
import by.epam.project.action.SessionRequestContent;
import by.epam.project.dao.query.Criteria;
import by.epam.project.dao.ClientType;
import by.epam.project.entity.Country;
import by.epam.project.entity.User;
import by.epam.project.exception.ServletLogicException;
import by.epam.project.exception.TechnicalException;
import by.epam.project.logic.CountryLogic;
import by.epam.project.manager.ClientTypeManager;
import by.epam.project.manager.ConfigurationManager;

/**
 * Class of command to restore country object
 * @author Helena.Grouk
 */
public class RestoreCountry extends CountryCommand implements ActionCommand {
    @Override
    public String execute(SessionRequestContent request) throws ServletLogicException {
        String page = ConfigurationManager.getProperty("path.page.countries");
        try {
            Criteria criteria = new Criteria();
            Country currCountry = (Country) request.getSessionAttribute(JSP_CURRENT_COUNTRY);
            if (currCountry == null) {
                throw new TechnicalException("message.errorNullEntity");
            }
            criteria.addParam(DAO_ID_COUNTRY, currCountry.getIdCountry());
            User user = (User) request.getSessionAttribute(JSP_USER);
            if (user != null) {
                ClientType type = ClientTypeManager.clientTypeOf(user.getRole().getRoleName());
                criteria.addParam(DAO_ROLE_NAME, type);
            } else {
                criteria.addParam(DAO_ROLE_NAME, request.getSessionAttribute(JSP_ROLE_TYPE));
            }
            new CountryLogic().doRestoreEntity(criteria);
            page = new GoShowCountry().execute(request);
        } catch (TechnicalException ex) {
            request.setAttribute("errorRestoreReason", ex.getMessage());
            request.setAttribute("errorRestore", "message.errorRestoreData");
            request.setSessionAttribute(JSP_PAGE, page);
        }
        return page;
    }
}
