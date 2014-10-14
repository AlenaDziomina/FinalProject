/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package by.epam.project.action.user;

import by.epam.project.action.ActionCommand;
import static by.epam.project.action.JspParamNames.JSP_ID_USER;
import static by.epam.project.action.JspParamNames.JSP_LENG;
import static by.epam.project.action.JspParamNames.JSP_LOCALE;
import static by.epam.project.action.JspParamNames.JSP_PAGE;
import static by.epam.project.action.JspParamNames.JSP_ROLE_TYPE;
import static by.epam.project.action.JspParamNames.JSP_USER_LOGIN;
import static by.epam.project.action.ProcessSavedParameters.resaveParams;
import by.epam.project.controller.SessionRequestContent;
import static by.epam.project.dao.entquery.RoleQuery.DAO_ROLE_NAME;
import static by.epam.project.dao.entquery.UserQuery.DAO_ID_USER;
import static by.epam.project.dao.entquery.UserQuery.DAO_USER_LANGUAGE;
import static by.epam.project.dao.entquery.UserQuery.DAO_USER_LOGIN;
import by.epam.project.dao.query.Criteria;
import by.epam.project.entity.User;
import by.epam.project.exception.DaoAccessPermission;
import by.epam.project.exception.DaoConnectException;
import by.epam.project.exception.DaoException;
import by.epam.project.exception.DaoInitException;
import by.epam.project.exception.DaoQueryException;
import by.epam.project.exception.DaoUserLogicException;
import by.epam.project.logic.UserLogic;
import by.epam.project.manager.ConfigurationManager;
import by.epam.project.manager.LocaleManager;
import by.epam.project.manager.MessageManager;
import java.util.Locale;

/**
 *
 * @author User
 */
public class LocalCommand implements ActionCommand {
 
    @Override
    public String execute(SessionRequestContent request) throws DaoUserLogicException {
        
        String page = (String)request.getSessionAttribute(JSP_PAGE);
        if (page == null) {
            page = ConfigurationManager.getProperty("path.page.index");
            request.setSessionAttribute(JSP_PAGE, page);
        }
        
        String leng = request.getParameter(JSP_LENG);
        Locale locale = LocaleManager.getLocale(leng);
        if (locale != null) {
            request.setSessionAttribute(JSP_LOCALE, locale);
        }
        
        Integer idUser = (Integer) request.getSessionAttribute(JSP_ID_USER);
        if (idUser == null) {
            return page;
        }
        
        Criteria bean = new Criteria();
        bean.addParam(DAO_USER_LOGIN, request.getSessionAttribute(JSP_USER_LOGIN));
        bean.addParam(DAO_ID_USER, idUser);
        bean.addParam(DAO_ROLE_NAME, request.getSessionAttribute(JSP_ROLE_TYPE));
        
        Criteria criteria = new Criteria();
        Locale currLocale = (Locale)(request.getSessionAttribute(JSP_LOCALE));
        criteria.addParam(DAO_USER_LANGUAGE, currLocale.getLanguage());
        
        try {
            User user = UserLogic.updateUser(bean, criteria);           
        } catch (DaoAccessPermission ex) {
            request.setAttribute("errorReason", MessageManager.getProperty("message.errordaoaccess"));
            request.setAttribute("errorAdminMsg", ex.getMessage());
        } catch (DaoConnectException ex) {
            request.setAttribute("errorReason", MessageManager.getProperty("message.errordaoconnect"));
            request.setAttribute("errorAdminMsg", ex.getMessage());
        } catch (DaoQueryException ex) {
            request.setAttribute("errorReason", MessageManager.getProperty("message.errordaoquery"));
            request.setAttribute("errorAdminMsg", ex.getMessage());
        } catch (DaoInitException ex) {
            throw new DaoUserLogicException(MessageManager.getProperty("message.daoerror" + ex.getMessage()));
        } catch (DaoException ex){
            throw new DaoUserLogicException(MessageManager.getProperty("message.daoerror" + ex.getMessage()));
        }
        return page;
    }
    
}
