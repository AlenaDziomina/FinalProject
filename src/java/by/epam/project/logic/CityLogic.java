/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package by.epam.project.logic;

import by.epam.project.dao.AbstractDao;
import static by.epam.project.dao.entquery.CityQuery.DAO_ID_CITY;
import static by.epam.project.dao.entquery.CountryQuery.DAO_ID_COUNTRY;
import static by.epam.project.dao.entquery.DescriptionQuery.DAO_ID_DESCRIPTION;
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
public class CityLogic extends AbstractLogic {
    
    @Override
    List<City> getEntity(Criteria criteria, AbstractDao dao) throws DaoException {
            List<City> cities = dao.showCities(criteria);
            fillCities(cities, dao);
            return cities;
    }
    
    @Override
    Integer redactEntity(Criteria criteria, AbstractDao dao) throws DaoException {
        Integer idCity = (Integer) criteria.getParam(DAO_ID_CITY);
        if (idCity == null) {      
            return createCity(criteria, dao);
        } else {
            return updateCity(criteria, dao);
        }  
    }
    
    private static void fillCities(List<City> cities, AbstractDao dao) throws DaoException {
        if (cities != null) {
            for (City city : cities) {
                Criteria crit1 = new Criteria();
                crit1.addParam(DAO_ID_DESCRIPTION, city.getDescription().getIdDescription());
                List<Description> desc = dao.showDescriptions(crit1);
                city.setDescription(desc.get(0));
                
                Criteria crit2 = new Criteria();
                crit2.addParam(DAO_ID_COUNTRY, city.getCountry().getIdCountry());
                List<Country> countries = dao.showCountries(crit2);
                city.setCountry(countries.get(0));
                
                Criteria crit3 = new Criteria();
                crit3.addParam(DAO_ID_CITY, city.getIdCity());
                List<Hotel> hotels = dao.showHotels(crit3);
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
        Criteria beans1 = new Criteria();
        Criteria beans2 = new Criteria();
        Integer idCity = (Integer) criteria.getParam(DAO_ID_CITY);
        beans1.addParam(DAO_ID_DESCRIPTION, criteria.getParam(DAO_ID_DESCRIPTION));
        beans2.addParam(DAO_ID_CITY, criteria.getParam(DAO_ID_CITY));
        dao.updateDescription(beans1, criteria);
        dao.updateCity(beans2, criteria);
        return idCity;
    }
    
}
