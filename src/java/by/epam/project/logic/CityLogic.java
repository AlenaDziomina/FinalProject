/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package by.epam.project.logic;

import static by.epam.project.action.JspParamNames.ACTIVE;
import static by.epam.project.action.JspParamNames.DELETED;
import by.epam.project.dao.AbstractDao;
import static by.epam.project.dao.entquery.CityQuery.DAO_CITY_STATUS;
import static by.epam.project.dao.entquery.CityQuery.DAO_ID_CITY;
import static by.epam.project.dao.entquery.CountryQuery.DAO_COUNTRY_STATUS;
import static by.epam.project.dao.entquery.CountryQuery.DAO_ID_COUNTRY;
import static by.epam.project.dao.entquery.DescriptionQuery.DAO_ID_DESCRIPTION;
import static by.epam.project.dao.entquery.HotelQuery.DAO_HOTEL_STATUS;
import static by.epam.project.dao.entquery.HotelQuery.DAO_ID_HOTEL;
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
        if (idCity != null) {
            return updateCity(criteria, dao);
        } else {      
            return createCity(criteria, dao);
        }  
    }
    
    private static void fillCities(List<City> cities, AbstractDao dao) throws DaoException {
        if (cities != null) {
            for (City city : cities) {
                Criteria crit1 = new Criteria();
                crit1.addParam(DAO_ID_DESCRIPTION, city.getDescription().getIdDescription());
                List<Description> desc = dao.showDescriptions(crit1);
                if (!desc.isEmpty()) {
                    city.setDescription(desc.get(0));
                }
                
                Criteria crit2 = new Criteria();
                crit2.addParam(DAO_ID_COUNTRY, city.getCountry().getIdCountry());
                List<Country> countries = dao.showCountries(crit2);
                if (!countries.isEmpty()) {
                    city.setCountry(countries.get(0));
                }
                
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
        Integer idDescription = (Integer) criteria.getParam(DAO_ID_DESCRIPTION);
        if (idDescription != null) {
            Criteria beans = new Criteria();
            beans.addParam(DAO_ID_DESCRIPTION, idDescription);
            dao.updateDescription(beans, criteria);
        }
        Integer idCity = (Integer) criteria.getParam(DAO_ID_CITY);
        if (idCity != null) {
            Criteria beans = new Criteria();
            beans.addParam(DAO_ID_CITY, criteria.getParam(DAO_ID_CITY));
            dao.updateCity(beans, criteria);
        }
        return idCity;
    }
    
    @Override
    Integer deleteEntity(Criteria criteria, AbstractDao dao) throws DaoException {
        criteria.addParam(DAO_CITY_STATUS, DELETED);
        Integer res = updateCity(criteria, dao);
        List<City> list = getEntity(criteria, dao);
        for (City c : list) {
            List<Hotel> hotels = (List<Hotel>) c.getHotelCollection();
            for (Hotel h : hotels) {
                Criteria crit = new Criteria();
                crit.addParam(DAO_ID_HOTEL, h.getIdHotel());
                new HotelLogic().deleteEntity(crit, dao);
            }
        }
        return res;
    }

    @Override
    Integer restoreEntity(Criteria criteria, AbstractDao dao) throws DaoException {
        criteria.addParam(DAO_CITY_STATUS, ACTIVE);
        Integer res = updateCity(criteria, dao);
        return res;
    }
    
}
