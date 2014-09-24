/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package by.epam.project.logic;

import by.epam.project.dao.AbstractDao;
import static by.epam.project.dao.AbstractDao.*;
import by.epam.project.dao.ClientType;
import by.epam.project.dao.DaoException;
import by.epam.project.dao.DaoFactory;
import by.epam.project.dao.query.Criteria;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 *
 * @author User
 */
public abstract class RegistrationLogic {
   
    public static void getRegistration(Criteria criteria) throws DaoException {
        ClientType role = (ClientType) criteria.getParam(PARAM_NAME_ROLE);
        AbstractDao dao = DaoFactory.getInstance(role); 
        try {
            Method method = dao.getClass().getMethod("toRegistrate", Criteria.class);
            method.invoke(dao, criteria);
                 
        } catch (NoSuchMethodException | SecurityException | IllegalAccessException | IllegalArgumentException | InvocationTargetException ex) {
            throw new DaoException("No such dao method");
        }
    }
}