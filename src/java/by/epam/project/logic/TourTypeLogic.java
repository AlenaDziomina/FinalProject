/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package by.epam.project.logic;

import by.epam.project.dao.AbstractDao;
import by.epam.project.dao.ClientType;
import by.epam.project.exception.DaoException;
import by.epam.project.dao.DaoFactory;
import static by.epam.project.dao.entquery.RoleQuery.DAO_ROLE_NAME;
import by.epam.project.dao.query.Criteria;
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
        ClientType role = (ClientType) criteria.getParam(DAO_ROLE_NAME);
        AbstractDao dao = DaoFactory.getInstance(role); 
        dao.open();
        
        try {
            
            List<TourType> types = dao.showTourTypes(criteria);
           
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
