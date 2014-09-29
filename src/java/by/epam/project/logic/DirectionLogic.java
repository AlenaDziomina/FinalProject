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
import by.epam.project.entity.Direction;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;

/**
 *
 * @author User
 */
public abstract class DirectionLogic {
    
    public static List<Direction> getDirections(Criteria criteria) throws DaoException {
        ClientType role = (ClientType) criteria.getParam(PARAM_NAME_ROLE);
        AbstractDao dao = null;
        
        try {
            dao = DaoFactory.getInstance(role); 
            dao.open();
            Method method = dao.getClass().getMethod("toShowDirections", Criteria.class);
            List<Direction> directions = (List<Direction>) method.invoke(dao, criteria);
            
//            Method meth = dao.getClass().getMethod("toShowCountryTags", Criteria.class);
//            for (Direction dir : directions) {
//                Criteria crit = new Criteria();
//                crit.addParam(PARAM_NAME_ID_DIRECTION, dir.getIdDirection());
//                List<Country> countries = (List<Country>) meth.invoke(dao, crit);
//                dir.setCountryCollection(countries);
//            }
            
            return directions;   
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
