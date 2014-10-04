/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package by.epam.project.manager;

import static by.epam.project.action.JspParamNames.JSP_PAGE;
import by.epam.project.controller.SessionRequestContent;
import by.epam.project.exception.DaoAccessPermission;
import by.epam.project.exception.DaoConnectException;
import by.epam.project.exception.DaoException;
import by.epam.project.exception.DaoInitException;
import by.epam.project.exception.DaoQueryException;
import by.epam.project.exception.DaoUserLogicException;

/**
 *
 * @author User
 */
public class ExceptionCatchManager {
    
    public static void determineResponce(DaoException ex, String currPage, SessionRequestContent request) throws DaoUserLogicException {
        throw new DaoUserLogicException(MessageManager.getProperty("message.daoerror" + ex.getMessage()));
    }
    
    public static void determineResponce(DaoAccessPermission ex, String currPage, SessionRequestContent request) {
        request.setSessionAttribute(JSP_PAGE, currPage);
        request.setAttribute("errorSaveData", MessageManager.getProperty("message.errorsavedata"));
        request.setAttribute("errorReason", MessageManager.getProperty("message.errordaoaccess"));
        request.setAttribute("errorAdminMsg", ex.getMessage());
    }
    
    public static void determineResponce(DaoConnectException ex, String currPage, SessionRequestContent request) {
            request.setSessionAttribute(JSP_PAGE, currPage);
            request.setAttribute("errorSaveData", MessageManager.getProperty("message.errorconnect"));
            request.setAttribute("errorReason", MessageManager.getProperty("message.errordaoconnect"));
            request.setAttribute("errorAdminMsg", ex.getMessage());
    }
    
    public static void determineResponce(DaoQueryException ex, String currPage, SessionRequestContent request) {
            request.setSessionAttribute(JSP_PAGE, currPage);
            request.setAttribute("errorSaveData", MessageManager.getProperty("message.errorsavedata"));
            request.setAttribute("errorReason", MessageManager.getProperty("message.errorquery"));
            request.setAttribute("errorAdminMsg", ex.getMessage());
    }
    
    public static void determineResponce(DaoInitException ex, String currPage, SessionRequestContent request) throws DaoUserLogicException {
            throw new DaoUserLogicException(MessageManager.getProperty("message.daoerror" + ex.getMessage()));
    }
            
            
    
}
