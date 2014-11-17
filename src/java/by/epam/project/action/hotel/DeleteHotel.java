package by.epam.project.action.hotel;

import by.epam.project.action.ActionCommand;
import static by.epam.project.action.JspParamNames.*;
import static by.epam.project.dao.DaoParamNames.*;
import by.epam.project.action.SessionRequestContent;
import by.epam.project.dao.query.Criteria;
import by.epam.project.entity.ClientType;
import by.epam.project.entity.Hotel;
import by.epam.project.entity.User;
import by.epam.project.exception.ServletLogicException;
import by.epam.project.exception.TechnicalException;
import by.epam.project.logic.HotelLogic;
import by.epam.project.manager.ClientTypeManager;
import by.epam.project.manager.ConfigurationManager;

/**
 * Class of command to delete hotel object
 * @author Helena.Grouk
 */
public class DeleteHotel extends HotelCommand implements ActionCommand {
    @Override
    public String execute(SessionRequestContent request) throws ServletLogicException {
        String page = ConfigurationManager.getProperty("path.page.hotels");
        try {
            Criteria criteria = new Criteria();
            Hotel currHotel = (Hotel) request.getSessionAttribute(JSP_CURRENT_HOTEL);
            if (currHotel == null) {
                throw new TechnicalException("message.errorNullEntity");
            }
            criteria.addParam(DAO_ID_HOTEL, currHotel.getIdHotel());
            User user = (User) request.getSessionAttribute(JSP_USER);
            if (user != null) {
                ClientType type = ClientTypeManager.clientTypeOf(user.getRole().getRoleName());
                criteria.addParam(DAO_ROLE_NAME, type);
            } else {
                criteria.addParam(DAO_ROLE_NAME, request.getSessionAttribute(JSP_ROLE_TYPE));
            }
            new HotelLogic().doDeleteEntity(criteria);
            page = new GoShowHotel().execute(request);
        } catch (TechnicalException ex) {
            request.setAttribute("errorDeleteReason", ex.getMessage());
            request.setAttribute("errorDelete", "message.errorDeleteData");
            request.setSessionAttribute(JSP_PAGE, page);
        }
        return page;
    }
}
