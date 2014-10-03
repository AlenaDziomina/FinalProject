/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package by.epam.project.logic;

import by.epam.project.dao.AbstractDao;
import by.epam.project.dao.ClientType;
import by.epam.project.dao.DaoFactory;
import static by.epam.project.dao.entquery.CityQuery.DAO_ID_CITY;
import static by.epam.project.dao.entquery.CountryQuery.DAO_ID_COUNTRY;
import static by.epam.project.dao.entquery.DescriptionQuery.DAO_ID_DESCRIPTION;
import static by.epam.project.dao.entquery.RoleQuery.DAO_ROLE_NAME;
import by.epam.project.dao.query.Criteria;
import by.epam.project.entity.City;
import by.epam.project.entity.Country;
import by.epam.project.entity.Description;
import by.epam.project.entity.Hotel;
import by.epam.project.exception.DaoException;
import java.util.List;

/**
 *
 * @author User
 */
public class CityLogic {
    
    public static List<City> getCities(Criteria criteria) throws DaoException {
        
        ClientType role = (ClientType) criteria.getParam(DAO_ROLE_NAME);
        AbstractDao dao = DaoFactory.getInstance(role);
        dao.open();
        
        try {
            List<City> cities = dao.showCities(criteria);
            fillCities(cities, dao);
            return cities;   
        } catch (DaoException ex) {
            dao.rollback();
            throw ex;
        } finally {
            dao.close();
        }
    }
    
    public static Integer redactCity(Criteria criteria) throws DaoException {
        ClientType role = (ClientType) criteria.getParam(DAO_ROLE_NAME);
        Integer idCity = (Integer) criteria.getParam(DAO_ID_CITY);
        AbstractDao dao = DaoFactory.getInstance(role); 
        dao.open();
        
        try {
            if (idCity == null) {      
                return createCity(criteria, dao);
            } else {
                return updateCity(criteria, dao);
            }  
        } catch (DaoException ex) {
            dao.rollback();
            throw ex;
        } finally {
            dao.close();
        }
    }
    
    private static void fillCities(List<City> cities, AbstractDao dao) throws DaoException {
        if (cities != null) {
            for (City city : cities) {
                Criteria crit = new Criteria();
                crit.addParam(DAO_ID_DESCRIPTION, city.getDescription().getIdDescription());
                crit.addParam(DAO_ID_COUNTRY, city.getCountry().getIdCountry());
                
                List<Description> desc = dao.showDescriptions(crit);
                if (desc != null && !desc.isEmpty()) {
                    city.setDescription(desc.get(0));
                }
                List<Country> countries = dao.showCountries(crit);
                if (countries != null && !countries.isEmpty()) {
                    city.setCountry(countries.get(0));
                }
                List<Hotel> hotels = dao.showHotels(crit);
                city.setHotelCollection(hotels);
            }
        }
    }

    private static Integer createCity(Criteria criteria, AbstractDao dao) throws DaoException {    
        Integer idDescription = dao.createNewDescription(criteria).get(0);
        criteria.remuveParam(DAO_ID_DESCRIPTION);
        criteria.addParam(DAO_ID_DESCRIPTION, idDescription);
        return dao.createNewCity(criteria).get(0);   
    }
    
    private static Integer updateCity(Criteria criteria, AbstractDao dao) throws DaoException {
        Criteria beans = new Criteria();
        beans.addParam(DAO_ID_CITY, criteria.getParam(DAO_ID_CITY));
        beans.addParam(DAO_ID_DESCRIPTION, criteria.getParam(DAO_ID_DESCRIPTION));
        criteria.remuveParam(DAO_ID_COUNTRY);
        criteria.remuveParam(DAO_ID_DESCRIPTION);
        
        Integer idDescription = dao.updateDescription(beans, criteria).get(0);
        criteria.addParam(DAO_ID_DESCRIPTION, idDescription);
        return dao.updateCity(beans, criteria).get(0);
    }
    
    
}
