/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package by.epam.project.logic;

import by.epam.project.dao.AbstractDao;
import static by.epam.project.dao.AbstractDao.PARAM_NAME_ROLE;
import by.epam.project.dao.ClientType;
import by.epam.project.dao.DaoException;
import by.epam.project.dao.DaoFactory;
import by.epam.project.dao.query.Criteria;
import by.epam.project.entity.User;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 *
 * @author User
 */
public class LoginLogic {
        
//    public static User checkLogin(String enterLogin, String enterPass, String enterRole) throws DaoException {
//        
//        ClientType clientType = clientTypeOf(enterRole);     
//        AbstractDao dao = DaoFactory.getInstance(clientType);      
//        Criteria criteria = new Criteria();     
//        criteria.addParam("login", enterLogin);
//        criteria.addParam("pass", enterPass);
//        try {
//            Method method = dao.getClass().getMethod("to_login", Criteria.class);
//            User person = (User) method.invoke(dao, criteria);
//            return person;       
//        } catch (NoSuchMethodException | SecurityException | IllegalAccessException | IllegalArgumentException | InvocationTargetException ex) {
//            throw new DaoException("No such dao method");
//        }
//    }

    public static User checkLogin(Criteria criteria) throws DaoException {
        ClientType role = (ClientType) criteria.getParam(PARAM_NAME_ROLE);
        AbstractDao dao = DaoFactory.getInstance(role);  
        try {
            Method method = dao.getClass().getMethod("to_login", Criteria.class);
            User person = (User) method.invoke(dao, criteria);
            return person;       
        } catch (NoSuchMethodException | SecurityException | IllegalAccessException | IllegalArgumentException | InvocationTargetException ex) {
            throw new DaoException("No such dao method");
        }
    }
}
        