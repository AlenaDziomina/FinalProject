/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package by.epam.project.logic;

import by.epam.project.dao.AbstractDao;
import static by.epam.project.dao.AbstractDao.PARAM_NAME_ID_CITY;
import static by.epam.project.dao.AbstractDao.PARAM_NAME_ROLE;
import by.epam.project.dao.ClientType;
import by.epam.project.dao.DaoException;
import by.epam.project.dao.DaoFactory;
import by.epam.project.dao.query.Criteria;
import by.epam.project.entity.City;
import by.epam.project.entity.Hotel;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;

/**
 *
 * @author User
 */
public class CityLogic {
    
    public static List<City> getCities(Criteria criteria) throws DaoException {
        ClientType role = (ClientType) criteria.getParam(PARAM_NAME_ROLE);
        AbstractDao dao = null;
        
        try {
            dao = DaoFactory.getInstance(role); 
            dao.open();
            Method method = dao.getClass().getMethod("toShowCities", Criteria.class);
            List<City> cities = (List<City>) method.invoke(dao, criteria);
            Method meth = dao.getClass().getMethod("toShowHotels", Criteria.class);
            for (City city : cities) {
                Criteria crit = new Criteria();
                crit.addParam(PARAM_NAME_ID_CITY, city.getIdCity());
                List<Hotel> hotels = (List<Hotel>) meth.invoke(dao, crit);
                city.setHotelCollection(hotels);
            }
            return cities;   
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

    public static Integer redactCity(Criteria criteria) throws DaoException {
        ClientType role = (ClientType) criteria.getParam(PARAM_NAME_ROLE);
        Integer idCity = (Integer) criteria.getParam(PARAM_NAME_ID_CITY);
        AbstractDao dao = null;

        try {
            dao = DaoFactory.getInstance(role); 
            dao.open();
            Method method;
            if (idCity == null) {      
                method = dao.getClass().getMethod("toCreateNewCity", Criteria.class);
            } else {
                method = dao.getClass().getMethod("toUpdateCity", Criteria.class);
            }
            Integer currIdCity = (Integer) method.invoke(dao, criteria);
            return currIdCity;    
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
