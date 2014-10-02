/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package by.epam.project.action;

import static by.epam.project.controller.JspParamNames.JSP_ROLE_TYPE;
import static by.epam.project.controller.JspParamNames.JSP_USER_LOGIN;
import by.epam.project.controller.SessionRequestContent;
import by.epam.project.exception.DaoException;
import static by.epam.project.dao.entquery.RoleQuery.DAO_ROLE_NAME;
import static by.epam.project.dao.entquery.UserQuery.DAO_USER_LOGIN;
import by.epam.project.dao.query.Criteria;
import by.epam.project.entity.TourType;
import by.epam.project.logic.TourTypeLogic;
import by.epam.project.manager.MessageManager;
import java.util.List;

/**
 *
 * @author User
 */
public class GoShowTourType implements ActionCommand {

    @Override
    public String execute(SessionRequestContent request) throws DaoLogicException {
        
        Criteria criteria = new Criteria();
        criteria.addParam(DAO_USER_LOGIN, request.getSessionAttribute(JSP_USER_LOGIN));
        criteria.addParam(DAO_ROLE_NAME, request.getSessionAttribute(JSP_ROLE_TYPE));
        
        try {
            List<TourType> types = TourTypeLogic.getTourTypes(criteria);
            if (types != null || !types.isEmpty()) {
                request.setSessionAttribute(PARAM_NAME_TOUR_TYPE_LIST, types);
                request.setSessionAttribute(PARAM_NAME_TOUR_TYPE_COUNT, types.size());
            } else {
                request.setAttribute("errorGetListMessage", MessageManager.getProperty("message.listerror"));
            }
        } catch (DaoException ex) {
            throw new DaoLogicException(MessageManager.getProperty("message.daoerror"));
        }
        return null;
    }
    
}
