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
import by.epam.project.exception.DaoException;
import java.util.List;

/**
 *
 * @author User
 */
public abstract class CountryLogic {

    public static List<Country> getCountries(Criteria criteria) throws DaoException {
        ClientType role = (ClientType) criteria.getParam(DAO_ROLE_NAME);
        AbstractDao dao = DaoFactory.getInstance(role);
        dao.open();
        try {
            List<Country> countries = dao.showCountries(criteria);
            fillCountries(countries, dao);
            return countries;   
        } catch (DaoException ex) {
            dao.rollback();
            throw ex;
        } finally {
            dao.close();
        }
    }

    public static Integer redactCountry(Criteria criteria) throws DaoException {
        ClientType role = (ClientType) criteria.getParam(DAO_ROLE_NAME);
        Integer idCountry = (Integer) criteria.getParam(DAO_ID_COUNTRY);
        AbstractDao dao = DaoFactory.getInstance(role); 
        dao.open();
        try {
            if (idCountry == null) {      
                return createCountry(criteria, dao);
            } else {
                return updateCountry(criteria, dao);
            }  
        } catch (DaoException ex) {
            dao.rollback();
            throw ex;
        } finally {
            dao.close();
        }
    }
    
    private static void fillCountries(List<Country> countries, AbstractDao dao) throws DaoException {
        if (countries != null) {
            for (Country country : countries) {
                Criteria crit1 = new Criteria();
                crit1.addParam(DAO_ID_DESCRIPTION, country.getDescription().getIdDescription());
                List<Description> desc = dao.showDescriptions(crit1);
                country.setDescription(desc.get(0));
                
                Criteria crit2 = new Criteria();
                crit2.addParam(DAO_ID_COUNTRY, country.getIdCountry());
                List<City> cities = dao.showCities(crit2);
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
        Criteria beans1 = new Criteria();
        beans1.addParam(DAO_ID_DESCRIPTION, criteria.getParam(DAO_ID_DESCRIPTION));
        criteria.remuveParam(DAO_ID_COUNTRY);
        criteria.remuveParam(DAO_ID_DESCRIPTION);
        Integer idDescription = dao.updateDescription(beans1, criteria).get(0);
        
        Criteria beans2 = new Criteria();
        beans2.addParam(DAO_ID_COUNTRY, criteria.getParam(DAO_ID_COUNTRY));
        criteria.addParam(DAO_ID_DESCRIPTION, idDescription);
        return dao.updateCountry(beans2, criteria).get(0);
    }
}
