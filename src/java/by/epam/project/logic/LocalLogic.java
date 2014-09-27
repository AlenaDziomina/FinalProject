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
public abstract class LocalLogic {

    public static User setUserLocal(Criteria bean, Criteria criteria) throws DaoException {
        ClientType role = (ClientType) bean.getParam(PARAM_NAME_ROLE);
        AbstractDao dao = null; 
        
        try {
            dao = DaoFactory.getInstance(role); 
            dao.open();
            Method method = dao.getClass().getMethod("toChangeOwnUser", Criteria.class, Criteria.class);
            User person = (User) method.invoke(dao, bean, criteria);
            return person; 
        } catch (DaoException ex) {
            if (dao != null) {
                dao.rollback();
            }
            throw ex;
        } catch (NoSuchMethodException | SecurityException | IllegalAccessException | IllegalArgumentException | InvocationTargetException ex) {
            return null;
        } finally {
            if (dao != null) {
                dao.close();
            } 
        }
    }
    
}
