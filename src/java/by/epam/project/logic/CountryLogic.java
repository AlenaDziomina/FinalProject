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
import by.epam.project.entity.City;
import by.epam.project.entity.Country;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;

/**
 *
 * @author User
 */
public abstract class CountryLogic {

    public static List<Country> getCountries(Criteria criteria) throws DaoException {
        ClientType role = (ClientType) criteria.getParam(PARAM_NAME_ROLE);
        AbstractDao dao = null;
        
        try {
            dao = DaoFactory.getInstance(role); 
            Method method = dao.getClass().getMethod("toShowCountries", Criteria.class);
            List<Country> countries = (List<Country>) method.invoke(dao, criteria);
            Method meth = dao.getClass().getMethod("toShowCities", Criteria.class);
            for (Country country : countries) {
                Criteria crit = new Criteria();
                crit.addParam(PARAM_NAME_ID_COUNTRY, country.getIdCountry());
                List<City> cities = (List<City>) meth.invoke(dao, crit);
                country.setCityCollection(cities);
            }
            return countries;       
        } catch (NoSuchMethodException | SecurityException | IllegalAccessException | IllegalArgumentException | InvocationTargetException ex) {
            return null;
        } finally {
            if (dao != null) {
                dao.close();
            } 
        }
        
        
    }
    
}
