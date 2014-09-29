/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package by.epam.project.logic;

import by.epam.project.dao.AbstractDao;
import static by.epam.project.dao.AbstractDao.PARAM_NAME_ID_COUNTRY;
import static by.epam.project.dao.AbstractDao.PARAM_NAME_ROLE;
import by.epam.project.dao.ClientType;
import by.epam.project.dao.DaoException;
import by.epam.project.dao.DaoFactory;
import by.epam.project.dao.query.Criteria;
import by.epam.project.entity.City;
import by.epam.project.entity.Country;
import by.epam.project.entity.TourType;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;

/**
 *
 * @author User
 */
public abstract class TourTypeLogic {
    
    public static List<TourType> getTourTypes(Criteria criteria) throws DaoException {
        ClientType role = (ClientType) criteria.getParam(PARAM_NAME_ROLE);
        AbstractDao dao = null;
        
        try {
            dao = DaoFactory.getInstance(role); 
            dao.open();
            Method method = dao.getClass().getMethod("toShowTourTypes", Criteria.class);
            List<TourType> types = (List<TourType>) method.invoke(dao, criteria);
           
            return types;   
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
