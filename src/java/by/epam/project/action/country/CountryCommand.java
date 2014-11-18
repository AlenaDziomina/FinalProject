package by.epam.project.action.country;

import static by.epam.project.action.JspParamNames.*;
import static by.epam.project.dao.DaoParamNames.*;
import by.epam.project.action.SessionRequestContent;
import by.epam.project.action.city.CityCommand;
import by.epam.project.dao.query.Criteria;
import by.epam.project.dao.ClientType;
import by.epam.project.entity.Country;
import by.epam.project.entity.User;
import by.epam.project.exception.ServletLogicException;
import by.epam.project.exception.TechnicalException;
import by.epam.project.logic.CountryLogic;
import by.epam.project.manager.ClientTypeManager;
import static by.epam.project.manager.ParamManager.getBoolParam;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;

/**
 * Class {@code CountryCommand} is the parent class of all commands of actions
 * on the country objects.
 * Contains custom public methods of actions on the country objects.
 * @author Helena.Grouk
 */
public class CountryCommand {
    /**
     * Find the list of countries and save it as the attribute of session.
     * Also determine and store in session attributes display options of country 
     * and city status.
     * @param request parameters and attributes of the request and the session
     * @throws ServletLogicException if this can not be done due to the 
     * exceptions of logic layer
     */
    public void formCountryList(SessionRequestContent request)throws ServletLogicException {
        Criteria criteria = new Criteria();
        User user = (User) request.getSessionAttribute(JSP_USER);
        if (user != null) {
            criteria.addParam(DAO_USER_LOGIN, user.getLogin());
            ClientType type = ClientTypeManager.clientTypeOf(user.getRole().getRoleName());
            criteria.addParam(DAO_ROLE_NAME, type);
        } else {
            criteria.addParam(DAO_ROLE_NAME, request.getSessionAttribute(JSP_ROLE_TYPE));
        }
        
        Short countryStatus = getCountryStatus(request);
        if (countryStatus != null) {
            criteria.addParam(DAO_COUNTRY_STATUS, countryStatus);
        }
        
        Short cityStatus = new CityCommand().getCityStatus(request);
        if (cityStatus != null) {
            criteria.addParam(DAO_CITY_STATUS, cityStatus);
        }
        
        try {
            List<Country> countrys = new CountryLogic().doGetEntity(criteria);
            request.setSessionAttribute(JSP_COUNTRY_LIST, countrys);
        } catch (TechnicalException ex) {
            throw new ServletLogicException(ex.getMessage(), ex);
        }
    }
    
    /**
     * Determine and store in session attributes current country and its id for 
     * displaying it. It needs list of countries and selected id in request.
     * @param request parameters and attributes of the request and the session
     */
    protected void showSelectedCountry(SessionRequestContent request) {
        String selected = request.getParameter(JSP_SELECT_ID);
        Country currCountry = null;
        if (selected != null) {
            Integer idCountry = Integer.decode(selected); 
            if (idCountry != null) {
                List<Country> list = (List<Country>) request.getSessionAttribute(JSP_COUNTRY_LIST);
                Iterator<Country> it = list.iterator();
                while (it.hasNext() && currCountry == null) {
                    Country country = it.next();
                    if (Objects.equals(country.getIdCountry(), idCountry)) {
                        currCountry = country;
                        request.setAttribute(JSP_CURR_ID_COUNTRY, idCountry);
                    }
                }
            }
        }
        request.setSessionAttribute(JSP_CURRENT_COUNTRY, currCountry);
    }
    
    /**
     * Determine and store in session attributes display options of country 
     * status. Default value means 'only valid'.
     * @param request parameters and attributes of the request and the session
     * @return {@code ACTIVE} == 1 if 'only valid'; {@code DELETED} == 0 
     * if 'only invalid'.
     */
    private Short getCountryStatus(SessionRequestContent request) {
        Boolean validStatus = getBoolParam(request, JSP_COUNTRY_VALID_STATUS);
        Boolean invalidStatus = getBoolParam(request, JSP_COUNTRY_INVALID_STATUS);
        if (validStatus == null && invalidStatus == null) {
            validStatus = (Boolean) request.getSessionAttribute(JSP_COUNTRY_VALID_STATUS);
            invalidStatus = (Boolean) request.getSessionAttribute(JSP_COUNTRY_INVALID_STATUS);
            if (validStatus == null && invalidStatus == null) {
                validStatus = true;
                invalidStatus = false;
            }
        } else {
            if (validStatus == null) {
                validStatus = false;
            }
            if (invalidStatus == null) {
                invalidStatus = false;
            }
        }
        request.setSessionAttribute(JSP_COUNTRY_VALID_STATUS, validStatus);
        request.setSessionAttribute(JSP_COUNTRY_INVALID_STATUS, invalidStatus);
        
        Short status = null;
        if (validStatus && ! invalidStatus) {
            status = ACTIVE;
        } else if ( ! validStatus && invalidStatus) {
            status = DELETED;
        }
        return status;
    }
}
