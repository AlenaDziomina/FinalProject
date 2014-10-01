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
import by.epam.project.entity.Country;
import by.epam.project.entity.Direction;
import by.epam.project.entity.LinkDirectionCountry;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

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
            
            if (directions != null) {
                getCountryCollection(dao, directions);
            }
            
            
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
    
    private static void getCountryCollection(AbstractDao dao, List<Direction> directions) throws DaoException {  
        try {
            Method meth = dao.getClass().getMethod("toShowLinkDirectionCountry", Criteria.class);
            for (Direction dir : directions) {
                Criteria crit = new Criteria();
                crit.addParam(PARAM_NAME_ID_DIRECTION, dir.getIdDirection());
                List<LinkDirectionCountry> links = (List<LinkDirectionCountry>) meth.invoke(dao, crit);
                if (links != null) {
                    dir.setCountryCollection(getCountryInfo(dao, links));
                }
            }
        } catch (NoSuchMethodException | SecurityException | IllegalAccessException | IllegalArgumentException | InvocationTargetException ex) {
            throw new DaoException("Error in get direction links to counties.");
        }
    }
    
    public static List<Country> getCountryInfo(AbstractDao dao, List<LinkDirectionCountry> links) throws DaoException {
        try {
            Method meth = dao.getClass().getMethod("toShowCountries", Criteria.class);
            List<Country> list = new ArrayList<>();
            for (LinkDirectionCountry link : links) {
                Criteria crit = new Criteria();
                crit.addParam(PARAM_NAME_ID_COUNTRY, link.getIdCountry());
                List<Country> countries = (List<Country>) meth.invoke(dao, crit);
                if (countries != null) {
                    list.addAll(countries);
                }
            }
            return list;
        } catch (NoSuchMethodException | SecurityException | IllegalAccessException | IllegalArgumentException | InvocationTargetException ex) {
            throw new DaoException("Error in getCountryInfo");
        }
    }

    public static Integer redactDirection(Criteria criteria) throws DaoException {
        
        ClientType role = (ClientType) criteria.getParam(PARAM_NAME_ROLE);
        Integer idDirection = (Integer) criteria.getParam(PARAM_NAME_ID_DIRECTION);
        AbstractDao dao = null;

        try {
            dao = DaoFactory.getInstance(role); 
            dao.open();
            Method method;
            if (idDirection == null) {      
                method = dao.getClass().getMethod("toCreateNewDirection", Criteria.class);
            } else {
                method = dao.getClass().getMethod("toUpdateDirection", Criteria.class);
            }
            Integer currIdDirection = (Integer) method.invoke(dao, criteria);
            return currIdDirection;    
        } catch (DaoException ex) {
            if (dao != null) {
                dao.rollback();
            }
            throw ex;
        } catch (NoSuchMethodException | SecurityException | IllegalAccessException | IllegalArgumentException | InvocationTargetException ex ) {
            if (dao != null) {
                dao.rollback();
            }
            return null;
        } finally {
            if (dao != null) {
                dao.close();
            } 
        }
    }
    
}
