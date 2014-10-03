/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package by.epam.project.logic;

import by.epam.project.dao.AbstractDao;
import by.epam.project.dao.ClientType;
import by.epam.project.dao.DaoFactory;
import static by.epam.project.dao.entquery.RoleQuery.DAO_ID_ROLE;
import static by.epam.project.dao.entquery.RoleQuery.DAO_ROLE_NAME;
import static by.epam.project.dao.entquery.UserQuery.DAO_USER_EMAIL;
import static by.epam.project.dao.entquery.UserQuery.DAO_USER_LOGIN;
import by.epam.project.dao.query.Criteria;
import by.epam.project.entity.Role;
import by.epam.project.entity.User;
import by.epam.project.exception.DaoException;
import java.util.List;

/**
 *
 * @author User
 */
public abstract class UserLogic {
    
    public static User updateUser(Criteria bean, Criteria criteria) throws DaoException{   
        ClientType role = (ClientType) bean.getParam(DAO_ROLE_NAME);
        AbstractDao dao = DaoFactory.getInstance(role); 
        dao.open();
        try {
            User person = dao.changeOwnUser(bean, criteria);
            Criteria crit = new Criteria();
            crit.addParam(DAO_ID_ROLE, person.getRole().getIdRole());
            List<Role> roles = dao.showRoles(crit);
            if (roles != null && !roles.isEmpty()) {
                person.setRole(roles.get(0));
            }
            return person; 
        } catch (DaoException ex) {
            dao.rollback();
            throw ex;
        } finally {
            dao.close();
        }
    }
    
    public static User checkLogin(Criteria criteria) throws DaoException {
        ClientType role = (ClientType) criteria.getParam(DAO_ROLE_NAME);
        AbstractDao dao = DaoFactory.getInstance(role);
        dao.open();
        try {
            List<User> users = dao.showUsers(criteria);
            if (users != null && !users.isEmpty()) {
                return users.get(0);  
            } else {
                return null;
            }
        } catch (DaoException ex) {
            dao.rollback();
            throw ex;
        } finally {
            dao.close(); 
        }
    }
    
    public static void userRegistration(Criteria criteria) throws DaoException {
        ClientType role = (ClientType) criteria.getParam(DAO_ROLE_NAME);
        AbstractDao dao = DaoFactory.getInstance(role);
        dao.open();
        try {
            if (checkLogin(criteria, dao) && checkEmail(criteria, dao) 
                    && checkRoleName(criteria, dao)) {
                dao.createNewUser(criteria);
            }  
        } catch (DaoException ex) {
            dao.rollback();
            throw ex;
        } finally {
            dao.close(); 
        }
    }
    
    private static boolean checkLogin(Criteria criteria, AbstractDao dao) throws DaoException {
        Criteria crit = new Criteria();
        crit.addParam(DAO_USER_LOGIN, criteria.getParam(DAO_USER_LOGIN));
        List<User> person = dao.showUsers(crit);
        return person.isEmpty();
    }
    
    private static boolean checkEmail(Criteria criteria, AbstractDao dao) throws DaoException { 
        Criteria crit = new Criteria();
        crit.addParam(DAO_USER_EMAIL, criteria.getParam(DAO_USER_EMAIL));
        List<User> person = dao.showUsers(crit);
        return person.isEmpty();
    }
    
    private static boolean checkRoleName(Criteria criteria, AbstractDao dao) throws DaoException { 
        Criteria crit = new Criteria();
        crit.addParam(DAO_ROLE_NAME, criteria.getParam(DAO_ROLE_NAME).toString());
        List<Role> roles = dao.showRoles(crit);
        if (roles != null && !roles.isEmpty()) {
            criteria.addParam(DAO_ID_ROLE, roles.get(0).getIdRole());
            return true;
        }
        return false;
    }
}
