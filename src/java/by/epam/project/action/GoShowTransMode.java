/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package by.epam.project.action;

import by.epam.project.controller.SessionRequestContent;
import static by.epam.project.dao.AbstractDao.PARAM_NAME_LOGIN;
import static by.epam.project.dao.AbstractDao.PARAM_NAME_ROLE;
import by.epam.project.dao.DaoException;
import by.epam.project.dao.query.Criteria;
import by.epam.project.entity.TransportationMode;
import by.epam.project.logic.TransModeLogic;
import by.epam.project.manager.MessageManager;
import java.util.List;

/**
 *
 * @author User
 */
public class GoShowTransMode implements ActionCommand {

    @Override
    public String execute(SessionRequestContent request) throws DaoLogicException {
        
        Criteria criteria = new Criteria();
        criteria.addParam(PARAM_NAME_LOGIN, request.getSessionAttribute(PARAM_NAME_LOGIN));
        criteria.addParam(PARAM_NAME_ROLE, request.getSessionAttribute(PARAM_NAME_ROLE));
        
        try {
            List<TransportationMode> modes = TransModeLogic.getTransModes(criteria);
            if (modes != null || !modes.isEmpty()) {
                request.setSessionAttribute(PARAM_NAME_TRANS_MODE_LIST, modes);
                request.setSessionAttribute(PARAM_NAME_TRANS_MODE_COUNT, modes.size());
            } else {
                request.setAttribute("errorGetListMessage", MessageManager.getProperty("message.listerror"));
            }
        } catch (DaoException ex) {
            throw new DaoLogicException(MessageManager.getProperty("message.daoerror"));
        }
        return null;
    }
    
}