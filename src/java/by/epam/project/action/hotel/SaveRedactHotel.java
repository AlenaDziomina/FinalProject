package by.epam.project.action.hotel;

import by.epam.project.action.ActionCommand;
import static by.epam.project.action.JspParamNames.*;
import static by.epam.project.dao.DaoParamNames.*;
import by.epam.project.action.SessionRequestContent;
import by.epam.project.dao.query.Criteria;
import by.epam.project.entity.ClientType;
import by.epam.project.entity.Hotel;
import by.epam.project.entity.User;
import by.epam.project.exception.LogicException;
import by.epam.project.exception.ServletLogicException;
import by.epam.project.exception.TechnicalException;
import by.epam.project.logic.HotelLogic;
import by.epam.project.manager.ClientTypeManager;
import by.epam.project.manager.ConfigurationManager;
import by.epam.project.manager.Validator;

/**
 * Class of command to save redacted or created hotel object.
 * @author Helena.Grouk
 */
public class SaveRedactHotel extends HotelCommand implements ActionCommand {
    @Override
    public String execute(SessionRequestContent request) throws ServletLogicException {
        String page = ConfigurationManager.getProperty("path.page.edithotel");
        resaveParamsSaveHotel(request);
        try {
            Criteria criteria = new Criteria();
            Hotel hotel = (Hotel) request.getSessionAttribute(JSP_CURRENT_HOTEL);
            Validator.validateHotel(hotel);
            Integer idHotel = hotel.getIdHotel();
            if (idHotel != null) {
                criteria.addParam(DAO_ID_HOTEL, idHotel);
            }
            Integer idDescription = hotel.getDescription().getIdDescription();
            if (idDescription != null) {
                criteria.addParam(DAO_ID_DESCRIPTION, idDescription);
            }
            Integer stars = hotel.getStars();
            if (stars != null) {
                criteria.addParam(DAO_HOTEL_STARS, stars);
            }
            criteria.addParam(DAO_HOTEL_NAME, hotel.getName());
            criteria.addParam(DAO_HOTEL_PICTURE, hotel.getPicture());
            criteria.addParam(DAO_DESCRIPTION_TEXT, hotel.getDescription().getText());
            criteria.addParam(DAO_ID_CITY, hotel.getCity().getIdCity());
        
            User user = (User) request.getSessionAttribute(JSP_USER);
            if (user != null) {
                criteria.addParam(DAO_USER_LOGIN, user.getLogin());
                ClientType type = ClientTypeManager.clientTypeOf(user.getRole().getRoleName());
                criteria.addParam(DAO_ROLE_NAME, type);
            } else {
                criteria.addParam(DAO_ROLE_NAME, request.getSessionAttribute(JSP_ROLE_TYPE));
            }
        
            Integer resIdHotel = new HotelLogic().doRedactEntity(criteria);
            request.setParameter(JSP_SELECT_ID, resIdHotel.toString());
            page = new GoShowHotel().execute(request);
        } catch (TechnicalException | LogicException ex) {
            request.setAttribute("errorSaveReason", ex.getMessage());
            request.setAttribute("errorSave", "message.errorSaveData");
            request.setSessionAttribute(JSP_PAGE, page);
        }   
        return page;
    }
}