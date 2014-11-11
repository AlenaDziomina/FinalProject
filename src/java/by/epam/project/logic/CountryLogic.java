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
import by.epam.project.dao.query.Criteria;
import by.epam.project.entity.City;
import by.epam.project.entity.Country;
import by.epam.project.entity.Description;
import by.epam.project.exception.DaoException;
import java.util.List;

/**
 *
 * @author User
 */
public class CountryLogic extends AbstractLogic {

    @Override
    List<Country> getEntity (Criteria criteria, AbstractDao dao) throws DaoException {
        List<Country> countries = dao.showCountries(criteria);
        fillCountries(countries, dao, criteria);
        return countries;   
    }

    @Override
    Integer redactEntity(Criteria criteria, AbstractDao dao) throws DaoException {
        Integer idCountry = (Integer) criteria.getParam(DAO_ID_COUNTRY);
        if (idCountry != null) {
            return updateCountry(criteria, dao);
        } else {      
            return createCountry(criteria, dao);
        }  
    }
    
    private static void fillCountries(List<Country> countries, AbstractDao dao, Criteria criteria) throws DaoException {
        if (countries != null) {
            Integer cityStatus = (Integer) criteria.getParam(DAO_CITY_STATUS);
            for (Country country : countries) {
                Criteria descCrit = new Criteria();
                descCrit.addParam(DAO_ID_DESCRIPTION, country.getDescription().getIdDescription());
                List<Description> desc = dao.showDescriptions(descCrit);
                if (!desc.isEmpty()) {
                    country.setDescription(desc.get(0));
                }
                
                Criteria cityCrit = new Criteria();
                cityCrit.addParam(DAO_ID_COUNTRY, country.getIdCountry());
                cityCrit.addParam(DAO_CITY_STATUS, cityStatus);
                List<City> cities = dao.showCities(cityCrit);
                country.setCityCollection(cities);
            }
        }
    }
    
    private static Integer createCountry(Criteria criteria, AbstractDao dao) throws DaoException {    
        Integer idDescription = dao.createNewDescription(criteria).get(0);
        criteria.remuveParam(DAO_ID_DESCRIPTION);
        criteria.addParam(DAO_ID_DESCRIPTION, idDescription);
        return dao.createNewCountry(criteria).get(0);   
    }
    
    private static Integer updateCountry(Criteria criteria, AbstractDao dao) throws DaoException {
        Integer idDescription = (Integer) criteria.getParam(DAO_ID_DESCRIPTION);
        if (idDescription != null) {
            Criteria beans = new Criteria();
            beans.addParam(DAO_ID_DESCRIPTION, idDescription);
            dao.updateDescription(beans, criteria);
        }
        Integer idCountry = (Integer) criteria.getParam(DAO_ID_COUNTRY);
        if (idCountry != null) {
            Criteria beans = new Criteria();
            beans.addParam(DAO_ID_COUNTRY, idCountry);
            dao.updateCountry(beans, criteria);
        }
        return idCountry;
    }

    @Override
    Integer deleteEntity(Criteria criteria, AbstractDao dao) throws DaoException {
        criteria.addParam(DAO_COUNTRY_STATUS, DELETED);
        Integer res = updateCountry(criteria, dao);
        List<Country> list = getEntity(criteria, dao);
        for (Country c : list) {
            List<City> cities = (List<City>) c.getCityCollection();
            for (City city : cities) {
                Criteria crit = new Criteria();
                crit.addParam(DAO_ID_CITY, city.getIdCity());
                new CityLogic().deleteEntity(crit, dao);
            }
        }
        return res;
    }

    @Override
    Integer restoreEntity(Criteria criteria, AbstractDao dao) throws DaoException {
        criteria.addParam(DAO_COUNTRY_STATUS, ACTIVE);
        Integer res = updateCountry(criteria, dao);
        return res;
    }
}
